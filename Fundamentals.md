# Caching
Once something has been requested, cache the result so that subse   quent calls can pull from the cache rather than going through the database. This saves time at the cost of space.


# Web
## Same-Origin Policy
Origin - protocol, port, and host tuple

Scripts executed with an `about:black` or `javascript:` URL inherit the origin of the document (ex: script) containing the URL.

IE doesn't check for ports and also has highly trusted zones (ex: corporate intranet domains) that it doesn't limit access to.

Each origin gets its own data storage (Web Storage, IndexedDB). Cannot read or write cross-origin. 

Cookies, however, can be set by domain or any parent domain (except public suffixes) regardless of protocol and port. Can limit this to `Domain`, `Path`, `Secure`, and `HttpOnly`. Reading a cookie does not expose where it was set, e.g. if it was set using an insecure connection.

### Allowances
Cross-origin writes typically allowed - links, redirects, form submissions.
> Some HTTP requests require preflight

Cross-origin embedding is typically allowed.
* JavaScript: `<script src="...></script>`
* CSS: `<link rel="stylesheet" href="…">`, require correct `Content-Type` header for the return type (`text/css`). Some restrictions depending on the browser.
* Images: `<img>`
* Media: `<video>` and `<audio>`.
* External resources: `<object>` and `<embed>`.
* Embedded anything: `<iframe>`. Use the `X-Frame-Options` header to prevent cross-origin framing.
* Fonts: @font-face. Allowances depend on the browser.

Cross-origin reads are typically disallowed but often leaked by embedding (ex: reading the dimensions of an embedded image, the actions of an embedded script, and the avilability of an embedded resource).

### Allowing with CORS

### Blocking
Writes: check a Cross-Site Request Forgery (CSRF) token - an unguessable (significant entropy and strongly unpredictable, like a pseudo-random number generator seeded with the timstamp created and a static secret - need to tie it to a session cookie) token put in every request made by the client, generated first by the server-side application. Pages require this token in order to be read. Don't have these be transmitted within cookies.
* CSRF/ Sea Surf/ Session Riding/ Hostile Linking attacks
* Note that all cookies, even secret ones, are submitted with every request - authentication tokens submitted whether or not the end user was tricked into submitting them. Session identifiers can't tell us of intention. If someone is logged into a vulnerable site, the browser will automatically include their session cookie to a malicious request (unless you use `SameSite=Strict` or `=Lax` - and not a GET method - when set up the session cookie) regardless of originator.
* Add that nonce to URL and all forms (form keys) and standard session - can have it as a hidden field in HTML forms submitted using POST methods or, less safely, place the token in the URL query string, or transmit within a custom request header, which aren't normally allowed to be sent cross-domain.
* Then make sure you reject requests that contain an invalid token or contain no token.


Embeds: ensure cannot be interpreted as an embeddable format. Browsers may not respect the `Content-Type` header - an HTML document in a script tag will be parsed as JavaScript.

Reads: Ensure not embeddable.

https://portswigger.net/web-security/csrf

## Cross-Site Request Forgery (CSRF) Attacks
CSRF/ Sea Surf/ Session Riding/ Hostile Linking attacks happen when something that's not you performs an unwanted action on a trusted site where you're authenticated (think logged in).

Summary:
* Use a framework with built-in protection
* Set up SameSite Cookie Attribute for session cookies
    * This way, cookies (including secret ones) aren't submitted with every request, as doing so now depends on the originator
    * `SameSite=Strict` or use `=Lax` (default) and not a GET method
        * `Lax` will block cross-site requests in CSRF-prone request methods like POST. Requires top-level navigations and safe HTTP methods for it to be allowed.
* Synchronizer Token Pattern: 
    * Use custom request headers
        * Generate a token on the server-side once per user session or for each request (more secure as reduces time range for exploitation, but may hinder usability such as the "Back" browser button). 
        * Tokens are unique, secret, and unpredictable. 
        * Don't transmit them using cookies or leak in the server logs or URL - instead, consider adding them through hidden fields or headers (custom request headers via JS, more secure). 
    * Verify
        * For each request, verify the existance and validity of the token sent in the request - if this fails, abort the request, terminate the user session, and log the event as a potential CSRF attack in process.
* Encryption-Based Token Pattern
    * If don't want to maintain state server-side
        * Server generates a token comprised of the user's session ID and a timestamp, in order to prevent replay attacks, and a unique key available only on the server. 
        * Return this token to the client and embed it in the hidden field of forms or in the request header/ parameter for AJAX requests.
    * Verify the token
        * Decrypt  it using the same key used to create it. If this fails, could be an intrusion attempt - block the request and log the incident.
        * Validate the session and ID against currently logged-in user and timestamp against current time (to determine if it is past the token expiration time).
* HMAC-Based Token Pattern
    * Also stateless, uses an HMAC function rather than an encryption one. Also uses the timestamp and session ID
* Verify the origin with standard headers
    * Use the Origin or, if not present, Referer header to find the source origin
        * Forbidden headers so cannot be altered programmically
    * Determine where it's going - the target
        * Application server often sitting behind multiple proxies, so either have it know its target origin, the X-Forwarded-Host header value which most proxies will pass the original Host header through, or have the applciation server be directly accessed.
        * Match against the entire origin, so something like `mine.org.attacker.com` does not pass.
    * Server-side figures out if they match (same origin)
* Use double submit cookies
    * Stateless, for use if cannot store a CSRF token server-side.
    * When a user visits, even before authentication/ login, generate the pseudorandom value and set it as a cookie separate from the session ID and any other authentication cookies, and send it in every request as a hidden form value or other request parameter or header.
    * The server verifies if the cookie and request values match.
    * You can encrypt the cookie or, more simply, hash the token with a secret salt known only to the server.
    * Note cookie facts (below) and ensure that subdomains are fully secured and set to only accept HTTPS connections. 
* Use custom request headers
    * Well-suited for AJAX or API endpoints. Does not require any UI changes or server-side state.
    * Relies on the same-origin policy restriction that only JavaScript can be used to add a custom header and only within its origin. Only works with AJAX calls, still need ot protect `<form>` tags.
    * Verify presence of this header and value on all server-side AJAX endpoints.
* Have user interaction based protection for highly sensitive operations
    * For sensitive operations, have the user verify their identity or humanity.
    * Re-authentication 
    * One-time token
    * CAPTCHA
* Don't use GET requests to change state
    * GET requests may leak tokens in the browser history, log files, network appliance logs, and Referer headers, so just avoid using this type of request for sensitive operations.
* Hase XSS prevention techniques

Can be vulnerable to CSRF attacks on login forms - before the user is authenticated. Create pre-sessions with tokens in login form. Do NOT transition these pre-sessions into real ones once the user is authenticated (in order to avoid session fixation attacks). Have trusted sub-domains or have strict subdomain and path level referrer header validation to mitigate risk.

Code solutions:
https://cheatsheetseries.owasp.org/cheatsheets/Cross-Site_Request_Forgery_Prevention_Cheat_Sheet.html 

## Injection
Send data to an applicatin that will change the meaning of commands being sent to an interpreter. Trick the interpreter's parser into interpreting data as commands.

Bobby Tables: https://xkcd.com/327/

## Cross-Site Scripting (XSS) Attacks
Injection of malicious scripts into otherwise benign and trusted websites that allow the attacker to do anything the victim can do through their browser.



## Cookies
Subdomains can write cookies to the parent directories.
Cookies can be set for the domain over plain HTTP connections.
Cookies are sent based on the destination, regardless of originator (i.e. will be sent in cross-origin requests).

__Host- prefix:
`Set-Cookie: __Host-token=RANDOM; path=/; Secure`
* Cannot be (over)written from another subdomain.
* Must have the path of /.
* Must be marked as Secure (i.e, cannot be send over unencrypted HTTP).
Beware prefixes may not be supported by IE and Edge.

## Keep Logged in
When a user logs in, generate a large (128 - 256 bit) random token and map it to their user id in some server/ storage/ database. Send the token through a cookie - just for this session - with an extended lifetime if the user wants to be remembered.
https://stackoverflow.com/questions/1354999/keep-me-logged-in-the-best-approach
https://blog.alejandrocelaya.com/2016/02/09/how-to-properly-implement-persistent-login/


Load up while logged into bank application.

0x0 fake image tag in an email with GET requests:
```HTML 
<img src="http://bank.com/transfer.do?acct=MARIA&amount=100000" width="0" height="0" border="0">
```
Can also do with links when clicked.

POST Requests using a form:
```HTML
<body onload="document.forms[0].submit()">

<form action="http://bank.com/transfer.do" method="POST">

<input type="hidden" name="acct" value="MARIA"/>
<input type="hidden" name="amount" value="100000"/>
<input type="submit" value="View my pictures"/>

</form>
```
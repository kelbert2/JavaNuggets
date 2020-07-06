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
* Note that all cookies, even secret ones, are submitted with every request - authentication rokens submitted whether or not the end user was tricked into submitting them. Session identifiers can't tell us of intention. If someone is logged into a vulnerable site, the browser will automatically include their session cookie to a malicious request (unless use `SameSite=Strict` or `=Lax` - and not a GET method - when set up the cookie) regardless of originator.
* Add that nonce to URL and all forms (form keys) and standard session - can have it as a hidden field in HTML forms submitted using POST methods or, less safely, place the token in the URL query string, or transmit within a custom request header, which aren't normally allowed to be sent cross-domain.
* Then make sure you reject requests that contain an invalid token or contain no token.

Embeds: ensure cannot be interpreted as an embeddable format. Browsers may not respect the `Content-Type` header - an HTML document in a script tag will be parsed as JavaScript.

Reads: Ensure not embeddable.

https://portswigger.net/web-security/csrf

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
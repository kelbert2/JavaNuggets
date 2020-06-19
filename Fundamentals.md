# Caching
Once something has been requested, cache the result so that subse   quent calls can pull from the cache rather than going through the database. This saves time at the cost of space.

# Keeping Logged in
When a user logs in, generate a large (128 - 256 bit) random token and map it to their user id in some server/ storage/ database. Send the token through a cookie - just for this session - with an extended lifetime if the user wants to be remembered.
https://stackoverflow.com/questions/1354999/keep-me-logged-in-the-best-approach
https://blog.alejandrocelaya.com/2016/02/09/how-to-properly-implement-persistent-login/


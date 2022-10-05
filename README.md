This demonstrates a simple OAuth2 use case in which we are authenticated with using form based authentication.  
The authorization server then authorizes the user and forwards them back to the api-gateway.  A JWT token is part of
this request.  That token is forwarded on to the resource server, which validates the JWT token against the oauth2/jwks
endpoint.  There are 2 user, bob and denise with the password equal to "password".  The resource server extracts the
subject from the JWT token and returns it to the browser.



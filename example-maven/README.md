1. Import the project
2. Create upwork.properties and set up your key/secret; by default OAuthClient reads data from it.
You can also overwrite super class Config and use your own as a parameter for OAuthClient constructor,
in that way, keys can be stored elsewhere.
3. See/app App.java example to build and try a simple application that uses Upwork API.

I some cases you may need to link (or use proper maven dependency) the following external jars
    - java-json.jar
    - google-http-client-VERSION.jar
    - google-http-client-jackson2-VERSION.jar
    - google-oauth-client-VERSION.jar

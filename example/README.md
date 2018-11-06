1. Download org.json from http://www.java2s.com/Code/Jar/j/Downloadjavajsonjar.htm
2. Link external jars
   - java-json.jar
   - google-http-client-VERSION.jar
   - google-http-client-jackson2-VERSION.jar
   - google-oauth-client-VERSION.jar
3. Finally link java-upwork.jar to your project.
4. Create upwork.properties and set up your key/secret; by default OAuthClient reads data from it.
You can also overwrite super class Config and use your own as a parameter for OAuthClient constructor,
in that way, keys can be stored elsewhere.
5. See TestApi.java example to build a simple application that uses Upwork API.

Some steps from the above are automated already in the `Makefile`, so to run the `TestApi.java`
example app you can just use:

      make
      make run

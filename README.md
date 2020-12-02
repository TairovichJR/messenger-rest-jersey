# messenger-rest-jersey

Simple messenger api using rest-jersey implementing jax-rs specs. Additional configuration needed if you are using Intellij on a Mac
Steps:
1. Install tomcat. 
2. Then configure tomcat in your Intellij. Preferences -> Tools -> External Tools. Click the plus sign and configure the tomcat installation location.
3. Configure automatic deployment of your project in webapps folder of tomcat using Maven command. 
  Go to Edit configurations, choose Maven, give a name on top input box, for the command line enter this line:
  clean install war:war org.codehaus.mojo:wagon-maven-plugin:upload-single -Dwagon.fromFile=absolute-location-of-your-jersey-project.war 
  -Dwagon.url=file:///Library/Tomcat/webapps
  4. To run the tomcat server, from tom menu Tools -> External Tools -> tomcat9 (whichever version you installed) 

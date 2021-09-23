# Exploring-Different-Web-Services-Films

This text file is written to help you set up the project solutions.

Apache Tomcat v9.0 is used for "FilmHTTPWebService", "FilmRESTfulWebService", "FilmSOAPWebService" and "FilmSOAPWebServiceClient".
These projects also use JDK 1.8 (more specifically, jdk1.8.0_271).

The JDK can be downloaded from here:
https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html

It is IMPORTANT that the correct JDK is used. Using Java SE 1.8 or others may result in (JAXB) ClassNotFound errors when attempting to make requests on the front-end.

Therefore, ensure that in the Java Build Path (located in the eclipse properties for that project)JDK 1.8 (specfically jdk1.8.0_271) is used, 
and in Targeted Runtimes (also located in eclipse properties for that project), Apache Tomcat v9.0 is set up and selected.

To set up Apache Tomcat v9.0, you will need to download the server here:
https://tomcat.apache.org/download-90.cgi

After this has been downloaded, in Targeted Runtimes you can select this folder in order to use Tomcat v9.0.


-------------  FilmCloudWebService  -------------
To note - you would have to change the username, password, socketFactory, cloudSqlInstance to your own Google Cloud Project & database
to get this to completely work, as it requires authentication of the Google Cloud account, and I am not providing access to that since it 
contains and is linked to Credit Card information.

This is an App Engine project so you will require the google plugin for this. To do that,
go to the Eclipse Marketplace and download and isntall the Google Cloud plugin.

Additionally, you will need the Google SDK, follow the steps to install this appropriately below (make sure to initialise gcloud and login):
https://cloud.google.com/sdk

App Engine projects come with Jetty as the Runtime environment, and JavaSE-1.8.

This project in particular will require configuration in the hibernate.cfg.xml file (located in src/main/java),
depending on what you want to do: run locally, or deploy.

If you are running locally, you will require a proxy - this will be different depending on your operating system.
The following link below will help you find the right proxy for you:
https://cloud.google.com/sql/docs/mysql/sql-proxy

This proxy is used to connect you to the cloud database when requests are made locally (this is required due to security reasons)

For the proxy to work:
-the Cloud SQL Admin API must be enabled
-you must provide the proxy with google cloud authentication credentials
-you must provide the proxy with a valid database user account and password
-the instance must have either a public IPv4 address, or is configured to use a private IP

More information on this can be found in the link provided earlier above.

The hibernate.connection.url property needs to be set to: jdbc:mysql://127.0.0.1:3306 when testing locally (may vary depending on OS)
The hibernate.connection.socketFactory and hibernate.connection.cloudSqlInstance can be commented out for testing locally.

When the proxy is configured, navigate to the location where the proxy is and start running the proxy with the appropriate command (may vary on OS),
for windows I used:
cloud_sql_proxy -instances=democloudproject-296016:europe-west1:enterprise-assignment-db=tcp:3306

You will know when the proxy is working when the cmd prompt prints listening for a connection.

Run the project - Run as -> App Engine.

Navigate to the url: 
http://localhost:8080/

Requests made through the front-end should work, and you will see a connection is made to the database in the command prompt.

When deploying the App Engine to the Cloud, you want to ensure the configuration is set up correctly to target the Cloud Database without a proxy.
To do this, change the hibernate.connection.url property to: jdbc:mysql:/// and uncomment out the hibernate.connection.socketFactory and hibernate.connection.cloudSqlInstance

Then you can right click the project, select Deploy to App Engine Standard, select the Google Cloud Project to deploy to, and then
press Deploy.

The URL for the deployed App Engine project is:
https://democloudproject-296016.ew.r.appspot.com/


-------------  FilmHTTPWebService  -------------
This project uses Apache Tomcat v9.0 and jdk1.8 (see top of README for links on where to get and set-up).

When this project is imported, ensure that these are set in the Targeted Runtime and Java Build Path.

Run the project - Run as -> Run on Server -> Tomcat v9.0
Navigate to the url below:
http://localhost:8080/FilmHTTPWebService/


-------------  FilmRESTfulWebService  -------------
This project uses Apache Tomcat v9.0 and jdk1.8 (see top of README for links on where to get and set-up).

When this project is imported, ensure that these are set in the Targeted Runtime and Java Build Path.

Run the project - Run as -> Run on Server -> Tomcat v9.0
Navigate to the url below:
http://localhost:8080/FilmRESTfulWebService/


-------------  FilmSOAPWebService & FilmSOAPWebServiceClient -------------
These projects use Apache Tomcat v9.0 and jdk1.8 (see top of README for links on where to get and set-up).

When these projects are imported, ensure that they are set in the Targeted Runtime and Java Build Path.

Run the projects - Run as -> Run on Server -> Tomcat v9.0
Navigate to the url below:
http://localhost:8080/FilmSOAPWebServiceClient/sampleFilmTraditionalDAOProxy/TestClient.jsp


------------- Critical Analysis & Proof of Working Code -------------
The critical analysis and proof of working code are two seperate documents. 

The proof of working code demonstrates that all of these projects compile and work. It demonstrates the functionality that satisfies criterias 1-6.

The critical analysis goes through key software engineering techniques and design patterns, discussing and evaluating them.

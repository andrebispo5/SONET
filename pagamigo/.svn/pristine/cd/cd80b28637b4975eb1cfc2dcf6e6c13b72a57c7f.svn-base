This is a simple Java Web application.

The service is defined by the WSDL file that generates Java code
(contract-first approach, also called top-down approach).

A Web Application has two types of source files:
Java classes (stored in /src) and
Web content (stored in /WebContent).

To run, these files need to be stored in a special ZIP file,
called a Web application ARchive (WAR) file.

To run the application, start JBoss, compile and deploy the application.
To test application, point your browser to http://localhost:8080/hello-ws/endpoint

Using Ant:
---------

Build steps are specified in build.xml file
JBoss configurations are in jboss7.xml jboss7-modules.xml

To list available targets:
ant -p

To create  (WAR file):
ant build

To deploy application:
ant deploy

To undeploy application:
ant undeploy


Testing:
-------

When running, the web service awaits connections from clients.
You can check if the service is running using your web browser:

    http://localhost:8080/hello-ws/endpoint

And see the generated WSDL file:

    http://localhost:8080/hello-ws/endpoint?WSDL

To call the service you will need a web service client,
including code generated from the WSDL.


To configure the project in Eclipse:
-----------------------------------

If JBoss server is not configured:
    Add a server runtime.
    Go to the 'Servers' view.
    Select JBoss 7.1

If Eclipse files (.project, .classpath) exist:
    'File', 'Import...', 'General'-'Existing Projects into Workspace'
    'Select root directory' and 'Browse' to the project folder.
    Check if everything is OK and 'Finish'.

If Eclipse files do not exist:
    Create a 'New Project', 'Dynamic Web Project'.
    Uncheck 'Use default location' and 'Browse' to the project folder.
    Fill in the 'Project name'.

    Add the required libraries to the project build path.
    Project, Build Path, Libraries, Server runtime

To run:
    To deploy the application, click 'Run' (the green play button).
    Select 'Run on Server'.
    Test the application using the internal Eclipse browser.


--
2013-03-12
Miguel.Pardal@ist.utl.pt

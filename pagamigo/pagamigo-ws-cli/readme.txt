This is a simple Java Web Service client

The client uses the wsimport tool (included with the JDK since version 6)
to generate classes that can invoke the web service and 
perform the Java to XML data conversion.

The client needs access to the WSDL file,
either using HTTP or using the local file system.


Using Ant:
---------

Build steps are specified in build.xml file

To list available targets:
    ant -p

To run:
    ant run

The wsimport target assumes that the tool is available in the PATH.
The web service server-side needs to be running.

To run using a different endpoint address:
    ant run -Dws.url=http://...

(-D defines a property and overrides the value in build.xml)


To configure the project in Eclipse:
-----------------------------------

If Eclipse files (.project, .classpath) exist:
    'File', 'Import...', 'General'-'Existing Projects into Workspace'
    'Select root directory' and 'Browse' to the project folder.
    Check if everything is OK and 'Finish'.

If Eclipse files do not exist:
    Create a 'New Project', 'Java Project'.
    Uncheck 'Use default location' and 'Browse' to the project folder.
    Fill in the 'Project name'.

    Configure source and output folders.

    Add the required libraries to the project build path.
    Project, Build Path, Libraries, Server runtime

    Click 'Next' to check if all configurations are correct and 'Finish'.

To run:
    To run the application, click 'Run' (the green play button).
    Select 'Java Application'.


--
2013-02-27
Miguel.Pardal@ist.utl.pt

# DeliveryDrone

The DeliveryDrone system is a software solution designed to efficiently plan and execute drone deliveries within the University of Edinburgh campus area. The system receives orders from a catalogue and generates optimized delivery routes to maximize profits while adhering to no-fly zones.

# Description
Main function is in the App class, it is responsible for running the whole drone
delivery programme, the main function calls some other methods from the other
classes, it first calls methods from classes in utils to get connection to
database/server to get connection to them , then use classes in parsers to receive
the data from them. Secondly it stores the data by creating entity classes in beans, which will be operated by the methods in the controller package. In the last, the
main function will create tables in database to store the data of the whole procedure
of the drone’s flying, and will write the calculated fly path into the geojson file using
the static write file function in main.

# Installation
To install the DeliveryDrone system, follow these steps:

1.Clone the repository to your local machine.

2.Install the required database and web server listed in the README file.

3.Assuming that your project is named ilp then when compiled with the Maven build system your Java application will produce an über JAR file in the target folder of your project named ilp-1.0-SNAPSHOT.jar.If you run this JAR file with the command

java -jar target/ilp-1.0-SNAPSHOT.jar 15 09 2022 80 1527

it should read the lunch orders for the date 15/09/2022 from the database, connecting at port 1527. It should
read the menus from the website, connecting to the web server at port 80. 




# Running the database server
In this project we will be working with the Apache Derby database software version 10.15.2.0. Visit the web page at https://db.apache.org/derby/derby_downloads.html and follow the link for the Apache Derby 10.15.2.0 release. Download the zipped binary distribution in the file db-derby-10.15.2.0-bin.zip and store this in a folder on your machine or in your DICE filespace as appropriate. Unzip the file. We will assume here that your username is s1234567 and that you have stored the Derby binary distribution in a folder Year3/ILP/Derby/ on your system. Change the commands below as appropriate for your UUN and the folder where you stored the Derby binary distribution.

We want to define the environment variable DERBY_HOME to refer to the location of the Derby binary distribution on your machine, or in your DICE filespace. Here are examples of the kinds of commands used to do this on various operating systems.

Caution: Do not replace the use of /home/s1234567/ with ∼/ in the commands below. Apache Derby will not expand path abbreviations such as these when they are used in java commands later.

• DICE (with the bash shell)

. Place this command in the file ∼/.brc

export DERBY_HOME="/home/s1234567/Year3/ILP/Derby/db-derby-10.15.2.0-bin"

. Open a new terminal window and check that $DERBY_HOME is a valid filepath with

ls $DERBY_HOME

• macOS (Catalina or later, with the zsh shell)

. Place the following command in the file ∼/.zshenv

export DERBY_HOME="/Users/s1234567/Year3/ILP/Derby/db-derby-10.15.2.0-bin"

. Open a new terminal window and check that $DERBY_HOME is a valid filepath with

ls $DERBY_HOME

• macOS (Mojave or earlier, with the bash shell)

. Place the following command in the file ∼/.bashrc

export DERBY_HOME="/Users/s1234567/Year3/ILP/Derby/db-derby-10.15.2.0-bin"

. Open a new terminal window and check that $DERBY_HOME is a valid filepath with

ls $DERBY_HOME

• Windows (varies by version)

. Become Administrator and open System > Advanced Settings > Environment variables. Check details for your version of Windows using Google or your favourite search engine.

. Via the UI, achieve the effect of

set DERBY_HOME=C:\User\s1234567\Year3\ILP\Derby\db-derby-10.15.2.0-bin

. Open a new terminal window and check that $DERBY_HOME is a valid filepath with

dir %DERBY_HOME%

To start the database server (the Derby Network Server), change directory to the folder where you unzipped database.zip. Make sure that you are in the database folder where you can see the files derby.log and derbyDB. To start the database server on the default port 1527, issue the appropriate command below:

• DICE / macOS

. java -jar $DERBY_HOME/lib/derbyrun.jar server start

• Windows

. java -jar %DERBY_HOME%\lib\derbyrun.jar server start

# Running the web server

Download the web server application and its content from the “Course materials” section of the ILP course web page at http://course.inf.ed.ac.uk/ilp.

We will use a very lightweight web server which is implemented entirely in Java and has a very small memory footprint. Unpack the ZIP file containing the web server and the web server content into a directory on your machine. You can now run the web server using the command:

java -jar WebServerLite.jar

This will start the web server on the default port (80) and it will serve the content in the menus, words, and buildings folders. You can check the web server is running by visiting the address http://localhost:80 in your preferred web browser.

All HTTP requests are logged to the console with output like this:

[Tue Sep 14 19:42:34 BST 2021] ... "GET /buildings/ HTTP/1.1" 200
[Tue Sep 14 19:42:36 BST 2021] ... "GET /buildings/no-fly-zones.geojson HTTP/1.1" 200


If you cannot run the web server on port 80 on your machine (say, because you already have a web server running there) then you can start the web server on a different port like this:

java -jar WebServerLite.jar /path/to/web/server/content 9898

Concretely, this command might be something like

java -jar WebServerLite.jar /home/s1234567/Year3/ILP/website 9898


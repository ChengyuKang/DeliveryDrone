# DeliveryDrone

The DeliveryDrone system is a software solution designed to efficiently plan and execute drone deliveries within the University of Edinburgh campus area. The system receives orders from a catalogue and generates optimized delivery routes to maximize profits while adhering to no-fly zones.

2.Description
Main function is in the App class, it is responsible for running the whole drone
delivery programme, the main function calls some other methods from the other
classes, it first calls methods from classes in utils to get connection to
database/server to get connection to them , then use classes in parsers to receive
the data from them. Secondly it stores the data by creating entity classes in beans, which will be operated by the methods in the controller package. In the last, the
main function will create tables in database to store the data of the whole procedure
of the drone’s flying, and will write the calculated fly path into the geojson file using
the static write file function in main.

# Running the web server

Download the web server application and its content from the “Course materials” section of the ILP course
web page at
http://course.inf.ed.ac.uk/ilp
We will use a very lightweight web server which is implemented entirely in Java and has a very small memory
footprint. Unpack the ZIP file containing the web server and the web server content into a directory on your
machine. You can now run the web server using the command
java -jar WebServerLite.jar
This will start the web server on the default port (80) and it will serve the content in the menus, words, and
buildings folders. You can check the web server is running by visiting the address
http://localhost:80
in your preferred web browser.

All HTTP requests are logged to the console with output like this:
[Tue Sep 14 19:42:34 BST 2021] ... "GET /buildings/ HTTP/1.1" 200
[Tue Sep 14 19:42:36 BST 2021] ... "GET /buildings/no-fly-zones.geojson HTTP/1.1" 200
If you cannot run the web server on port 80 on your machine (say, because you already have a web server
running there) then you can start the web server on a different port like this:
java -jar WebServerLite.jar /path/to/web/server/content 9898
Concretely, this command might be something like
java -jar WebServerLite.jar /home/s1234567/Year3/ILP/website 9898
if your UUN is s1234567 and you stored the website content in the Year3/ILP/website folder in your DICE
filespace. You can now check that the web server is running on your preferred port by visiting the address:
http://localhost:9898
in your favourite web browser and you would start your Java application with a command like
java -jar target/ilp-1.0-SNAPSHOT.jar 02 02 2022 9898 1527
passing the web server port number (9898) and the database server port number (1527) as the two last
command-line arguments.
School of Informatics — Informatics Large Practical — 2021–2022 25
You will need to have the web server running every time that you are running your Coursework 1 or Coursework 2 code. If the web server is not running then any attempt to connect to the web server to get JSON or
GeoJSON files will throw a Java exception such as
java.net.ConnectException: Connection refused

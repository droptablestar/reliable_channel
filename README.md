<h2>To build the project: </h2>
   ```ant build
   ```
To build with jar file:
   ant jar
To create javadocs:
   ant doc

To test project with 2 instances:
   cat data.txt | java -jar dist/rchannel.jar localhost 0
   cat data.txt | java -jar dist/rchannel.jar localhost 0

Data generation and evaluation tools can be found in the tests/ directory.
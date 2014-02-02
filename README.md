<h2>Compilation</h2>
<h3>To build the project: </h3>
   ```
   ant build
   ```
<h2>To build with jar file: </h2>
   ```
   ant jar
   ```
<h2>To create javadocs: </h2>
   ```
   ant doc
   ```
<h2>To test project with 2 instances:</h2>
   ```
   cat data.txt | java -jar dist/rchannel.jar localhost 0
   cat data.txt | java -jar dist/rchannel.jar localhost 0
   ```
<h2>Data generation and testing:</h2>
Data generation and evaluation tools can be found in the tests/ directory.
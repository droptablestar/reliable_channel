<h2>Compilation</h2>
<h4>To build the project: </h4>
   ```
   ant build
   ```
<h3>To build with jar file: </h3>
   ```
   ant jar
   ```
<h3>To create javadocs: </h2>
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
<h2>To build the project: </h2>
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

Data generation and evaluation tools can be found in the tests/ directory.
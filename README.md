To run the application,

please first open your terminal and navigate at the source folder where the pom.xml file is located
run the command: 
mvn clean install

once the build is finished, navigate to target folder (cd target) and run the command:
java -jar simulator-0.0.1-SNAPSHOT.jar 
followed by the 2 string arguments of your choice to list the patients and the drugs

ex: java -jar simulator-0.0.1-SNAPSHOT.jar T,H,F,X,D As,An,I,P

# STEPS TO BE FOLLOWED TO RUN EMPLOYEE MANAGEMENT APPLICATION
##GO TO ./employee folder 
1. Run "mvn clean install -DskipTests" to install depedent jars and libraries.
2. Go to "/employee/src/main/java/com/example/employee/EmployeeApplication.java" and run application as spring boot application.
3. verify the application is up and running in port 8083.

### GO TO ./frontend folder
1. run "npm install" in command prompt or terminal to install packages and dependencies.
2. After running successfully , verify in the localsystem by going to URL : "http://localhost:3000/".

### Can use sample DBscript statements to insert values in the DB
1. Copy the INSERT statements and run h2-console with no password set up for H2-DB.
2. TEST the application using UI.
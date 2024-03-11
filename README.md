<h1 align="center">Assignment 2 by Sanan Mardanli</h1>
<p align="center">
  In this assignment, we were required to demonstrate an understanding of automated testing and continuous delivery practices. 
</p>

<h3>Getting Started</h3>

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

<h3>Prerequisites</h3>

What you need to install the software and how to install them:

```bash
java -version
mvn -version
```

<h3>Installing</h3>
A step-by-step series of commands to set up the project:

```bash
git clone git@github.com:ADA-GWU/2024-a2-cicd-SenanM1.git
cd 2024-a2-cicd-SenanM1/
mvn clean install
```

<h3>Running the Tests</h3>

Explanation of how to run the automated tests for this project, focusing on the newly added test cases.

<h4>Web tests</h4>
1.Display All Students:

Navigate to the student list page and ensure the student table is displayed.

```bash
mvn test -Dtest=WebInterfaceTests#displayAllStudents
```

2.Display All Students and Verify Row Count:

Verify the row count of the student table matches the expected count.

```bash
mvn test -Dtest=WebInterfaceTests#displayAllStudentsAndVerifyRowCount
```

<h4>Functionality Tests</h4>
1.Test Updating a Student:

Update and verify a student's information.

```bash
mvn test -Dtest=FunctionalityTests#testStudentUpdate
```
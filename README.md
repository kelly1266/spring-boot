# Overview
This project contains my attempt to build a web application with Java for the internal technical exercise. I have chosen to use the Spring framework for this exercise, and the project is built using Maven wrapper.

# Objectives
- Create a Java Web API application
- Create an endpoint which calls https://demo7586857.mockable.io/people and returns the results ordered by either the `First name` or the `Score` property
- The caller of the API should be able to pass in the order type
- There should be an appropriate level of unit test coverage

# Getting Started
This project was developed with [JDK 23](https://www.oracle.com/java/technologies/downloads/) and built with [Apache Maven](https://maven.apache.org/download.cgi). The project structure was created using the [Spring Initializr](https://start.spring.io/).

# Running locally
The project can be run locally using the following command

```
mvnw spring-boot:run
```

# Tests
Test cases can be run using the following command

```
mvnw test
```

# Post Exercise Thoughts
It has been quite some time since I last used Java and this was my first time using the Spring framework. I was not quite as comfortable jumping into Java as I thought I would be, and had forgotten many of the fundamentals. I think this is fairly evident in the code, as Im sure there are cleaner ways of implementing the GetPeopleController. But I think the most glaring issue is the unit test coverage. I wanted to mock the GET request to https://demo7586857.mockable.io/people, but I had trouble setting up the implementation. As a result, the scope of unit tests that I was able to write was severely limited.

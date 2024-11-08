# Overview
This project contains my attempt to build a web application with Java for the internal technical exercise. I have chosen to use the Spring framework for this exercise, and the project is built using Maven wrapper.

# Objectives
- Create a Java Web API application
- Create an endpoint which calls https://demo7586857.mockable.io/people and returns the results ordered by either the `First name` or the `Score` property
- The caller of the API should be able to pass in the order type
- There should be an appropriate level of unit test coverage

# Getting Started
This project structure was built using the [Spring Initializr](https://start.spring.io/)

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
It has been quite awhile since I last used Java and this was my first time using the Spring framework. If I were to do this project over again, or a similar exercise, I would begin by doing a refresher course. I was not as comfortable jumping into Java as I thought I would be, and had forgotten many of the fundamentals. I believe the most glaring issue with my project is the lack of unit test coverage. Due to the way that I structured the getPeople function, I needed to mock the get request to https://demo7586857.mockable.io/people but I had difficulty successfully mocking the request. Without being able to mock that request, the number of unit tests that I could write was severely limited. What I should have done, is separated the sorting logic (lines 36-49) into its own function and wrote unit tests for that function.

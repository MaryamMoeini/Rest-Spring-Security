# Rest-Spring-Security

Overview:
This application is designed for an energy platform which enables the system to validate and store data related to Profiles and 
MeterResding in SQL data base.

This a spring boot application, demonstrating a simple back-end application which implements REST API. 

In this project I am using basic features of spring such as:

* Spring boot
* JPA
* Spring Data with interaction with sql database
* Hibernate ORM
* Spring Security for basic authentication and security configuration


We have a set of Entities which describe our model objects (Profile and MeterReading) These entities have one to many and many to one 
relations. 

In the next layer, We have Interfaces (Repositories and Validation Engine). Repositories are implementing JPARepository which help them in
communication with DB and Validation Engine is a build in Validation implementation which validates the data received from by REST services.

and Finally we have the REST services (ProfileRestService and MeterRest Service). These REST controllers provide basic CRUD services for
Profile and MeterReading.


REST API Security:
In the SecurityConfig.java I am applying a basic authentication and Authorization security for the entry point of REST Services. 


Sample of REST-API Documentation (Full Documentation is Available for Private Connections) 

URL /profile/create
Method
POST Data Params { "meterId" : [string] "profileName" : [string] “consumption” : [ { “fraction” : double “month” : String} ] }
Success Response
Content : {Data Recorded}
Code : 200 Error Response Http Status Code: 401 unauthorized

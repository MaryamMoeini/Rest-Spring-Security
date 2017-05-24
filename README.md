# Rest-Spring-Security

Overview:
In this project we are providing a set of REST-full WEB service which are designed for an energy platform which enables the front-end system to perform CRUD actions on sets of data which are provided by customers/users on the platform.

In this project I am using basic features of spring such as:

* Spring boot
* JPA
* Spring Data with interaction with sql database
* Hibernate ORM
* Spring Security for basic authentication and security configuration


We have a set of Entities which describe our model objects (Profile and MeterReading) These entities have one to many and many to one 
relations. 

In the next layer, We have Interfaces (Repositories and Validation Engine). Repositories are implementing JPARepository which help them in
communication with DB and Validation Engine is a build in Validation implementation which validates the data received from REST clients.

and Finally we have the REST services (ProfileRestService and MeterRestService). These REST controllers provide basic CRUD actions for
Profile and MeterReading.


REST API Security:
In the SecurityConfig.java I am applying a basic authentication and Authorization security for the entry point of REST Services. 


Sample of REST-API Documentation (Full Documentation is Available for Private Connections) 

URL /profile/create<br/>
Method<br/>
POST Data Params { "meterId" : [string] "profileName" : [string] “consumption” : [ { “fraction” : double “month” : String} ] }<br/>
Success Response<br/>
Content : {Data Recorded}<br/>
Code : 200 Error Response Http Status Code: 401 unauthorized<br/>

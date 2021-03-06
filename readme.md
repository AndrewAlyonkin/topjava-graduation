[![Build Status](https://app.travis-ci.com/AndrewAlyonkin/topjava-graduation.svg?token=jfYqsL5WSinFLfXxitzZ&branch=master)](https://app.travis-ci.com/AndrewAlyonkin/topjava-graduation)  
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/7c7cc798334646b9b1a7c94f75479aad)](https://www.codacy.com/gh/AndrewAlyonkin/topjava-graduation/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=AndrewAlyonkin/topjava-graduation&amp;utm_campaign=Badge_Grade)
----------------------------------------
Documentation:
----------------------------------------
**http://localhost:8080/swagger-ui.html**  
**https://tjgrad.herokuapp.com/swagger-ui.html**
--------------------------------------------

**Login/password for authorization in API**

*User email:* user@gmail.com  
*Password:* user

*Administrator email:* admin@gmail.com  
*Password:* admin

--------------------------------------------

Design and implement a REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) **without frontend**.

The task is:

Build a voting system for deciding where to have lunch.

* 2 types of users: admin and regular users
* Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
* Menu changes each day (admins do the updates)
* Users can vote on which restaurant they want to have lunch at
* Only one vote counted per user
* If user votes again the same day:
    - If it is before 11:00 we assume that he changed his mind.
    - If it is after 11:00 then it is too late, vote can't be changed

Each restaurant provides a new menu each day.

As a result, provide a link to github repository. It should contain the code, README.md with API documentation and
couple curl commands to test it (better - Swagger).

-----------------------------
P.S.: Make sure everything works with latest version that is on github :)

P.P.S.: Assume that your API will be used by a frontend developer to build frontend on top of that.

-----------------------------

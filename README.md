# Flight selector service test

## Description

This is my example project for the CGI summer internship program. The goal of this project was to create a flight seat recommendation web app. The requirements for the project were to use Java Spring Boot as the backend with no restrictions on the frontend.

The original timeframe for this project was from March 14 2025 - March 30 2025.

## How to run

### Requirements

This project requires Java version 21. It can be installed using the following command:

`sudo apt update
sudo apt install openjdk-21-jdk`

You will also need to install Apache Maven, which can be installed using the following command:

`sudo apt install maven`

### Clone the repository

You can clone the repository with the following command:

`git clone https://github.com/KarlKivv/flight-selector-service-test.git`

### Running the app

After cloning the project, navigate to the project folder. From there, run the following command:

`./mvnw spring-boot:run`

Afterwards, navigate to "http://localhost:8080" in your web browser of choice.

Note: you may need to grant the above Maven script executable rights. To do so, run the following command:

`chmod +x mvnw`

### Used dependencies

Spring web
Spring Boot devTools
spring-boot-starter-actuator

## Thoughts

In this section, I wrote my design thoughts that I had during the development of this project. Unfortunately due to time constraints, I was not able to realize all of the ideas that I had.

### Communication between front and back:

-   JSON:
      simplest, innate support by spring, requires a separate front-end to display data.
-   server-side HTML template:
      supported by "thymeleaf" package, no need for a separate front-end. Relies heavily on client identification to show correct page states.

Decided to use a java templating engine and use server-side-rendering in stead of creating a separate front-end since this method is simpler.
According to a Spring-Boot patch note, JSP-s are outdated and should be avoided. Will use Thymeleaf templates instead.

### Flight creation and timeout

Current idea is to create all flight data during app start and keep it in memory. Creating new flights and removing departed ones are controlled by cron jobs.
One option is to create a cron job for every flight in memory to automatically remove it when the flight has departed and a separate cron job will run on a fixed timer and will randomly create new flights.
Another option is to tie both flight creation and deletion to a single cron job that runs every f.e. 5 minutes. However, this would mean that every flight would depart at times that are divisible by 5.

Alternatively, the app could update the flight list only when the user is accessing the "flight listings" and "checkout" endpoints. This would cut back on the compute costs, but might lead to de-syncs between the back- and front-end.

### Seat selection

Give every seat a score based on the user's preferences, retrieve the seats with the largest score.
Seat location in relation to the plane (window, isle, near to exit etc.) seems simple enough to implement, closeness to previously selected seats seems trickier.

After giving every seat a score, use a priority queue to give the user suggestions on seating arrangements.

### General/unsorted ideas

Store user selections in front-end state, then send all data to back-end upon purchase.
Potentially generate a unique uuid link after ticket purchase that can be used to edit purchase before flight date.

### Final thoughts

This was my first real project using Spring Boot and Java as well. Since I had some past experiences designing web apps, I thought I could take my time with learning how Spring Boot works more generally. However, this turned out to be a mistake, since I just didn't have time to implement most of the features I wanted to within the original timeframe.

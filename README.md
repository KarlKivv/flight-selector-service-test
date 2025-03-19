# CGI-eesti-2025-test

## Description

This is my solution for the CGI summer internship program. The goal of this project is to create a plane seat recommendation web app, based on the user's preferences. This is done through the use of filters (window seat, more legroom, closer to the isle etc). The back-end needs to be done in Java using the Spring Boot framework. There are no restrictions on the front-end side.

## Thoughts

### Communication between front and back:

- JSON:
    simplest, innate support by spring, requires a separate front-end to display data.
- server-side HTML template:
    supported by "thymeleaf" package, no need for a separate front-end. Relies heavily on client identification to show correct page states.

### Flight creation and timeout

Current idea is to create all flight data during app start and keep it in memory. Creating new flights and removing departed ones are controlled by cron jobs.
One option is to create a cron job for every flight in memory to automatically remove it when the flight has departed and a separate cron job will run on a fixed timer and will randomly create new flights.
Another option is to tie both flight creation and deletion to a single cron job that runs every f.e. 5 minutes. However, this would mean that every flight would depart at times that are divisible by 5.

Alternatively, the app could update the flight list only when the user is accessing the "flight listings" and "checkout" endpoints. This would cut back on the compute costs, but might lead to de-syncs between the back- and front-end.

### Seat selection

Give every seat a score based on the user's preferences, retrieve the seats with the largest score.
Seat location in relation to the plane (window, isle, near to exit etc.) seems simple enough to implement, closeness to previously selected seats seems trickier.

### General ideas

Store user selections in front-end state, then send all data to back-end upon purchase.
Potentially generate a unique uuid link after ticket purchase that can be used to edit purchase before flight date.

## Dependencies

Spring web
Spring Boot devTools
Docker compose support

## Steps

Used VSCode-s "generate java project" to generate a blank java Spring Boot project template.

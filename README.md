# CGI-eesti-2025-test

## Description

This is my solution for the CGI summer internship program. The goal of this project is to create a plane seat recomendation web app, based on the user's preferences. This is done through the use of filters (window seat, more legroom, closer to the isle etc). The backend needs to be done in Java using the Spring Boot framework. There are no restrictions on the frontend side.

## Initial thoughts / TODOs

### Communication between front and back:

- JSON:
  simplest, innate support by spring, requires a separate front-end to display data.
- server-side HTML template:
  supported by "thymeleaf" package, no need for a separate front-end. Relies heavily on client identification to show correct page states.

### General ideas

Store user selections in frontend state, then send all data to backend upon purchase.
Potentially generate a unique uuid link after ticket purchase that can be used to edit purchase before flight date.

## Dependencies

Spring web
Spring Boot devTools
Docker compose support

## Steps

Used VSCode-s "generate java project" to generate a blank java Spring Boot project template.

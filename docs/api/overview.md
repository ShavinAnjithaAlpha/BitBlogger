# BitBlogger System API Overview

## Introudction

Welcome to the API documentation for the BitBlogger Tech Blog Platform. This system is built using microservice architecture to ensure maintainability, scalability, and flexibility and more. Each microservice responsible for specific domain and communicate with each other using HTTP/HTTPS for synchronous and message brokers for asynchronous communication (event driven architecture).

This odcument provides an overview of the available API of the eahc microservice, and their functionality.

## Table of Contents

- [Authentication Service API](#authentication-service)
- [User Service API](#user-service-api)

## Authentication Service

### Overview

### Endpoints

## User Service API

### Overview

The **User Service API** handles the user accounts and profile management functionalities like profile updating, user registration, reading list management, user reporting, followers management etc.

### Endpoints

- **POST** `/users/` - register a new user in the system
- **GET** `/users` - get public profiles of users
- **GET** `/users/{id}` - get the public profile of the specified user with the user id
- **GET** `/users/me` - get the user profile of the authenticated user
- **PUT** `/users/me` - update the user profile of the authenticated user
- **DELETE** `/users/me` - delete the user profile of the authenticated user
- **POST** `/users/me/follow` - follow a new user
- **DELETE** `/users/me/follow` - unfollow the exists user
- **GET** `/users/me/follow` - get the follower user profiles
- **GET** `/users/me/following` - get the following user profiles
- **GET** `/users/me/readings` - get the reading list of the authenticated user
- **POST** `/users/me/readings` - add a new reading to the reading list
- **DELETE** `/users/me/readings/{id}` - remove a reading with the specified id from the reading list
- **POST** `/users/me/links/` - add a new links to the user profile
- **PUT** `/users/me/links/{link_id}` - update the existing link in the user profile
- **POST** `/users/me/interests/` - add interests to the user profile
- **DELETE** `/users/me/intrests/{id}` - remove a interests from the user profile
- **PUT** `/users/me/email/display` - set the profile email as public or private
- **GET** `/users/links/platforms` - get the list of all the platform listed in the system
- **POST** `/users/links/platforms` - add a new platform link to the system (ADMIN ONLY)
- **POST** `/users/reports` - report a user profile
- **GET** `/users/reports` - get the reports on user profiles (ADMIN ONLY)
- **GET** `/users/reports/{profile_id}` - get the reports on a specific user (ADMIN ONLY)

## Conclusion

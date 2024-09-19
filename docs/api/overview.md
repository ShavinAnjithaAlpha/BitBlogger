# BitBlogger System API Overview

## Introudction

Welcome to the API documentation for the BitBlogger Tech Blog Platform. This system is built using microservice architecture to ensure maintainability, scalability, and flexibility and more. Each microservice responsible for specific domain and communicate with each other using HTTP/HTTPS for synchronous and message brokers for asynchronous communication (event driven architecture).

This odcument provides an overview of the available API of the eahc microservice, and their functionality.

## Table of Contents

- [Authentication Service API](#authentication-service)
- [User Service API](#user-service-api)
- [Like Service API](#like-service-api)

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
- **GET** `/users/me/follow` - get the followed user profiles
- **GET** `/users/me/following` - get the followers user profiles
- **POST** `/users/me/links/` - add a new links to the user profile
- **PUT** `/users/me/links/{link_id}` - update the existing link in the user profile
- **GET** `/users/me/readings` - get the reading list of the authenticated user
- **POST** `/users/me/readings` - add a new reading to the reading list
- **DELETE** `/users/me/readings/{id}` - remove a reading with the specified id from the reading list
- **POST** `/users/me/interests/` - add interests to the user profile
- **GET** `/users/me/interests` - get the interests of the user profile
- **DELETE** `/users/me/intrests/{tagId}` - remove a interests from the user profile
- **PUT** `/users/me/email/display` - set the profile email as public
- **PUT** `/users/me/email/hide` - set the profile email as private
- **POST** `/users/me/reports` - report a user profile
- **GET** `/users/links/platforms` - get the list of all the platform listed in the system
- **POST** `/users/links/platforms` - add a new platform link to the system (ADMIN ONLY)
- **GET** `/users/reports` - get the reports on user profiles (ADMIN ONLY)
- **GET** `/users/reports/{profile_id}` - get the reports on a specific user (ADMIN ONLY)

## Like Service API

### Overview

The **Like Service API** handles the like functionalities like liking a post, unliking a post, getting the likes of a post etc.

### Endpoints

- **POST** `/likes/{postId}` - like a post
- **DELETE** `/likes/{postId}` - unlike a post
- **GET** `/likes/{postId}` - get the likes of a post
- **GET** `/likes/me` - get the likes of the authenticated user
- **GET** `/likes/me/{postId}` - check if the authenticated user liked the post
- **GET** `/likes/me/count` - get the total number of likes of the authenticated user
- **GET** `/likes/count/{postId}` - get the total number of likes of the post
- **GET** `/likes/count` - get the total number of likes in the system (ADMIN ONLY)

### Topic Service API

### Overview

The **Topic Service API** handles the topic functionalities like creating a new topic, updating a topic, deleting a topic, getting the topics, sub topics and parent topics etc.

### Endpoints

- **POST** `/topics` - add new topic (ADMIN ONLY)
- **PUT** `/topics/{topicId}` - update the topic (ADMIN ONLY)
- **DELETE** `/topics/{topicId}` - delete the topic (ADMIN ONLY)
- **GET** `/topics` - get all the topics
- **GET** `/topics/{topicId}` - get the topic with the specified id
- **GET** `/topics/{topicId}/subtopics` - get the sub topics of the specified topic
- **GET** `/topics/{topicId}/parent` - get the parent topic of the specified topic
- **GET** `/topics/history/{topicId}` - get the history of the specified topic (ADMIN ONLY)

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

| Method     | Endpoint                      | Description                                                   |
| ---------- | ----------------------------- | ------------------------------------------------------------- |
| **POST**   | `/users/`                     | Register a new user in the system                             |
| **GET**    | `/users`                      | Get public profiles of users                                  |
| **GET**    | `/users/{id}`                 | Get the public profile of the specified user with the user id |
| **GET**    | `/users/me`                   | Get the user profile of the authenticated user                |
| **PUT**    | `/users/me`                   | Update the user profile of the authenticated user             |
| **DELETE** | `/users/me`                   | Delete the user profile of the authenticated user             |
| **POST**   | `/users/me/follow`            | Follow a new user                                             |
| **DELETE** | `/users/me/follow`            | Unfollow the existing user                                    |
| **GET**    | `/users/me/follow`            | Get the followed user profiles                                |
| **GET**    | `/users/me/following`         | Get the followers user profiles                               |
| **POST**   | `/users/me/links/`            | Add new links to the user profile                             |
| **PUT**    | `/users/me/links/{link_id}`   | Update the existing link in the user profile                  |
| **GET**    | `/users/me/readings`          | Get the reading list of the authenticated user                |
| **POST**   | `/users/me/readings`          | Add a new reading to the reading list                         |
| **DELETE** | `/users/me/readings/{id}`     | Remove a reading with the specified id from the reading list  |
| **POST**   | `/users/me/interests/`        | Add interests to the user profile                             |
| **GET**    | `/users/me/interests`         | Get the interests of the user profile                         |
| **DELETE** | `/users/me/interests/{tagId}` | Remove interests from the user profile                        |
| **PUT**    | `/users/me/email/display`     | Set the profile email as public                               |
| **PUT**    | `/users/me/email/hide`        | Set the profile email as private                              |
| **POST**   | `/users/me/reports`           | Report a user profile                                         |
| **GET**    | `/users/links/platforms`      | Get the list of all the platforms listed in the system        |
| **POST**   | `/users/links/platforms`      | Add a new platform link to the system (ADMIN ONLY)            |
| **GET**    | `/users/reports`              | Get the reports on user profiles (ADMIN ONLY)                 |
| **GET**    | `/users/reports/{profile_id}` | Get the reports on a specific user (ADMIN ONLY)               |

## Like Service API

### Overview

The **Like Service API** handles the like functionalities like liking a post, unliking a post, getting the likes of a post etc.

### Endpoints

| Method     | Endpoint                | Description                                              |
| ---------- | ----------------------- | -------------------------------------------------------- |
| **POST**   | `/likes/{postId}`       | Like a post                                              |
| **DELETE** | `/likes/{postId}`       | Unlike a post                                            |
| **GET**    | `/likes/{postId}`       | Get the likes of a post                                  |
| **GET**    | `/likes/me`             | Get the likes of the authenticated user                  |
| **GET**    | `/likes/me/{postId}`    | Check if the authenticated user liked the post           |
| **GET**    | `/likes/me/count`       | Get the total number of likes of the authenticated user  |
| **GET**    | `/likes/count/{postId}` | Get the total number of likes of the post                |
| **GET**    | `/likes/global/count`   | Get the total number of likes in the system (ADMIN ONLY) |

## Topic Service API

### Overview

The **Topic Service API** handles the topic functionalities like creating a new topic, updating a topic, deleting a topic, getting the topics, sub topics and parent topics etc.

### Endpoints

| Method     | Endpoint                      | Description                                         |
| ---------- | ----------------------------- | --------------------------------------------------- |
| **POST**   | `/topics`                     | Add new topic (ADMIN ONLY)                          |
| **PUT**    | `/topics/{topicId}`           | Update the topic (ADMIN ONLY)                       |
| **DELETE** | `/topics/{topicId}`           | Delete the topic (ADMIN ONLY)                       |
| **GET**    | `/topics`                     | Get all the topics                                  |
| **GET**    | `/topics/{topicId}`           | Get the topic with the specified id                 |
| **GET**    | `/topics/{topicId}/subtopics` | Get the sub topics of the specified topic           |
| **GET**    | `/topics/{topicId}/parent`    | Get the parent topic of the specified topic         |
| **GET**    | `/topics/history/{topicId}`   | Get the history of the specified topic (ADMIN ONLY) |

## Tag Service API

### Overview

The **Tag Service API** handles the tag functionalities like creating a new tag, updating a tag, deleting a tag, getting the tags etc.
Also, it handles the taggings functionalities like tagging a post, untagging a post, getting the tags of a post etc.

### Endpoints

| Method     | Endpoint                       | Description                                       |
| ---------- | ------------------------------ | ------------------------------------------------- |
| **POST**   | `/tags`                        | Add new tag (ADMIN ONLY)                          |
| **PUT**    | `/tags/{tagId}`                | Update the tag (ADMIN ONLY)                       |
| **DELETE** | `/tags/{tagId}`                | Delete the tag (ADMIN ONLY)                       |
| **GET**    | `/tags`                        | Get all the tags                                  |
| **GET**    | `/tags/{tagId}`                | Get the tag with the specified id                 |
| **POST**   | `/tags/posts/{postId}/{tagId}` | Tag a post                                        |
| **DELETE** | `/tags/posts/{postId}/{tagId}` | Untag a post                                      |
| **GET**    | `/tags/posts/{postId}`         | Get the tags of a post                            |
| **POST**   | `/tags/posts/{postId}`         | Add new tags to a post                            |
| **GET**    | `/tags/{tagId}/posts`          | Get the posts with the specified tag              |
| **GET**    | `/tags/history/{tagId}`        | Get the history of the specified tag (ADMIN ONLY) |
| **POST**   | `/tags/batch`                  | Get multiple tags by ids                          |

## Poll Service API

### Overview

The **Poll Service API** handles the poll functionalities like creating a new poll, updating a poll, deleting a poll, getting the polls, take user attempts on a poll, get the poll results, adding answers to the poll etc.

### Endpoints

| Method     | Endpoint                        | Description                                                 |
| ---------- | ------------------------------- | ----------------------------------------------------------- |
| **POST**   | `/polls`                        | Add new poll                                                |
| **PUT**    | `/polls/{pollId}`               | Update the poll                                             |
| **DELETE** | `/polls/{pollId}`               | Delete the poll                                             |
| **POST**   | `/polls/{pollId}/tags/{tagId}`  | Add tag to the specified poll                               |
| **GET**    | `/polls`                        | Get all the polls                                           |
| **GET**    | `/polls/{pollId}`               | Get the poll with the specified id                          |
| **GET**    | `/polls/users/{userId}`         | Get the published polls of the specified user               |
| **GET**    | `/polls/users/me`               | get the polls of the authenticated user                     |
| **POST**   | `/polls/{pollId}/answer`        | Add answers to the poll                                     |
| **GET**    | `/polls/{pollId}/answer/status` | Get whether the answer is correct or not if have the answer |
| **GET**    | `/polls/{pollId}/results`       | Get the poll results                                        |
| **GET**    | `/polls/{pollId}/results/stat`  | Get the statictics about the poll votes                     |

## Comment Service API

### Overview

The **Comment Service API** handles the comment functionalities like creating a new comment, updating a comment, deleting a comment, getting the comments, getting the comments of a post, reacting to a comment, reply to a comment, reporting a abuse comment etc.

### Endpoints

| Method     | Endpoint                                | Description                                                            |
| ---------- | --------------------------------------- | ---------------------------------------------------------------------- |
| **POST**   | `post/{postId}/comments`                | Add new comment                                                        |
| **GET**    | `/posts/{postId}/comments`              | Get the comments of the specified post                                 |
| **GET**    | `/posts/{postId}/comments/count`        | Get the total number of comments of the specified post                 |
| **PUT**    | `/comments/{commentId}`                 | Update the comment                                                     |
| **DELETE** | `/comments/{commentId}`                 | Delete the comment                                                     |
| **GET**    | `/comments/{commentId}`                 | Get the comment with the specified id                                  |
| **POST**   | `/comments/{commentId}/replies`         | Reply to the comment                                                   |
| **GET**    | `/comments/{commentId}/replies`         | Get the replies of the specified comment                               |
| **POST**   | `/comments/{commentId}/reactions`       | React to the comment                                                   |
| **DELETE** | `/comments/{commentId}/reactions`       | Unreact to the comment                                                 |
| **GET**    | `/comments/{commentId}/reactions`       | Get the reactions of the specified comment                             |
| **GET**    | `/comments/{commentId}/reactions/me`    | Get the reaction of the authenticated user on the specified comment    |
| **PUT**    | `/comments/{commentId}/reactions`       | Update the reaction of the authenticated user on the specified comment |
| **GET**    | `/comments/{commentId}/reactions/count` | Get the total number of reactions of the specified comment             |
| **GET**    | `/comments/{commentId}/reactions/stats` | Get the reaction count on each reaction type of the specified comment  |
| **POST**   | `/comments/{commentId}/reports`         | Report the comment                                                     |
| **GET**    | `/comments/reports`                     | Get the reports on comments (ADMIN ONLY)                               |
| **GET**    | `/comments/{commentId}/reports`         | Get the reports of the specified comment (ADMIN ONLY)                  |

## Media Service API

### Overveiw

The **Media Service API** handles the media functionalities like uploading a media, getting the media, updating the media, deleting the media etc.

### Endpoints

| Method     | Endpoint       | Description                            |
| ---------- | -------------- | -------------------------------------- |
| **POST**   | `/media`       | Upload new media                       |
| **POST**   | `/media/image` | Upload new image with a thumbnail      |
| **GET**    | `/media/{id}`  | Get the media with the specified id    |
| **PUT**    | `/media/{id}`  | Update the media with the specified id |
| **DELETE** | `/media/{id}`  | Delete the media with the specified id |

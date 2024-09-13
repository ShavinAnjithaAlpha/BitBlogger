# API Endpoint Documentation

## Introduction

This documents provides the detailed information about the availabale REST API endpoints of each microservices used in the system. For each endpoint, document provides the information about the HTT method, URL, request and response body, request paramaters, path varaibles, request headers, and the response status codes.

## Table of Contents

- [API Endpoint Documentation](#api-endpoint-documentation)
  - [Introduction](#introduction)
  - [Table of Contents](#table-of-contents)
  - [Authentication Service](#authentication-service)
  - [User Service API](#user-service-api)
    - [1. **POST** `api/v1/users/`](#1-post-apiv1users)
    - [2. **GET** `api/v1/users`](#2-get-apiv1users)
    - [3. **GET** `api/v1/users/{id}`](#3-get-apiv1usersid)
    - [4. **GET** `api/v1/users/me`](#4-get-apiv1usersme)
    - [5. **PUT** `api/v1/users/me`](#5-put-apiv1usersme)
    - [6. **DELETE** `api/v1/users/me`](#6-delete-apiv1usersme)
    - [7. **POST** `api/v1/users/me/follow`](#7-post-apiv1usersmefollow)
    - [8. **DELETE** `api/v1/users/me/follow`](#8-delete-apiv1usersmefollow)
    - [9. **GET** `api/v1/users/me/follow`](#9-get-apiv1usersmefollow)
    - [10. **GET** `api/v1/users/me/following`](#10-get-apiv1usersmefollowing)

## Authentication Service

## User Service API

### 1. **POST** `api/v1/users/`

**Description**: Register a new user in the system

- **Request**:

  ```json
  {
    "name": "Sanjana Kumarasingha",
    "username": "sanjana",
    "email": "sanjana@gmail.com",
    "password": "sanjanakuma"
  }
  ```

  - **Response**
    ```json
    1 // id of the user
    ```

- **Error Codes**:
  - `400 Bad Request`: Invalid Parameters
    ```json
    {
      "errors": {
        "email": "Invalid email format"
      }
    }
    ```
  - `409 Conflict`: User with username or email already exists
    ```json
    {
      "error": "User with email {email} or username {username} is already registered"
    }
    ```
  - `500 Internal Server Error`: Server Error

### 2. **GET** `api/v1/users`

**Description**: Get public profiles of users

- **Request Parameters**:

  - `page`: page number
  - `size`: number of items per page
  - `sort`: sort by field
  - `direction`: sort direction

- **Response**:
  ```json
  {
    "content": [
      {
        "id": 2,
        "username": "username",
        "email": "email",
        "name": "name",
        "profileImage": "profileImage",
        "websiteUrl": "websiteUrl",
        "location": {
          "city": "city",
          "country": "country"
        },
        "profile": {
          "bio": "bio",
          "skills": "skills",
          "availableFor": "availableFor",
          "learnings": "learnings",
          "pronouns": "pronouns",
          "work": "work",
          "education": "education"
        },
        "links": [],
        "createdAt": "2021-09-01T00:00:00.000Z"
      }
      , ...
    ],
    "pageable": {
      "pageNumber": 0,
      "pageSize": 20,
      "sort": {
        "empty": true,
        "sorted": false,
        "unsorted": true
      },
      "offset": 0,
      "paged": true,
      "unpaged": false
    },
    "last": true,
    "totalElements": 4,
    "totalPages": 1,
    "size": 20,
    "number": 0,
    "sort": {
      "empty": true,
      "sorted": false,
      "unsorted": true
    },
    "numberOfElements": 4,
    "first": true,
    "empty": false
  }
  ```

### 3. **GET** `api/v1/users/{id}`

**Description**: Get the public profile of the specified user with the user id

- **Response**:

  ```json
  {
    "id": 2,
    "username": "username",
    "email": "email",
    "name": "name",
    "profileImage": "profileImage",
    "websiteUrl": "websiteUrl",
    "location": {
      "city": "city",
      "country": "country"
    },
    "profile": {
      "bio": "bio",
      "skills": "skills",
      "availableFor": "availableFor",
      "learnings": "learnings",
      "pronouns": "pronouns",
      "work": "work",
      "education": "education"
    },
    "links": [],
    "createdAt": "2021-09-01T00:00:00.000Z"
  }
  ```

- **Error Codes**:
- `404 Not Found`: User not found
  ```json
  {
    "error": "user with user id {userId} is not found"
  }
  ```

### 4. **GET** `api/v1/users/me`

**Description**: Get the user profile of the authenticated user

- **Request Headers**:
  - `Authorization`: Bearer token
- **Response**:
  ```json
  {
    "id": 2,
    "username": "username",
    "email": "email",
    "name": "name",
    "profileImage": "profileImage",
    "websiteUrl": "websiteUrl",
    "location": {
      "city": "city",
      "country": "country"
    },
    "profile": {
      "bio": "bio",
      "skills": "skills",
      "availableFor": "availableFor",
      "learnings": "learnings",
      "pronouns": "pronouns",
      "work": "work",
      "education": "education"
    },
    "links": [],
    "createdAt": "2021-09-01T00:00:00.000Z"
  }
  ```

### 5. **PUT** `api/v1/users/me`

**Description**: Update the user profile of the authenticated user

- **Request Headers**:
  - `Authorization`: Bearer token
- **Request**:

  ```json
  {
    "username": "username",
    "email": "email",
    "name": "name",
    "profileImage": "profileImage",
    "websiteUrl": "websiteUrl",
    "location": {
      "city": "city",
      "country": "country"
    },
    "profile": {
      "bio": "bio",
      "skills": "skills",
      "availableFor": "availableFor",
      "learnings": "learnings",
      "pronouns": "pronouns",
      "work": "work",
      "education": "education"
    },
    "createdAt": "2021-09-01T00:00:00.000Z"
  }
  ```

  - **Response**
    ```json
    {
      "message": "profile updated successfully"
    }
    ```

- **Error Codes**:
  - `400 Bad Request`: Invalid Parameters
    ```json
    {
      "errors": {
        "email": "Invalid email format"
      }
    }
    ```
  - `409 Conflict`: User with username or email already exists
    ```json
    {
      "error": "user with username {username} or email {email} is already exists"
    }
    ```
  - `500 Internal Server Error`: Server Error

### 6. **DELETE** `api/v1/users/me`

**Description**: Delete the user profile of the authenticated user

- **Request Headers**:
  - `Authorization`: Bearer token
- **Response**:
  ```json
  {
    "message": "user deleted successfully"
  }
  ```

### 7. **POST** `api/v1/users/me/follow`

**Description**: Follow a new user

- **Request Headers**:
  - `Authorization`: Bearer token
- **Request**:

  ```json
  {
    "followerId": 1
  }
  ```

- **Response**:

  ```json
  {
    "message": "follower added successfully"
  }
  ```

- **Error Codes**:

  - `400 Bad Request`: Follow yourself

    ```json
    {
      "error": "cannot follow yourselves"
    }
    ```

    Follower already exists

    ```json
    {
      "error": "follow user with id {userId} already"
    }
    ```

  - `404 Not Found`: User not found
    ```json
    {
      "error": "follower with user id 10 not found"
    }
    ```
  - `500 Internal Server Error`: Server Error

### 8. **DELETE** `api/v1/users/me/follow`

**Description**: Unfollow the exists user

- **Request Headers**:
  - `Authorization`: Bearer token
- **Request**:

  ```json
  {
    "followerId": 1
  }
  ```

- **Response**:

  ```json
  {
    "message": "unfollow the user successfully"
  }
  ```

- **Error Codes**:
- `500 Internal Server Error`: Server Error

### 9. **GET** `api/v1/users/me/follow`

**Description**: Get the followed user profiles

- **Request Headers**:
  - `Authorization`: Bearer token
- **Request Parameters**:

  - `page`: page number
  - `size`: number of items per page
  - `sort`: sort by field
  - `direction`: sort direction
  - `detailed`: include detailed information

- **Response**:
  (detailed=0)

  ```json
  {
    "content": [
      {
        "id": 4,
        "username": "name",
        "email": "email",
        "name": "name",
        "profileImage": "profileImage",
        "websiteUrl": "websiteUrl",
        "followedAt": "2024-09-12T03:26:24"
      }
    ],
    "pageable": {
      "pageNumber": 0,
      "pageSize": 20,
      "sort": {
        "empty": true,
        "sorted": false,
        "unsorted": true
      },
      "offset": 0,
      "paged": true,
      "unpaged": false
    },
    "last": true,
    "totalPages": 1,
    "totalElements": 1,
    "size": 20,
    "number": 0,
    "sort": {
      "empty": true,
      "sorted": false,
      "unsorted": true
    },
    "first": true,
    "numberOfElements": 1,
    "empty": false
  }
  ```

  (detailed=1)

  ```json
  {
    "content": [
      {
        "id": 1,
        "username": "username",
        "email": "email",
        "name": "name",
        "profileImage": "profileImage",
        "websiteUrl": "websiteUrl",
        "location": "location",
        "profile": "profile",
        "links": [],
        "createdAt": "2024-09-12T03:17:38"
      }, ...
    ],
    "pageable": {
      "pageNumber": 0,
      "pageSize": 20,
      "sort": {
        "empty": true,
        "sorted": false,
        "unsorted": true
      },
      "offset": 0,
      "paged": true,
      "unpaged": false
    },
    "last": true,
    "totalPages": 1,
    "totalElements": 1,
    "size": 20,
    "number": 0,
    "sort": {
      "empty": true,
      "sorted": false,
      "unsorted": true
    },
    "first": true,
    "numberOfElements": 1,
    "empty": false
  }
  ```

  ### 10. **GET** `api/v1/users/me/following`

**Description**: Get the followers user profiles

- **Request Headers**:
  - `Authorization`: Bearer token
- **Request Parameters**:
  - `page`: page number
  - `size`: number of items per page
  - `sort`: sort by field
  - `direction`: sort direction
  - `detailed`: include detailed information
- **Response**:
  (detailed=0)
  ```json
  {
    "content": [
      {
        "id": 4,
        "username": "name",
        "email": "email",
        "name": "name",
        "profileImage": "profileImage",
        "websiteUrl": "websiteUrl",
        "followedAt": "2024-09-12T03:26:24"
      }
    ],
    "pageable": {
      "pageNumber": 0,
      "pageSize": 20,
      "sort": {
        "empty": true,
        "sorted": false,
        "unsorted": true
      },
      "offset": 0,
      "paged": true,
      "unpaged": false
    },
    "last": true,
    "totalPages": 1,
    "totalElements": 1,
    "size": 20,
    "number": 0,
    "sort": {
      "empty": true,
      "sorted": false,
      "unsorted": true
    },
    "first": true,
    "numberOfElements": 1,
    "empty": false
  }
  ```
  (detailed=1)
  ```json
  {
      "content": [
      {
          "id": 1,
          "username": "username",
          "email": "email",
          "name": "name",
          "profileImage": "profileImage",
          "websiteUrl": "websiteUrl",
          "location": "location",
          "profile": "profile",
          "links": [],
          "createdAt": "2024-09-12T03:17:38"
      }, ...
      ],
      "pageable": {
      "pageNumber": 0,
      "pageSize": 20,
      "sort": {
          "empty": true,
          "sorted": false,
          "unsorted": true
      },
      "offset": 0,
      "paged": true,
      "unpaged": false
      },
      "last": true,
      "totalPages": 1,
      "totalElements": 1,
      "size": 20,
      "number": 0,
      "sort": {
      "empty": true,
      "sorted": false,
      "unsorted": true
      },
      "first": true,
      "numberOfElements": 1,
      "empty": false
  }
  ```

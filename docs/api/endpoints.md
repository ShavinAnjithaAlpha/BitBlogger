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
    - [11. **POST** `api/v1/users/me/links/`](#11-post-apiv1usersmelinks)
    - [12. **PUT** `api/v1/users/me/links/{link_id}`](#12-put-apiv1usersmelinkslink_id)
    - [13. **GET** `api/v1/users/me/readings`](#13-get-apiv1usersmereadings)
    - [14. **POST** `api/v1/users/me/readings`](#14-post-apiv1usersmereadings)
    - [15. **DELETE** `api/v1/users/me/readings/{id}`](#15-delete-apiv1usersmereadingsid)
    - [16. **POST** `api/v1/users/me/interests/`](#16-post-apiv1usersmeinterests)
    - [17. **GET** `api/v1/users/me/interests`](#17-get-apiv1usersmeinterests)
    - [18. **DELETE** `api/v1/users/me/intrests/{tagId}`](#18-delete-apiv1usersmeintreststagid)
    - [19. **PUT** `api/v1/users/me/email/display`](#19-put-apiv1usersmeemaildisplay)
    - [20. **POST** `api/v1/users/me/email/hide`](#20-post-apiv1usersmeemailhide)
    - [21. **POST** `api/v1/users/me/reports`](#21-post-apiv1usersmereports)
    - [22. **GET** `api/v1/users/links/platforms`](#22-get-apiv1userslinksplatforms)
    - [23. **POST** `api/v1/users/links/platforms`](#23-post-apiv1userslinksplatforms)
    - [24. **GET** `api/v1/users/reports`](#24-get-apiv1usersreports)
    - [25. **GET** `api/v1/users/reports/{profile_id}`](#25-get-apiv1usersreportsprofile_id)
  - [Like Service API](#like-service-api)
    - [1. **POST** `/api/v1/likes/{postId}`](#1-post-apiv1likespostid)
    - [2. **DELETE** `/api/v1/likes/{postId}`](#2-delete-apiv1likespostid)
    - [3. **GET** `/api/v1/likes/{postId}`](#3-get-apiv1likespostid)
    - [4. **GET** `/aipi/v1/likes/me`](#4-get-aipiv1likesme)
    - [5. **GET** `/api/v1/likes/me/{postId}`](#5-get-apiv1likesmepostid)
    - [6. **GET** `/api/v1/likes/me/count`](#6-get-apiv1likesmecount)
    - [7. **GET** `/api/v1/likes/count/{postId}`](#7-get-apiv1likescountpostid)
    - [8. **GET** `/api/v1/likes/global/count`](#8-get-apiv1likesglobalcount)
  - [Topic Service API](#topic-service-api)
    - [1. **POST** `/api/v1/topics`](#1-post-apiv1topics)
    - [2. **PUT** `/api/v1/topics/{topicId}`](#2-put-apiv1topicstopicid)
    - [3. **GET** `/api/v1/topics`](#3-get-apiv1topics)
    - [4. **GET** `/api/v1/topics/{topicId}`](#4-get-apiv1topicstopicid)
    - [5. **GET** `/api/v1/topics/{topicId}/parent`](#5-get-apiv1topicstopicidparent)
    - [6. **GET** `/api/v1/topics/{topicId}/subtopics`](#6-get-apiv1topicstopicidsubtopics)
    - [7. **GET** `/api/v1/topics/{topicId}/history`](#7-get-apiv1topicstopicidhistory)
    - [8. **DELETE** `/api/v1/topics/{topicId}`](#8-delete-apiv1topicstopicid)

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

  ### 11. **POST** `api/v1/users/me/links/`

**Description**: Add a new links to the user profile (MAXIMUM LINK COUNT = 10)

- **Request Headers**:

  - `Authorization`: Bearer token

- **Request**:

  ```json
  {
    "platformId": 1,
    "url": "url",
    "custom": 0
  }
  ```

- **Response**:

  ```json
  {
    "id": 1,
    "message": "user link created successfully"
  }
  ```

- **Error Codes**:

  - `400 Bad Request`: Invalid Parameters

    ```json
    {
      "error": "link with platform id {platformId} is already associated with user with id {userId}"
    }
    ```

    Invalid platform id

    ```json
    {
      "error": "invalid platform id {platformId}"
    }
    ```

    Maximum links count reached

    ```json
    {
      "error": "user has exceeded the maximum link count"
    }
    ```

  - `500 Internal Server Error`: Server Error

### 12. **PUT** `api/v1/users/me/links/{link_id}`

**Description**: Update the existing link in the user profile

- **Request Headers**:

  - `Authorization`: Bearer token

- **Request**:

  ```json
  {
    "url": "url",
    "custom": 0
  }
  ```

- **Response**:

  ```json
  {
    "message": "user link updated successfully"
  }
  ```

- **Error Codes**:

  - `400 Bad Request`: link not exists

    ```json
    {
      "error": "user's link with id {linkId} of the user with id {userId} is not exists"
    }
    ```

    Invalid link Id with user Id

    ```json
    {
      "error": "link with id {linkId} is not belongs to user with id {userId}"
    }
    ```

  - `500 Internal Server Error`: Server Error

### 13. **GET** `api/v1/users/me/readings`

**Description**: Get the reading list of the authenticated user

- **Request Headers**:
  - `Authorization`: Bearer token
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
        "id": 1,
        "postId": 1,
        "note": "note"
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
    "totalElements": 2,
    "totalPages": 1,
    "size": 20,
    "number": 0,
    "sort": {
      "empty": true,
      "sorted": false,
      "unsorted": true
    },
    "numberOfElements": 2,
    "first": true,
    "empty": false
  }
  ```

### 14. **POST** `api/v1/users/me/readings`

**Description**: Add a new reading to the reading list

- **Request Headers**:
  - `Authorization`: Bearer token
- **Request**:

  ```json
  {
    "postId": 1,
    "note": "note"
  }
  ```

- **Response**:

  ```json
  {
    "id": 1,
    "message": "reading added successfully"
  }
  ```

- **Error Codes**:

  - `400 Bad Request`: Invalid Parameters

    ```json
    {
      "errors": {
        "note": "description cannot exceed 512 characters"
      }
    }
    ```

    Reading already exists

    ```json
    {
      "error": "reading with post id {postId} of the user with id {userId} is already exists"
    }
    ```

  - `500 Internal Server Error`: Server Error

### 15. **DELETE** `api/v1/users/me/readings/{id}`

**Description**: Remove a reading with the specified id from the reading list

- **Request Headers**:
  - `Authorization`: Bearer token
- **Response**:

  ```json
  {
    "message": "reading removed successfully"
  }
  ```

- **Error Codes**:
  - `400 Bad Request`: Reading is not belong to authenticated user
    ```json
    {
      "error": "reading with id {readingId} is not correspond to user with id {userId}"
    }
    ```
  - `500 Internal Server Error`: Server Error

### 16. **POST** `api/v1/users/me/interests/`

**Description**: Add interests to the user profile

- **Request Headers**:
  - `Authorization`: Bearer token
- **Request**:

  ```json
  {
    "tagId": 1
  }
  ```

- **Response**:

  ```json
  {
    "id": 1,
    "message": "interest added successfully"
  }
  ```

- **Error Codes**:

  - `409 Conflict`: Invalid Parameters

    ```json
    {
      "errors": {
        "tagId": "tag id is required"
      }
    }
    ```

    Interest already exists

    ```json
    {
      "error": "interest with tag id {tagId} already exists in the user {userId}"
    }
    ```

  - `500 Internal Server Error`: Server Error

### 17. **GET** `api/v1/users/me/interests`

**Description**: Get the interests of the user profile

- **Request Headers**:
  - `Authorization`: Bearer token
- **Response**:
  ```json
  [1, 2, 3, 4]
  ```

### 18. **DELETE** `api/v1/users/me/intrests/{tagId}`

**Description**: Remove a interests from the user profile

- **Request Headers**:

  - `Authorization`: Bearer token

- **Response**:

  ```json
  {
    "message": "interest removed successfully"
  }
  ```

### 19. **PUT** `api/v1/users/me/email/display`

**Description**: Set the profile email as public

- **Request Headers**:

  - `Authorization`: Bearer token

- **Response**:

  ```json
  {
    "message": "email set as public"
  }
  ```

### 20. **POST** `api/v1/users/me/email/hide`

**Description**: Set the profile email as private

- **Request Headers**:

  - `Authorization`: Bearer token

- **Response**:

  ```json
  {
    "message": "email set as private"
  }
  ```

### 21. **POST** `api/v1/users/me/reports`

**Description**: Report a user profile

- **Request Headers**:

  - `Authorization`: Bearer token

- **Request**:

  ```json
  {
    "userId": 1,
    "reason": "REASON",
    "note": "note"
  }
  ```

- **Response**:

  ```json
  {
    "id": 1,
    "message": "user reported successfully"
  }
  ```

- **Error Codes**:

  - `400 Bad Request`: Invalid Parameters

    ```json
    {
      "errors": {
        "userId": "user id is required"
      }
    }
    ```

    User already reported

    ```json
    {
      "error": "user with id {userId} is already reported"
    }
    ```

  - `500 Internal Server Error`: Server Error

### 22. **GET** `api/v1/users/links/platforms`

**Description**: Get the list of all the platform listed in the system

- **Response**:
  ```json
  [
    {
      "id": 1,
      "name": "GitHub",
      "baseUrl": "https://github.com",
      "description": "platform of developers to share their codes with others"
    },
    {
      "id": 2,
      "name": "Dev",
      "baseUrl": "https://dev.to",
      "description": "technical blogging platform"
    }
  ]
  ```

### 23. **POST** `api/v1/users/links/platforms`

**Description**: Add a new platform link to the system (ADMIN ONLY)

- **Request Headers**:

  - `Authorization`: Bearer token

- **Request**:

  ```json
  {
    "name": "GitHub",
    "baseUrl": "https://github.com",
    "description": "platform of developers to share their codes with others"
  }
  ```

- **Response**:

  ```json
  {
    "id": 1,
    "message": "platform added to the system successfully"
  }
  ```

- **Error Codes**:

  - `409 Conflict`: Platform already exists

    ```json
    {
      "error": "platform with name {name} or base url {baseUrl} is already exists"
    }
    ```

  - `400 Bad Request`: Invalid Parameters

  ```json
  {
    "errors": {
      "baseUrl": "Invalid URL format"
    }
  }
  ```

  - `500 Internal Server Error`: Server Error

### 24. **GET** `api/v1/users/reports`

**Description**: Get the reports on user profiles (ADMIN ONLY)

- **Request Headers**:

  - `Authorization`: Bearer token

- **Response**
  ```json
  {
    "content": [
        {
            "id": 1,
            "user": {
                "id": 6,
                "name": "name",
                "username": "username",
                "email": "email",
                "profileImage": "profileImage"
            },
            "reporter": {
                "id": 2,
                "name": "name",
                "username": "username",
                "email": "email",
                "profileImage": "profileImage"
            },
            "reason": "REASON",
            "note": "note",
            "createdAt": "2024-09-15T16:50:30"
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
    "totalElements": 2,
    "size": 20,
    "number": 0,
    "sort": {
        "empty": true,
        "sorted": false,
        "unsorted": true
    },
    "numberOfElements": 2,
    "first": true,
    "empty": false
  }
  ```

### 25. **GET** `api/v1/users/reports/{profile_id}`

**Description**: Get the reports on a specific user (ADMIN ONLY)

- **Request Headers**:

  - `Authorization`: Bearer token

- **Response**:
  ```json
  {
    "content": [
        {
            "id": 1,
            "user": {
                "id": 6,
                "name": "name",
                "username": "username",
                "email": "email",
                "profileImage": "profileImage"
            },
            "reporter": {
                "id": 2,
                "name": "name",
                "username": "username",
                "email": "email",
                "profileImage": "profileImage"
            },
            "reason": "REASON",
            "note": "note",
            "createdAt": "2024-09-15T16:50:30"
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
    "totalElements": 2,
    "size": 20,
    "number": 0,
    "sort": {
        "empty": true,
        "sorted": false,
        "unsorted": true
    },
    "numberOfElements": 2,
    "first": true,
    "empty": false
  }
  ```

## Like Service API

### 1. **POST** `/api/v1/likes/{postId}`

**Description**: Like a post

- **Request Headers**:

  - `Authorization`: Bearer token

- **Request Body**:

  ```json
  0 // like type
  ```

- **Response**: `201 Created`

### 2. **DELETE** `/api/v1/likes/{postId}`

**Description**: Unlike a post

- **Request Headers**:

  - `Authorization`: Bearer token

- **Response**: `200 OK`

### 3. **GET** `/api/v1/likes/{postId}`

**Description**: Get the likes of the post

- **Response**:

  ```json
  {
    "count": 1,
    "content": [
      {
      "userId": 1,
      "likeStatus": 0,
      "likedAt": "2024-09-19T23:53:54"
      }, ...
    ],
    "pagingState": "pagingState",
    "isFirst": true,
    "isLast": true,
    "hasNext": false,
    "hasPrevious": false
  }
  ```

  - `404 Not Found`: Post not found

    ```json
    {
      "error": "post with id {postId} is not found"
    }
    ```

    - `500 Internal Server Error`: Server Error

### 4. **GET** `/aipi/v1/likes/me`

**Description**: Get the likes of the authenticated user

- **Request Headers**:

  - `Authorization`: Bearer token

- **Response**:

  ```json
  {
    "count": 1,
    "content": [
      {
      "postId": 1,
      "likeStatus": 0,
      "likedAt": "2024-09-19T23:53:54"
      }, ...
    ],
    "pagingState": "pagingState",
    "isFirst": true,
    "isLast": true,
    "hasNext": false,
    "hasPrevious": false
  }
  ```

### 5. **GET** `/api/v1/likes/me/{postId}`

**Description**: Get the like status of the authenticated user for the specified post

- **Request Headers**:

  - `Authorization`: Bearer token

- **Response**:

  if user likes

  ```json
  0 // like type
  ```

  if user not likes

  ```json
  false
  ```

  - `404 Not Found`: Post not found

    ```json
    {
      "error": "post with id {postId} is not found"
    }
    ```

    - `500 Internal Server Error`: Server Error

### 6. **GET** `/api/v1/likes/me/count`

- **Description**: Get the total number of likes of the authenticated user

- **Request Headers**:

  - `Authorization`: Bearer token

- **Response**:

  ```json
  {
    "count": {
      "LIKE": 1,
      "HEART": 0,
      ...
    }
  }
  ```

### 7. **GET** `/api/v1/likes/count/{postId}`

- **Description**: Get the total number of likes of the specified post

- **Response**:

  ```json
  {
    "count": {
      "LIKE": 1,
      "HEART": 0,
      ...
    }
  }
  ```

  - `404 Not Found`: Post not found

    ```json
    {
      "error": "post with id {postId} is not found"
    }
    ```

    - `500 Internal Server Error`: Server Error

### 8. **GET** `/api/v1/likes/global/count`

- **Description**: Get the total number of likes of the system (ADMIN ONLY)

- **Request Headers**:

  - `Authorization`: Bearer token

- **Response**:

  ```json
  {
    "count": {
      "LIKE": 1,
      "HEART": 0,
      ...
    }
  }
  ```

## Topic Service API

### 1. **POST** `/api/v1/topics`

**Description**: Create a new topic

- **Request Headers**:

  - `Authorization`: Bearer token

- **Request Body**:

  ```json
  {
    "name": "name",
    "description": "decsription",
    "parentTopicId": 1
  }
  ```

- **Response**:

  ```json
  {
    "id": 1
  }
  ```

- **Error Codes**:

  - `400 Bad Request`: Invalid Parameters

    ```json
    {
      "errors": {
        "name": "name is required"
      }
    }
    ```

    Topic already exists

    ```json
    {
      "error": "topic with name {name} already exists"
    }
    ```

  - `500 Internal Server Error`: Server Error

### 2. **PUT** `/api/v1/topics/{topicId}`

**Description**: Update the existing topic

- **Request Headers**:

  - `Authorization`: Bearer token

- **Request Body**:

  ```json
  {
    "name": "name",
    "description": "decsription",
    "parentTopicId": 1
  }
  ```

- **Response**: `200 OK`

- **Error Codes**:

  - `400 Bad Request`: Invalid Parameters

    ```json
    {
      "errors": {
        "name": "name is required"
      }
    }
    ```

    Topic not exists

    ```json
    {
      "error": "topic with id {topicId} is not exists"
    }
    ```

  - `500 Internal Server Error`: Server Error

### 3. **GET** `/api/v1/topics`

**Description**: Get the list of all topics

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
            "id": 1,
            "name": "name",
            "description": "description",
            "isParent": true,
            "isChild": false
        }, ...
    ],
    "pageable": {
        "pageNumber": 0,
        "pageSize": 20,
        "sort": [],
        "offset": 0,
        "paged": true,
        "unpaged": false
    },
    "last": true,
    "totalPages": 1,
    "totalElements": 4,
    "size": 20,
    "number": 0,
    "sort": [],
    "numberOfElements": 4,
    "first": true,
    "empty": false
  }
  ```

### 4. **GET** `/api/v1/topics/{topicId}`

**Description**: Get the topic with the specified id

- **Response**:

  ```json
  {
    "id": 1,
    "name": "name",
    "description": "description",
    "isParent": true,
    "isChild": false
  }
  ```

- **Error Codes**:

  - `404 Not Found`: Topic not found

    ```json
    {
      "error": "topic with id {topicId} is not found"
    }
    ```

### 5. **GET** `/api/v1/topics/{topicId}/parent`

**Description**: Get the parent topic of the specified topic

- **Response**:

  ```json
  {
    "id": 1,
    "name": "name",
    "description": "description",
    "isParent": true,
    "isChild": false
  }
  ```

- **Error Codes**:

  - `404 Not Found`: Topic not found

    ```json
    {
      "error": "topic with id {topicId} is not found"
    }
    ```

### 6. **GET** `/api/v1/topics/{topicId}/subtopics`

**Description**: Get the subtopics of the specified topic

- **Response**:

```json
[
  {
      "id": 1,
      "name": "name",
      "description": "description",
      "isParent": false,
      "isChild": true
  }, ...
]
```

- **Error Codes**:

  - `404 Not Found`: Topic not found

    ```json
    {
      "error": "topic with id {topicId} is not found"
    }
    ```

### 7. **GET** `/api/v1/topics/{topicId}/history`

**Description**: Get the history of the specified topic along with the changes

- **Response**:

```json
{
    "topic": {
        "id": 1,
        "name": "tagName",
        "description": "tagDescription",
        "isParent": false,
        "isChild": true
    },
    "history": [
        {
            "id": 1,
            "name": "tagName",
            "description": "tagDescription",
            "changedBy": 1,
            "action": "TOPIC_CREATED",
            "changedAt": "2024-09-19T23:53:54"
        }, ...
    ]
}
```

- **Error Codes**:

  - `404 Not Found`: Topic not found

    ```json
    {
      "error": "topic with id {topicId} is not found"
    }
    ```

### 8. **DELETE** `/api/v1/topics/{topicId}`

**Description**: Delete the topic with the specified id

- **Request Headers**:

  - `Authorization`: Bearer token

- **Response**: `200 OK`

# Project Architecture

## Overview

This document provides the detailed overview of the architecture for BitBlogger Web App. It explains the system design, the different component involved, and how they interract with each other. The architecture is designed to be scalabale, modular, secure, and maintainatable, adhering to the best practices and design principles.

## High Level Architecture

The whole system is consisting of two main components. Which is client and backend of the system. The client is the frontend of the system which is responsible for the user interface and user interaction.

The backend follows a microservice architecture, consisting of multiple independant services that communicate with eachothre via REST API. Each service is responsible for specific domain of the system and looselt coupled with each other to make the system more scalable and maintanable.

## System Component

### Backend System Components

### 1. Authentication Server

- **purpose**: manage user authentication and authorization, issue and validate jwt tokens.
- **features**:
  - user registration
  - user login
  - OAuth 2.0 and JWT based login
  - role based access control
  - token validation

### 2. User Service

- **purpose**: manages user accounts and profiles.
- **features**:
  - profile update
  - profile deletion
  - follow/unfollow user
  - intergration with the auth server for authorization
  - user profile reporting
  - maintain a reading list fo reach users

Primary Database: **MySQL**

- **Consideration** : need of a strong consistency of the relationship between the entities in the service domain model and need to prioritize ACID compliance.

### 3. Content Service

### 4. Topic Service

### 5. Tags Service

### 6. Likes Service

### 7. Comment Service

### 8. Poll Service

### 9. Survey Service

### 10. Notification Service

### 11. Search Service

### 12. API Gateway

## Data Flow

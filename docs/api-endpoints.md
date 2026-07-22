# API Endpoints

## Base URL

Local development:

```text
http://localhost:8080
```

When using Expo on a physical device, `localhost` may need to be replaced with the computer's local IP address.

Example:

```text
http://192.168.x.x:8080
```

## Authentication

Protected endpoints require a JWT token.

Header:

```text
Authorization: Bearer <token>
```

---

# Health

## GET /api/health

Checks if the backend is running.

### Response example

```json
{
  "status": "ok",
  "project": "Chronicles of Elia",
  "message": "Backend is running",
  "timestamp": "2026-07-22T12:00:00"
}
```

---

# Auth

## POST /api/auth/register

Creates a new user account.

### Request body

```json
{
  "fullName": "María Fernanda Rodríguez",
  "email": "mafer@test.com",
  "password": "Password123!"
}
```

### Response example

```json
{
  "id": 1,
  "fullName": "María Fernanda Rodríguez",
  "email": "mafer@test.com",
  "role": "USER",
  "message": "User registered successfully"
}
```

---

## POST /api/auth/login

Authenticates a user and returns a JWT token.

### Request body

```json
{
  "email": "mafer@test.com",
  "password": "Password123!"
}
```

### Response example

```json
{
  "id": 1,
  "fullName": "María Fernanda Rodríguez",
  "email": "mafer@test.com",
  "role": "USER",
  "token": "jwt-token-here",
  "tokenType": "Bearer",
  "expiresIn": 86400
}
```

---

# Users

## GET /api/users/me

Returns the authenticated user's basic information.

Requires authentication.

### Response example

```json
{
  "id": 1,
  "fullName": "María Fernanda Rodríguez",
  "email": "mafer@test.com",
  "role": "USER",
  "isActive": true
}
```

---

# Catalogs

## GET /api/catalogs/professions

Returns available professional paths.

### Response example

```json
[
  {
    "id": 1,
    "code": "DEV",
    "name": "Dev Path",
    "description": "English for projects, interviews, documentation and teamwork."
  }
]
```

---

## GET /api/catalogs/english-levels

Returns available CEFR-inspired English levels.

### Response example

```json
[
  {
    "id": 2,
    "code": "A2",
    "name": "Wanderer",
    "description": "Elementary English level for simple conversations and everyday contexts."
  }
]
```

---

## GET /api/catalogs/interests

Returns available interests.

### Response example

```json
[
  {
    "id": 1,
    "code": "BOOKS",
    "name": "Books",
    "description": null
  }
]
```

---

## GET /api/catalogs/learning-goals

Returns available learning goals.

### Response example

```json
[
  {
    "id": 1,
    "code": "TECHNICAL_INTERVIEWS",
    "name": "Technical Interviews",
    "description": "Practice English for technical interviews and professional introductions."
  }
]
```

---

# Learning Profile

## PUT /api/profiles/me

Creates or updates the authenticated user's learning profile.

Requires authentication.

### Request body

```json
{
  "professionCode": "DEV",
  "englishLevelCode": "A2",
  "interestCodes": ["TECHNOLOGY", "BOOKS", "MUSIC"],
  "learningGoalCodes": ["TECHNICAL_INTERVIEWS", "REMOTE_WORK"],
  "bio": "I want to practice English for backend interviews."
}
```

### Response example

```json
{
  "id": 1,
  "profession": "Dev Path",
  "professionCode": "DEV",
  "englishLevel": "Wanderer",
  "englishLevelCode": "A2",
  "interests": ["Technology", "Books", "Music"],
  "learningGoals": ["Technical Interviews", "Remote Work"],
  "bio": "I want to practice English for backend interviews."
}
```

---

## GET /api/profiles/me

Returns the authenticated user's learning profile.

Requires authentication.

---

# Diagnostic

## GET /api/diagnostic/questions

Returns active diagnostic questions with options.

Requires authentication.

Important: this endpoint does not return correct answers.

### Response example

```json
[
  {
    "id": 1,
    "questionText": "Choose the correct sentence.",
    "instruction": "Select the sentence that is grammatically correct.",
    "skillFocus": "Grammar",
    "englishLevel": "Beginner",
    "englishLevelCode": "A1",
    "orderIndex": 1,
    "options": [
      {
        "id": 1,
        "optionText": "I am a student.",
        "orderIndex": 1
      }
    ]
  }
]
```

---

## POST /api/diagnostic/submit

Submits diagnostic answers and returns a suggested English level.

Requires authentication.

### Request body

```json
{
  "updateProfileLevel": false,
  "answers": [
    {
      "questionId": 1,
      "selectedOptionId": 1
    },
    {
      "questionId": 2,
      "selectedOptionId": 7
    }
  ]
}
```

The MVP requires all active diagnostic questions to be answered.

### Response example

```json
{
  "resultId": 1,
  "totalScore": 6,
  "maxScore": 9,
  "suggestedEnglishLevel": "Wanderer",
  "suggestedEnglishLevelCode": "A2",
  "resultLabel": "A2 Wanderer",
  "resultDescription": "You can start with simple professional communication: routines, requests, descriptions and basic work situations.",
  "profileLevelUpdated": false,
  "takenAt": "2026-07-22T12:00:00"
}
```

---

# Missions

## GET /api/missions/me

Returns missions for the authenticated user's learning profile.

Requires authentication.

The response includes progress status for each mission.

### Response example

```json
[
  {
    "id": 1,
    "title": "Professional Introduction",
    "slug": "dev-a2-professional-introduction",
    "description": "Learn how to introduce yourself professionally as a junior developer.",
    "communicativeObjective": "The learner can introduce herself as a junior developer using simple and clear English sentences.",
    "englishLevel": "Wanderer",
    "englishLevelCode": "A2",
    "profession": "Dev Path",
    "professionCode": "DEV",
    "learningGoal": "Technical Interviews",
    "learningGoalCode": "TECHNICAL_INTERVIEWS",
    "functionFocus": "Giving personal information",
    "grammarFocus": "Verb to be + present simple",
    "vocabularyFocus": "role, project, backend, frontend, team",
    "mainSkill": "Writing",
    "estimatedMinutes": 30,
    "xpReward": 50,
    "orderIndex": 1,
    "progressStatus": "NOT_STARTED",
    "studyMinutesCompleted": 0,
    "xpEarned": 0,
    "startedAt": null,
    "completedAt": null,
    "lastAccessedAt": null
  }
]
```

---

## GET /api/missions/{missionId}

Returns mission detail, including lessons, vocabulary items, exercises and options.

Requires authentication.

### Response includes

```text
mission information
lessons
vocabularyItems
exercises
exercise options
```

Important: exercise options are returned, but correct answers are not exposed before submission.

---

## POST /api/missions/{missionId}/start

Marks a mission as in progress.

Requires authentication.

### Response example

```json
{
  "missionId": 1,
  "missionTitle": "Professional Introduction",
  "missionSlug": "dev-a2-professional-introduction",
  "status": "IN_PROGRESS",
  "studyMinutesCompleted": 0,
  "xpEarned": 0,
  "startedAt": "2026-07-22T12:00:00",
  "completedAt": null,
  "lastAccessedAt": "2026-07-22T12:00:00"
}
```

---

## POST /api/missions/{missionId}/complete

Marks a mission as completed and updates level progress.

Requires authentication.

### Response example

```json
{
  "missionId": 1,
  "missionTitle": "Professional Introduction",
  "missionSlug": "dev-a2-professional-introduction",
  "status": "COMPLETED",
  "studyMinutesCompleted": 30,
  "xpEarned": 50,
  "startedAt": "2026-07-22T12:00:00",
  "completedAt": "2026-07-22T12:20:00",
  "lastAccessedAt": "2026-07-22T12:20:00"
}
```

---

# Exercises

## POST /api/exercises/{exerciseId}/submit

Submits an exercise answer.

Requires authentication.

### Multiple choice request body

```json
{
  "selectedOptionId": 2
}
```

### Written answer request body

```json
{
  "textAnswer": "I am a junior full stack developer."
}
```

### Response example

```json
{
  "answerId": 1,
  "exerciseId": 1,
  "exerciseType": "MULTIPLE_CHOICE",
  "isCorrect": true,
  "xpEarned": 10,
  "attemptNumber": 1,
  "feedback": "Correct! We use \"am\" with \"I\".",
  "correctAnswer": "I am a junior developer."
}
```

---

# Progress

## GET /api/progress/me

Returns the authenticated user's level progress and mission progress.

Requires authentication.

### Response example

```json
{
  "userId": 1,
  "englishLevel": "Wanderer",
  "englishLevelCode": "A2",
  "targetStudyMinutes": 30000,
  "completedStudyMinutes": 30,
  "progressPercentage": 0.1,
  "totalXp": 60,
  "missionsCompletedCount": 1,
  "missions": [
    {
      "missionId": 1,
      "missionTitle": "Professional Introduction",
      "missionSlug": "dev-a2-professional-introduction",
      "status": "COMPLETED",
      "studyMinutesCompleted": 30,
      "xpEarned": 50,
      "startedAt": "2026-07-22T12:00:00",
      "completedAt": "2026-07-22T12:20:00",
      "lastAccessedAt": "2026-07-22T12:20:00"
    }
  ]
}
```

---

# Error Response Format

Most validation or business errors return this structure:

```json
{
  "timestamp": "2026-07-22T12:00:00",
  "status": 400,
  "error": "Bad request",
  "message": "Mission was not found",
  "path": "/api/missions/99",
  "details": {}
}
```

---

# MVP Frontend Flow

The frontend can use the endpoints in this order:

```text
1. POST /api/auth/register
2. POST /api/auth/login
3. GET /api/users/me
4. GET /api/catalogs/professions
5. GET /api/catalogs/english-levels
6. GET /api/catalogs/interests
7. GET /api/catalogs/learning-goals
8. PUT /api/profiles/me
9. GET /api/diagnostic/questions
10. POST /api/diagnostic/submit
11. GET /api/missions/me
12. GET /api/missions/{missionId}
13. POST /api/missions/{missionId}/start
14. POST /api/exercises/{exerciseId}/submit
15. POST /api/missions/{missionId}/complete
16. GET /api/progress/me
```
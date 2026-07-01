# Chronicles of Elia

Chronicles of Elia is a personal full stack project focused on personalized English learning through a fantasy RPG-inspired mobile experience.

The app adapts each learning path according to the user's English level, profession, interests and real goals. Instead of offering the same general English content to every learner, Chronicles of Elia creates a more meaningful journey based on who the learner is and what they need English for.

## Project Status

This project is currently in the planning, documentation and initial development phase.

Current progress:

- Product concept defined
- MVP scope defined
- Technical stack selected
- Initial repository structure created
- UI/UX prototype planned
- Backend and mobile app development pending

## Concept

Chronicles of Elia turns English learning into a fantasy adventure.

The user is an adventurer.  
Their profession becomes their class.  
Their learning goals become quests.  
Their English skills become abilities to unlock.  
Elia, the guide, accompanies the learner through lessons, missions and challenges.

The main goal is to build a personalized learning experience where each user follows a path based on:

- Current English level
- Profession
- Interests
- Real learning goals
- Weakest skills
- Progress inside the app

## Problem

Most English learning apps offer general content for all users. However, learners often need English for specific contexts.

For example:

- A software developer may need English for technical interviews, documentation, meetings and code reviews.
- A chef may need English for recipes, ingredients, kitchen instructions and customer service.
- An artist may need English to present a portfolio, explain a creative process or communicate with clients.

Chronicles of Elia aims to connect English learning with the learner's real personal and professional context.

## Value Proposition

Chronicles of Elia combines:

- Personalized English learning paths
- Fantasy RPG-inspired experience
- Professional classes such as Dev, Chef and Artist
- Learning missions and skill progression
- AI-assisted practice and feedback
- Pedagogical structure based on real learning goals

## MVP Goal

The first version of the project will focus on proving the core learning flow:

```text
User registration
→ Login
→ Learning profile
→ Initial diagnostic
→ Personalized learning path
→ First mission
→ First lesson
→ First exercise
→ Progress tracking
```

## MVP Features

The first version will include:

- User registration
- User login
- JWT authentication
- Learning profile creation
- Profession/class selection
- English level selection
- Initial diagnostic flow
- Basic personalized learning path
- Dashboard / adventure hub
- Learning path map
- First mission
- First lesson
- Basic exercise
- User progress tracking

## Out of Scope for MVP

The MVP will not include yet:

- Real payment system
- Premium subscriptions
- App Store or Play Store publication
- Advanced speech recognition
- Real-time voice conversations
- Complete AI tutor experience
- Full microservices architecture
- Admin panel
- All professions
- All prototype screens
- Official certification system

## Initial Professional Classes

The MVP will start with three professional classes:

1. Dev
2. Chef
3. Artist

## Initial English Levels

The MVP will start with three English levels:

1. A1
2. A2
3. B1

## Tech Stack

### Mobile App

- React Native
- Expo
- TypeScript
- React Navigation
- Axios
- Expo SecureStore or AsyncStorage

### Backend

- Java
- Spring Boot
- Spring Web
- Spring Security
- JWT
- Spring Data JPA
- Bean Validation

### Database

- PostgreSQL

### API Documentation and Testing

- Swagger / OpenAPI
- Postman

### DevOps

- Docker
- Docker Compose

### Future AI Integration

The project will use an external AI API in future phases to support:

- Writing correction
- Exercise generation
- Personalized feedback
- Role-play scenarios
- Learning path recommendations

## Architecture

The project will start as a modular monolith.

This means the backend will be one Spring Boot application, but internally organized by modules such as:

- Auth
- Users
- Profiles
- Diagnostic
- Learning paths
- Lessons
- Exercises
- Progress
- Gamification
- AI

Future versions may evolve into microservices, separating responsibilities such as:

- Auth service
- User profile service
- Learning service
- Progress service
- AI service

## Repository Structure

```text
chronicles-of-elia/
├── backend/
├── mobile/
├── docs/
│   ├── product-overview.md
│   ├── mvp-scope.md
│   ├── tech-stack.md
│   └── user-flow.md
├── .gitignore
└── README.md
```

## Documentation

Project documentation is organized inside the `docs/` folder:

- `product-overview.md`: product concept, problem and value proposition.
- `mvp-scope.md`: first version scope and limitations.
- `tech-stack.md`: technologies and architecture.
- `user-flow.md`: main app navigation and user journey.

## Planned Backend Modules

```text
backend/
└── chronicles-api/
    └── src/main/java/com/chroniclesofelia/
        ├── auth/
        ├── users/
        ├── profiles/
        ├── catalogs/
        ├── diagnostic/
        ├── learningpath/
        ├── lessons/
        ├── exercises/
        ├── progress/
        ├── gamification/
        ├── ai/
        └── common/
```

## Planned Mobile Structure

```text
mobile/
└── chronicles-app/
    └── src/
        ├── api/
        ├── components/
        ├── navigation/
        ├── screens/
        ├── storage/
        ├── theme/
        └── types/
```

## Main User Flow

1. The user opens the app.
2. The user sees the fantasy onboarding experience.
3. The user creates an account or logs in.
4. The user creates a learning profile.
5. The user selects or confirms their profession/class.
6. The user selects interests and learning goals.
7. The user completes an initial diagnostic.
8. The system assigns a starting level.
9. The system creates a basic learning path.
10. The user enters the dashboard.
11. The user opens the first mission.
12. The user completes the first lesson.
13. The user answers the first exercise.
14. The system saves progress.
15. The user sees updated XP, progress and next mission.

## Planned API Endpoints

Initial planned endpoints:

```text
POST /api/auth/register
POST /api/auth/login
GET /api/users/me
PUT /api/profiles/me
GET /api/catalogs/professions
GET /api/catalogs/english-levels
GET /api/diagnostic/questions
POST /api/diagnostic/submit
POST /api/learning-paths/generate
GET /api/learning-paths/me
GET /api/lessons/{lessonId}
POST /api/exercises/{exerciseId}/submit
GET /api/progress/me
```

Future AI endpoints:

```text
POST /api/ai/chat
POST /api/ai/check-writing
POST /api/ai/generate-exercise
POST /api/ai/recommend-next-mission
```

## Initial Database Entities

Planned initial entities:

- users
- roles
- user_profiles
- professions
- english_levels
- learning_goals
- diagnostic_questions
- diagnostic_answers
- diagnostic_results
- learning_paths
- learning_modules
- lessons
- exercises
- exercise_options
- user_exercise_answers
- user_progress

Future entities:

- badges
- user_badges
- streaks
- ai_interactions
- inventory_items
- subscriptions
- admin_logs

## Learning and Gamification Model

Chronicles of Elia uses RPG elements to support learning:

| RPG Element | Learning Equivalent |
|---|---|
| Adventurer | User |
| Class | Profession or learning profile |
| Quest | Learning mission |
| Skill tree | English skills |
| XP | Learning progress |
| Badge | Achievement |
| Boss battle | Integrative challenge |
| Guide | Elia |

## Current Development Roadmap

### Phase 1: Repository and Planning

- Create repository structure
- Add README
- Add initial documentation
- Define MVP scope

### Phase 2: Backend Base

- Create Spring Boot project
- Configure PostgreSQL
- Add Docker Compose
- Create first entities
- Add Swagger
- Create health check endpoint

### Phase 3: Authentication and Profiles

- Implement user registration
- Implement login
- Add JWT authentication
- Create learning profile flow

### Phase 4: Diagnostic and Learning Path

- Create diagnostic questions
- Save diagnostic answers
- Generate basic learning path
- Show user learning path

### Phase 5: Mobile App

- Create Expo project with TypeScript
- Add navigation
- Build public screens
- Build authenticated screens
- Connect mobile app with backend

### Phase 6: Lessons, Exercises and Progress

- Add lessons
- Add exercises
- Save user answers
- Track progress

### Phase 7: AI Integration

- Add writing correction
- Add exercise generation
- Add AI interaction logging
- Add personalized recommendations

### Phase 8: Gamification and Demo

- Add XP
- Add badges
- Add skill tree
- Prepare demo
- Update portfolio documentation

## How to Run the Project

This section will be updated once the backend and mobile app are created.

### Backend

Pending.

### Mobile App

Pending.

### Database

Pending.

## Author

María Fernanda Rodríguez Trinidad

Full Stack Jr. Developer with a background in English language teaching, focused on building user-centered, educational and scalable digital products.
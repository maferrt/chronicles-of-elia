# MVP Scope

## MVP Goal

The first version of Chronicles of Elia will focus on proving the core learning flow:

User registration → learning profile → diagnostic → personalized path → first mission → first lesson → first exercise → progress tracking.

## In Scope

The MVP will include:

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

## Out of Scope

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
- All 100+ prototype screens
- Official certification system

## Initial Professions

The MVP will start with three professional classes:

1. Dev
2. Chef
3. Artist

## Initial English Levels

The MVP will start with three levels:

1. A1
2. A2
3. B1

## Pedagogical Scope

The MVP does not aim to provide full mastery of a CEFR-inspired level.

Instead, it will demonstrate the structure of a personalized learning path based on:

- CEFR-inspired language level
- Professional path
- Learning goals
- Contextualized missions
- Basic progress tracking

For internal progress tracking, each CEFR-inspired level will be treated as a long-term learning path with an estimated target of 500 study hours.

```text
1 level = 500 study hours
500 hours = 30,000 study minutes
```

This means that one mission, lesson or exercise does not represent full level mastery.

The MVP will store A1, A2 and B1 as available starting levels, but the first content seed will focus mainly on:

```text
A2 + DEV Path
```

Secondary paths such as CHEF and ARTIST will be included as proof of personalization, but the first complete learning flow will focus on DEV.

## First Technical Objective

Build the backend base with:

- Java
- Spring Boot
- PostgreSQL
- REST API
- JWT authentication
- Basic user profile
- Initial catalogs
- Pedagogical model documentation
- Initial MVP content plan
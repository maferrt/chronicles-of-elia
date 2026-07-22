# Pedagogical Model

## Overview

Chronicles of Elia uses a CEFR-inspired pedagogical model to create personalized English learning paths.

The app does not teach random vocabulary or isolated grammar points. Instead, it connects language learning with real communicative situations based on the learner's English level, professional path, interests and goals.

The core pedagogical idea is:

```text
CEFR-inspired level = language difficulty
Professional path = learning context
Mission = communicative objective
Progress = accumulated study, practice and performance
```

## CEFR-Inspired Progression

Chronicles of Elia uses the Common European Framework of Reference for Languages as a reference for organizing language progression.

For the MVP, the initial levels are:

- A1
- A2
- B1

Each level defines the expected language complexity.

For example:

- A1 focuses on basic personal information, simple phrases, greetings, numbers, basic routines and simple questions.
- A2 focuses on routines, descriptions, requests, suggestions, obligations, past experiences and everyday interactions.
- B1 focuses on opinions, experiences, events, explanations, plans, feelings and more independent communication.

The app will not claim to officially certify CEFR levels. Instead, it uses CEFR-inspired descriptors to structure content and learning progression.

## CEFR Level Mastery and Study Hours

Chronicles of Elia treats each CEFR-inspired level as a long-term learning path.

For internal progress tracking, each level will be represented with an estimated target of 500 study hours.

```text
1 level = 500 study hours
500 hours = 30,000 study minutes
```

This means that completing one mission does not mean the learner has mastered a level.

A mission represents a small communicative objective inside a much larger learning journey.

For example:

```text
Completing "Professional Introduction" does not mean the learner has mastered A2.
It means the learner has completed one A2 communicative objective in a professional context.
```

The app should avoid messages such as:

```text
You mastered A2.
```

Unless the user has completed the expected long-term progress for that level.

Better messages are:

```text
Your A2 journey has begun.
You completed your first A2 mission.
You are building your A2 foundation.
You completed a communicative objective in your A2 path.
```

## Professional Contextualization

The learner's profession does not change the English level.

Instead, the profession changes the context, examples, vocabulary and final tasks.

For example, an A2 learner may study present simple and routines.

A general textbook example could be:

```text
I usually go to school.
She works in a restaurant.
```

In Chronicles of Elia, the same A2 structure is adapted to the user's professional path.

For a Dev Path learner:

```text
I usually write documentation.
She works with frontend components.
I have to fix a bug.
```

For a Chef Path learner:

```text
I usually prepare the ingredients.
She works in the kitchen.
I have to clean the station.
```

For an Artist Path learner:

```text
I usually sketch ideas.
She works with digital tools.
I have to present my portfolio.
```

The language difficulty remains appropriate to the learner's level, but the context becomes more meaningful.

## Learning Path Structure

The learning experience follows this structure:

```text
User
↓
Learning Profile
↓
CEFR-Inspired Level
↓
Professional Path
↓
Learning Goals
↓
Missions
↓
Lessons
↓
Practice
↓
Feedback
↓
Progress
```

## Learning Profile

The learning profile contains the information needed to personalize the user's experience.

It includes:

- English level
- Professional path
- Interests
- Learning goals
- Optional bio or learning intention

Example:

```text
User: Mafer
Level: A2
Path: Dev
Goals: Technical Interviews, Remote Work
Interests: Technology, Books, Music
```

This profile helps the system decide what kind of missions, examples, vocabulary and tasks should be shown.

## Mission-Based Learning

The main learning unit in Chronicles of Elia is the mission.

A mission is not just a topic. A mission is a communicative objective.

A communicative objective describes what the learner should be able to do after completing the mission.

Example:

```text
Mission:
Professional Introduction

Communicative Objective:
The learner can introduce herself as a junior developer using simple and clear English sentences.
```

Each mission belongs to:

- A CEFR-inspired level
- A professional path
- One or more learning goals
- One or more language functions
- One or more skills
- A specific communicative context

## Mission Structure

Each mission should contain:

1. Mission title
2. Communicative objective
3. CEFR-inspired level
4. Professional path
5. Learning goal
6. Function focus
7. Grammar focus
8. Vocabulary focus
9. Skills practiced
10. Estimated study time
11. Lesson content
12. Examples
13. Guided practice
14. Final task
15. Feedback
16. XP or progress reward

## Mission Flow

A mission follows this learning flow:

```text
Mission Detail
↓
Short Lesson
↓
Vocabulary
↓
Examples
↓
Guided Practice
↓
Final Task
↓
Feedback
↓
Progress Update
```

## Lesson Structure

A lesson should be short, clear and focused.

Each lesson should include:

- One main explanation
- Simple examples
- Professional context
- A short tip from Elia
- A connection to the final task

Example:

```text
Lesson:
Introducing Yourself Professionally

Explanation:
When you introduce yourself professionally, you can mention your name, your role, your main tools and your goal.

Examples:
My name is Mafer.
I am a junior full stack developer.
I work with Java, Spring Boot and TypeScript.
I am interested in backend development.

Elia's Tip:
Start simple. A clear introduction is better than a long and confusing one.
```

## Exercise Types

For the MVP, the app will start with simple exercise types.

Initial exercise types:

- Multiple choice
- Fill in the blank
- Sentence ordering
- Vocabulary matching
- Short written answer

Future exercise types:

- AI writing correction
- Speaking practice
- Listening practice
- Role-play scenarios
- Interview simulation
- Pronunciation feedback

## Feedback Model

Feedback should be clear, kind and useful.

The app should explain why an answer is correct or incorrect.

Example:

```text
Correct!
We use "am" with "I".

Incorrect.
Remember: with "he", "she" and "it", we usually add -s in the present simple.
```

Feedback from Elia should be encouraging but not overly poetic.

Good example:

```text
Elia's tip:
Start with your name, your role and one technology you use.
```

Avoid overly vague feedback:

```text
The forest reveals your linguistic destiny.
```

The fantasy style should support the learning experience, not make the explanation confusing.

## Skills

Chronicles of Elia tracks English skills as learning abilities.

Initial skills:

- Vocabulary
- Grammar
- Reading
- Writing

Future skills:

- Listening
- Speaking
- Pronunciation
- Interaction
- Professional communication

## Progress Model

Progress should be measured through different signals:

- Study minutes completed
- Missions completed
- Lessons completed
- Exercises answered
- Correct answers
- Final tasks completed
- XP earned
- Skill-specific progress
- CEFR-level journey progress

The app should distinguish between:

```text
Mission progress
Skill progress
Level progress
Overall user progress
```

Example:

```text
Mission completed: Professional Introduction
Skill progress: Vocabulary + Grammar
Study time: 30 minutes
XP earned: 50 XP
A2 progress: 30 / 30,000 minutes
```

## XP and Gamification

XP is a gamification element, not a direct proof of language mastery.

XP helps motivate the user and represent activity inside the app.

Example:

```text
Complete a lesson: +10 XP
Complete vocabulary practice: +10 XP
Complete an exercise: +15 XP
Complete final task: +25 XP
Complete full mission: +50 XP
```

XP should support motivation, but level mastery should still depend on long-term study, practice and performance.

## MVP Pedagogical Scope

The MVP does not aim to provide full mastery of A1, A2 or B1.

The MVP aims to prove that the system can:

- Register and authenticate users
- Create a learning profile
- Store level, profession, interests and goals
- Display catalog options
- Create contextualized learning content
- Show an initial personalized mission
- Save basic progress
- Represent the beginning of a long-term CEFR-inspired path

The first strong content focus will be:

```text
Level: A2
Path: DEV
Goal: Technical Interviews / Remote Work
```

Secondary proof-of-concept paths:

```text
A2 + CHEF
A2 + ARTIST
```

These secondary paths will demonstrate personalization, but the first complete content flow will focus on A2 + DEV.

## Core Principle

Chronicles of Elia should always follow this principle:

```text
Do not simplify the pedagogy for the fantasy.
Use the fantasy to make the pedagogy more engaging.
```

The app should be magical in experience, but serious in learning structure.
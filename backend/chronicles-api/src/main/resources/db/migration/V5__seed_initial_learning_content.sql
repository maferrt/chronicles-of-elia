INSERT INTO missions (
    title,
    slug,
    description,
    communicative_objective,
    english_level_id,
    profession_id,
    learning_goal_id,
    function_focus,
    grammar_focus,
    vocabulary_focus,
    main_skill,
    estimated_minutes,
    xp_reward,
    order_index
)
VALUES
(
    'Professional Introduction',
    'dev-a2-professional-introduction',
    'Learn how to introduce yourself professionally as a junior developer.',
    'The learner can introduce herself as a junior developer using simple and clear English sentences.',
    (SELECT id FROM english_levels WHERE code = 'A2'),
    (SELECT id FROM professions WHERE code = 'DEV'),
    (SELECT id FROM learning_goals WHERE code = 'TECHNICAL_INTERVIEWS'),
    'Giving personal information',
    'Verb to be + present simple',
    'role, project, backend, frontend, team',
    'Writing',
    30,
    50,
    1
),
(
    'Daily Work Routine',
    'dev-a2-daily-work-routine',
    'Practice describing simple work and study routines as a developer.',
    'The learner can describe simple work habits and routines as a developer.',
    (SELECT id FROM english_levels WHERE code = 'A2'),
    (SELECT id FROM professions WHERE code = 'DEV'),
    (SELECT id FROM learning_goals WHERE code = 'REMOTE_WORK'),
    'Describing habits and routines',
    'Present simple + adverbs of frequency',
    'tasks, meetings, documentation, review',
    'Vocabulary / Grammar',
    35,
    50,
    2
),
(
    'Asking for Help',
    'dev-a2-asking-for-help',
    'Practice asking for help politely in a development or learning context.',
    'The learner can ask for help politely in a work or learning context.',
    (SELECT id FROM english_levels WHERE code = 'A2'),
    (SELECT id FROM professions WHERE code = 'DEV'),
    (SELECT id FROM learning_goals WHERE code = 'REMOTE_WORK'),
    'Making requests',
    'Can / Could / polite requests',
    'issue, bug, help, explain, review',
    'Interaction',
    30,
    50,
    3
),
(
    'Talking About a Project',
    'dev-a2-talking-about-a-project',
    'Practice describing a simple project using basic past simple sentences.',
    'The learner can describe a simple project using basic past simple sentences.',
    (SELECT id FROM english_levels WHERE code = 'A2'),
    (SELECT id FROM professions WHERE code = 'DEV'),
    (SELECT id FROM learning_goals WHERE code = 'PORTFOLIO_PRESENTATION'),
    'Describing past experience',
    'Past simple',
    'built, created, implemented, fixed, deployed',
    'Writing',
    40,
    60,
    4
),
(
    'Explaining a Simple Bug',
    'dev-a2-explaining-a-simple-bug',
    'Practice explaining a simple technical problem using clear English.',
    'The learner can explain a simple technical problem using clear and basic English.',
    (SELECT id FROM english_levels WHERE code = 'A2'),
    (SELECT id FROM professions WHERE code = 'DEV'),
    (SELECT id FROM learning_goals WHERE code = 'REMOTE_WORK'),
    'Describing problems',
    'Present simple + basic cause and effect',
    'error, bug, server, request, database',
    'Vocabulary / Writing',
    45,
    60,
    5
),
(
    'Introducing a Recipe',
    'chef-a2-introducing-a-recipe',
    'Practice describing a simple recipe using basic steps and ingredients.',
    'The learner can describe a simple recipe using basic steps and ingredients.',
    (SELECT id FROM english_levels WHERE code = 'A2'),
    (SELECT id FROM professions WHERE code = 'CHEF'),
    (SELECT id FROM learning_goals WHERE code = 'CUSTOMER_SERVICE'),
    'Giving instructions',
    'Imperatives + sequence markers',
    'ingredients, recipe, cut, mix, boil, serve',
    'Writing',
    30,
    50,
    1
),
(
    'Presenting Your Portfolio',
    'artist-a2-presenting-your-portfolio',
    'Practice presenting a simple creative portfolio using clear English.',
    'The learner can present a simple creative portfolio using clear English.',
    (SELECT id FROM english_levels WHERE code = 'A2'),
    (SELECT id FROM professions WHERE code = 'ARTIST'),
    (SELECT id FROM learning_goals WHERE code = 'PORTFOLIO_PRESENTATION'),
    'Describing creative work',
    'Verb to be + present simple + adjectives',
    'portfolio, design, illustration, client, style',
    'Writing',
    30,
    50,
    1
);

INSERT INTO lessons (
    mission_id,
    title,
    content,
    elia_tip,
    order_index,
    estimated_minutes
)
VALUES
(
    (SELECT id FROM missions WHERE slug = 'dev-a2-professional-introduction'),
    'Introducing Yourself Professionally',
    $$When you introduce yourself professionally, you can mention your name, your role, the technologies or tools you use, what you are learning and your professional goal.

A clear introduction can be short.

Examples:

My name is Mafer.
I am a junior full stack developer.
I work with Java, Spring Boot and TypeScript.
I am interested in backend development.
I want to improve my English for technical interviews.$$,
    'Start simple. A clear introduction is better than a long and confusing one.',
    1,
    10
),
(
    (SELECT id FROM missions WHERE slug = 'dev-a2-daily-work-routine'),
    'Talking About Your Routine',
    $$When you describe your routine, you can use the present simple.

Use words like usually, sometimes and every day to explain how often you do something.

Examples:

I usually check my tasks in the morning.
I sometimes join a daily standup.
I write documentation after testing.
I review my code before pushing changes.$$,
    'Use simple routine sentences first. You can add more details later.',
    1,
    10
),
(
    (SELECT id FROM missions WHERE slug = 'dev-a2-asking-for-help'),
    'Asking for Help Politely',
    $$When you need help, you can use polite questions with can or could.

Examples:

Can you help me with this issue?
Could you explain this error?
Can you review my code?
I do not understand this bug.$$,
    'Asking for help is part of professional communication. Keep your question clear.',
    1,
    10
),
(
    (SELECT id FROM missions WHERE slug = 'dev-a2-talking-about-a-project'),
    'Describing a Project',
    $$When you talk about a project you finished, you can use the past simple.

Examples:

I built a personal portfolio.
I created a login page.
I implemented a contact form.
I fixed a problem with the API.
I deployed the project on Vercel.$$,
    'Mention what you created, what you used and what problem you solved.',
    1,
    10
),
(
    (SELECT id FROM missions WHERE slug = 'dev-a2-explaining-a-simple-bug'),
    'Explaining a Simple Bug',
    $$When you explain a bug, say what is not working and where the problem appears.

Examples:

There is an error in the login form.
The button does not work.
The server returns a 500 error.
The request fails because the token is missing.$$,
    'A good bug explanation is clear, short and specific.',
    1,
    10
),
(
    (SELECT id FROM missions WHERE slug = 'chef-a2-introducing-a-recipe'),
    'Explaining Recipe Steps',
    $$When you explain a recipe, you can use simple instructions.

Use sequence words like first, then, after that and finally.

Examples:

First, cut the vegetables.
Then, add salt and pepper.
Boil the soup for 20 minutes.
Finally, serve the dish.$$,
    'Recipe instructions are easier to understand when the steps are short.',
    1,
    10
),
(
    (SELECT id FROM missions WHERE slug = 'artist-a2-presenting-your-portfolio'),
    'Presenting Creative Work',
    $$When you present your portfolio, you can describe who you are and what your work includes.

Examples:

I am a digital artist.
My portfolio includes illustrations and logos.
I use warm colors in this project.
This design is for a small business.$$,
    'Describe your work with simple adjectives and clear examples.',
    1,
    10
);

INSERT INTO vocabulary_items (
    mission_id,
    term,
    definition,
    example_sentence,
    order_index
)
VALUES
(
    (SELECT id FROM missions WHERE slug = 'dev-a2-professional-introduction'),
    'role',
    'The position or function someone has in a team or project.',
    'My role is junior developer.',
    1
),
(
    (SELECT id FROM missions WHERE slug = 'dev-a2-professional-introduction'),
    'junior developer',
    'A developer at an early professional level.',
    'I am a junior developer.',
    2
),
(
    (SELECT id FROM missions WHERE slug = 'dev-a2-professional-introduction'),
    'backend',
    'The server-side part of an application.',
    'I am interested in backend development.',
    3
),
(
    (SELECT id FROM missions WHERE slug = 'dev-a2-professional-introduction'),
    'frontend',
    'The user-facing part of an application.',
    'I work with frontend components.',
    4
),
(
    (SELECT id FROM missions WHERE slug = 'dev-a2-professional-introduction'),
    'full stack',
    'Frontend and backend development.',
    'I am a junior full stack developer.',
    5
),
(
    (SELECT id FROM missions WHERE slug = 'dev-a2-professional-introduction'),
    'project',
    'A planned piece of work.',
    'I created a personal project.',
    6
),
(
    (SELECT id FROM missions WHERE slug = 'dev-a2-professional-introduction'),
    'team',
    'A group of people working together.',
    'I work with a development team.',
    7
),
(
    (SELECT id FROM missions WHERE slug = 'dev-a2-professional-introduction'),
    'interested in',
    'Used to talk about something you want to learn or do.',
    'I am interested in backend development.',
    8
);

WITH inserted_exercise AS (
    INSERT INTO exercises (
        mission_id,
        lesson_id,
        exercise_type,
        question,
        instruction,
        correct_answer,
        feedback,
        xp_reward,
        order_index
    )
    VALUES (
        (SELECT id FROM missions WHERE slug = 'dev-a2-professional-introduction'),
        (SELECT id FROM lessons WHERE mission_id = (SELECT id FROM missions WHERE slug = 'dev-a2-professional-introduction') AND order_index = 1),
        'MULTIPLE_CHOICE',
        'Choose the correct sentence.',
        'Select the sentence that uses the verb to be correctly.',
        'I am a junior developer.',
        'Correct! We use "am" with "I".',
        10,
        1
    )
    RETURNING id
)
INSERT INTO exercise_options (
    exercise_id,
    option_text,
    is_correct,
    order_index
)
SELECT id, 'I are a junior developer.', FALSE, 1 FROM inserted_exercise
UNION ALL
SELECT id, 'I am a junior developer.', TRUE, 2 FROM inserted_exercise
UNION ALL
SELECT id, 'I be a junior developer.', FALSE, 3 FROM inserted_exercise
UNION ALL
SELECT id, 'I is a junior developer.', FALSE, 4 FROM inserted_exercise;

WITH inserted_exercise AS (
    INSERT INTO exercises (
        mission_id,
        lesson_id,
        exercise_type,
        question,
        instruction,
        correct_answer,
        feedback,
        xp_reward,
        order_index
    )
    VALUES (
        (SELECT id FROM missions WHERE slug = 'dev-a2-professional-introduction'),
        (SELECT id FROM lessons WHERE mission_id = (SELECT id FROM missions WHERE slug = 'dev-a2-professional-introduction') AND order_index = 1),
        'FILL_IN_THE_BLANK',
        'I ____ with Java and Spring Boot.',
        'Complete the sentence with the correct verb.',
        'work',
        'Correct! With "I", we use the base form: "I work".',
        10,
        2
    )
    RETURNING id
)
INSERT INTO exercise_options (
    exercise_id,
    option_text,
    is_correct,
    order_index
)
SELECT id, 'work', TRUE, 1 FROM inserted_exercise
UNION ALL
SELECT id, 'works', FALSE, 2 FROM inserted_exercise
UNION ALL
SELECT id, 'working', FALSE, 3 FROM inserted_exercise
UNION ALL
SELECT id, 'worked', FALSE, 4 FROM inserted_exercise;

INSERT INTO exercises (
    mission_id,
    lesson_id,
    exercise_type,
    question,
    instruction,
    correct_answer,
    feedback,
    xp_reward,
    order_index
)
VALUES
(
    (SELECT id FROM missions WHERE slug = 'dev-a2-professional-introduction'),
    (SELECT id FROM lessons WHERE mission_id = (SELECT id FROM missions WHERE slug = 'dev-a2-professional-introduction') AND order_index = 1),
    'SENTENCE_ORDERING',
    'am / I / full stack / junior / developer / a',
    'Put the words in the correct order.',
    'I am a junior full stack developer.',
    'Good job! This sentence clearly describes your professional role.',
    15,
    3
),
(
    (SELECT id FROM missions WHERE slug = 'dev-a2-professional-introduction'),
    (SELECT id FROM lessons WHERE mission_id = (SELECT id FROM missions WHERE slug = 'dev-a2-professional-introduction') AND order_index = 1),
    'SHORT_WRITTEN_ANSWER',
    'Write a short professional introduction.',
    'Include your name, your role, one technology and one professional goal.',
    'My name is Mafer. I am a junior full stack developer. I work with Java and TypeScript. I am interested in backend development.',
    'Great! A strong introduction includes your name, role, tools and goal.',
    25,
    4
);
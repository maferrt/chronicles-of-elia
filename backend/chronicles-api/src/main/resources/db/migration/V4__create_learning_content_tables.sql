CREATE TABLE missions (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(120) NOT NULL,
    slug VARCHAR(140) NOT NULL UNIQUE,
    description VARCHAR(500),
    communicative_objective VARCHAR(500) NOT NULL,

    english_level_id BIGINT NOT NULL,
    profession_id BIGINT NOT NULL,
    learning_goal_id BIGINT,

    function_focus VARCHAR(150),
    grammar_focus VARCHAR(150),
    vocabulary_focus VARCHAR(150),
    main_skill VARCHAR(80),

    estimated_minutes INTEGER NOT NULL,
    xp_reward INTEGER NOT NULL,
    order_index INTEGER NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,

    CONSTRAINT fk_missions_english_levels
        FOREIGN KEY (english_level_id)
        REFERENCES english_levels(id),

    CONSTRAINT fk_missions_professions
        FOREIGN KEY (profession_id)
        REFERENCES professions(id),

    CONSTRAINT fk_missions_learning_goals
        FOREIGN KEY (learning_goal_id)
        REFERENCES learning_goals(id)
);

CREATE TABLE lessons (
    id BIGSERIAL PRIMARY KEY,
    mission_id BIGINT NOT NULL,

    title VARCHAR(120) NOT NULL,
    content TEXT NOT NULL,
    elia_tip VARCHAR(500),
    order_index INTEGER NOT NULL,
    estimated_minutes INTEGER NOT NULL DEFAULT 5,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,

    CONSTRAINT fk_lessons_missions
        FOREIGN KEY (mission_id)
        REFERENCES missions(id)
        ON DELETE CASCADE
);

CREATE TABLE vocabulary_items (
    id BIGSERIAL PRIMARY KEY,
    mission_id BIGINT NOT NULL,

    term VARCHAR(120) NOT NULL,
    definition VARCHAR(500) NOT NULL,
    example_sentence VARCHAR(500),
    order_index INTEGER NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,

    CONSTRAINT fk_vocabulary_items_missions
        FOREIGN KEY (mission_id)
        REFERENCES missions(id)
        ON DELETE CASCADE
);

CREATE TABLE exercises (
    id BIGSERIAL PRIMARY KEY,
    mission_id BIGINT NOT NULL,
    lesson_id BIGINT,

    exercise_type VARCHAR(50) NOT NULL,
    question TEXT NOT NULL,
    instruction VARCHAR(500),
    correct_answer TEXT,
    feedback TEXT,
    xp_reward INTEGER NOT NULL DEFAULT 10,
    order_index INTEGER NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,

    CONSTRAINT fk_exercises_missions
        FOREIGN KEY (mission_id)
        REFERENCES missions(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_exercises_lessons
        FOREIGN KEY (lesson_id)
        REFERENCES lessons(id)
        ON DELETE SET NULL
);

CREATE TABLE exercise_options (
    id BIGSERIAL PRIMARY KEY,
    exercise_id BIGINT NOT NULL,

    option_text TEXT NOT NULL,
    is_correct BOOLEAN NOT NULL DEFAULT FALSE,
    order_index INTEGER NOT NULL,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,

    CONSTRAINT fk_exercise_options_exercises
        FOREIGN KEY (exercise_id)
        REFERENCES exercises(id)
        ON DELETE CASCADE
);
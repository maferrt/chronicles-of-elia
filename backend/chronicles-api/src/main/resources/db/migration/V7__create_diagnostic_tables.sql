CREATE TABLE diagnostic_questions (
    id BIGSERIAL PRIMARY KEY,

    question_text TEXT NOT NULL,
    instruction VARCHAR(500),
    skill_focus VARCHAR(80) NOT NULL,
    english_level_id BIGINT NOT NULL,

    order_index INTEGER NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,

    CONSTRAINT fk_diagnostic_questions_english_levels
        FOREIGN KEY (english_level_id)
        REFERENCES english_levels(id)
);

CREATE TABLE diagnostic_options (
    id BIGSERIAL PRIMARY KEY,

    question_id BIGINT NOT NULL,
    option_text TEXT NOT NULL,
    is_correct BOOLEAN NOT NULL DEFAULT FALSE,
    points INTEGER NOT NULL DEFAULT 0,
    order_index INTEGER NOT NULL,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,

    CONSTRAINT fk_diagnostic_options_questions
        FOREIGN KEY (question_id)
        REFERENCES diagnostic_questions(id)
        ON DELETE CASCADE,

    CONSTRAINT chk_diagnostic_options_points
        CHECK (points >= 0)
);

CREATE TABLE diagnostic_results (
    id BIGSERIAL PRIMARY KEY,

    user_id BIGINT NOT NULL,
    suggested_english_level_id BIGINT NOT NULL,

    total_score INTEGER NOT NULL,
    max_score INTEGER NOT NULL,
    result_label VARCHAR(80) NOT NULL,
    result_description VARCHAR(500),

    taken_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,

    CONSTRAINT fk_diagnostic_results_users
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_diagnostic_results_english_levels
        FOREIGN KEY (suggested_english_level_id)
        REFERENCES english_levels(id),

    CONSTRAINT chk_diagnostic_results_total_score
        CHECK (total_score >= 0),

    CONSTRAINT chk_diagnostic_results_max_score
        CHECK (max_score > 0)
);

CREATE TABLE diagnostic_user_answers (
    id BIGSERIAL PRIMARY KEY,

    diagnostic_result_id BIGINT NOT NULL,
    question_id BIGINT NOT NULL,
    selected_option_id BIGINT NOT NULL,

    is_correct BOOLEAN NOT NULL,
    points_earned INTEGER NOT NULL DEFAULT 0,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,

    CONSTRAINT fk_diagnostic_user_answers_results
        FOREIGN KEY (diagnostic_result_id)
        REFERENCES diagnostic_results(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_diagnostic_user_answers_questions
        FOREIGN KEY (question_id)
        REFERENCES diagnostic_questions(id),

    CONSTRAINT fk_diagnostic_user_answers_options
        FOREIGN KEY (selected_option_id)
        REFERENCES diagnostic_options(id),

    CONSTRAINT chk_diagnostic_user_answers_points
        CHECK (points_earned >= 0)
);

CREATE INDEX idx_diagnostic_questions_level_id
    ON diagnostic_questions(english_level_id);

CREATE INDEX idx_diagnostic_options_question_id
    ON diagnostic_options(question_id);

CREATE INDEX idx_diagnostic_results_user_id
    ON diagnostic_results(user_id);

CREATE INDEX idx_diagnostic_user_answers_result_id
    ON diagnostic_user_answers(diagnostic_result_id);

CREATE INDEX idx_diagnostic_user_answers_question_id
    ON diagnostic_user_answers(question_id);

INSERT INTO diagnostic_questions (
    question_text,
    instruction,
    skill_focus,
    english_level_id,
    order_index
)
VALUES
(
    'Choose the correct sentence.',
    'Select the sentence that is grammatically correct.',
    'Grammar',
    (SELECT id FROM english_levels WHERE code = 'A1'),
    1
),
(
    'Complete the sentence: My name ____ Elia.',
    'Choose the correct form of the verb to be.',
    'Grammar',
    (SELECT id FROM english_levels WHERE code = 'A1'),
    2
),
(
    'Choose the correct meaning of: book.',
    'Select the best meaning.',
    'Vocabulary',
    (SELECT id FROM english_levels WHERE code = 'A1'),
    3
),
(
    'Choose the correct sentence about routine.',
    'Select the sentence that uses present simple correctly.',
    'Grammar',
    (SELECT id FROM english_levels WHERE code = 'A2'),
    4
),
(
    'Complete the sentence: I usually ____ my tasks in the morning.',
    'Choose the correct verb.',
    'Grammar',
    (SELECT id FROM english_levels WHERE code = 'A2'),
    5
),
(
    'Choose the best polite request.',
    'Select the most appropriate request in a work context.',
    'Communication',
    (SELECT id FROM english_levels WHERE code = 'A2'),
    6
),
(
    'Choose the best sentence in past simple.',
    'Select the sentence that correctly describes a completed action.',
    'Grammar',
    (SELECT id FROM english_levels WHERE code = 'B1'),
    7
),
(
    'Choose the best way to explain a problem.',
    'Select the clearest sentence for a work context.',
    'Communication',
    (SELECT id FROM english_levels WHERE code = 'B1'),
    8
),
(
    'Read the sentence and choose the best conclusion: The server returns a 500 error, so the user cannot log in.',
    'Choose the sentence that best explains the situation.',
    'Reading',
    (SELECT id FROM english_levels WHERE code = 'B1'),
    9
);

INSERT INTO diagnostic_options (
    question_id,
    option_text,
    is_correct,
    points,
    order_index
)
VALUES
-- Question 1
(
    (SELECT id FROM diagnostic_questions WHERE order_index = 1),
    'I am a student.',
    TRUE,
    1,
    1
),
(
    (SELECT id FROM diagnostic_questions WHERE order_index = 1),
    'I are a student.',
    FALSE,
    0,
    2
),
(
    (SELECT id FROM diagnostic_questions WHERE order_index = 1),
    'I is a student.',
    FALSE,
    0,
    3
),
(
    (SELECT id FROM diagnostic_questions WHERE order_index = 1),
    'I be a student.',
    FALSE,
    0,
    4
),

-- Question 2
(
    (SELECT id FROM diagnostic_questions WHERE order_index = 2),
    'am',
    FALSE,
    0,
    1
),
(
    (SELECT id FROM diagnostic_questions WHERE order_index = 2),
    'are',
    FALSE,
    0,
    2
),
(
    (SELECT id FROM diagnostic_questions WHERE order_index = 2),
    'is',
    TRUE,
    1,
    3
),
(
    (SELECT id FROM diagnostic_questions WHERE order_index = 2),
    'be',
    FALSE,
    0,
    4
),

-- Question 3
(
    (SELECT id FROM diagnostic_questions WHERE order_index = 3),
    'A thing you read.',
    TRUE,
    1,
    1
),
(
    (SELECT id FROM diagnostic_questions WHERE order_index = 3),
    'A place where you sleep.',
    FALSE,
    0,
    2
),
(
    (SELECT id FROM diagnostic_questions WHERE order_index = 3),
    'A person who cooks.',
    FALSE,
    0,
    3
),
(
    (SELECT id FROM diagnostic_questions WHERE order_index = 3),
    'A type of weather.',
    FALSE,
    0,
    4
),

-- Question 4
(
    (SELECT id FROM diagnostic_questions WHERE order_index = 4),
    'She work every day.',
    FALSE,
    0,
    1
),
(
    (SELECT id FROM diagnostic_questions WHERE order_index = 4),
    'She works every day.',
    TRUE,
    1,
    2
),
(
    (SELECT id FROM diagnostic_questions WHERE order_index = 4),
    'She working every day.',
    FALSE,
    0,
    3
),
(
    (SELECT id FROM diagnostic_questions WHERE order_index = 4),
    'She worked every day now.',
    FALSE,
    0,
    4
),

-- Question 5
(
    (SELECT id FROM diagnostic_questions WHERE order_index = 5),
    'check',
    TRUE,
    1,
    1
),
(
    (SELECT id FROM diagnostic_questions WHERE order_index = 5),
    'checks',
    FALSE,
    0,
    2
),
(
    (SELECT id FROM diagnostic_questions WHERE order_index = 5),
    'checking',
    FALSE,
    0,
    3
),
(
    (SELECT id FROM diagnostic_questions WHERE order_index = 5),
    'checked',
    FALSE,
    0,
    4
),

-- Question 6
(
    (SELECT id FROM diagnostic_questions WHERE order_index = 6),
    'Help me now.',
    FALSE,
    0,
    1
),
(
    (SELECT id FROM diagnostic_questions WHERE order_index = 6),
    'You help me.',
    FALSE,
    0,
    2
),
(
    (SELECT id FROM diagnostic_questions WHERE order_index = 6),
    'Could you help me with this issue?',
    TRUE,
    1,
    3
),
(
    (SELECT id FROM diagnostic_questions WHERE order_index = 6),
    'I need. You explain.',
    FALSE,
    0,
    4
),

-- Question 7
(
    (SELECT id FROM diagnostic_questions WHERE order_index = 7),
    'I build a portfolio yesterday.',
    FALSE,
    0,
    1
),
(
    (SELECT id FROM diagnostic_questions WHERE order_index = 7),
    'I built a portfolio yesterday.',
    TRUE,
    1,
    2
),
(
    (SELECT id FROM diagnostic_questions WHERE order_index = 7),
    'I building a portfolio yesterday.',
    FALSE,
    0,
    3
),
(
    (SELECT id FROM diagnostic_questions WHERE order_index = 7),
    'I builds a portfolio yesterday.',
    FALSE,
    0,
    4
),

-- Question 8
(
    (SELECT id FROM diagnostic_questions WHERE order_index = 8),
    'The login form does not work because the token is missing.',
    TRUE,
    1,
    1
),
(
    (SELECT id FROM diagnostic_questions WHERE order_index = 8),
    'Login bad token thing.',
    FALSE,
    0,
    2
),
(
    (SELECT id FROM diagnostic_questions WHERE order_index = 8),
    'The form are not working because missing.',
    FALSE,
    0,
    3
),
(
    (SELECT id FROM diagnostic_questions WHERE order_index = 8),
    'User cannot because server maybe.',
    FALSE,
    0,
    4
),

-- Question 9
(
    (SELECT id FROM diagnostic_questions WHERE order_index = 9),
    'The login is successful.',
    FALSE,
    0,
    1
),
(
    (SELECT id FROM diagnostic_questions WHERE order_index = 9),
    'There is a server problem that blocks login.',
    TRUE,
    1,
    2
),
(
    (SELECT id FROM diagnostic_questions WHERE order_index = 9),
    'The user changed the password.',
    FALSE,
    0,
    3
),
(
    (SELECT id FROM diagnostic_questions WHERE order_index = 9),
    'The app is working correctly.',
    FALSE,
    0,
    4
);
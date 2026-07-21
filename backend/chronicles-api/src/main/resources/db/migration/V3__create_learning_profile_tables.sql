CREATE TABLE professions (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(80) NOT NULL UNIQUE,
    code VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(255),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE TABLE english_levels (
    id BIGSERIAL PRIMARY KEY,
    code VARCHAR(10) NOT NULL UNIQUE,
    name VARCHAR(80) NOT NULL,
    description VARCHAR(255),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE TABLE interests (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(80) NOT NULL UNIQUE,
    code VARCHAR(50) NOT NULL UNIQUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE TABLE learning_goals (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    code VARCHAR(60) NOT NULL UNIQUE,
    description VARCHAR(255),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE TABLE user_profiles (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL UNIQUE,
    profession_id BIGINT NOT NULL,
    english_level_id BIGINT NOT NULL,
    bio VARCHAR(255),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,

    CONSTRAINT fk_user_profiles_users
        FOREIGN KEY (user_id)
        REFERENCES users(id),

    CONSTRAINT fk_user_profiles_professions
        FOREIGN KEY (profession_id)
        REFERENCES professions(id),

    CONSTRAINT fk_user_profiles_english_levels
        FOREIGN KEY (english_level_id)
        REFERENCES english_levels(id)
);

CREATE TABLE user_profile_interests (
    user_profile_id BIGINT NOT NULL,
    interest_id BIGINT NOT NULL,

    PRIMARY KEY (user_profile_id, interest_id),

    CONSTRAINT fk_user_profile_interests_profiles
        FOREIGN KEY (user_profile_id)
        REFERENCES user_profiles(id),

    CONSTRAINT fk_user_profile_interests_interests
        FOREIGN KEY (interest_id)
        REFERENCES interests(id)
);

CREATE TABLE user_profile_goals (
    user_profile_id BIGINT NOT NULL,
    learning_goal_id BIGINT NOT NULL,

    PRIMARY KEY (user_profile_id, learning_goal_id),

    CONSTRAINT fk_user_profile_goals_profiles
        FOREIGN KEY (user_profile_id)
        REFERENCES user_profiles(id),

    CONSTRAINT fk_user_profile_goals_learning_goals
        FOREIGN KEY (learning_goal_id)
        REFERENCES learning_goals(id)
);

INSERT INTO professions (name, code, description)
VALUES
    ('Dev Path', 'DEV', 'English for projects, interviews, documentation and teamwork.'),
    ('Chef Path', 'CHEF', 'English for recipes, ingredients, kitchen tasks and customers.'),
    ('Artist Path', 'ARTIST', 'English for portfolios, creative process and client conversations.');

INSERT INTO english_levels (code, name, description)
VALUES
    ('A1', 'Beginner', 'Basic English level for simple words, phrases and introductions.'),
    ('A2', 'Wanderer', 'Elementary English level for simple conversations and everyday contexts.'),
    ('B1', 'Explorer', 'Intermediate English level for clearer communication and work situations.');

INSERT INTO interests (name, code)
VALUES
    ('Books', 'BOOKS'),
    ('Music', 'MUSIC'),
    ('Technology', 'TECHNOLOGY'),
    ('Cooking', 'COOKING'),
    ('Travel', 'TRAVEL'),
    ('Art', 'ART'),
    ('Games', 'GAMES'),
    ('Movies', 'MOVIES'),
    ('Nature', 'NATURE');

INSERT INTO learning_goals (name, code, description)
VALUES
    ('Technical Interviews', 'TECHNICAL_INTERVIEWS', 'Practice English for technical interviews and professional introductions.'),
    ('Remote Work', 'REMOTE_WORK', 'Practice English for remote communication, meetings and teamwork.'),
    ('Travel', 'TRAVEL', 'Practice English for trips, directions, reservations and basic conversations.'),
    ('Customer Service', 'CUSTOMER_SERVICE', 'Practice English for helping customers and solving common situations.'),
    ('Portfolio Presentation', 'PORTFOLIO_PRESENTATION', 'Practice English to present projects, portfolios and creative work.'),
    ('Daily Conversation', 'DAILY_CONVERSATION', 'Practice English for everyday conversations.');
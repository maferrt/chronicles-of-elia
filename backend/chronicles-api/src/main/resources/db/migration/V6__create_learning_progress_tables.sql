CREATE TABLE user_level_progress (
    id BIGSERIAL PRIMARY KEY,

    user_id BIGINT NOT NULL,
    english_level_id BIGINT NOT NULL,

    target_study_minutes INTEGER NOT NULL DEFAULT 30000,
    completed_study_minutes INTEGER NOT NULL DEFAULT 0,
    total_xp INTEGER NOT NULL DEFAULT 0,
    missions_completed_count INTEGER NOT NULL DEFAULT 0,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,

    CONSTRAINT fk_user_level_progress_users
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_user_level_progress_english_levels
        FOREIGN KEY (english_level_id)
        REFERENCES english_levels(id),

    CONSTRAINT uq_user_level_progress_user_level
        UNIQUE (user_id, english_level_id),

    CONSTRAINT chk_user_level_progress_target_minutes
        CHECK (target_study_minutes > 0),

    CONSTRAINT chk_user_level_progress_completed_minutes
        CHECK (completed_study_minutes >= 0),

    CONSTRAINT chk_user_level_progress_total_xp
        CHECK (total_xp >= 0),

    CONSTRAINT chk_user_level_progress_missions_count
        CHECK (missions_completed_count >= 0)
);

CREATE TABLE user_mission_progress (
    id BIGSERIAL PRIMARY KEY,

    user_id BIGINT NOT NULL,
    mission_id BIGINT NOT NULL,

    status VARCHAR(30) NOT NULL DEFAULT 'NOT_STARTED',
    study_minutes_completed INTEGER NOT NULL DEFAULT 0,
    xp_earned INTEGER NOT NULL DEFAULT 0,

    started_at TIMESTAMP,
    completed_at TIMESTAMP,
    last_accessed_at TIMESTAMP,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,

    CONSTRAINT fk_user_mission_progress_users
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_user_mission_progress_missions
        FOREIGN KEY (mission_id)
        REFERENCES missions(id)
        ON DELETE CASCADE,

    CONSTRAINT uq_user_mission_progress_user_mission
        UNIQUE (user_id, mission_id),

    CONSTRAINT chk_user_mission_progress_status
        CHECK (status IN ('NOT_STARTED', 'IN_PROGRESS', 'COMPLETED')),

    CONSTRAINT chk_user_mission_progress_minutes
        CHECK (study_minutes_completed >= 0),

    CONSTRAINT chk_user_mission_progress_xp
        CHECK (xp_earned >= 0)
);

CREATE TABLE user_exercise_answers (
    id BIGSERIAL PRIMARY KEY,

    user_id BIGINT NOT NULL,
    exercise_id BIGINT NOT NULL,
    selected_option_id BIGINT,
    text_answer TEXT,

    is_correct BOOLEAN,
    xp_earned INTEGER NOT NULL DEFAULT 0,
    attempt_number INTEGER NOT NULL DEFAULT 1,

    answered_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,

    CONSTRAINT fk_user_exercise_answers_users
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_user_exercise_answers_exercises
        FOREIGN KEY (exercise_id)
        REFERENCES exercises(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_user_exercise_answers_selected_options
        FOREIGN KEY (selected_option_id)
        REFERENCES exercise_options(id)
        ON DELETE SET NULL,

    CONSTRAINT chk_user_exercise_answers_xp
        CHECK (xp_earned >= 0),

    CONSTRAINT chk_user_exercise_answers_attempt
        CHECK (attempt_number > 0)
);

CREATE INDEX idx_user_level_progress_user_id
    ON user_level_progress(user_id);

CREATE INDEX idx_user_mission_progress_user_id
    ON user_mission_progress(user_id);

CREATE INDEX idx_user_mission_progress_mission_id
    ON user_mission_progress(mission_id);

CREATE INDEX idx_user_exercise_answers_user_id
    ON user_exercise_answers(user_id);

CREATE INDEX idx_user_exercise_answers_exercise_id
    ON user_exercise_answers(exercise_id);
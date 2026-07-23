export type LearningProfile = {
  id: number;
  profession: string;
  professionCode: string;
  englishLevel: string;
  englishLevelCode: string;
  interests: string[];
  learningGoals: string[];
  bio: string | null;
};

export type UpsertLearningProfileRequest = {
  professionCode: string;
  englishLevelCode: string;
  interestCodes: string[];
  learningGoalCodes: string[];
  bio?: string | null;
};
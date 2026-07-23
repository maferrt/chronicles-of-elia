export type LearningProfile = {
    id: number;
    profession: string;
    professionCode: string;
    englishLevel: string;
    englishLevelCode: string;
    interests: string[];
    learningGoals: string[];
    bio: string | null;
}
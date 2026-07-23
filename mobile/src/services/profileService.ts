import { apiClient } from "./apiClient";
import { LearningProfile } from "../types/profile.types";

export async function getMyLearningProfile(): Promise<LearningProfile> {
    return apiClient<LearningProfile>("/api/profiles/me", {
        method: "GET",
        requiresAuth: true,
    });
}
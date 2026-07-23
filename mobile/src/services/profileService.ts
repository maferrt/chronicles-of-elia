import { apiClient } from "./apiClient";
import {
  LearningProfile,
  UpsertLearningProfileRequest,
} from "../types/profile.types";

export async function getMyLearningProfile(): Promise<LearningProfile> {
  return apiClient<LearningProfile>("/api/profiles/me", {
    method: "GET",
    requiresAuth: true,
  });
}

export async function saveMyLearningProfile(
  request: UpsertLearningProfileRequest
): Promise<LearningProfile> {
  return apiClient<LearningProfile>("/api/profiles/me", {
    method: "PUT",
    requiresAuth: true,
    body: request,
  });
}
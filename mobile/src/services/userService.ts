import { apiClient } from "./apiClient";
import { CurrentUser } from "../types/user.types";

export async function getCurrentUser(): Promise<CurrentUser> {
    return apiClient<CurrentUser>("/api/users/me", {
        method: "GET",
        requiresAuth: true,
    });
}
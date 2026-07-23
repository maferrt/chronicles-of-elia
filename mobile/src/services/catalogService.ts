import { apiClient } from "./apiClient";
import { CatalogItem } from "../types/catalog.types";

export async function getProfessions(): Promise<CatalogItem[]> {
  return apiClient<CatalogItem[]>("/api/catalogs/professions", {
    method: "GET",
    requiresAuth: true,
  });
}

export async function getEnglishLevels(): Promise<CatalogItem[]> {
  return apiClient<CatalogItem[]>("/api/catalogs/english-levels", {
    method: "GET",
    requiresAuth: true,
  });
}

export async function getInterests(): Promise<CatalogItem[]> {
  return apiClient<CatalogItem[]>("/api/catalogs/interests", {
    method: "GET",
    requiresAuth: true,
  });
}

export async function getLearningGoals(): Promise<CatalogItem[]> {
  return apiClient<CatalogItem[]>("/api/catalogs/learning-goals", {
    method: "GET",
    requiresAuth: true,
  });
}
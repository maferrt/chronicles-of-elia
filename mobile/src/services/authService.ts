import AsyncStorage from "@react-native-async-storage/async-storage";

import { apiClient } from "./apiClient";
import {
  LoginRequest,
  LoginResponse,
  RegisterRequest,
  RegisterResponse,
} from "../types/auth.types";

export async function registerUser(
  request: RegisterRequest
): Promise<RegisterResponse> {
  return apiClient<RegisterResponse>("/api/auth/register", {
    method: "POST",
    body: request,
  });
}

export async function loginUser(
  request: LoginRequest
): Promise<LoginResponse> {
  const response = await apiClient<LoginResponse>("/api/auth/login", {
    method: "POST",
    body: request,
  });

  await AsyncStorage.setItem("authToken", response.token);
  await AsyncStorage.setItem("currentUser", JSON.stringify(response));

  return response;
}

export async function logoutUser() {
  await AsyncStorage.removeItem("authToken");
  await AsyncStorage.removeItem("currentUser");
}
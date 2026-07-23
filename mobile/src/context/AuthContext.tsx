import AsyncStorage from "@react-native-async-storage/async-storage";
import {
  createContext,
  ReactNode,
  useContext,
  useEffect,
  useMemo,
  useState,
} from "react";

import { loginUser, logoutUser, registerUser } from "../services/authService";
import { getCurrentUser } from "../services/userService";
import { LoginRequest, RegisterRequest } from "../types/auth.types";
import { CurrentUser } from "../types/user.types";

type AuthContextValue = {
  currentUser: CurrentUser | null;
  isAuthLoading: boolean;
  hasSeenOnboarding: boolean;
  login: (request: LoginRequest) => Promise<void>;
  register: (request: RegisterRequest) => Promise<void>;
  logout: () => Promise<void>;
  completeOnboarding: () => Promise<void>;
  refreshCurrentUser: () => Promise<void>;
};

const AuthContext = createContext<AuthContextValue | undefined>(undefined);

const AUTH_TOKEN_KEY = "authToken";
const ONBOARDING_KEY = "hasSeenOnboarding";

type AuthProviderProps = {
  children: ReactNode;
};

export function AuthProvider({ children }: AuthProviderProps) {
  const [currentUser, setCurrentUser] = useState<CurrentUser | null>(null);
  const [isAuthLoading, setIsAuthLoading] = useState(true);
  const [hasSeenOnboarding, setHasSeenOnboarding] = useState(false);

  useEffect(() => {
    bootstrapAuth();
  }, []);

  async function bootstrapAuth() {
    try {
      setIsAuthLoading(true);

      const storedOnboarding = await AsyncStorage.getItem(ONBOARDING_KEY);
      setHasSeenOnboarding(storedOnboarding === "true");

      const token = await AsyncStorage.getItem(AUTH_TOKEN_KEY);

      if (!token) {
        setCurrentUser(null);
        return;
      }

      const user = await getCurrentUser();
      setCurrentUser(user);
    } catch (error) {
      await logoutUser();
      setCurrentUser(null);
    } finally {
      setIsAuthLoading(false);
    }
  }

  async function login(request: LoginRequest) {
    await loginUser(request);

    const user = await getCurrentUser();
    setCurrentUser(user);
  }

  async function register(request: RegisterRequest) {
    await registerUser(request);

    await loginUser({
      email: request.email,
      password: request.password,
    });

    const user = await getCurrentUser();
    setCurrentUser(user);
  }

  async function logout() {
    await logoutUser();
    setCurrentUser(null);
  }

  async function completeOnboarding() {
    await AsyncStorage.setItem(ONBOARDING_KEY, "true");
    setHasSeenOnboarding(true);
  }

  async function refreshCurrentUser() {
    const user = await getCurrentUser();
    setCurrentUser(user);
  }

  const value = useMemo(
    () => ({
      currentUser,
      isAuthLoading,
      hasSeenOnboarding,
      login,
      register,
      logout,
      completeOnboarding,
      refreshCurrentUser,
    }),
    [currentUser, isAuthLoading, hasSeenOnboarding]
  );

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
}

export function useAuth() {
  const context = useContext(AuthContext);

  if (!context) {
    throw new Error("useAuth must be used inside AuthProvider");
  }

  return context;
}
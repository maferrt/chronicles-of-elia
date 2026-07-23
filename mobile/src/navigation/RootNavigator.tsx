import { ActivityIndicator, StyleSheet, View } from "react-native";
import { createNativeStackNavigator } from "@react-navigation/native-stack";

import { RootStackParamList } from "./navigation.types";
import { SplashScreen } from "../screens/public/SplashScreen";
import { LoginScreen } from "../screens/public/LoginScreen";
import { RegisterScreen } from "../screens/public/RegisterScreen";
import { AppTabsNavigator } from "./AppTabsNavigator";
import { colors } from "../constants/theme";
import { useAuth } from "../context/AuthContext";
import { ProfileSetupScreen } from "../screens/app/ProfileSetupScreen";

const Stack = createNativeStackNavigator<RootStackParamList>();

export function RootNavigator() {
  const { currentUser, isAuthLoading, hasSeenOnboarding } = useAuth();

  if (isAuthLoading) {
    return (
      <View style={styles.loadingContainer}>
        <ActivityIndicator size="large" color={colors.parchment} />
      </View>
    );
  }

  const isAuthenticated = Boolean(currentUser);

  return (
    <Stack.Navigator
      key={isAuthenticated ? "authenticated" : "unauthenticated"}
      initialRouteName={
        isAuthenticated ? "MainApp" : hasSeenOnboarding ? "Login" : "Splash"
      }
      screenOptions={{
        headerShown: false,
      }}
    >
      {isAuthenticated ? (
        <>
          <Stack.Screen name="MainApp" component={AppTabsNavigator} />
          <Stack.Screen name="ProfileSetup" component={ProfileSetupScreen} />
        </>
      ) : (
        <>
          <Stack.Screen name="Splash" component={SplashScreen} />
          <Stack.Screen name="Login" component={LoginScreen} />
          <Stack.Screen name="Register" component={RegisterScreen} />
        </>
      )}
    </Stack.Navigator>
  );
}

const styles = StyleSheet.create({
  loadingContainer: {
    flex: 1,
    backgroundColor: colors.forestDark,
    alignItems: "center",
    justifyContent: "center",
  },
});
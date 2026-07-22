import { createNativeStackNavigator } from "@react-navigation/native-stack";

import { RootStackParamList } from "./navigation.types";
import { SplashScreen } from "../screens/public/SplashScreen";
import { LoginScreen } from "../screens/public/LoginScreen";
import { RegisterScreen } from "../screens/public/RegisterScreen";
import { AppTabsNavigator } from "./AppTabsNavigator";

const Stack = createNativeStackNavigator<RootStackParamList>();

export function RootNavigator() {
  return (
    <Stack.Navigator
      initialRouteName="Splash"
      screenOptions={{
        headerShown: false,
      }}
    >
      <Stack.Screen name="Splash" component={SplashScreen} />
      <Stack.Screen name="Login" component={LoginScreen} />
      <Stack.Screen name="Register" component={RegisterScreen} />
      <Stack.Screen name="MainApp" component={AppTabsNavigator} />
    </Stack.Navigator>
  );
}
import { NavigatorScreenParams } from "@react-navigation/native";

export type AppTabParamList = {
  Home: undefined;
  Map: undefined;
  Practice: undefined;
  Progress: undefined;
  Profile: undefined;
};

export type RootStackParamList = {
  Splash: undefined;
  Login: undefined;
  Register: undefined;
  MainApp: NavigatorScreenParams<AppTabParamList> | undefined;
};
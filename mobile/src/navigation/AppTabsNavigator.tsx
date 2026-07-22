import { createBottomTabNavigator } from "@react-navigation/bottom-tabs";
import { MaterialCommunityIcons } from "@expo/vector-icons";

import { AppTabParamList } from "./navigation.types";
import { HomeScreen } from "../screens/app/HomeScreen";
import { MissionMapScreen } from "../screens/app/MissionMapScreen";
import { PracticeScreen } from "../screens/app/PracticeScreen";
import { ProgressScreen } from "../screens/app/ProgressScreen";
import { ProfileScreen } from "../screens/app/ProfileScreen";
import { colors, fonts } from "../constants/theme";

const Tab = createBottomTabNavigator<AppTabParamList>();

type IconName = keyof typeof MaterialCommunityIcons.glyphMap;

const tabIcons: Record<keyof AppTabParamList, IconName> = {
  Home: "home-variant",
  Map: "map-marker-path",
  Practice: "book-open-page-variant",
  Progress: "chart-line",
  Profile: "account-circle",
};

export function AppTabsNavigator() {
  return (
    <Tab.Navigator
      initialRouteName="Home"
      screenOptions={({ route }) => ({
        headerShown: false,
        tabBarShowLabel: true,
        tabBarActiveTintColor: colors.parchment,
        tabBarInactiveTintColor: "rgba(247, 244, 213, 0.55)",
        tabBarLabelStyle: {
          fontFamily: fonts.body,
          fontSize: 11,
          marginTop: 2,
        },
        tabBarStyle: {
          position: "absolute",
          left: 18,
          right: 18,
          bottom: 16,
          height: 74,
          paddingTop: 8,
          paddingBottom: 10,
          borderRadius: 26,
          backgroundColor: "rgba(6, 29, 22, 0.94)",
          borderTopWidth: 0,
          borderWidth: 1,
          borderColor: colors.borderGold,
          elevation: 8,
          shadowColor: colors.forestDeep,
          shadowOpacity: 0.4,
          shadowRadius: 12,
          shadowOffset: {
            width: 0,
            height: 6,
          },
        },
        tabBarIcon: ({ color, size }) => (
          <MaterialCommunityIcons
            name={tabIcons[route.name]}
            color={color}
            size={size}
          />
        ),
      })}
    >
      <Tab.Screen name="Home" component={HomeScreen} />
      <Tab.Screen name="Map" component={MissionMapScreen} />
      <Tab.Screen name="Practice" component={PracticeScreen} />
      <Tab.Screen name="Progress" component={ProgressScreen} />
      <Tab.Screen name="Profile" component={ProfileScreen} />
    </Tab.Navigator>
  );
}
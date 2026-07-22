import "react-native-gesture-handler";

import { StatusBar } from "expo-status-bar";
import { NavigationContainer, DefaultTheme } from "@react-navigation/native";
import { SafeAreaProvider } from "react-native-safe-area-context";

import {
  useFonts as useCinzelFonts,
  CinzelDecorative_400Regular,
  CinzelDecorative_700Bold,
} from "@expo-google-fonts/cinzel-decorative";

import {
  useFonts as useIMFellFonts,
  IMFellEnglish_400Regular_Italic,
} from "@expo-google-fonts/im-fell-english";

import {
  useFonts as useLatoFonts,
  Lato_400Regular,
  Lato_700Bold,
} from "@expo-google-fonts/lato";

import { RootNavigator } from "./src/navigation/RootNavigator";
import { colors } from "./src/constants/theme";

const appTheme = {
  ...DefaultTheme,
  colors: {
    ...DefaultTheme.colors,
    background: colors.forestDark,
  },
};

export default function App() {
  const [cinzelLoaded] = useCinzelFonts({
    CinzelDecorative_400Regular,
    CinzelDecorative_700Bold,
  });

  const [imFellLoaded] = useIMFellFonts({
    IMFellEnglish_400Regular_Italic,
  });

  const [latoLoaded] = useLatoFonts({
    Lato_400Regular,
    Lato_700Bold,
  });

  const fontsLoaded = cinzelLoaded && imFellLoaded && latoLoaded;

  if (!fontsLoaded) {
    return null;
  }

  return (
    <SafeAreaProvider>
      <NavigationContainer theme={appTheme}>
        <StatusBar style="light" />
        <RootNavigator />
      </NavigationContainer>
    </SafeAreaProvider>
  );
}
import { StyleSheet, Text, View } from "react-native";
import { NativeStackScreenProps } from "@react-navigation/native-stack";

import { AppScreen, EliaGuideCard, PrimaryButton } from "../../components";
import { colors, spacing, typography } from "../../constants/theme";
import { RootStackParamList } from "../../navigation/navigation.types";

type Props = NativeStackScreenProps<RootStackParamList, "Splash">;

export function SplashScreen({ navigation }: Props) {
  return (
    <AppScreen centered>
      <View style={styles.hero}>
        <Text style={styles.title}>Chronicles{"\n"}of Elia</Text>

        <Text style={styles.subtitle}>
          Learn English through personalized quests.
        </Text>
      </View>

      <EliaGuideCard
        title="Welcome, traveler"
        message="I'm Elia. I'll guide you through lessons, missions and little victories in English."
      />

      <View style={styles.actions}>
        <PrimaryButton
          title="Begin your journey"
          onPress={() => navigation.navigate("Login")}
        />

        <PrimaryButton
          title="Create an account"
          variant="ghost"
          onPress={() => navigation.navigate("Register")}
        />
      </View>
    </AppScreen>
  );
}

const styles = StyleSheet.create({
  hero: {
    alignItems: "center",
    marginBottom: spacing.xl,
  },
  title: {
    ...typography.screenTitle,
    color: colors.parchment,
    textAlign: "center",
    marginBottom: spacing.md,
  },
  subtitle: {
    ...typography.subtitle,
    color: colors.parchment,
    textAlign: "center",
    opacity: 0.9,
  },
  actions: {
    width: "100%",
    gap: spacing.md,
    marginTop: spacing.xl,
  },
});
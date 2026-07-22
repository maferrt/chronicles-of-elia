import { Image, StyleSheet, Text, View } from "react-native";
import { NativeStackScreenProps } from "@react-navigation/native-stack";

import { AppScreen, PrimaryButton, ParchmentCard } from "../../components";
import { eliaAssets } from "../../constants/eliaAssets";
import { colors, spacing, typography } from "../../constants/theme";
import { RootStackParamList } from "../../navigation/navigation.types";

type Props = NativeStackScreenProps<RootStackParamList, "Splash">;

export function SplashScreen({ navigation }: Props) {
  return (
    <AppScreen scroll contentStyle={styles.container}>
      <View style={styles.header}>
        <Text style={styles.title}>Chronicles{"\n"}of Elia</Text>

        <Text style={styles.subtitle}>
          Learn English through personalized quests.
        </Text>
      </View>

      <View style={styles.eliaContainer}>
        <View style={styles.glow} />

        <Image
          source={eliaAssets.wave}
          style={styles.eliaImage}
          resizeMode="contain"
        />
      </View>

      <ParchmentCard style={styles.messageCard}>
        <Text style={styles.messageTitle}>Welcome, traveler</Text>

        <Text style={styles.messageText}>
          I'm Elia. I'll guide you through lessons, missions and little
          victories in English.
        </Text>
      </ParchmentCard>

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
  container: {
    justifyContent: "center",
    paddingTop: spacing.xl,
    paddingBottom: spacing.xl,
  },
  header: {
    alignItems: "center",
    marginBottom: spacing.lg,
  },
  title: {
    ...typography.screenTitle,
    color: colors.parchment,
    textAlign: "center",
    textTransform: "uppercase",
    marginBottom: spacing.md,
  },
  subtitle: {
    ...typography.subtitle,
    color: colors.parchment,
    textAlign: "center",
    opacity: 0.9,
  },
  eliaContainer: {
    width: "100%",
    height: 360,
    alignItems: "center",
    justifyContent: "center",
    marginBottom: spacing.lg,
  },
  glow: {
    position: "absolute",
    width: 260,
    height: 260,
    borderRadius: 130,
    backgroundColor: colors.glowGold,
    opacity: 0.9,
  },
  eliaImage: {
    width: "100%",
    height: "100%",
  },
  messageCard: {
    marginBottom: spacing.xl,
  },
  messageTitle: {
    ...typography.label,
    color: colors.forestDark,
    marginBottom: spacing.sm,
  },
  messageText: {
    ...typography.subtitle,
    color: colors.textDark,
  },
  actions: {
    width: "100%",
    gap: spacing.md,
  },
});
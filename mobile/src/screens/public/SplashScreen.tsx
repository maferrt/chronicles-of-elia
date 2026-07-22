import { Pressable, StyleSheet, Text, View } from "react-native";

import { colors, radius, spacing, typography } from "../../constants/theme";
import { NativeStackScreenProps } from "@react-navigation/native-stack";
import { RootStackParamList } from "../../navigation/navigation.types";

type Props = NativeStackScreenProps<RootStackParamList, "Splash">;

export function SplashScreen({ navigation }: Props) {
  return (
    <View style={styles.container}>
      <View style={styles.glow} />

      <Text style={styles.logo}>✦</Text>

      <Text style={styles.title}>Chronicles of Elia</Text>

      <Text style={styles.subtitle}>
        Learn English through personalized quests, guided by Elia.
      </Text>

      <Pressable
        style={styles.primaryButton}
        onPress={() => navigation.navigate("Login")}
      >
        <Text style={styles.primaryButtonText}>Start your journey</Text>
      </Pressable>

      <Pressable onPress={() => navigation.navigate("Register")}>
        <Text style={styles.secondaryText}>Create an account</Text>
      </Pressable>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: colors.forestDark,
    alignItems: "center",
    justifyContent: "center",
    padding: spacing.xl,
  },
  glow: {
    position: "absolute",
    width: 260,
    height: 260,
    borderRadius: 130,
    backgroundColor: colors.deepTeal,
    opacity: 0.28,
  },
  logo: {
    fontSize: 64,
    color: colors.dustyRose,
    marginBottom: spacing.md,
  },
  title: {
    ...typography.title,
    color: colors.parchment,
    textAlign: "center",
    marginBottom: spacing.md,
  },
  subtitle: {
    ...typography.subtitle,
    color: colors.parchment,
    opacity: 0.85,
    textAlign: "center",
    lineHeight: 26,
    marginBottom: spacing.xl,
  },
  primaryButton: {
    backgroundColor: colors.dustyRose,
    paddingVertical: spacing.md,
    paddingHorizontal: spacing.xl,
    borderRadius: radius.full,
    marginBottom: spacing.lg,
  },
  primaryButtonText: {
    color: colors.forestDark,
    fontSize: 16,
    fontWeight: "700",
  },
  secondaryText: {
    color: colors.parchment,
    fontSize: 15,
    textDecorationLine: "underline",
  },
});
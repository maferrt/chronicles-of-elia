import { Pressable, StyleSheet, Text, TextInput, View } from "react-native";

import { colors, radius, spacing, typography } from "../../constants/theme";
import { NativeStackScreenProps } from "@react-navigation/native-stack";
import { RootStackParamList } from "../../navigation/navigation.types";

type Props = NativeStackScreenProps<RootStackParamList, "Login">;

export function LoginScreen({ navigation }: Props) {
  return (
    <View style={styles.container}>
      <Text style={styles.title}>Welcome back</Text>

      <Text style={styles.subtitle}>
        Elia is waiting to continue your learning path.
      </Text>

      <View style={styles.form}>
        <TextInput
          placeholder="Email"
          placeholderTextColor={colors.mutedText}
          style={styles.input}
          autoCapitalize="none"
          keyboardType="email-address"
        />

        <TextInput
          placeholder="Password"
          placeholderTextColor={colors.mutedText}
          style={styles.input}
          secureTextEntry
        />

        <Pressable
          style={styles.primaryButton}
          onPress={() => navigation.navigate("Home")}
        >
          <Text style={styles.primaryButtonText}>Log in</Text>
        </Pressable>
      </View>

      <Pressable onPress={() => navigation.navigate("Register")}>
        <Text style={styles.linkText}>I do not have an account yet</Text>
      </Pressable>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: colors.forestDark,
    padding: spacing.xl,
    justifyContent: "center",
  },
  title: {
    ...typography.title,
    color: colors.parchment,
    marginBottom: spacing.sm,
  },
  subtitle: {
    ...typography.subtitle,
    color: colors.parchment,
    opacity: 0.85,
    lineHeight: 26,
    marginBottom: spacing.xl,
  },
  form: {
    gap: spacing.md,
    marginBottom: spacing.lg,
  },
  input: {
    backgroundColor: colors.card,
    borderRadius: radius.md,
    padding: spacing.md,
    fontSize: 16,
    color: colors.textDark,
  },
  primaryButton: {
    backgroundColor: colors.dustyRose,
    padding: spacing.md,
    borderRadius: radius.full,
    alignItems: "center",
    marginTop: spacing.sm,
  },
  primaryButtonText: {
    color: colors.forestDark,
    fontWeight: "700",
    fontSize: 16,
  },
  linkText: {
    color: colors.parchment,
    textAlign: "center",
    textDecorationLine: "underline",
  },
});
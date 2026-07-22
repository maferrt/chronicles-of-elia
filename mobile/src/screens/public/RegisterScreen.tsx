import { Pressable, StyleSheet, Text, TextInput, View } from "react-native";

import { colors, radius, spacing, typography } from "../../constants/theme";
import { NativeStackScreenProps } from "@react-navigation/native-stack";
import { RootStackParamList } from "../../navigation/navigation.types";

type Props = NativeStackScreenProps<RootStackParamList, "Register">;

export function RegisterScreen({ navigation }: Props) {
  return (
    <View style={styles.container}>
      <Text style={styles.title}>Begin your chronicle</Text>

      <Text style={styles.subtitle}>
        Create your account and let Elia prepare your first path.
      </Text>

      <View style={styles.form}>
        <TextInput
          placeholder="Full name"
          placeholderTextColor={colors.mutedText}
          style={styles.input}
        />

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
          <Text style={styles.primaryButtonText}>Create account</Text>
        </Pressable>
      </View>

      <Pressable onPress={() => navigation.navigate("Login")}>
        <Text style={styles.linkText}>I already have an account</Text>
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
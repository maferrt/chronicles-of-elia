import { useState } from "react";
import { StyleSheet, Text, View } from "react-native";
import { NativeStackScreenProps } from "@react-navigation/native-stack";

import {
  AppScreen,
  ParchmentCard,
  PrimaryButton,
  ScreenHeader,
  TextField,
} from "../../components";
import { colors, spacing, typography } from "../../constants/theme";
import { RootStackParamList } from "../../navigation/navigation.types";
import { useAuth } from "../../context/AuthContext";

type Props = NativeStackScreenProps<RootStackParamList, "Login">;

export function LoginScreen({ navigation }: Props) {
  const { login } = useAuth();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const [formError, setFormError] = useState<string | null>(null);
  const [isLoading, setIsLoading] = useState(false);

  async function handleLogin() {
    if (!email.trim() || !password.trim()) {
      setFormError("Please enter your email and password.");
      return;
    }

    try {
      setIsLoading(true);
      setFormError(null);

      await login({
        email: email.trim(),
        password,
      });
    } catch (error) {
      const message =
        error instanceof Error
          ? error.message
          : "Unable to log in. Please try again.";

      setFormError(message);
    } finally {
      setIsLoading(false);
    }
  }

  return (
    <AppScreen scroll contentStyle={styles.container}>
      <ScreenHeader
        title="Welcome back"
        subtitle="Continue your learning path with Elia."
      />

      <ParchmentCard style={styles.card}>
        <View style={styles.form}>
          <TextField
            label="Email"
            value={email}
            onChangeText={setEmail}
            autoCapitalize="none"
            keyboardType="email-address"
          />

          <TextField
            label="Password"
            value={password}
            onChangeText={setPassword}
            secureTextEntry
          />

          {formError ? (
            <View style={styles.errorBox}>
              <Text style={styles.errorText}>{formError}</Text>
            </View>
          ) : null}

          <PrimaryButton
            title={isLoading ? "Logging in..." : "Log in"}
            disabled={isLoading}
            onPress={handleLogin}
          />
        </View>
      </ParchmentCard>

      <PrimaryButton
        title="I do not have an account yet"
        variant="ghost"
        onPress={() => navigation.navigate("Register")}
      />
    </AppScreen>
  );
}

const styles = StyleSheet.create({
  container: {
    justifyContent: "center",
  },
  card: {
    marginBottom: spacing.xl,
  },
  form: {
    gap: spacing.md,
  },
  errorBox: {
    backgroundColor: "rgba(211, 150, 140, 0.22)",
    borderWidth: 1,
    borderColor: colors.dustyRose,
    borderRadius: 16,
    padding: spacing.md,
  },
  errorText: {
    ...typography.body,
    color: colors.textDark,
  },
});
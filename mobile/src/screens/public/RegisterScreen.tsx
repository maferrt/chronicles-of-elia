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

type Props = NativeStackScreenProps<RootStackParamList, "Register">;

export function RegisterScreen({ navigation }: Props) {
  const { register } = useAuth();
  const [fullName, setFullName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const [formError, setFormError] = useState<string | null>(null);
  const [isLoading, setIsLoading] = useState(false);

  async function handleRegister() {
    if (!fullName.trim() || !email.trim() || !password.trim()) {
      setFormError("Please complete all fields.");
      return;
    }

    try {
      setIsLoading(true);
      setFormError(null);

      await register({
        fullName: fullName.trim(),
        email: email.trim(),
        password,
      });
    } catch (error) {
      const message =
        error instanceof Error
          ? error.message
          : "Unable to create account. Please try again.";

      setFormError(message);
    } finally {
      setIsLoading(false);
    }
  }

  return (
    <AppScreen scroll contentStyle={styles.container}>
      <ScreenHeader
        title="Begin your chronicle"
        subtitle="Create your account and prepare your first path."
      />

      <ParchmentCard style={styles.card}>
        <View style={styles.form}>
          <TextField
            label="Full name"
            value={fullName}
            onChangeText={setFullName}
          />

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
            title={isLoading ? "Creating account..." : "Create account"}
            disabled={isLoading}
            onPress={handleRegister}
          />
        </View>
      </ParchmentCard>

      <PrimaryButton
        title="I already have an account"
        variant="ghost"
        onPress={() => navigation.navigate("Login")}
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
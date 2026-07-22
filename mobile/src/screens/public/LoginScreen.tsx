import { StyleSheet, Text, View } from "react-native";
import { NativeStackScreenProps } from "@react-navigation/native-stack";

import {
  AppScreen,
  ParchmentCard,
  PrimaryButton,
  TextField,
} from "../../components";
import { colors, spacing, typography } from "../../constants/theme";
import { RootStackParamList } from "../../navigation/navigation.types";

type Props = NativeStackScreenProps<RootStackParamList, "Login">;

export function LoginScreen({ navigation }: Props) {
  return (
    <AppScreen scroll contentStyle={styles.container}>
      <View style={styles.header}>
        <Text style={styles.title}>Welcome back</Text>
        <Text style={styles.subtitle}>
          Continue your learning path with Elia.
        </Text>
      </View>

      <ParchmentCard style={styles.card}>
        <View style={styles.form}>
          <TextField
            label="Email"
            placeholder="mafer@test.com"
            autoCapitalize="none"
            keyboardType="email-address"
          />

          <TextField
            label="Password"
            placeholder="Password"
            secureTextEntry
          />

          <PrimaryButton
            title="Log in"
            onPress={() => navigation.navigate("Home")}
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
  header: {
    marginBottom: spacing.xl,
  },
  title: {
    ...typography.screenTitle,
    color: colors.parchment,
    marginBottom: spacing.sm,
  },
  subtitle: {
    ...typography.subtitle,
    color: colors.parchment,
  },
  card: {
    marginBottom: spacing.lg,
  },
  form: {
    gap: spacing.md,
  },
});
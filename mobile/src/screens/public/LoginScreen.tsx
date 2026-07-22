import { StyleSheet, View } from "react-native";
import { NativeStackScreenProps } from "@react-navigation/native-stack";

import {
  AppScreen,
  ParchmentCard,
  PrimaryButton,
  ScreenHeader,
  TextField,
} from "../../components";
import { spacing } from "../../constants/theme";
import { RootStackParamList } from "../../navigation/navigation.types";

type Props = NativeStackScreenProps<RootStackParamList, "Login">;

export function LoginScreen({ navigation }: Props) {
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
  card: {
    marginBottom: spacing.xl,
  },
  form: {
    gap: spacing.md,
  },
});
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

type Props = NativeStackScreenProps<RootStackParamList, "Register">;

export function RegisterScreen({ navigation }: Props) {
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
            placeholder="María Fernanda Rodríguez"
          />

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
            title="Create account"
            onPress={() => navigation.replace("MainApp")}
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
});
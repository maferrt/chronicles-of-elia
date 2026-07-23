import { StyleSheet, Text } from "react-native";
import { StackActions } from "@react-navigation/native";
import { BottomTabScreenProps } from "@react-navigation/bottom-tabs";
import { NativeStackNavigationProp } from "@react-navigation/native-stack";

import {
  AppScreen,
  EliaGuideCard,
  ParchmentCard,
  PrimaryButton,
  ScreenHeader,
} from "../../components";
import {
  AppTabParamList,
  RootStackParamList,
} from "../../navigation/navigation.types";
import { colors, spacing, typography } from "../../constants/theme";
import { logoutUser } from "../../services/authService";

type Props = BottomTabScreenProps<AppTabParamList, "Profile">;

export function ProfileScreen({ navigation }: Props) {
  async function handleLogout() {
    await logoutUser();

    const rootNavigation =
      navigation.getParent<NativeStackNavigationProp<RootStackParamList>>();

    rootNavigation?.dispatch(StackActions.replace("Login"));
  }

  return (
    <AppScreen scroll contentStyle={styles.container}>
      <ScreenHeader
        eyebrow="Profile"
        title="Your Profile"
        subtitle="Review your learning path and preferences."
      />

      <EliaGuideCard
        eliaVariant="reading"
        title="Your current path"
        message="You are currently following the Dev Path as an A2 Wanderer."
      />

      <ParchmentCard style={styles.card}>
        <Text style={styles.cardLabel}>Learning profile</Text>
        <Text style={styles.cardTitle}>Dev Path · A2 Wanderer</Text>
        <Text style={styles.cardText}>
          Goals: Technical Interviews, Remote Work
        </Text>
      </ParchmentCard>

      <PrimaryButton title="Log out" variant="secondary" onPress={handleLogout} />
    </AppScreen>
  );
}

const styles = StyleSheet.create({
  container: {
    paddingBottom: 120,
  },
  card: {
    marginVertical: spacing.lg,
  },
  cardLabel: {
    ...typography.label,
    color: colors.deepTeal,
    marginBottom: spacing.xs,
  },
  cardTitle: {
    ...typography.sectionTitle,
    color: colors.textDark,
    marginBottom: spacing.sm,
  },
  cardText: {
    ...typography.body,
    color: colors.textMuted,
  },
});
import { StyleSheet, Text } from "react-native";

import {
  AppScreen,
  EliaGuideCard,
  ParchmentCard,
  PrimaryButton,
  ScreenHeader,
} from "../../components";
import { colors, spacing, typography } from "../../constants/theme";
import { useAuth } from "../../context/AuthContext";

export function ProfileScreen() {
  const { logout, currentUser } = useAuth();

  async function handleLogout() {
    await logout();
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
        message={`Welcome, ${
          currentUser?.fullName ?? "traveler"
        }. You are currently following the Dev Path as an A2 Wanderer.`}
      />

      <ParchmentCard style={styles.card}>
        <Text style={styles.cardLabel}>Learning profile</Text>

        <Text style={styles.cardTitle}>Dev Path · A2 Wanderer</Text>

        <Text style={styles.cardText}>
          Goals: Technical Interviews, Remote Work
        </Text>
      </ParchmentCard>

      <PrimaryButton
        title="Log out"
        variant="secondary"
        onPress={handleLogout}
      />
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
import { StyleSheet, Text } from "react-native";

import { AppScreen, EliaGuideCard, ParchmentCard, ScreenHeader } from "../../components";
import { colors, spacing, typography } from "../../constants/theme";

export function PracticeScreen() {
  return (
    <AppScreen scroll>
      <ScreenHeader
        eyebrow="Practice"
        title="Practice with Elia"
        subtitle="Review vocabulary, grammar and exercises from your missions."
      />

      <EliaGuideCard
        eliaVariant="teaching"
        title="Elia's reminder"
        message="Practice does not have to be perfect. It only has to be consistent."
      />

      <ParchmentCard style={styles.card}>
        <Text style={styles.cardLabel}>Suggested practice</Text>
        <Text style={styles.cardTitle}>Vocabulary Review</Text>
        <Text style={styles.cardText}>
          Review useful words from your current Dev Path mission.
        </Text>
      </ParchmentCard>
    </AppScreen>
  );
}

const styles = StyleSheet.create({
  card: {
    marginTop: spacing.lg,
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
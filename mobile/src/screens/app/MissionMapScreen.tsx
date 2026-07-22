import { StyleSheet, Text } from "react-native";

import { AppScreen, ParchmentCard, ScreenHeader } from "../../components";
import { colors, spacing, typography } from "../../constants/theme";

export function MissionMapScreen() {
  return (
    <AppScreen scroll>
      <ScreenHeader
        eyebrow="Story path"
        title="Mission Map"
        subtitle="Follow your personalized English learning route."
      />

      <ParchmentCard style={styles.card}>
        <Text style={styles.cardLabel}>Next mission</Text>
        <Text style={styles.cardTitle}>Professional Introduction</Text>
        <Text style={styles.cardText}>
          Learn how to introduce yourself as a junior developer using clear and
          simple English.
        </Text>
      </ParchmentCard>

      <ParchmentCard style={styles.card}>
        <Text style={styles.cardLabel}>Coming next</Text>
        <Text style={styles.cardTitle}>Daily Work Routine</Text>
        <Text style={styles.cardText}>
          Practice how to describe your work habits, tools and daily tasks.
        </Text>
      </ParchmentCard>
    </AppScreen>
  );
}

const styles = StyleSheet.create({
  card: {
    marginBottom: spacing.md,
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
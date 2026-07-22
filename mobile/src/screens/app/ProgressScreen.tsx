import { StyleSheet, Text, View } from "react-native";

import { AppScreen, DarkForestCard, ParchmentCard, ScreenHeader } from "../../components";
import { colors, spacing, typography } from "../../constants/theme";

export function ProgressScreen() {
  return (
    <AppScreen scroll>
      <ScreenHeader
        eyebrow="Progress"
        title="Your Growth"
        subtitle="Track your XP, completed missions and study time."
      />

      <DarkForestCard style={styles.statsCard}>
        <View style={styles.statItem}>
          <Text style={styles.statValue}>A2</Text>
          <Text style={styles.statLabel}>Level</Text>
        </View>

        <View style={styles.statItem}>
          <Text style={styles.statValue}>60</Text>
          <Text style={styles.statLabel}>XP</Text>
        </View>

        <View style={styles.statItem}>
          <Text style={styles.statValue}>0.1%</Text>
          <Text style={styles.statLabel}>Progress</Text>
        </View>
      </DarkForestCard>

      <ParchmentCard>
        <Text style={styles.cardTitle}>Level mastery</Text>
        <Text style={styles.cardText}>
          Your A2 path is based on an estimated 30,000 study minutes. Every
          mission adds time, XP and progress toward your learning goal.
        </Text>
      </ParchmentCard>
    </AppScreen>
  );
}

const styles = StyleSheet.create({
  statsCard: {
    flexDirection: "row",
    justifyContent: "space-between",
    marginBottom: spacing.lg,
  },
  statItem: {
    flex: 1,
    alignItems: "center",
  },
  statValue: {
    ...typography.sectionTitle,
    color: colors.gold,
  },
  statLabel: {
    ...typography.small,
    color: colors.parchment,
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
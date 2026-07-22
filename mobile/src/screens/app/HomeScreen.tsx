import { StyleSheet, Text, View } from "react-native";

import {
  AppScreen,
  DarkForestCard,
  EliaGuideCard,
  ParchmentCard,
  PrimaryButton,
} from "../../components";
import { colors, spacing, typography } from "../../constants/theme";

export function HomeScreen() {
  return (
    <AppScreen scroll>
      <Text style={styles.greeting}>Good evening, traveler ✦</Text>
      <Text style={styles.title}>Your learning path awaits</Text>

      <EliaGuideCard
        eliaVariant="teaching"
        title="Elia's tip"
        message="Consistency is your magic. One small mission today still counts."
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
          <Text style={styles.statValue}>1</Text>
          <Text style={styles.statLabel}>Mission</Text>
        </View>
      </DarkForestCard>

      <ParchmentCard style={styles.card}>
        <Text style={styles.cardLabel}>Current path</Text>
        <Text style={styles.cardTitle}>Dev Path · A2 Wanderer</Text>
        <Text style={styles.cardText}>
          Practice professional English through short missions, lessons and
          exercises.
        </Text>
      </ParchmentCard>

      <ParchmentCard style={styles.card}>
        <Text style={styles.cardLabel}>Next mission</Text>
        <Text style={styles.cardTitle}>Professional Introduction</Text>
        <Text style={styles.cardText}>
          Learn how to introduce yourself as a junior developer.
        </Text>
      </ParchmentCard>

      <PrimaryButton title="Open mission map" onPress={() => {}} />
    </AppScreen>
  );
}

const styles = StyleSheet.create({
  greeting: {
    ...typography.small,
    color: colors.dustyRose,
    marginBottom: spacing.sm,
  },
  title: {
    ...typography.screenTitle,
    color: colors.parchment,
    marginBottom: spacing.xl,
  },
  statsCard: {
    flexDirection: "row",
    justifyContent: "space-between",
    marginVertical: spacing.lg,
  },
  statItem: {
    alignItems: "center",
    flex: 1,
  },
  statValue: {
    ...typography.sectionTitle,
    color: colors.gold,
  },
  statLabel: {
    ...typography.small,
    color: colors.parchment,
  },
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
import { Pressable, StyleSheet, Text, View } from "react-native";

import { colors, radius, spacing, typography } from "../../constants/theme";

export function HomeScreen() {
  return (
    <View style={styles.container}>
      <Text style={styles.greeting}>Good evening, traveler ✦</Text>

      <Text style={styles.title}>Your learning path awaits</Text>

      <View style={styles.card}>
        <Text style={styles.cardLabel}>Current path</Text>
        <Text style={styles.cardTitle}>Dev Path · A2 Wanderer</Text>
        <Text style={styles.cardText}>
          Practice professional English through short missions, lessons and
          exercises.
        </Text>
      </View>

      <View style={styles.card}>
        <Text style={styles.cardLabel}>Next mission</Text>
        <Text style={styles.cardTitle}>Professional Introduction</Text>
        <Text style={styles.cardText}>
          Learn how to introduce yourself as a junior developer.
        </Text>
      </View>

      <Pressable style={styles.primaryButton}>
        <Text style={styles.primaryButtonText}>Open mission map</Text>
      </Pressable>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: colors.forestDark,
    padding: spacing.xl,
    justifyContent: "center",
  },
  greeting: {
    ...typography.small,
    color: colors.dustyRose,
    marginBottom: spacing.sm,
  },
  title: {
    ...typography.title,
    color: colors.parchment,
    marginBottom: spacing.xl,
  },
  card: {
    backgroundColor: colors.card,
    borderRadius: radius.lg,
    padding: spacing.lg,
    marginBottom: spacing.md,
  },
  cardLabel: {
    color: colors.deepTeal,
    fontSize: 13,
    fontWeight: "700",
    marginBottom: spacing.xs,
  },
  cardTitle: {
    color: colors.textDark,
    fontSize: 20,
    fontWeight: "700",
    marginBottom: spacing.sm,
  },
  cardText: {
    color: colors.mutedText,
    fontSize: 15,
    lineHeight: 22,
  },
  primaryButton: {
    backgroundColor: colors.dustyRose,
    padding: spacing.md,
    borderRadius: radius.full,
    alignItems: "center",
    marginTop: spacing.lg,
  },
  primaryButtonText: {
    color: colors.forestDark,
    fontWeight: "700",
    fontSize: 16,
  },
});
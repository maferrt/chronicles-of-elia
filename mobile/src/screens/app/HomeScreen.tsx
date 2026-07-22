import { StyleSheet, Text, View } from "react-native";
import { BottomTabScreenProps } from "@react-navigation/bottom-tabs";

import {
  AppScreen,
  DarkForestCard,
  EliaGuideCard,
  ParchmentCard,
  PrimaryButton,
  ScreenHeader,
} from "../../components";
import { AppTabParamList } from "../../navigation/navigation.types";
import { colors, spacing, typography } from "../../constants/theme";

type Props = BottomTabScreenProps<AppTabParamList, "Home">;

export function HomeScreen({ navigation }: Props) {
  return (
    <AppScreen scroll contentStyle={styles.container}>
      <ScreenHeader
        eyebrow="Good evening, traveler ✦"
        title="Your learning path awaits"
      />

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

      <PrimaryButton
        title="Open mission map"
        onPress={() => navigation.navigate("Map")}
      />
    </AppScreen>
  );
}

const styles = StyleSheet.create({
  container: {
    paddingBottom: 120,
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
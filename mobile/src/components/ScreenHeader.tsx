import { StyleSheet, Text, View } from "react-native";

import { colors, spacing, typography } from "../constants/theme";

type ScreenHeaderProps = {
  eyebrow?: string;
  title: string;
  subtitle?: string;
};

export function ScreenHeader({ eyebrow, title, subtitle }: ScreenHeaderProps) {
  return (
    <View style={styles.container}>
      {eyebrow ? <Text style={styles.eyebrow}>{eyebrow}</Text> : null}

      <Text style={styles.title}>{title}</Text>

      {subtitle ? <Text style={styles.subtitle}>{subtitle}</Text> : null}
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    marginBottom: spacing.xl,
  },
  eyebrow: {
    ...typography.small,
    color: colors.dustyRose,
    marginBottom: spacing.sm,
  },
  title: {
    ...typography.screenTitle,
    color: colors.parchment,
    textTransform: "uppercase",
    textShadowColor: "rgba(0, 0, 0, 0.55)",
    textShadowOffset: {
      width: 0,
      height: 3,
    },
    textShadowRadius: 8,
  },
  subtitle: {
    ...typography.subtitle,
    color: colors.parchment,
    marginTop: spacing.sm,
    textShadowColor: "rgba(0, 0, 0, 0.55)",
    textShadowOffset: {
      width: 0,
      height: 2,
    },
    textShadowRadius: 5,
  },
});
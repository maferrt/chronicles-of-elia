import { ReactNode } from "react";
import { StyleProp, StyleSheet, View, ViewStyle } from "react-native";

import { colors, radius, spacing } from "../constants/theme";

type DarkForestCardProps = {
  children: ReactNode;
  style?: StyleProp<ViewStyle>;
};

export function DarkForestCard({ children, style }: DarkForestCardProps) {
  return <View style={[styles.card, style]}>{children}</View>;
}

const styles = StyleSheet.create({
  card: {
    backgroundColor: colors.cardDark,
    borderRadius: radius.lg,
    borderWidth: 1,
    borderColor: colors.borderGold,
    padding: spacing.lg,
  },
});
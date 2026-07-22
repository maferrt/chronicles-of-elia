import { ReactNode } from "react";
import { StyleProp, StyleSheet, View, ViewStyle } from "react-native";

import { colors, radius, spacing } from "../constants/theme";

type ParchmentCardProps = {
  children: ReactNode;
  style?: StyleProp<ViewStyle>;
};

export function ParchmentCard({ children, style }: ParchmentCardProps) {
  return <View style={[styles.card, style]}>{children}</View>;
}

const styles = StyleSheet.create({
  card: {
    backgroundColor: colors.parchment,
    borderRadius: radius.xl,
    borderWidth: 1,
    borderColor: colors.parchmentDark,
    padding: spacing.xl,
    shadowColor: colors.forestDeep,
    shadowOpacity: 0.28,
    shadowRadius: 14,
    shadowOffset: {
      width: 0,
      height: 8,
    },
    elevation: 5,
  },
});
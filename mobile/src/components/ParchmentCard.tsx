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
    backgroundColor: colors.parchmentSoft,
    borderRadius: radius.lg,
    borderWidth: 1,
    borderColor: colors.borderGold,
    padding: spacing.lg,
    shadowColor: colors.forestDeep,
    shadowOpacity: 0.25,
    shadowRadius: 12,
    shadowOffset: {
      width: 0,
      height: 6,
    },
    elevation: 4,
  },
});
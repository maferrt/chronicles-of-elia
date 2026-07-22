import { Pressable, StyleSheet, Text } from "react-native";

import { colors, fonts, radius, spacing } from "../constants/theme";

type ButtonVariant = "primary" | "secondary" | "ghost";

type PrimaryButtonProps = {
  title: string;
  onPress: () => void;
  variant?: ButtonVariant;
  disabled?: boolean;
};

export function PrimaryButton({
  title,
  onPress,
  variant = "primary",
  disabled = false,
}: PrimaryButtonProps) {
  return (
    <Pressable
      onPress={onPress}
      disabled={disabled}
      style={({ pressed }) => [
        styles.base,
        styles[variant],
        pressed && !disabled && styles.pressed,
        disabled && styles.disabled,
      ]}
    >
      <Text style={[styles.text, variant === "ghost" && styles.ghostText]}>
        {title}
      </Text>
    </Pressable>
  );
}

const styles = StyleSheet.create({
  base: {
    width: "100%",
    minHeight: 58,
    borderRadius: radius.full,
    alignItems: "center",
    justifyContent: "center",
    paddingHorizontal: spacing.lg,
    paddingVertical: spacing.md,
  },
  primary: {
    backgroundColor: "rgba(49, 76, 40, 0.94)",
    borderWidth: 1.5,
    borderColor: colors.gold,
  },
  secondary: {
    backgroundColor: colors.dustyRose,
    borderWidth: 1,
    borderColor: colors.borderGold,
  },
  ghost: {
    backgroundColor: "transparent",
  },
  text: {
    fontFamily: fonts.titleRegular,
    fontSize: 17,
    color: colors.parchment,
    textAlign: "center",
    textTransform: "uppercase",
  },
  ghostText: {
    color: colors.parchment,
    textDecorationLine: "underline",
    textShadowColor: "rgba(0, 0, 0, 0.55)",
    textShadowOffset: {
      width: 0,
      height: 1,
    },
    textShadowRadius: 3,
  },
  pressed: {
    opacity: 0.86,
    transform: [{ scale: 0.98 }],
  },
  disabled: {
    opacity: 0.5,
  },
});
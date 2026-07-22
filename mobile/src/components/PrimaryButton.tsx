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
      <Text
        style={[
          styles.text,
          variant === "ghost" && styles.ghostText,
        ]}
      >
        {title}
      </Text>
    </Pressable>
  );
}

const styles = StyleSheet.create({
  base: {
    width: "100%",
    minHeight: 52,
    borderRadius: radius.full,
    alignItems: "center",
    justifyContent: "center",
    paddingHorizontal: spacing.lg,
    paddingVertical: spacing.md,
  },
  primary: {
    backgroundColor: colors.dustyRose,
    borderWidth: 1,
    borderColor: colors.borderGold,
  },
  secondary: {
    backgroundColor: colors.moss,
    borderWidth: 1,
    borderColor: colors.borderGold,
  },
  ghost: {
    backgroundColor: "transparent",
  },
  text: {
    fontFamily: fonts.titleRegular,
    fontSize: 16,
    color: colors.forestDark,
    textAlign: "center",
  },
  ghostText: {
    color: colors.parchment,
    textDecorationLine: "underline",
  },
  pressed: {
    opacity: 0.82,
    transform: [{ scale: 0.98 }],
  },
  disabled: {
    opacity: 0.5,
  },
});
import { StyleSheet, Text, TextInput, TextInputProps, View } from "react-native";

import { colors, fonts, radius, spacing, typography } from "../constants/theme";

type TextFieldProps = TextInputProps & {
  label?: string;
  error?: string;
};

export function TextField({
  label,
  error,
  style,
  ...textInputProps
}: TextFieldProps) {
  return (
    <View style={styles.container}>
      {label ? <Text style={styles.label}>{label}</Text> : null}

      <TextInput
        placeholderTextColor={colors.textMuted}
        style={[styles.input, style]}
        {...textInputProps}
      />

      {error ? <Text style={styles.error}>{error}</Text> : null}
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    gap: spacing.xs,
  },
  label: {
    ...typography.label,
    color: colors.forestDark,
  },
  input: {
    minHeight: 52,
    backgroundColor: colors.parchmentSoft,
    borderRadius: radius.md,
    borderWidth: 1,
    borderColor: colors.parchmentDark,
    paddingHorizontal: spacing.md,
    fontFamily: fonts.body,
    fontSize: 16,
    color: colors.textDark,
  },
  error: {
    ...typography.small,
    color: colors.error,
  },
});
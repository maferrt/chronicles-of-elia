import { Image, ImageSourcePropType, StyleSheet, Text, View } from "react-native";

import { colors, radius, spacing, typography } from "../constants/theme";
import { ParchmentCard } from "./ParchmentCard";

type EliaGuideCardProps = {
  title?: string;
  message: string;
  imageSource?: ImageSourcePropType;
};

export function EliaGuideCard({
  title = "Elia says",
  message,
  imageSource,
}: EliaGuideCardProps) {
  return (
    <ParchmentCard style={styles.card}>
      <View style={styles.content}>
        {imageSource ? (
          <Image source={imageSource} style={styles.image} resizeMode="contain" />
        ) : (
          <View style={styles.placeholder}>
            <Text style={styles.placeholderText}>Elia</Text>
          </View>
        )}

        <View style={styles.textContent}>
          <Text style={styles.title}>{title}</Text>
          <Text style={styles.message}>{message}</Text>
        </View>
      </View>
    </ParchmentCard>
  );
}

const styles = StyleSheet.create({
  card: {
    width: "100%",
  },
  content: {
    flexDirection: "row",
    gap: spacing.md,
    alignItems: "center",
  },
  image: {
    width: 82,
    height: 100,
  },
  placeholder: {
    width: 72,
    height: 72,
    borderRadius: radius.full,
    backgroundColor: colors.dustyRose,
    alignItems: "center",
    justifyContent: "center",
    borderWidth: 1,
    borderColor: colors.borderGold,
  },
  placeholderText: {
    ...typography.label,
    color: colors.forestDark,
  },
  textContent: {
    flex: 1,
  },
  title: {
    ...typography.label,
    color: colors.forestDark,
    marginBottom: spacing.xs,
  },
  message: {
    ...typography.subtitle,
    color: colors.textDark,
  },
});
import { Image, ImageSourcePropType, StyleSheet, Text, View } from "react-native";

import { eliaAssets, EliaVariant } from "../constants/eliaAssets";
import { colors, radius, spacing, typography } from "../constants/theme";
import { ParchmentCard } from "./ParchmentCard";

type EliaGuideCardProps = {
  title?: string;
  message: string;
  imageSource?: ImageSourcePropType;
  eliaVariant?: EliaVariant;
};

export function EliaGuideCard({
  title = "Elia says",
  message,
  imageSource,
  eliaVariant = "wave",
}: EliaGuideCardProps) {
  const selectedImage = imageSource ?? eliaAssets[eliaVariant];

  return (
    <ParchmentCard style={styles.card}>
      <View style={styles.content}>
        <Image
          source={selectedImage}
          style={styles.image}
          resizeMode="contain"
        />

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
    width: 96,
    height: 116,
    borderRadius: radius.md,
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
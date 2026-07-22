import { ReactNode } from "react";
import { ImageBackground, StyleSheet, View } from "react-native";
import { LinearGradient } from "expo-linear-gradient";

import { colors } from "../constants/theme";

type ForestBackgroundProps = {
  children: ReactNode;
};

const forestBackground = require("../assets/images/backgrounds/forest-splash.png");

export function ForestBackground({ children }: ForestBackgroundProps) {
  return (
    <View style={styles.container}>
      <ImageBackground
        source={forestBackground}
        style={styles.backgroundImage}
        resizeMode="cover"
      >
        <LinearGradient
          colors={[
            "rgba(6, 29, 22, 0.58)",
            "rgba(10, 51, 35, 0.72)",
            "rgba(6, 29, 22, 0.88)",
          ]}
          locations={[0, 0.45, 1]}
          style={styles.overlay}
        />

        <View style={styles.vignetteTop} />
        <View style={styles.vignetteBottom} />

        <View style={[styles.firefly, styles.fireflyOne]} />
        <View style={[styles.firefly, styles.fireflyTwo]} />
        <View style={[styles.firefly, styles.fireflyThree]} />
        <View style={[styles.firefly, styles.fireflyFour]} />
        <View style={[styles.firefly, styles.fireflyFive]} />

        {children}
      </ImageBackground>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: colors.forestDark,
  },
  backgroundImage: {
    flex: 1,
  },
  overlay: {
    ...StyleSheet.absoluteFillObject,
  },
  vignetteTop: {
    position: "absolute",
    top: 0,
    left: 0,
    right: 0,
    height: 180,
    backgroundColor: "rgba(0, 0, 0, 0.25)",
  },
  vignetteBottom: {
    position: "absolute",
    bottom: 0,
    left: 0,
    right: 0,
    height: 240,
    backgroundColor: "rgba(0, 0, 0, 0.38)",
  },
  firefly: {
    position: "absolute",
    width: 5,
    height: 5,
    borderRadius: 999,
    backgroundColor: colors.gold,
    opacity: 0.85,
  },
  fireflyOne: {
    top: 130,
    left: 54,
  },
  fireflyTwo: {
    top: 230,
    right: 64,
    width: 4,
    height: 4,
  },
  fireflyThree: {
    top: 360,
    left: 90,
    width: 3,
    height: 3,
  },
  fireflyFour: {
    bottom: 220,
    right: 80,
    width: 4,
    height: 4,
  },
  fireflyFive: {
    bottom: 140,
    left: 120,
    width: 3,
    height: 3,
  },
});
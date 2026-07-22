import { Image, ImageBackground, Pressable, StyleSheet, Text, useWindowDimensions, View } from "react-native";
import { LinearGradient } from "expo-linear-gradient";
import { SafeAreaView } from "react-native-safe-area-context";
import { NativeStackScreenProps } from "@react-navigation/native-stack";

import { eliaAssets } from "../../constants/eliaAssets";
import { colors, fonts } from "../../constants/theme";
import { RootStackParamList } from "../../navigation/navigation.types";

type Props = NativeStackScreenProps<RootStackParamList, "Splash">;

const forestSplash = require("../../assets/images/backgrounds/forest-splash.png");

export function SplashScreen({ navigation }: Props) {
  const { width, height } = useWindowDimensions();

  const eliaHeight = height * 0.5;
  const eliaWidth = width * 0.9;

  return (
    <ImageBackground
      source={forestSplash}
      style={styles.background}
      resizeMode="cover"
    >
      <LinearGradient
        colors={[
          "rgba(6, 29, 22, 0.42)",
          "rgba(6, 29, 22, 0.12)",
          "rgba(6, 29, 22, 0.24)",
          "rgba(6, 29, 22, 0.72)",
        ]}
        locations={[0, 0.28, 0.58, 1]}
        style={styles.overlay}
      />

      <SafeAreaView style={styles.safeArea}>
        <View style={styles.content}>
          <View style={styles.titleArea}>
            <Text style={styles.title}>Chronicles{"\n"}of Elia</Text>

            <Text style={styles.subtitle}>
              Learn English through personalized quests.
            </Text>
          </View>

          <Image
            source={eliaAssets.wave}
            resizeMode="contain"
            style={[
              styles.elia,
              {
                width: eliaWidth,
                height: eliaHeight,
                top: height * 0.255,
              },
            ]}
          />

          <View style={styles.bottomArea}>
            <Pressable
              style={({ pressed }) => [
                styles.primaryButton,
                pressed && styles.pressed,
              ]}
              onPress={() => navigation.navigate("Login")}
            >
              <Text style={styles.primaryButtonText}>Begin Your Journey</Text>
            </Pressable>

            <Text style={styles.bottomTagline}>
              Learn English. Live the Adventure.
            </Text>

            <View style={styles.dots}>
              <View style={[styles.dot, styles.activeDot]} />
              <View style={styles.dot} />
              <View style={styles.dot} />
              <View style={styles.dot} />
            </View>

            <Pressable onPress={() => navigation.navigate("Register")}>
              <Text style={styles.secondaryLink}>Create an account</Text>
            </Pressable>
          </View>
        </View>
      </SafeAreaView>
    </ImageBackground>
  );
}

const styles = StyleSheet.create({
  background: {
    flex: 1,
    backgroundColor: colors.forestDark,
  },
  overlay: {
    ...StyleSheet.absoluteFillObject,
  },
  safeArea: {
    flex: 1,
  },
  content: {
    flex: 1,
    position: "relative",
    paddingHorizontal: 28,
  },
  titleArea: {
    alignItems: "center",
    paddingTop: 28,
    zIndex: 2,
  },
  title: {
    fontFamily: fonts.title,
    fontSize: 45,
    lineHeight: 50,
    color: colors.parchment,
    textAlign: "center",
    textTransform: "capitalize",
    textShadowColor: "rgba(0, 0, 0, 0.45)",
    textShadowOffset: {
      width: 0,
      height: 3,
    },
    textShadowRadius: 8,
  },
  subtitle: {
    marginTop: 18,
    fontFamily: fonts.subtitle,
    fontSize: 21,
    lineHeight: 28,
    color: colors.parchment,
    textAlign: "center",
    textShadowColor: "rgba(0, 0, 0, 0.55)",
    textShadowOffset: {
      width: 0,
      height: 2,
    },
    textShadowRadius: 5,
  },
  elia: {
    position: "absolute",
    alignSelf: "center",
    zIndex: 1,
  },
  bottomArea: {
    position: "absolute",
    left: 28,
    right: 28,
    bottom: 34,
    alignItems: "center",
    zIndex: 3,
  },
  primaryButton: {
    width: "100%",
    minHeight: 64,
    borderRadius: 22,
    backgroundColor: "rgba(10, 51, 35, 0.92)",
    borderWidth: 2,
    borderColor: colors.sage,
    alignItems: "center",
    justifyContent: "center",
    shadowColor: colors.forestDeep,
    shadowOpacity: 0.45,
    shadowRadius: 12,
    shadowOffset: {
      width: 0,
      height: 6,
    },
    elevation: 6,
  },
  primaryButtonText: {
    fontFamily: fonts.titleRegular,
    fontSize: 22,
    color: colors.parchment,
    textAlign: "center",
    textShadowColor: "rgba(0, 0, 0, 0.45)",
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
  bottomTagline: {
    marginTop: 24,
    fontFamily: fonts.subtitle,
    fontSize: 18,
    color: colors.parchment,
    textAlign: "center",
    textShadowColor: "rgba(0, 0, 0, 0.55)",
    textShadowOffset: {
      width: 0,
      height: 1,
    },
    textShadowRadius: 4,
  },
  dots: {
    flexDirection: "row",
    gap: 10,
    marginTop: 34,
    marginBottom: 22,
  },
  dot: {
    width: 10,
    height: 10,
    borderRadius: 5,
    backgroundColor: "rgba(247, 244, 213, 0.35)",
  },
  activeDot: {
    backgroundColor: colors.parchment,
  },
  secondaryLink: {
    fontFamily: fonts.titleRegular,
    fontSize: 16,
    color: colors.parchment,
    textTransform: "uppercase",
    textDecorationLine: "underline",
    textShadowColor: "rgba(0, 0, 0, 0.5)",
    textShadowOffset: {
      width: 0,
      height: 1,
    },
    textShadowRadius: 3,
  },
});
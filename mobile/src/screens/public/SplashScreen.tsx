import { useState } from "react";
import {
  Image,
  ImageBackground,
  Pressable,
  StyleSheet,
  Text,
  useWindowDimensions,
  View,
} from "react-native";
import { LinearGradient } from "expo-linear-gradient";
import { SafeAreaView } from "react-native-safe-area-context";
import { NativeStackScreenProps } from "@react-navigation/native-stack";

import { eliaAssets } from "../../constants/eliaAssets";
import { colors, fonts } from "../../constants/theme";
import { RootStackParamList } from "../../navigation/navigation.types";

type Props = NativeStackScreenProps<RootStackParamList, "Splash">;

const forestSplash = require("../../assets/images/backgrounds/forest-splash.png");

const onboardingSlides = [
  {
    id: 1,
    title: "Chronicles\nof Elia",
    subtitle: "Learn English through personalized quests.",
    benefit: "Discover a magical way to practice English with guidance, purpose and story.",
    buttonText: "Next",
  },
  {
    id: 2,
    title: "Your path,\nyour story",
    subtitle: "Learn according to your level, goals and profession.",
    benefit: "Choose a path like Dev, Chef or Artist and receive missions that match your real-life context.",
    buttonText: "Next",
  },
  {
    id: 3,
    title: "Practice with\npurpose",
    subtitle: "Complete lessons, vocabulary and exercises.",
    benefit: "Every mission helps you practice grammar, writing, vocabulary and communication through useful situations.",
    buttonText: "Next",
  },
  {
    id: 4,
    title: "Grow with\nElia",
    subtitle: "Track your progress and unlock your learning journey.",
    benefit: "Elia will guide you step by step while you build confidence and move through your English path.",
    buttonText: "Begin your journey",
  },
];

export function SplashScreen({ navigation }: Props) {
  const { width, height } = useWindowDimensions();
  const [currentSlideIndex, setCurrentSlideIndex] = useState(0);

  const currentSlide = onboardingSlides[currentSlideIndex];
  const isLastSlide = currentSlideIndex === onboardingSlides.length - 1;

  const eliaHeight = height * 0.5;
  const eliaWidth = width * 0.9;

  function handlePrimaryAction() {
    if (isLastSlide) {
      navigation.navigate("Register");
      return;
    }

    setCurrentSlideIndex((previousIndex) => previousIndex + 1);
  }

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
            <Text style={styles.title}>{currentSlide.title}</Text>

            <Text style={styles.subtitle}>{currentSlide.subtitle}</Text>
          </View>

          <Image
            source={eliaAssets.wave}
            resizeMode="contain"
            style={[
              styles.elia,
              {
                width: eliaWidth,
                height: eliaHeight,
                top: height * 0.25,
              },
            ]}
          />

          <View style={styles.bottomArea}>
            <Text style={styles.benefitText}>{currentSlide.benefit}</Text>

            <Pressable
              style={({ pressed }) => [
                styles.primaryButton,
                pressed && styles.pressed,
              ]}
              onPress={handlePrimaryAction}
            >
              <Text style={styles.primaryButtonText}>
                {currentSlide.buttonText}
              </Text>
            </Pressable>

            <View style={styles.dots}>
              {onboardingSlides.map((slide, index) => {
                const isActive = index === currentSlideIndex;

                return (
                  <Pressable
                    key={slide.id}
                    onPress={() => setCurrentSlideIndex(index)}
                    style={[
                      styles.dot,
                      isActive && styles.activeDot,
                    ]}
                  />
                );
              })}
            </View>
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
  benefitText: {
    marginBottom: 24,
    fontFamily: fonts.subtitle,
    fontSize: 19,
    lineHeight: 26,
    color: colors.parchment,
    textAlign: "center",
    textShadowColor: "rgba(0, 0, 0, 0.7)",
    textShadowOffset: {
      width: 0,
      height: 2,
    },
    textShadowRadius: 5,
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
    textTransform: "uppercase",
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
  dots: {
    flexDirection: "row",
    gap: 10,
    marginTop: 28,
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
});
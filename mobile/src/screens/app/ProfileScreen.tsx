import { useCallback, useState } from "react";
import { ActivityIndicator, StyleSheet, Text, View } from "react-native";
import { useFocusEffect } from "@react-navigation/native";
import { BottomTabScreenProps } from "@react-navigation/bottom-tabs";
import { NativeStackNavigationProp } from "@react-navigation/native-stack";

import {
  AppScreen,
  EliaGuideCard,
  ParchmentCard,
  PrimaryButton,
  ScreenHeader,
} from "../../components";
import {
  AppTabParamList,
  RootStackParamList,
} from "../../navigation/navigation.types";
import { colors, spacing, typography } from "../../constants/theme";
import { useAuth } from "../../context/AuthContext";
import { getMyLearningProfile } from "../../services/profileService";
import { LearningProfile } from "../../types/profile.types";

type Props = BottomTabScreenProps<AppTabParamList, "Profile">;

export function ProfileScreen({ navigation }: Props) {
  const { logout, currentUser } = useAuth();

  const [profile, setProfile] = useState<LearningProfile | null>(null);
  const [isLoading, setIsLoading] = useState(true);
  const [profileError, setProfileError] = useState<string | null>(null);

  useFocusEffect(
    useCallback(() => {
      loadProfile();
    }, [])
  );

  async function loadProfile() {
    try {
      setIsLoading(true);
      setProfileError(null);

      const response = await getMyLearningProfile();
      setProfile(response);
    } catch (error) {
      const message =
        error instanceof Error
          ? error.message
          : "Unable to load your learning profile.";

      setProfile(null);
      setProfileError(message);
    } finally {
      setIsLoading(false);
    }
  }

  function handleOpenProfileSetup() {
    const rootNavigation =
      navigation.getParent<NativeStackNavigationProp<RootStackParamList>>();

    rootNavigation?.navigate("ProfileSetup");
  }

  async function handleLogout() {
    await logout();
  }

  return (
    <AppScreen scroll contentStyle={styles.container}>
      <ScreenHeader
        eyebrow="Profile"
        title="Your Profile"
        subtitle="Review your learning path and preferences."
      />

      <EliaGuideCard
        eliaVariant="reading"
        title="Your current path"
        message={`Welcome, ${
          currentUser?.fullName ?? "traveler"
        }. ${
          profile
            ? `You are currently following the ${profile.profession} as an ${profile.englishLevelCode} ${profile.englishLevel}.`
            : "Your learning profile will appear here once it is created."
        }`}
      />

      {isLoading ? (
        <View style={styles.loadingBox}>
          <ActivityIndicator size="large" color={colors.parchment} />
          <Text style={styles.loadingText}>Loading your profile...</Text>
        </View>
      ) : null}

      {!isLoading && profileError ? (
        <ParchmentCard style={styles.card}>
          <Text style={styles.cardLabel}>Learning profile</Text>

          <Text style={styles.cardTitle}>Profile not completed yet</Text>

          <Text style={styles.cardText}>
            Elia needs a few details to create your personalized learning path.
          </Text>

          <PrimaryButton
            title="Create learning profile"
            onPress={handleOpenProfileSetup}
          />
        </ParchmentCard>
      ) : null}

      {!isLoading && profile ? (
        <>
          <ParchmentCard style={styles.card}>
            <Text style={styles.cardLabel}>Learning profile</Text>

            <Text style={styles.cardTitle}>
              {profile.profession} · {profile.englishLevelCode}{" "}
              {profile.englishLevel}
            </Text>

            <Text style={styles.cardText}>
              Goals: {profile.learningGoals.join(", ")}
            </Text>

            <Text style={styles.cardText}>
              Interests: {profile.interests.join(", ")}
            </Text>

            {profile.bio ? (
              <Text style={styles.bioText}>{profile.bio}</Text>
            ) : null}
          </ParchmentCard>

          <PrimaryButton
            title="Edit learning profile"
            onPress={handleOpenProfileSetup}
          />
        </>
      ) : null}

      <PrimaryButton
        title="Log out"
        variant="secondary"
        onPress={handleLogout}
      />
    </AppScreen>
  );
}

const styles = StyleSheet.create({
  container: {
    paddingBottom: 120,
  },
  card: {
    marginVertical: spacing.lg,
  },
  cardLabel: {
    ...typography.label,
    color: colors.deepTeal,
    marginBottom: spacing.xs,
  },
  cardTitle: {
    ...typography.sectionTitle,
    color: colors.textDark,
    marginBottom: spacing.sm,
  },
  cardText: {
    ...typography.body,
    color: colors.textMuted,
    marginBottom: spacing.xs,
  },
  bioText: {
    ...typography.subtitle,
    color: colors.textDark,
    marginTop: spacing.md,
  },
  loadingBox: {
    alignItems: "center",
    marginVertical: spacing.xl,
    gap: spacing.md,
  },
  loadingText: {
    ...typography.body,
    color: colors.parchment,
  },
});
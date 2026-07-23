import { useEffect, useState } from "react";
import {
  ActivityIndicator,
  Pressable,
  StyleSheet,
  Text,
  TextInput,
  View,
} from "react-native";
import { NativeStackScreenProps } from "@react-navigation/native-stack";

import {
  AppScreen,
  EliaGuideCard,
  ParchmentCard,
  PrimaryButton,
  ScreenHeader,
} from "../../components";
import { colors, fonts, spacing, typography } from "../../constants/theme";
import { RootStackParamList } from "../../navigation/navigation.types";
import { CatalogItem } from "../../types/catalog.types";
import {
  getEnglishLevels,
  getInterests,
  getLearningGoals,
  getProfessions,
} from "../../services/catalogService";
import {
  getMyLearningProfile,
  saveMyLearningProfile,
} from "../../services/profileService";

type Props = NativeStackScreenProps<RootStackParamList, "ProfileSetup">;

export function ProfileSetupScreen({ navigation }: Props) {
  const [professions, setProfessions] = useState<CatalogItem[]>([]);
  const [englishLevels, setEnglishLevels] = useState<CatalogItem[]>([]);
  const [interests, setInterests] = useState<CatalogItem[]>([]);
  const [learningGoals, setLearningGoals] = useState<CatalogItem[]>([]);

  const [selectedProfessionId, setSelectedProfessionId] = useState<
    number | null
  >(null);
  const [selectedEnglishLevelId, setSelectedEnglishLevelId] = useState<
    number | null
  >(null);
  const [selectedInterestIds, setSelectedInterestIds] = useState<number[]>([]);
  const [selectedLearningGoalIds, setSelectedLearningGoalIds] = useState<
    number[]
  >([]);
  const [bio, setBio] = useState("");

  const [isLoading, setIsLoading] = useState(true);
  const [isSaving, setIsSaving] = useState(false);
  const [formError, setFormError] = useState<string | null>(null);

  useEffect(() => {
    loadSetupData();
  }, []);

  async function loadSetupData() {
    try {
      setIsLoading(true);
      setFormError(null);

      const [
        professionsResponse,
        englishLevelsResponse,
        interestsResponse,
        learningGoalsResponse,
      ] = await Promise.all([
        getProfessions(),
        getEnglishLevels(),
        getInterests(),
        getLearningGoals(),
      ]);

      setProfessions(professionsResponse);
      setEnglishLevels(englishLevelsResponse);
      setInterests(interestsResponse);
      setLearningGoals(learningGoalsResponse);

      await tryLoadExistingProfile({
        professionsResponse,
        englishLevelsResponse,
        interestsResponse,
        learningGoalsResponse,
      });
    } catch (error) {
      const message =
        error instanceof Error
          ? error.message
          : "Unable to load profile setup data.";

      setFormError(message);
    } finally {
      setIsLoading(false);
    }
  }

  async function tryLoadExistingProfile(data: {
    professionsResponse: CatalogItem[];
    englishLevelsResponse: CatalogItem[];
    interestsResponse: CatalogItem[];
    learningGoalsResponse: CatalogItem[];
  }) {
    try {
      const existingProfile = await getMyLearningProfile();

      const profession = data.professionsResponse.find(
        (item) => item.code === existingProfile.professionCode
      );

      const englishLevel = data.englishLevelsResponse.find(
        (item) => item.code === existingProfile.englishLevelCode
      );

      const existingInterestIds = data.interestsResponse
        .filter((item) => existingProfile.interests.includes(item.name))
        .map((item) => item.id);

      const existingLearningGoalIds = data.learningGoalsResponse
        .filter((item) => existingProfile.learningGoals.includes(item.name))
        .map((item) => item.id);

      setSelectedProfessionId(profession?.id ?? null);
      setSelectedEnglishLevelId(englishLevel?.id ?? null);
      setSelectedInterestIds(existingInterestIds);
      setSelectedLearningGoalIds(existingLearningGoalIds);
      setBio(existingProfile.bio ?? "");
    } catch {
      // Si todavía no existe perfil, no pasa nada.
      // La pantalla se queda vacía para crear uno nuevo.
    }
  }

  function toggleInterest(id: number) {
    setSelectedInterestIds((currentIds) =>
      currentIds.includes(id)
        ? currentIds.filter((currentId) => currentId !== id)
        : [...currentIds, id]
    );
  }

  function toggleLearningGoal(id: number) {
    setSelectedLearningGoalIds((currentIds) =>
      currentIds.includes(id)
        ? currentIds.filter((currentId) => currentId !== id)
        : [...currentIds, id]
    );
  }

  async function handleSaveProfile() {
    if (!selectedProfessionId) {
      setFormError("Please choose your learning path.");
      return;
    }

    if (!selectedEnglishLevelId) {
      setFormError("Please choose your English level.");
      return;
    }

    if (selectedLearningGoalIds.length === 0) {
      setFormError("Please choose at least one learning goal.");
      return;
    }

    if (selectedInterestIds.length === 0) {
      setFormError("Please choose at least one interest.");
      return;
    }

    const selectedProfession = professions.find(
      (profession) => profession.id === selectedProfessionId
    );

    const selectedEnglishLevel = englishLevels.find(
      (level) => level.id === selectedEnglishLevelId
    );

    const selectedInterestCodes = interests
      .filter((interest) => selectedInterestIds.includes(interest.id))
      .map((interest) => interest.code);

    const selectedLearningGoalCodes = learningGoals
      .filter((goal) => selectedLearningGoalIds.includes(goal.id))
      .map((goal) => goal.code);

    if (!selectedProfession || !selectedEnglishLevel) {
      setFormError("Some selected options are invalid. Please try again.");
      return;
    }

    try {
      setIsSaving(true);
      setFormError(null);

      await saveMyLearningProfile({
        professionCode: selectedProfession.code,
        englishLevelCode: selectedEnglishLevel.code,
        interestCodes: selectedInterestCodes,
        learningGoalCodes: selectedLearningGoalCodes,
        bio: bio.trim() || null,
      });

      navigation.goBack();
    } catch (error) {
      const message =
        error instanceof Error
          ? error.message
          : "Unable to save your learning profile.";

      setFormError(message);
    } finally {
      setIsSaving(false);
    }
  }

  if (isLoading) {
    return (
      <AppScreen contentStyle={styles.loadingContainer}>
        <ActivityIndicator size="large" color={colors.parchment} />
        <Text style={styles.loadingText}>Preparing your path...</Text>
      </AppScreen>
    );
  }

  return (
    <AppScreen scroll contentStyle={styles.container}>
      <ScreenHeader
        eyebrow="Learning path"
        title="Create your path"
        subtitle="Choose how Elia should personalize your English journey."
      />

      <EliaGuideCard
        eliaVariant="teaching"
        title="Elia's guide"
        message="Your profile helps me choose missions, vocabulary and exercises that match your real goals."
      />

      {formError ? (
        <View style={styles.errorBox}>
          <Text style={styles.errorText}>{formError}</Text>
        </View>
      ) : null}

      <ParchmentCard style={styles.section}>
        <Text style={styles.sectionLabel}>Choose your path</Text>
        <Text style={styles.sectionHelp}>
          This tells Elia what kind of English context you want to practice.
        </Text>

        <View style={styles.optionsGrid}>
          {professions.map((profession) => (
            <OptionButton
              key={profession.id}
              item={profession}
              selected={selectedProfessionId === profession.id}
              onPress={() => setSelectedProfessionId(profession.id)}
            />
          ))}
        </View>
      </ParchmentCard>

      <ParchmentCard style={styles.section}>
        <Text style={styles.sectionLabel}>Choose your English level</Text>
        <Text style={styles.sectionHelp}>
          Pick your current level. Later, the diagnostic test can help refine it.
        </Text>

        <View style={styles.optionsGrid}>
          {englishLevels.map((level) => (
            <OptionButton
              key={level.id}
              item={level}
              selected={selectedEnglishLevelId === level.id}
              onPress={() => setSelectedEnglishLevelId(level.id)}
            />
          ))}
        </View>
      </ParchmentCard>

      <ParchmentCard style={styles.section}>
        <Text style={styles.sectionLabel}>Learning goals</Text>
        <Text style={styles.sectionHelp}>
          You can choose more than one goal.
        </Text>

        <View style={styles.optionsGrid}>
          {learningGoals.map((goal) => (
            <OptionButton
              key={goal.id}
              item={goal}
              selected={selectedLearningGoalIds.includes(goal.id)}
              onPress={() => toggleLearningGoal(goal.id)}
            />
          ))}
        </View>
      </ParchmentCard>

      <ParchmentCard style={styles.section}>
        <Text style={styles.sectionLabel}>Interests</Text>
        <Text style={styles.sectionHelp}>
          These help Elia make your examples feel more personal.
        </Text>

        <View style={styles.optionsGrid}>
          {interests.map((interest) => (
            <OptionButton
              key={interest.id}
              item={interest}
              selected={selectedInterestIds.includes(interest.id)}
              onPress={() => toggleInterest(interest.id)}
            />
          ))}
        </View>
      </ParchmentCard>

      <ParchmentCard style={styles.section}>
        <Text style={styles.sectionLabel}>About you</Text>
        <Text style={styles.sectionHelp}>
          Optional. Tell Elia anything useful about your learning journey.
        </Text>

        <TextInput
          value={bio}
          onChangeText={setBio}
          placeholder="Example: I want to practice English for technical interviews."
          placeholderTextColor={colors.textMuted}
          multiline
          textAlignVertical="top"
          style={styles.bioInput}
        />
      </ParchmentCard>

      <PrimaryButton
        title={isSaving ? "Saving path..." : "Save my path"}
        disabled={isSaving}
        onPress={handleSaveProfile}
      />

      <PrimaryButton
        title="Cancel"
        variant="ghost"
        disabled={isSaving}
        onPress={() => navigation.goBack()}
      />
    </AppScreen>
  );
}

type OptionButtonProps = {
  item: CatalogItem;
  selected: boolean;
  onPress: () => void;
};

function OptionButton({ item, selected, onPress }: OptionButtonProps) {
  return (
    <Pressable
      onPress={onPress}
      style={({ pressed }) => [
        styles.optionButton,
        selected && styles.optionButtonSelected,
        pressed && styles.optionButtonPressed,
      ]}
    >
      <Text
        style={[
          styles.optionTitle,
          selected && styles.optionTitleSelected,
        ]}
      >
        {item.name}
      </Text>

      {item.description ? (
        <Text
          style={[
            styles.optionDescription,
            selected && styles.optionDescriptionSelected,
          ]}
        >
          {item.description}
        </Text>
      ) : null}
    </Pressable>
  );
}

const styles = StyleSheet.create({
  container: {
    paddingBottom: 140,
  },
  loadingContainer: {
    alignItems: "center",
    justifyContent: "center",
    gap: spacing.md,
  },
  loadingText: {
    ...typography.body,
    color: colors.parchment,
  },
  section: {
    marginBottom: spacing.lg,
  },
  sectionLabel: {
    ...typography.sectionTitle,
    color: colors.textDark,
    marginBottom: spacing.xs,
  },
  sectionHelp: {
    ...typography.body,
    color: colors.textMuted,
    marginBottom: spacing.md,
  },
  optionsGrid: {
    gap: spacing.sm,
  },
  optionButton: {
    borderWidth: 1,
    borderColor: colors.parchmentDark,
    borderRadius: 18,
    padding: spacing.md,
    backgroundColor: "rgba(247, 244, 213, 0.55)",
  },
  optionButtonSelected: {
    backgroundColor: colors.forestDark,
    borderColor: colors.gold,
  },
  optionButtonPressed: {
    opacity: 0.85,
  },
  optionTitle: {
    fontFamily: fonts.bodyBold,
    fontSize: 16,
    color: colors.textDark,
  },
  optionTitleSelected: {
    color: colors.parchment,
  },
  optionDescription: {
    ...typography.small,
    color: colors.textMuted,
    marginTop: spacing.xs,
  },
  optionDescriptionSelected: {
    color: colors.parchment,
  },
  bioInput: {
    minHeight: 120,
    borderWidth: 1,
    borderColor: colors.parchmentDark,
    borderRadius: 18,
    padding: spacing.md,
    backgroundColor: "rgba(247, 244, 213, 0.55)",
    fontFamily: fonts.body,
    fontSize: 16,
    color: colors.textDark,
  },
  errorBox: {
    backgroundColor: "rgba(211, 150, 140, 0.22)",
    borderWidth: 1,
    borderColor: colors.dustyRose,
    borderRadius: 16,
    padding: spacing.md,
    marginBottom: spacing.lg,
  },
  errorText: {
    ...typography.body,
    color: colors.parchment,
  },
});
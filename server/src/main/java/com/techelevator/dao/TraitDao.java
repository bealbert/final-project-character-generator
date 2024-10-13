package com.techelevator.dao;

import com.techelevator.model.Trait;

import java.util.List;

public interface TraitDao {
    List<String> getAllNames();
    List<Integer> getAllNameIds();
    List<String> getAllAppearances();
    List<Integer> getAllAppearanceIds();
    List<String> getAllDefiningTraits();
    List<Integer> getAllDefiningTraitIds();
    List<String> getAllAdventurerRoles();
    List<Integer> getAllAdventurerRoleIds();
    Trait getNameById(int nameId);
    Trait getAppearanceById(int appearanceId);
    Trait getDefiningTraitById(int definingTraitId);
    Trait getAdventurerRoleById(int adventurerRoleId);
    Trait createName(Trait name);
    Trait createAppearance(Trait appearance);
    Trait createDefiningTrait(Trait definingTrait);
    Trait createAdventurerRole(Trait adventurerRole);
    Trait updateName(Trait name);
    Trait updateAppearance(Trait appearance);
    Trait updateDefiningTrait(Trait definingTrait);
    Trait updateAdventurerRole(Trait adventurerRole);
    int deleteName(int nameId);
    int deleteAppearance(int appearanceId);
    int deleteDefiningTrait(int definingTraitId);
    int deleteAdventurerRole(int adventurerRoleId);
    int getNameIdByName(String name);
    int getAppearanceIdByAppearance(String appearance);
    int getDefiningTraitIdByDefiningTrait(String definingTrait);
    int getAdventurerRoleIdByAdventurerRole(String adventurerRole);
}

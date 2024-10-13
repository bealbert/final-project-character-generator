package com.techelevator.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class Character {
    @NotNull
    private int characterId;
    @NotNull
    private int nameId;
    @NotNull
    private int appearanceId;
    @NotNull
    private int definingTraitId;
    @NotNull
    private int adventurerRoleId;
    private int userId;

    private String name;

    private String appearance;

    private String definingTrait;

    private String adventurerRole;

    public String getAppearance() {
        return appearance;
    }

    public void setAppearance(String appearance) {
        this.appearance = appearance;
    }

    public String getDefiningTrait() {
        return definingTrait;
    }

    public void setDefiningTrait(String definingTrait) {
        this.definingTrait = definingTrait;
    }

    public String getAdventurerRole() {
        return adventurerRole;
    }

    public void setAdventurerRole(String adventurerRole) {
        this.adventurerRole = adventurerRole;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCharacterId() {
        return characterId;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

    public int getNameId() {
        return nameId;
    }

    public void setNameId(int nameId) {
        this.nameId = nameId;
    }

    public int getAppearanceId() {
        return appearanceId;
    }

    public void setAppearanceId(int appearanceId) {
        this.appearanceId = appearanceId;
    }

    public int getDefiningTraitId() {
        return definingTraitId;
    }

    public void setDefiningTraitId(int definingTraitId) {
        this.definingTraitId = definingTraitId;
    }

    public int getAdventurerRoleId() {
        return adventurerRoleId;
    }

    public void setAdventurerRoleId(int adventurerRoleId) {
        this.adventurerRoleId = adventurerRoleId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Character(int characterId, int nameId, int appearanceId, int definingTraitId, int adventurerRoleId, int userId) {
        this.characterId = characterId;
        this.nameId = nameId;
        this.appearanceId = appearanceId;
        this.definingTraitId = definingTraitId;
        this.adventurerRoleId = adventurerRoleId;
        this.userId = userId;
    }

    public Character() {
    }

    @Override
    public String toString() {
        return "Name: " + name +
                ", Appearance: " + appearance +
                ", A defining trait of this character is " + definingTrait +
                ". This character's role is " + adventurerRole +
                ".";
    }
}

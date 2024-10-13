package com.techelevator.dao;

import com.techelevator.model.Character;

import java.util.List;

public interface CharacterDao {
    /**
     * List all Characters in the database. Return an empty List if no characters are found in the datasource.
     *
     * @return List of Character objects. Empty list if no saved characters are found.
     */
    List<Character> getAllCharacters();

    /**
     * Find a character in the datastore using a character id. Return null if no character is found.
     *
     * @param characterId id of the character saved to the database.
     * @return Filled-out Character object. Null if no character is found in the database.
     */
    Character getCharacterById(int characterId);

    List<Character> getCharactersByUserId(int userId);

    Character createNewCharacter(Character character);

    Character updateCharacterById(Character character);

    int deleteCharacterById(int characterId);

    void getCharacterName(Character character);

    void getCharacterAppearance(Character character);

    void getCharacterDefiningTrait(Character character);

    void getCharacterAdventurerRole(Character character);
    List<Character> getCharactersBySharedName(int nameId);
    List<Character> getCharactersBySharedAppearance(int appearanceId);
    List<Character> getCharactersBySharedDefiningTrait(int definingTraitId);
    List<Character> getCharactersBySharedAdventurerRole(int adventurerRoleId);
}

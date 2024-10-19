package com.techelevator.controller;

import com.techelevator.dao.CharacterDao;
import com.techelevator.dao.TraitDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Character;
import com.techelevator.model.Trait;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@CrossOrigin
@RestController
@RequestMapping("/characters")
public class CharacterController {

    private final CharacterDao characterDao;
    private final TraitDao traitDao;

    public CharacterController(CharacterDao characterDao, TraitDao traitDao) {
        this.characterDao = characterDao;
        this.traitDao = traitDao;
    }
    @PreAuthorize(("isAuthenticated()"))
    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Character getCharacterById(@PathVariable int id) {
        return characterDao.getCharacterById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Character generateNewCharacter() {
        Character character = new Character();

        Random random = new Random(System.currentTimeMillis());

        List<Integer> nameIds = traitDao.getAllNameIds();
        List<Integer> appearanceIds = traitDao.getAllAppearanceIds();
        List<Integer> definingTraitIds = traitDao.getAllDefiningTraitIds();
        List<Integer> adventurerRoleIds = traitDao.getAllAdventurerRoleIds();
        character.setNameId(nameIds.get(random.nextInt(nameIds.size())));
        character.setAppearanceId(appearanceIds.get(random.nextInt(appearanceIds.size())));
        character.setDefiningTraitId(definingTraitIds.get(random.nextInt(definingTraitIds.size())));
        character.setAdventurerRoleId(adventurerRoleIds.get(random.nextInt(adventurerRoleIds.size())));

        return characterDao.createNewCharacter(character);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Character updateCharacter(@PathVariable int id, @Valid @RequestBody Character character) {
        try {
            character.setCharacterId(id);
            return characterDao.updateCharacterById(character);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Oops! Please log in to perform this action.");
        }
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCharacterById(@PathVariable int id) {
        try {
            characterDao.deleteCharacterById(id);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You must be logged in to delete characters.");
        }
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Character> searchByName(@RequestParam(name = "trait_name", defaultValue = "") String searchedName) {
        List<String> names = traitDao.getAllNames();
        if (searchedName.isEmpty()) {
            return characterDao.getAllCharacters();
        } else {
            List<Character> characters = new ArrayList<>();
            searchedName = searchedName.toLowerCase();
            for (String name : names) {
                if (name != null) {
                    String lowerName = name.toLowerCase();
                    if (lowerName.contains(searchedName)) {
                        int nameId = traitDao.getNameIdByName(name);
                        List<Character> charactersBySharedName = characterDao.getCharactersBySharedName(nameId);
                        if (charactersBySharedName != null) {
                            characters.addAll(charactersBySharedName);
                        }
                    }
                }
            }
            return characters;
        }
    }
}

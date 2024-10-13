package com.techelevator.controller;

import com.techelevator.dao.CharacterDao;
import com.techelevator.model.Character;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    private final CharacterDao characterDao;

    public UserController(CharacterDao characterDao) {
        this.characterDao = characterDao;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(path = "/{user_id}/characters/{character_id}")
    public Character saveCharacterToUser(@PathVariable (name = "user_id") int userId, @PathVariable (name = "character_id") int characterId, Character character) {
        Character savedCharacter = characterDao.getCharacterById(characterId);
        savedCharacter.setUserId(userId);
        return characterDao.updateCharacterById(savedCharacter);
    }
}

package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Character;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCharacterDao implements CharacterDao {
    private final JdbcTemplate template;

    public JdbcCharacterDao(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    private RowMapper<Character> mapper = new RowMapper<Character>() {
        @Override
        public Character mapRow(ResultSet rs, int rowNum) throws SQLException {
            int characterId = rs.getInt("character_id");
            int nameId = rs.getInt("name_id");
            int appearanceId = rs.getInt("appearance_id");
            int definingTraitId = rs.getInt("defining_trait_id");
            int adventurerRoleId = rs.getInt("adventurer_role_id");
            int userId = rs.getInt("user_id");
            Character character = new Character(characterId, nameId, appearanceId, definingTraitId, adventurerRoleId, userId);
            getCharacterName(character);
            getCharacterAppearance(character);
            getCharacterAdventurerRole(character);
            getCharacterDefiningTrait(character);
            return character;
        }
    };

    @Override
    public List<Character> getAllCharacters() {
        String sql = "SELECT * FROM characters ORDER BY character_id";
        return template.query(sql, mapper);
    }

    @Override
    public Character getCharacterById(int characterId) {
        String sql = "SELECT * FROM characters WHERE character_id = ?";
        try {
            List<Character> characters = template.query(sql, mapper, characterId);
            if (!characters.isEmpty()) {
                Character character = characters.get(0);
                return character;
            }
        } catch (DataAccessException e) {
            //todo: throw daoexception
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Character> getCharactersByUserId(int userId) {
        String sql = "SELECT * FROM characters WHERE user_id = ?";
        try {
            List<Character> characters = template.query(sql, mapper, userId);
            if (!characters.isEmpty()) {
                return characters;
            }
        } catch (DaoException e) {
            throw new DaoException(e.getMessage());
        }
        return null;
    }

    @Override
    public Character createNewCharacter(Character character) {
        String sql = "INSERT INTO characters (name_id, appearance_id, defining_trait_id, adventurer_role_id) VALUES (?, ?, ?, ?) RETURNING character_id";
        int newCharacterId = template.queryForObject(sql, int.class, character.getNameId(), character.getAppearanceId(), character.getDefiningTraitId(), character.getAdventurerRoleId());
        return getCharacterById(newCharacterId);
    }

    @Override
    public Character updateCharacterById(Character character) {
        String sql = "UPDATE characters SET name_id = ?, appearance_id = ?, defining_trait_id = ?, adventurer_role_id = ?, user_id = ?" +
                "WHERE character_id = ?";
        Character updatedCharacter = null;
        template.update(sql, character.getNameId(), character.getAppearanceId(), character.getDefiningTraitId(), character.getAdventurerRoleId(), character.getUserId(), character.getCharacterId());
        updatedCharacter = getCharacterById(character.getCharacterId());
        return updatedCharacter;
    }

    @Override
    public int deleteCharacterById(int characterId) {
        String deleteCharacterSql = "DELETE FROM characters WHERE character_id = ?";
        int numberOfRows = 0;
        numberOfRows = template.update(deleteCharacterSql, characterId);
        return numberOfRows;
    }

    @Override
    public void getCharacterName(Character character) {
        String sql = "SELECT name FROM character_names WHERE name_id = ?";
        String name = template.queryForObject(sql, String.class, character.getNameId());
        character.setName(name);
    }

    @Override
    public void getCharacterAppearance(Character character) {
        String sql = "SELECT appearance_description FROM appearances WHERE appearance_id = ?";
        String appearance = template.queryForObject(sql, String.class, character.getAppearanceId());
        character.setAppearance(appearance);
    }

    @Override
    public void getCharacterDefiningTrait(Character character) {
        String sql = "SELECT defining_trait FROM defining_traits WHERE defining_trait_id = ?";
        String definingTrait = template.queryForObject(sql, String.class, character.getDefiningTraitId());
        character.setDefiningTrait(definingTrait);
    }

    @Override
    public void getCharacterAdventurerRole(Character character) {
        String sql = "SELECT adventurer_role FROM adventurer_roles WHERE adventurer_role_id = ?";
        String adventurerRole = template.queryForObject(sql, String.class, character.getAdventurerRoleId());
        character.setAdventurerRole(adventurerRole);
    }

    @Override
    public List<Character> getCharactersBySharedName(int nameId) {
        String sql = "SELECT * FROM characters WHERE name_id = ? ORDER BY character_id";
        List<Character> characters = template.query(sql, mapper, nameId);
        if (!characters.isEmpty()) {
            return characters;
        }
        return null;
    }

    @Override
    public List<Character> getCharactersBySharedAppearance(int appearanceId) {
        String sql = "SELECT * FROM characters WHERE appearance_id = ? ORDER BY character_id";
        List<Character> characters = template.query(sql, mapper, appearanceId);
        if(!characters.isEmpty()) {
            return characters;
        }
        return null;
    }

    @Override
    public List<Character> getCharactersBySharedDefiningTrait(int definingTraitId) {
        String sql = "SELECT * FROM characters WHERE defining_trait_id = ? ORDER BY character_id";
        List<Character> characters = template.query(sql, mapper, definingTraitId);
        if(!characters.isEmpty()) {
            return characters;
        }
        return null;
    }

    @Override
    public List<Character> getCharactersBySharedAdventurerRole(int adventurerRoleId) {
        String sql = "SELECT * FROM characters WHERE adventurer_role_id = ? ORDER BY character_id";
        List<Character> characters = template.query(sql, mapper, adventurerRoleId);
        if(!characters.isEmpty()) {
            return characters;
        }
        return null;
    }
}

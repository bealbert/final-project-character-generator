package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Trait;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
@Component
public class JdbcTraitDao implements TraitDao {
    private final JdbcTemplate template;

    public JdbcTraitDao(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    private RowMapper<Trait> nameMapper = new RowMapper<Trait>() {
        @Override
        public Trait mapRow(ResultSet rs, int rowNum) throws SQLException {
            int nameId = rs.getInt("name_id");
            String savedName = rs.getString("name");
            Trait name = new Trait(nameId, savedName);
            return name;
        }
    };

    private RowMapper<Trait> appearanceMapper = new RowMapper<Trait>() {
        @Override
        public Trait mapRow(ResultSet rs, int rowNum) throws SQLException {
            int appearanceId = rs.getInt("appearance_id");
            String appearanceDescription = rs.getString("appearance_description");
            return new Trait(appearanceId, appearanceDescription);
        }
    };

    private RowMapper<Trait> definingTraitMapper = new RowMapper<Trait>() {
        @Override
        public Trait mapRow(ResultSet rs, int rowNum) throws SQLException {
            int definingTraitId = rs.getInt("defining_trait_id");
            String definingTraitDescription = rs.getString("defining_trait");
            return new Trait(definingTraitId, definingTraitDescription);
        }
    };

    private RowMapper<Trait> adventurerRoleMapper = new RowMapper<Trait>() {
        @Override
        public Trait mapRow(ResultSet rs, int rowNum) throws SQLException {
            int adventurerRoleId = rs.getInt("adventurer_role_id");
            String adventurerRoleName = rs.getString("adventurer_role");
            return new Trait(adventurerRoleId, adventurerRoleName);
        }
    };
    @Override
    public List<String> getAllNames() {
        List<String> names = new ArrayList<>();
        String sql = "SELECT * FROM character_names ORDER BY name_id";
        SqlRowSet rowSet = template.queryForRowSet(sql);
        while(rowSet.next()) {
            names.add(rowSet.getString("name"));
        }
        return names;
    }

    @Override
    public List<Trait> getAllNameObj() {
        String sql = "SELECT * FROM character_names ORDER BY name_id";
        try {
            return template.query(sql, nameMapper);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Trait> getAllAppearanceObj() {
        String sql = "SELECT * FROM appearances ORDER BY appearance_id";
        try {
            return template.query(sql, appearanceMapper);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Trait> getAllDefiningTraitObj() {
        String sql = "SELECT * FROM defining_traits ORDER BY defining_trait_id";
        try {
            return template.query(sql, definingTraitMapper);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Trait> getAllAdventurerRoleObj() {
        String sql = "SELECT * FROM adventurer_roles ORDER BY adventurer_role_id";
        try {
            return template.query(sql, adventurerRoleMapper);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Integer> getAllNameIds() {
        String sql = "SELECT name_id FROM character_names ORDER BY name_id";
        SqlRowSet rowSet = template.queryForRowSet(sql);
        List<Integer> nameIds = new ArrayList<>();
        while (rowSet.next()) {
            nameIds.add(rowSet.getInt("name_id"));
        }
        return nameIds;
    }

    @Override
    public List<String> getAllAppearances() {
        List<String> appearances = new ArrayList<>();
        String sql = "SELECT appearance_description FROM appearances ORDER BY appearance_id";
        SqlRowSet rowSet = template.queryForRowSet(sql);
        while(rowSet.next()) {
            appearances.add(rowSet.getString("appearance_description"));
        }
        return appearances;
    }

    @Override
    public List<Integer> getAllAppearanceIds() {
        String sql = "SELECT appearance_id FROM appearances ORDER BY appearance_id";
        SqlRowSet rowSet = template.queryForRowSet(sql);
        List<Integer> appearanceIds = new ArrayList<>();
        while (rowSet.next()) {
            appearanceIds.add(rowSet.getInt("appearance_id"));
        }
        return appearanceIds;
    }

    @Override
    public List<String> getAllDefiningTraits() {
        List<String> definingTraits = new ArrayList<>();
        String sql = "SELECT defining_trait FROM defining_traits ORDER BY defining_trait_id";
        SqlRowSet rowSet = template.queryForRowSet(sql);
        while(rowSet.next()) {
            definingTraits.add(rowSet.getString("defining_trait"));
        }
        return definingTraits;
    }

    @Override
    public List<Integer> getAllDefiningTraitIds() {
        String sql = "SELECT defining_trait_id FROM defining_traits ORDER BY defining_trait_id";
        SqlRowSet rowSet = template.queryForRowSet(sql);
        List<Integer> definingTraitIds = new ArrayList<>();
        while (rowSet.next()) {
            definingTraitIds.add(rowSet.getInt("defining_trait_id"));
        }
        return definingTraitIds;
    }

    @Override
    public List<String> getAllAdventurerRoles() {
        List<String> adventurerRoles = new ArrayList<>();
        String sql = "SELECT adventurer_role FROM adventurer_roles ORDER BY adventurer_role_id";
        SqlRowSet rowSet = template.queryForRowSet(sql);
        while(rowSet.next()) {
            adventurerRoles.add(rowSet.getString("adventurer_role"));
        }
        return adventurerRoles;
    }

    @Override
    public List<Integer> getAllAdventurerRoleIds() {
        String sql = "SELECT adventurer_role_id FROM adventurer_roles ORDER BY adventurer_role_id";
        SqlRowSet rowSet = template.queryForRowSet(sql);
        List<Integer> adventurerRoleIds = new ArrayList<>();
        while (rowSet.next()) {
            adventurerRoleIds.add(rowSet.getInt("adventurer_role_id"));
        }
        return adventurerRoleIds;
    }

    @Override
    public Trait getNameById(int nameId) {
        String sql = "SELECT * FROM character_names WHERE name_id = ?";
        try {
            List<Trait> names = template.query(sql, nameMapper, nameId);
            if (!names.isEmpty()) {
                Trait name = names.get(0);
                return name;
            }
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Trait getAppearanceById(int appearanceId) {
        String sql = "SELECT * FROM appearances WHERE appearance_id = ?";
        try {
            List<Trait> appearances = template.query(sql, appearanceMapper, appearanceId);
            if (!appearances.isEmpty()) {
                Trait appearance = appearances.get(0);
                return appearance;
            }
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Trait getDefiningTraitById(int definingTraitId) {
        String sql = "SELECT * FROM defining_traits WHERE defining_trait_id = ?";
        try {
            List<Trait> definingTraits = template.query(sql, definingTraitMapper, definingTraitId);
            if (!definingTraits.isEmpty()) {
                Trait definingTrait = definingTraits.get(0);
                return definingTrait;
            }
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Trait getAdventurerRoleById(int adventurerRoleId) {
        String sql = "SELECT * FROM adventurer_roles WHERE adventurer_role_id = ?";
        try {
            List<Trait> adventurerRoles = template.query(sql, adventurerRoleMapper, adventurerRoleId);
            if (!adventurerRoles.isEmpty()) {
                Trait adventurerRole = adventurerRoles.get(0);
                return adventurerRole;
            }
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Trait createName(Trait name) {
        String sql = "INSERT INTO character_names (name) VALUES (?) RETURNING name_id";
        int newNameId = template.queryForObject(sql, int.class, name.getTraitDescription());
        Trait newName = new Trait(newNameId, name.getTraitDescription());
        return newName;
    }

    @Override
    public Trait createAppearance(Trait appearance) {
        String sql = "INSERT INTO appearances (appearance_description) VALUES (?) RETURNING appearance_id";
        int newAppearanceId = template.queryForObject(sql, int.class, appearance.getTraitDescription());
        Trait newAppearance = new Trait(newAppearanceId, appearance.getTraitDescription());
        return newAppearance;
    }

    @Override
    public Trait createDefiningTrait(Trait definingTrait) {
        String sql = "INSERT INTO defining_traits (defining_trait) VALUES (?) RETURNING defining_trait_id";
        int newDefiningTraitId = template.queryForObject(sql, int.class, definingTrait.getTraitDescription());
        Trait newDefiningTrait = new Trait(newDefiningTraitId, definingTrait.getTraitDescription());
        return newDefiningTrait;
    }

    @Override
    public Trait createAdventurerRole(Trait adventurerRole) {
        String sql = "INSERT INTO adventurer_roles (adventurer_role) VALUES (?) RETURNING adventurer_role_id";
        int newAdventurerRoleId = template.queryForObject(sql, int.class, adventurerRole.getTraitDescription());
        Trait newAdventurerRole = new Trait(newAdventurerRoleId, adventurerRole.getTraitDescription());
        return newAdventurerRole;
    }

    @Override
    public Trait updateName(Trait name) {
        String sql = "UPDATE character_names SET name = ? WHERE name_id = ?";
        Trait updatedTrait = null;
        template.update(sql, name.getTraitDescription(), name.getTraitId());
        updatedTrait = getNameById(name.getTraitId());
        return updatedTrait;
    }

    @Override
    public Trait updateAppearance(Trait appearance) {
        String sql = "UPDATE appearances SET appearance_description = ? WHERE appearance_id = ?";
        Trait updatedTrait = null;
        template.update(sql, appearance.getTraitDescription(), appearance.getTraitId());
        updatedTrait = getAppearanceById(appearance.getTraitId());
        return updatedTrait;
    }

    @Override
    public Trait updateDefiningTrait(Trait definingTrait) {
        String sql = "UPDATE defining_traits SET defining_trait = ? WHERE defining_trait_id = ?";
        Trait updatedTrait = null;
        template.update(sql, definingTrait.getTraitDescription(), definingTrait.getTraitId());
        updatedTrait = getDefiningTraitById(definingTrait.getTraitId());
        return updatedTrait;
    }

    @Override
    public Trait updateAdventurerRole(Trait adventurerRole) {
        String sql = "UPDATE adventurer_roles SET adventurer_role = ? WHERE adventurer_role_id = ?";
        Trait updatedTrait = null;
        template.update(sql, adventurerRole.getTraitDescription(), adventurerRole.getTraitId());
        updatedTrait = getAdventurerRoleById(adventurerRole.getTraitId());
        return updatedTrait;
    }

    @Override
    public int deleteName(int nameId) {
        String deleteCharacterSql = "DELETE FROM characters WHERE name_id = ?";
        String deleteNameSql = "DELETE FROM character_names WHERE name_id = ?";
        int numberOfRows = 0;
        template.update(deleteCharacterSql, nameId);
        numberOfRows = template.update(deleteNameSql, nameId);
        return numberOfRows;
    }

    @Override
    public int deleteAppearance(int appearanceId) {
        String deleteCharacterSql = "DELETE FROM characters WHERE appearance_id = ?";
        String deleteAppearanceSql = "DELETE FROM appearances WHERE appearance_id = ?";
        int numberOfRows = 0;
        template.update(deleteCharacterSql, appearanceId);
        numberOfRows = template.update(deleteAppearanceSql, appearanceId);
        return numberOfRows;
    }

    @Override
    public int deleteDefiningTrait(int definingTraitId) {
        String deleteCharacterSql = "DELETE FROM characters WHERE defining_trait_id = ?";
        String deleteDefiningTraitSql = "DELETE FROM defining_traits WHERE defining_trait_id = ?";
        int numberOfRows = 0;
        template.update(deleteCharacterSql, definingTraitId);
        numberOfRows = template.update(deleteDefiningTraitSql, definingTraitId);
        return numberOfRows;
    }

    @Override
    public int deleteAdventurerRole(int adventurerRoleId) {
        String deleteCharacterSql = "DELETE FROM characters WHERE adventurer_role_id = ?";
        String deleteAdventurerRoleSql = "DELETE FROM adventurer_roles WHERE adventurer_role_id = ?";
        int numberOfRows = 0;
        template.update(deleteCharacterSql, adventurerRoleId);
        numberOfRows = template.update(deleteAdventurerRoleSql, adventurerRoleId);
        return numberOfRows;
    }

    @Override
    public int getNameIdByName(String name) {
        String sql = "SELECT * FROM character_names WHERE name ILIKE ?";
        try {
            List<Trait> nameIds = template.query(sql, nameMapper, name);
            if (!nameIds.isEmpty()) {
                Trait searchedName = nameIds.get(0);
                return searchedName.getTraitId();
            }
            return 0;
        } catch (DaoException e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public int getAppearanceIdByAppearance(String appearance) {
        String sql = "SELECT appearance_id FROM appearances WHERE appearance_description = ?";
        try {
            List<Trait> appearanceIds = template.query(sql, appearanceMapper, appearance);
            if (!appearanceIds.isEmpty()) {
                Trait searchedAppearance = appearanceIds.get(0);
                return searchedAppearance.getTraitId();
            }
            return 0;
        } catch (DaoException e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public int getDefiningTraitIdByDefiningTrait(String definingTrait) {
        String sql = "SELECT defining_trait_id FROM defining_traits WHERE defining_trait = ?";
        try {
            List<Trait> definingTraitIds = template.query(sql, definingTraitMapper, definingTrait);
            if (!definingTraitIds.isEmpty()) {
                Trait searchedDefiningTrait = definingTraitIds.get(0);
                return searchedDefiningTrait.getTraitId();
            }
            return 0;
        } catch (DaoException e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public int getAdventurerRoleIdByAdventurerRole(String adventurerRole) {
        String sql = "SELECT adventurer_role_id FROM adventurer_roles WHERE adventurer_role = ?";
        try {
            List<Trait> adventurerRoleIds = template.query(sql, adventurerRoleMapper, adventurerRole);
            if (!adventurerRoleIds.isEmpty()) {
                Trait searchedAdventurerRole = adventurerRoleIds.get(0);
                return searchedAdventurerRole.getTraitId();
            }
            return 0;
        } catch (DaoException e) {
            throw new DaoException(e.getMessage());
        }
    }
}

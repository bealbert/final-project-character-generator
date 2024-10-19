package com.techelevator.controller;

import com.techelevator.dao.TraitDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Trait;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/traits")
public class TraitsController {
    private final TraitDao traitDao;

    public TraitsController(TraitDao traitDao) {
        this.traitDao = traitDao;
    }

//    @GetMapping(path = "/names")
//    @ResponseStatus(HttpStatus.OK)
//    public List<String> getAllNames() {
//        return traitDao.getAllNames();
//    }

    @GetMapping(path = "/names")
    @ResponseStatus(HttpStatus.OK)
    public List<Trait> getAllNameObj() {
        return traitDao.getAllNameObj();
    }

//    @GetMapping(path = "/appearances")
//    @ResponseStatus(HttpStatus.OK)
//    public List<String> getAllAppearances() {
//        return traitDao.getAllAppearances();
//    }

    @GetMapping(path = "/appearances")
    @ResponseStatus(HttpStatus.OK)
    public List<Trait> getAllAppearanceObj() {
        return traitDao.getAllAppearanceObj();
    }

//    @GetMapping(path = "/defining_traits")
//    @ResponseStatus(HttpStatus.OK)
//    public List<String> getAllDefiningTraits() {
//        return traitDao.getAllDefiningTraits();
//    }

    @GetMapping(path = "/defining_traits")
    @ResponseStatus(HttpStatus.OK)
    public List<Trait> getAllDefiningTraitObj() {
        return traitDao.getAllDefiningTraitObj();
    }

//    @GetMapping(path = "/adventurer_roles")
//    @ResponseStatus(HttpStatus.OK)
//    public List<String> getAllAdventurerRoles() {
//        return traitDao.getAllAdventurerRoles();
//    }

    @GetMapping(path = "/adventurer_roles")
    @ResponseStatus(HttpStatus.OK)
    public List<Trait> getAllAdventurerRoleObj() {
        return traitDao.getAllAdventurerRoleObj();
    }

    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/names")
    public Trait addNewName(@Valid @RequestBody Trait trait) {
        try {
            return traitDao.createName(trait);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Oops! You must be logged in to perform this action.");
        }
    }

    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/appearances")
    public Trait addNewAppearance(@Valid @RequestBody Trait trait) {
        try {
            return traitDao.createAppearance(trait);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Oops! You must be logged in to perform this action.");
        }
    }

    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/defining_traits")
    public Trait addNewDefiningTrait(@Valid @RequestBody Trait trait) {
        try {
            return traitDao.createDefiningTrait(trait);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Oops! You must be logged in to perform this action.");
        }
    }

    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/adventurer_roles")
    public Trait addNewAdventurerRole(@Valid @RequestBody Trait trait) {
        try {
            return traitDao.createAdventurerRole(trait);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Oops! You must be logged in to perform this action.");
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(path = "/names/{id}")
    public Trait updateName(@Valid @RequestBody Trait name, @PathVariable int id) {
        try {
            name.setTraitId(id);
            return traitDao.updateName(name);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Oops! You must be an Admin to update this trait.");
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(path = "/appearances/{id}")
    public Trait updateAppearance(@Valid @RequestBody Trait appearance, @PathVariable int id) {
        try {
            appearance.setTraitId(id);
            return traitDao.updateAppearance(appearance);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Oops! You must be an Admin to update this trait.");
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(path = "/defining_traits/{id}")
    public Trait updateDefiningTrait(@Valid @RequestBody Trait definingTrait, @PathVariable int id) {
        try {
            definingTrait.setTraitId(id);
            return traitDao.updateDefiningTrait(definingTrait);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Oops! You must be an Admin to update this trait.");
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(path = "/adventurer_roles/{id}")
    public Trait updateAdventurerRole(@Valid @RequestBody Trait adventurerRole, @PathVariable int id) {
        try {
            adventurerRole.setTraitId(id);
            return traitDao.updateAdventurerRole(adventurerRole);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Oops! You must be an Admin to update this trait.");
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/names/{id}")
    public void deleteName(@PathVariable int id) {
        try {
            traitDao.deleteName(id);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Oops! You must be an Admin to delete this trait.");
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/appearances/{id}")
    public void deleteAppearance(@PathVariable int id) {
        try {
            traitDao.deleteAppearance(id);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Oops! You must be an Admin to delete this trait.");
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/defining_traits/{id}")
    public void deleteDefiningTrait(@PathVariable int id) {
        try {
            traitDao.deleteDefiningTrait(id);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Oops! You must be an Admin to delete this trait.");
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/adventurer_roles/{id}")
    public void deleteAdventurerRole(@PathVariable int id) {
        try {
            traitDao.deleteAdventurerRole(id);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Oops! You must be an Admin to delete this trait.");
        }
    }
}

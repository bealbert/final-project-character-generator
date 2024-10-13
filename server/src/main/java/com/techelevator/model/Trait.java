package com.techelevator.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Map;

public class Trait {
    int traitId;
    @NotEmpty
    String traitDescription;

    public int getTraitId() {
        return traitId;
    }

    public void setTraitId(int traitId) {
        this.traitId = traitId;
    }

    public String getTraitDescription() {
        return traitDescription;
    }

    public void setTraitDescription(String traitDescription) {
        this.traitDescription = traitDescription;
    }

    public Trait(int traitId, String traitDescription) {
        this.traitId = traitId;
        this.traitDescription = traitDescription;
    }
}

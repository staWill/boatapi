package ch.boatapi.model;


import jakarta.validation.constraints.NotNull;

public class BoatDto {

    @NotNull(message = "boat name cannot be null")
    private String name;

    @NotNull(message = "boat description cannot be null")
    private String description;

    public BoatDto(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

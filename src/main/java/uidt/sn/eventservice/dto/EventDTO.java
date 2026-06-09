package uidt.sn.eventservice.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EventDTO {
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotNull
    private LocalDate date;

    @NotBlank
    private String location;

    public @NotBlank String getTitle() {
        return title;
    }

    public @NotBlank String getDescription() {
        return description;
    }

    public @NotNull LocalDate getDate() {
        return date;
    }

    public @NotBlank String getLocation() {
        return location;
    }

    public void setTitle(@NotBlank String title) {
        this.title = title;
    }

    public void setDescription(@NotBlank String description) {
        this.description = description;
    }

    public void setDate(@NotNull LocalDate date) {
        this.date = date;
    }

    public void setLocation(@NotBlank String location) {
        this.location = location;
    }

    public void setCapacity(@Min(1) int capacity) {
        this.capacity = capacity;
    }

    @Min(1)
    public int getCapacity() {
        return capacity;
    }

    @Min(1)
    private int capacity;
}
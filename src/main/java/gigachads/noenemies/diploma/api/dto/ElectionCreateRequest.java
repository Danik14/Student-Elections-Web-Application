package gigachads.noenemies.diploma.api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ElectionCreateRequest {
    @NotNull
    private String description;
    @NotNull
    private LocalDateTime deadline;
}

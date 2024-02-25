package gigachads.noenemies.diploma.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import gigachads.noenemies.diploma.domain.model.StageId;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Builder
@Getter
public class StageResponse {
    @NonNull
    private final StageId id;
    @NonNull
    private final String description;
    @NonNull
    private final String status;
    private final boolean votable;
    @NonNull
    private final Integer number;
    @NonNull
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private final LocalDateTime deadline;

    static class CustomLocalDateTimeDeserializer extends LocalDateTimeDeserializer {
        public CustomLocalDateTimeDeserializer() {
            super(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }
    }
}

package mate.academy.rickandmorty.dto.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExternalResponseDataDto {
    private ExternalMetaDataDto info;
    @JsonProperty(value = "results")
    private List<ExternalCharacterDataDto> characters;
}

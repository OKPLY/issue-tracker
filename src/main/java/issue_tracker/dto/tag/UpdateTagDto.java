package issue_tracker.dto.tag;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTagDto {
    @JsonIgnore
    private Long id;

    private String name;
}

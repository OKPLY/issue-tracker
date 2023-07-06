package issue_tracker.dto.type;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTypeDto {
    @JsonIgnore
    private Long id;

    private String name;

}

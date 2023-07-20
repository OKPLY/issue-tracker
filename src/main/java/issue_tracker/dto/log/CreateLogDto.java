package issue_tracker.dto.log;


import issue_tracker.domain.User;
import lombok.Data;

@Data
public class CreateLogDto {
    private String action;
    private String clazz;

}

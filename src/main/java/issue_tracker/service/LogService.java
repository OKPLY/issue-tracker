package issue_tracker.service;

import issue_tracker.domain.Log;
import issue_tracker.dto.log.CreateLogDto;

import java.util.List;

public interface LogService {

    List<Log> findAll();

    Log findById(Long id);

    Log create(CreateLogDto creatLog);

}

package issue_tracker.service.impl;

import issue_tracker.domain.Log;
import issue_tracker.domain.Tag;
import issue_tracker.dto.log.CreateLogDto;
import issue_tracker.repository.LogRepo;
import issue_tracker.service.LogService;
import issue_tracker.utility.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {
    private final LogRepo logRepo;
    private final Util util;
    @Override
    public List<Log> findAll() {
        return logRepo.findAllByOrderByIdDesc();
    }

    @Override
    public Log findById(Long id) {
       var res = logRepo.findById(id);

       if(res.isPresent())
           return res.get();

       throw new NoSuchElementException("Log Not Found");
    }

    @Override
    public Log create(CreateLogDto creatLog) {
        Log log = new Log();
        log.setUser(util.getUserFromContext());
        log.setAction(creatLog.getAction());
        var split = creatLog.getClazz().split("\\.");
        log.setClazz(split[split.length-1]);

        return logRepo.save(log);
    }
}

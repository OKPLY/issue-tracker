package issue_tracker.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import issue_tracker.domain.Log;
import issue_tracker.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("logs")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@CrossOrigin
public class LogController {

    private final LogService logService;

    @GetMapping
    public List<Log> findAll() {
        return logService.findAll();
    }

}

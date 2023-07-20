package issue_tracker.aspect;

import issue_tracker.dto.log.CreateLogDto;
import issue_tracker.service.LogService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class LogAspect {
    private final LogService logService;
    @Pointcut("@annotation(issue_tracker.aspect.annotation.Log)")
    public void log(){}

    @After("log()")
    public void logAction(JoinPoint joinPoint){
        String action = joinPoint.getSignature().getName();
        String clazz =  joinPoint.getTarget().getClass().getName();

        CreateLogDto logDto = new CreateLogDto();
        logDto.setAction(action);
        logDto.setClazz(clazz);

        logService.create(logDto);
    }

}

package issue_tracker.aspect;

import issue_tracker.dto.log.CreateLogDto;
import issue_tracker.service.LogService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Parameter;

@Aspect
@Component
@RequiredArgsConstructor
public class LogAspect {
    private final LogService logService;

    @Pointcut("@annotation(issue_tracker.aspect.annotation.Log)")
    public void log() {
    }

    @After("log()")
    public void logAction(JoinPoint joinPoint) {
        String action = joinPoint.getSignature().getName();
        String clazz = joinPoint.getTarget().getClass().getName();

        // get the fields of the method
        Parameter[] parameters = joinPoint.getSignature().getDeclaringType().getDeclaredMethods()[0].getParameters();

        int i = 0;
        Long id = null;
        for (Parameter parameter : parameters) {
            if (!parameter.isNamePresent()) {
                throw new IllegalArgumentException("Parameter names are not present!");
            }

            String parameterName = parameter.getName();

            try {
                if (parameterName.equalsIgnoreCase("id")) {
                    if (joinPoint.getArgs().length > 0)
                        id = (Long) joinPoint.getArgs()[i];
                }
            } catch (Exception e) {
                try {
                    if (parameterName.equalsIgnoreCase("id")) {
                        if (joinPoint.getArgs().length > 0)
                            id = Long.parseLong(String.valueOf((Integer) joinPoint.getArgs()[i]));
                    }
                } catch (Exception ex) {
                }
            }
        }


        CreateLogDto logDto = new CreateLogDto();
        logDto.setAction(action);
        logDto.setClazz(clazz);
        logDto.setChangeId(id);

        logService.create(logDto);
    }

}

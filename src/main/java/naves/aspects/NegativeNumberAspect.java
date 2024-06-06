package naves.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class NegativeNumberAspect {

	private static final Logger logger = LoggerFactory.getLogger(NegativeNumberAspect.class);
	
	@After("execution(* naves.controller.NavesController.getById(..))")
    public void logIfNegativeNumber(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof Long && (Long) arg < 0) {
                logger.warn("Se ha introducido un numero negativo: " + arg);
            }
        }
    }
}

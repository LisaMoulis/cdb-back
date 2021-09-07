package aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class PerformanceAspect {

	private static final Logger LOGGER =  LoggerFactory.getLogger(PerformanceAspect.class);
	
	@Around("aop.pointcut.ServicePointcut.AllServices()")
	public Object ComputerTimer(final ProceedingJoinPoint joinpoint) throws Throwable 
	{
		Object returnValue;
		StopWatch clock = new StopWatch(getClass().getName());
	    try {
	      clock.start(joinpoint.toString());
	      returnValue = joinpoint.proceed();
	    } finally {
	      clock.stop();
	      LOGGER.info("Execution time :\n\n " + clock.prettyPrint());
	    }
	    return returnValue;
	}
}

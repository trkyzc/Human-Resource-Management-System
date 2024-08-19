package com.example.hrms.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
@Component
public class LoggingAspect {
	
	private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
	
//	@AfterReturning(pointcut = "execution(* com.example.hrms.business.concretes..*(..))", returning = "result") 
//	public void logAfter(JoinPoint joinPoint, Object result) {      //jointPoint: yürütülen method, result: dönen değer
//		String methodName = joinPoint.getSignature().getName();
//	    logger.debug("<< {}() - {}", methodName, result);
//	}
//	
//	@AfterThrowing(pointcut = "execution(* com.example.hrms.business.concretes..*(..))", throwing = "exception")
//	public void logException(JoinPoint joinPoint, Throwable exception) {
//		String methodName = joinPoint.getSignature().getName();
//		logger.error("<< {}() - {}", methodName, exception.getMessage());
//	}
	
//	 @Around(value = "execution(* com.example.hrms.business.concretes..*(..))")
//	    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
//	        Object[] args = joinPoint.getArgs();
//	        String methodName = joinPoint.getSignature().getName();
//	        logger.debug(">> {}() - {}", methodName, Arrays.toString(args));
//	        Object result = joinPoint.proceed();
//	        logger.debug("<< {}() - {}", methodName, result);
//	        return result;
//	    }
	 
	 	@Before(value = "execution(* com.example.hrms.business.concretes..*(..))")  //pointcut
	    public void logBefore(JoinPoint joinPoint) {
	        Object[] args = joinPoint.getArgs();
	        String methodName = joinPoint.getSignature().getName();
	        //logger.debug(">> {}() - {}", methodName, Arrays.toString(args));
	        logger.debug("Before Advice: " + joinPoint.getSignature().getName() + " method is called");
	    }
	 
		@After(value = "execution(* com.example.hrms.business.concretes..*(..))")
		public void logAfter(JoinPoint joinPoint) {
			String methodName = joinPoint.getSignature().getName();
			//logger.debug("<< {}()", methodName);
			logger.debug("After Advice: " + joinPoint.getSignature().getName() + " method is called");
		}
		
		@Around("@annotation(LogExecutionTime)") 
		public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable{
			final long start = System.currentTimeMillis();
			Object proceed = joinPoint.proceed(); //proceed: ilgili methodu çalıştır. Metodun döndürdüğü değeri döndürür.
			final long executionTime = System.currentTimeMillis() - start;
			logger.info(joinPoint.getSignature() + " executed in " + executionTime + "ms");
			return proceed;
		}


}

package www.spring.security.common.module;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * 2014.02.21 금요일
 * @author 위메프 개발3팀 유창근
 * AOP 관련 작업
 */

@Aspect
public class LogWriter {
	private static final Logger logger = Logger.getLogger(LogWriter.class);
	
	@Before(value="execution(* www.spring.security.controller.*.*(..))",argNames="call")
	public void logBeforeWrite(JoinPoint call) throws Throwable{
		Object[] args 	= call.getArgs();
		StringBuffer strParam = new StringBuffer();
		
		for(int i = 0;i < args.length;i++){
			if(i != args.length){
				strParam.append(args[i].toString() + ",");
			}else{
				strParam.append(args[i].toString());
			}
		}
		
		logger.info("******************Before Start****************************");
		logger.info("Class  Name : " + call.getTarget().getClass().getName());
		logger.info("Method Name : " + call.toShortString());
		logger.info("Parameter   : " + strParam.toString());
		logger.info("******************Before End******************************");
	}
	
	@AfterReturning(value="execution(* www.spring.security.controller.*.*(..))",argNames="call")
	public void logAfterWrite(JoinPoint call) throws Throwable{
		Object[] args 	= call.getArgs();
		StringBuffer strParam = new StringBuffer();
		
		for(int i = 0;i < args.length;i++){
			if(i != args.length){
				strParam.append(args[i].toString() + ",");
			}else{
				strParam.append(args[i].toString());
			}
		}
		
		logger.info("******************After Start****************************");
		logger.info("Class  Name : " + call.getTarget().getClass().getName());
		logger.info("Method Name : " + call.toShortString());
		logger.info("Parameter   : " + strParam.toString());
		logger.info("******************After End******************************");
	}
	
	@AfterThrowing(value="execution(* www.spring.security.controller.*.*(..))",argNames="call,ex",throwing="ex")
	public void logErrorWrite(JoinPoint call,Exception ex) throws Throwable{
		
		Object[] args 	= call.getArgs();
		StringBuffer strParam = new StringBuffer();
		
		for(int i = 0;i < args.length;i++){
			if(i != args.length){
				strParam.append(args[i].toString() + ",");
			}else{
				strParam.append(args[i].toString());
			}
		}
		
		logger.error("******************After Throwing Start****************************");
		logger.error("Class  Name : " + call.getTarget().getClass().getName());
		logger.error("Method Name : " + call.toShortString());
		logger.error("Parameter   : " + strParam.toString());
		logger.error("Error Message   : " + ex.getMessage());
		logger.error("******************After Throwing End******************************");
		//logger.trace(ex.getMessage(), ex);
		//logger.trace();
	}
}

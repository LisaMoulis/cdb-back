package aop.pointcut;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class ServicePointcut {

	@Pointcut("execution(* service.*Service.*(..))")
	public void AllServices() {}
	
	@Pointcut("execution(* service.ComputerService.*(..))")
	public void ComputerPointcut() {}
	
	@Pointcut("execution(* service.CompanyService.*(..))")
	public void CompanyPointcut() {}
	
	@Pointcut("execution(* service.UserService.*(..))")
	public void UserPointcut() {}
}

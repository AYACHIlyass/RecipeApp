package org.mql.recipe.logging;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RecipeLoggingAspect {

    Logger logger = LogManager.getLogger(RecipeLoggingAspect.class);

    @Before("org.mql.recipe.helper.PointCutExpressions.AppFlow()")
    public void traceAppFlowBefore(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        StringBuilder log = new StringBuilder();
        log.append("Method : ").append(signature.toShortString()).append("\n");
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            log.append("arg : ").append(arg.toString());
        }
        logger.info(log.toString());

    }

    @AfterThrowing(value = "org.mql.recipe.helper.PointCutExpressions.AppFlow()", throwing = "exception")
    public void traceAppFlowAfter(JoinPoint joinPoint, Throwable exception) {
        Signature signature = joinPoint.getSignature();
        String log = "Method : " + signature.toShortString() + "\n" +
                "exception : " + exception.toString();
        logger.error(log);
    }

    @AfterReturning(value = "org.mql.recipe.helper.PointCutExpressions.AppFlow()", returning = "result")
    public void traceAppFlowAfter(JoinPoint joinPoint, Object result) {
        Signature signature = joinPoint.getSignature();
        String log = "Method : " + signature.toShortString() + "\n" +
                "returns  : " + ((result != null) ? result.toString() : "void");
        logger.error(log);
    }


}

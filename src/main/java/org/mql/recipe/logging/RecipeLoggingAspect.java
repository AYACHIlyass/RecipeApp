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

    @Before("org.mql.recipe.helper.PointCutExpressions.Appflow()")
    public void traceAppFlowBefore(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        StringBuffer log = new StringBuffer();
        log.append("Method : " + signature.toShortString() + "\n");
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            log.append("arg : " + arg.toString());
        }
        logger.info(log.toString());

    }

    @AfterThrowing(value = "org.mql.recipe.helper.PointCutExpressions.Appflow()", throwing = "exception")
    public void traceAppFlowAfter(JoinPoint joinPoint, Throwable exception) {
        Signature signature = joinPoint.getSignature();
        StringBuffer log = new StringBuffer();
        log.append("Method : " + signature.toShortString() + "\n");
        log.append("exception : " + exception.toString());
        logger.error(log.toString());
    }

    @AfterReturning(value = "org.mql.recipe.helper.PointCutExpressions.Appflow()", returning = "result")
    public void traceAppFlowAfter(JoinPoint joinPoint, Object result) {
        Signature signature = joinPoint.getSignature();
        StringBuffer log = new StringBuffer();
        log.append("Method : " + signature.toShortString() + "\n");
        log.append("returns  : " + ((result != null) ? result.toString() : "void"));
        logger.error(log.toString());
    }


}

package org.mql.recipe.helper;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PointCutExpressions {
    @Pointcut("execution(* org.mql.recipe.bootstrap.*.*(..))")
    public void forBootstrapPackage(){}

    @Pointcut("execution(* org.mql.recipe.controller.*.*(..))")
    public void forControllerPackage(){}

    @Pointcut("execution(* org.mql.recipe.repository.*.*(..))")
    public void forRepositoryPackage(){}

    @Pointcut("execution(* org.mql.recipe.service.*.*(..))")
    public void forServicePackage(){}

    @Pointcut("forBootstrapPackage() || forControllerPackage() || forRepositoryPackage()|| forServicePackage()")
    public void AppFlow(){}


}

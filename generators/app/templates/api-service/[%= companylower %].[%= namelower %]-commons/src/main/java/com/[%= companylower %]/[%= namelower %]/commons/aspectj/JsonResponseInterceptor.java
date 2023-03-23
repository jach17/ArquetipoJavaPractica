package com.[%= companylower %].[%= namelower %].commons.aspectj;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Annotation to intercept controller methods that return a JSON object
 * 
 * @author [%= username %]
 */
@Retention(RUNTIME)
@Target(ElementType.METHOD)
public @interface JsonResponseInterceptor
{
}

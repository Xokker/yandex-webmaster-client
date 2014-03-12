package ru.webeffector;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;

/**
 * @author Ernest Sadykov
 * @since 12.03.2014
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value=METHOD)
public @interface MethodType {

    LinkType value();

}

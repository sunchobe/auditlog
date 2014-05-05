package org.silverbullit.auditlog.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation marks fields to be auditable. These fields will be tracked
 * within {@link AuditionTransactions} for "creation"- and "change"-actions.
 * 
 * @author Christian Ober
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Auditable {

}

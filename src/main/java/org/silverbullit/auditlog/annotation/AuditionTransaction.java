package org.silverbullit.auditlog.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation marks methods to start a transaction according to
 * data-audition. Within this transaction, creation and changes of fieldvalues
 * marked with the {@link Auditable} will be tracked.
 * 
 * @author Christian Ober
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AuditionTransaction {

}


package net.bounceme.dur.nntp;

import static java.lang.annotation.ElementType.*;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import javax.inject.Qualifier;


//@Qualifier
//@Retention(RUNTIME)
//@Target({METHOD, FIELD, PARAMETER, TYPE})
public @interface HttpParam {
     public String value() default "";
}

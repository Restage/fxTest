package de.bitandgo.fxtest.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a test method as FxTest. Only methods with this annotation are executed by the FxTest framework. All other methods are ignored.
 *
 * @author Kaufmann, Richard
 * @since 0.1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface FxTest {
  
  /**
   * Value to explicitly enable or disable FxTests. If this value is not present, the test is implicitly enabled.
   *
   * @return {@code true} if the test should be executed, otherwise {@code false}
   */
  boolean enabled() default true;
}

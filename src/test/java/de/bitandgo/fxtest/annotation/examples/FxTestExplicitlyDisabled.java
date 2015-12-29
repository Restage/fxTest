package de.bitandgo.fxtest.annotation.examples;

import de.bitandgo.fxtest.annotation.FxTest;
import de.bitandgo.fxtest.runner.FxTestRunner;
import org.junit.runner.RunWith;

/**
 * FxTest test class, which has one method annotated with {@link FxTest}. The value of the property enabled is {@code false}, which indicates that the
 * test must not be executed by the framework.
 *
 * @author Kaufmann, Richard
 * @since 0.1.0
 */
@RunWith(FxTestRunner.class)
public class FxTestExplicitlyDisabled {

  @FxTest(enabled = false)
  public void testMethod() {
    // empty
  }
}
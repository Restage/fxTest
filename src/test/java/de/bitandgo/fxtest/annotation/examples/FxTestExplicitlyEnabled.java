package de.bitandgo.fxtest.annotation.examples;

import de.bitandgo.fxtest.annotation.FxTest;
import de.bitandgo.fxtest.runner.FxTestRunner;
import org.junit.runner.RunWith;

/**
 * FxTest test class, which has one method annotated with {@link FxTest}. The value of the property enabled is {@code true}, which indicates that the
 * test must be executed by the framework.
 *
 * @author Kaufmann, Richard
 * @since 0.1.0
 */
@RunWith(FxTestRunner.class)
public class FxTestExplicitlyEnabled {

  @FxTest(enabled = true)
  public void testMethod() {
    // empty
  }
}

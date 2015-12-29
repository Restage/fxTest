package de.bitandgo.fxtest.annotation.examples;

import de.bitandgo.fxtest.annotation.FxTest;
import de.bitandgo.fxtest.runner.FxTestRunner;
import org.junit.runner.RunWith;

/**
 * FxTest test class, which has one method annotated with {@link de.bitandgo.fxtest.annotation.FxTest} but no field annotated with
 * {@link de.bitandgo.fxtest.annotation.FxTestObject}. This is an invalid configuration since the test methods need to know which element should be
 * used for testing.
 *
 * @author Kaufmann, Richard
 * @since 0.1.0
 */
@RunWith(FxTestRunner.class)
public class FxTestButNoFxTestObjectAnnotationPresent {
  // field, which is not annotated with @FxTestObject
  private Object obj = null;

  @FxTest
  public void testObject() {

  }
}

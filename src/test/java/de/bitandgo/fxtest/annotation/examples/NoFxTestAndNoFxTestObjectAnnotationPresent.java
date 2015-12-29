package de.bitandgo.fxtest.annotation.examples;

import de.bitandgo.fxtest.runner.FxTestRunner;
import org.junit.runner.RunWith;

/**
 * FxTest test class, which has no method annotated with {@link de.bitandgo.fxtest.annotation.FxTest} and also no field annotated with
 * {@link de.bitandgo.fxtest.annotation.FxTestObject}. This is a valid configuration although the test class does absolutely nothing.
 *
 * @author Kaufmann, Richard
 * @since 0.1.0
 */
@RunWith(FxTestRunner.class)
public class NoFxTestAndNoFxTestObjectAnnotationPresent {
  // field, which is not annotated with @FxTestObject
  private Object obj = null;

  // method not annotated with @FxTest
  public void testObject() {

  }
}
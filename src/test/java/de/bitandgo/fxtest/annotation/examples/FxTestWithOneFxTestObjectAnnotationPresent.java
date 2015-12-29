package de.bitandgo.fxtest.annotation.examples;

import de.bitandgo.fxtest.annotation.FxTest;
import de.bitandgo.fxtest.annotation.FxTestObject;
import de.bitandgo.fxtest.runner.FxTestRunner;
import org.junit.runner.RunWith;

/**
 * FxTest test class, which has one method annotated with {@link de.bitandgo.fxtest.annotation.FxTest} and exactly one field annotated with
 * {@link de.bitandgo.fxtest.annotation.FxTestObject}. This is a valid configuration.
 *
 * @author Kaufmann, Richard
 * @since 0.1.0
 */
@RunWith(FxTestRunner.class)
public class FxTestWithOneFxTestObjectAnnotationPresent {
  @FxTestObject
  private Object obj = null;

  @FxTest
  public void testObject() {

  }
}

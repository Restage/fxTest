package de.bitandgo.fxtest.annotation.examples;

import de.bitandgo.fxtest.annotation.FxTest;
import de.bitandgo.fxtest.annotation.FxTestObject;
import de.bitandgo.fxtest.runner.FxTestRunner;
import org.junit.runner.RunWith;

/**
 * FxTest test class, which has one method annotated with {@link de.bitandgo.fxtest.annotation.FxTest} and two fields annotated with
 * {@link de.bitandgo.fxtest.annotation.FxTestObject}. This is a invalid configuration because the framework would not know which one to use for
 * testing.
 *
 * @author Kaufmann, Richard
 * @since 0.1.0
 */
@RunWith(FxTestRunner.class)
public class FxTestWithTwoFxTestObjectAnnotationsPresent {
  @FxTestObject
  private Object obj_1 = null;

  @FxTestObject
  private Object obj_2 = null;

  @FxTest
  public void testObject() {

  }
}

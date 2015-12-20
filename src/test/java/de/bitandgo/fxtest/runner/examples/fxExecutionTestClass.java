package de.bitandgo.fxtest.runner.examples;


import de.bitandgo.fxtest.annotation.FxTest;
import org.junit.Assert;

/**
 * FxTest test class, which is used to proof the correct execution of FxTests by the {@link de.bitandgo.fxtest.runner.FxTestRunner}.
 *
 * @author Kaufmann, Richard
 * @since 0.1.0
 */
public class fxExecutionTestClass {
  @FxTest
  public void fxTestExecutedWithoutAssertionError() {
    Assert.assertTrue(true);
  }

  @FxTest
  public void fxTestExecutedWithAssertionError() {
    Assert.assertTrue(false);
  }

  @FxTest(enabled = false)
  public void fxTestIgnored() {
    // this code should never be executed
    Assert.assertTrue(false);
  }
}
package de.bitandgo.fxtest.runner.examples;

import de.bitandgo.fxtest.annotation.FxTest;
import de.bitandgo.fxtest.runner.FxTestRunner;
import org.junit.Assert;
import org.junit.runner.RunWith;

/**
 * FxTest test class, which has one {@link FxTest} method with a not fulfilled JUnit assertion. The framework has to mark this test as failed.
 *
 * @author Kaufmann, Richard
 * @since 0.1.0
 */
@RunWith(FxTestRunner.class)
public class FxTestWithAssertionErrorTest {

  @FxTest
  public void testMethod() {
    Assert.assertTrue(false);
  }
}
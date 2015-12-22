package de.bitandgo.fxtest.runner;

import de.bitandgo.fxtest.runner.util.FxTestRunListener;
import de.bitandgo.fxtest.runner.examples.fxExecutionTestClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.notification.RunNotifier;


/**
 * JUnit Tests for the {@code FxTestRunner} class, which proof the correct execution of the tests.
 *
 * @author Kaufmann, Richard
 * @since 0.1.0
 */
public class FxTestRunnerExecutionTests {

  /**
   * FxTestRunner is the class to be tested.
   */
  private static FxTestRunListener testRunListener;

  /**
   * Setup before the first test is executed.
   */
  @BeforeClass
  public static void setupClass() {
    final FxTestRunner testRunner      = new FxTestRunner(fxExecutionTestClass.class);
    final RunNotifier  testRunNotifier = new RunNotifier();

    testRunListener = new FxTestRunListener();
    testRunNotifier.addListener(testRunListener);
    testRunner.run(testRunNotifier);
  }

  /**
   * Test, to ensure a test method with an assertion that is not violated is marked as successfully executed.
   */
  @Test
  public void testNoAssertionErrorResultsInFinishedState() {
    Assert.assertEquals("Teststate of fxTestExecutedWithoutAssertionError is SUCCESS",
                        FxTestRunListener.FxTestState.SUCCESS,
                        testRunListener.getTestState("fxTestExecutedWithoutAssertionError"));
  }

  /**
   * Test, to ensure a test method with a violated assertion is marked as test failure.
   */
  @Test
  public void testAssertionErrorResultsInFailedState() {
    Assert.assertEquals("Teststate of fxTestExecutedWithAssertionError is FAIL",
                        FxTestRunListener.FxTestState.FAIL,
                        testRunListener.getTestState("fxTestExecutedWithAssertionError"));
  }

  /**
   * Test, to ensure a test method, which is disabled, is marked as ignored.
   */
  @Test
  public void testDisabledTestResultsInIgnoredState() {
    Assert.assertEquals("Teststate of fxTestIgnored is IGNORED",
                        FxTestRunListener.FxTestState.IGNORED,
                        testRunListener.getTestState("fxTestIgnored"));
  }
}

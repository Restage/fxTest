package de.bitandgo.fxtest.runner;

import de.bitandgo.fxtest.runner.util.FxTestRunListener;
import de.bitandgo.fxtest.runner.examples.fxExecutionTestClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;


/**
 * JUnit Tests for the {@code FxTestRunner} class, which proof the correct execution of the tests.
 *
 * @author Kaufmann, Richard
 * @since 0.1.0
 */
public class FxTestRunnerExecutionTests {

  /**
   * The {@link FxTestRunListener} gives information about the state of the executed fxTest.
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

  /**
   * Test, to ensure a test method without a violated assertion does not produce a {@link Failure}.
   */
  @Test
  public void testNoAssertionErrorResultsInNoFailure() {
    Assert.assertNull("fxTestExecutedWithoutAssertionError throws no Failure",
                      testRunListener.getTestFailure("fxTestExecutedWithoutAssertionError"));
  }

  /**
   * Test, to ensure a test method that violates an assertion does produce a {@link Failure}.
   */
  @Test
  public void testAssertionErrorResultsInFailure() {
    final Failure failure = testRunListener.getTestFailure("fxTestExecutedWithAssertionError");

    Assert.assertNotNull("fxTestExecutedWithAssertionError throws Failure",
                        failure);

    Assert.assertEquals("Assertion error must be thrown for fxTestExecutedWithAssertionError",
                        AssertionError.class,
                        failure.getException().getClass());

    Assert.assertEquals("Failure description belongs to the right class name",
                        fxExecutionTestClass.class,
                        failure.getDescription().getTestClass());

    Assert.assertEquals("Failure description belongs to the right method name",
                        "fxTestExecutedWithAssertionError",
                        failure.getDescription().getMethodName());
  }

  /**
   * Test, to ensure a disabled test method does not produce a {@link Failure}.
   */
  @Test
  public void testDisabledTestResultsInNoFailure() {
    Assert.assertNull("fxTestIgnored throws no Failure",
                      testRunListener.getTestFailure("fxTestIgnored"));
  }
}

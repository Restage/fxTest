package de.bitandgo.fxtest.runner;

import de.bitandgo.fxtest.runner.examples.FxTestAssertionErrorIgnoredTest;
import de.bitandgo.fxtest.runner.examples.FxTestWithAssertionErrorTest;
import de.bitandgo.fxtest.runner.examples.FxTestWithoutAssertionErrorTest;
import de.bitandgo.fxtest.runner.util.FxTestRunListener;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;


/**
 * JUnit Tests for the {@code FxTestRunner} class, which proof that the test state is correctly set depending on the outcome of the
 * {@link de.bitandgo.fxtest.annotation.FxTest}.
 *
 * @author Kaufmann, Richard
 * @since 0.1.0
 */
public class FxTestRunnerExecutionStateTests {

  /**
   * Ensures that a FxTest with an assertion that is not violated, is marked as successfully executed and produces no {@link Failure} object.
   */
  @Test
  public void testTestStateOfFxTestWithoutAssertionError() {
    final FxTestRunner      fxTestRunner    = new FxTestRunner(FxTestWithoutAssertionErrorTest.class);
    final RunNotifier       testRunNotifier = new RunNotifier();
    final FxTestRunListener testRunListener = new FxTestRunListener();

    testRunNotifier.addListener(testRunListener);
    fxTestRunner.run(testRunNotifier);

    Assert.assertEquals("Test state of FxTest without assertion error is SUCCESS",
                        FxTestRunListener.FxTestState.SUCCESS,
                        testRunListener.getTestState("testMethod"));

    Assert.assertNull("FxTest without assertion error produces no Failure",
                      testRunListener.getTestFailure("testMethod"));
  }

  /**
   * Ensures that a FxTest which throws an assertion error, is marked as failed and produces a a {@link Failure} object.
   */
  @Test
  public void testTestStateOfFxTestWithAssertionError() {
    final FxTestRunner      fxTestRunner    = new FxTestRunner(FxTestWithAssertionErrorTest.class);
    final RunNotifier       testRunNotifier = new RunNotifier();
    final FxTestRunListener testRunListener = new FxTestRunListener();

    testRunNotifier.addListener(testRunListener);
    fxTestRunner.run(testRunNotifier);

    Assert.assertEquals("Test state of FxTest with assertion error is FAIL",
                        FxTestRunListener.FxTestState.FAIL,
                        testRunListener.getTestState("testMethod"));

    final Failure failure = testRunListener.getTestFailure("testMethod");

    Assert.assertNotNull("FxTest with assertion error produces a Failure",
                         failure);

    Assert.assertEquals("Failure object must contain AssertionError as Exception class",
                        AssertionError.class,
                        failure.getException()
                               .getClass());

    Assert.assertEquals("Failure description has to contain the expected class name",
                        FxTestWithAssertionErrorTest.class,
                        failure.getDescription()
                               .getTestClass());

    Assert.assertEquals("Failure description has to contain the expected method name",
                        "testMethod",
                        failure.getDescription()
                               .getMethodName());
  }

  /**
   * Ensures that a FxTest which is disabled is marked as ignored and produces no {@link Failure} object.
   */
  @Test
  public void testTestStateOfDisabledFxTest() {
    final FxTestRunner      fxTestRunner    = new FxTestRunner(FxTestAssertionErrorIgnoredTest.class);
    final RunNotifier       testRunNotifier = new RunNotifier();
    final FxTestRunListener testRunListener = new FxTestRunListener();

    testRunNotifier.addListener(testRunListener);
    fxTestRunner.run(testRunNotifier);

    Assert.assertEquals("Test state of disabled FxTest is IGNORED",
                        FxTestRunListener.FxTestState.IGNORED,
                        testRunListener.getTestState("testMethod"));

    Assert.assertNull("Ignored FxTest produces no Failure",
                      testRunListener.getTestFailure("testMethod"));
  }
}

package de.bitandgo.fxtest.util;

import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Extends the JUnit {@link RunListener} for the use in Unit-Tests. It saves the state of the last executed FxTest, which can requested afterwards via
 * a getter method.
 */
public class FxTestRunListener extends RunListener {
  /**
   * Inner enum to represent the different states a test can result in.
   */
  public enum FxTestState {
    IGNORED,
    SUCCESS,
    FAIL
  }

  /**
   * Execution state of the test methods annotated with {@link de.bitandgo.fxtest.annotation.FxTest}.
   */
  private Map<String, FxTestState> testState = new HashMap<>();

  /**
   * Failure descriptions of the test methods which threw an assertion error during execution.
   */
  private Map<String, Failure> testFailures = new HashMap<>();

  /**
   * {@inheritDoc}
   */
  @Override
  public void testFinished(Description description) throws Exception {
    testState.put(description.getMethodName(), FxTestState.SUCCESS);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void testFailure(Failure failure) throws Exception {
    testState.put(failure.getDescription().getMethodName(), FxTestState.FAIL);
    testFailures.put(failure.getDescription().getMethodName(), failure);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void testIgnored(Description description) throws Exception {
    testState.put(description.getMethodName(), FxTestState.IGNORED);
  }

  /**
   * Returns the {@link FxTestState} of a test method.
   *
   * @param testMethodName for which method should the {@link FxTestState} be returned
   *
   * @return {@link FxTestState} of the method or {@code null} if no {@link FxTestState} was set
   */
  public FxTestState getTestState(final String testMethodName) {
    return testState.get(testMethodName);
  }

  /**
   * Returns the {@link Failure} thrown during the execution of a test method.
   *
   * @param testMethodName for which method should the {@link Failure} be returned
   *
   * @return {@link Failure} or {@code null} if no one happened during execution
   */
  public Failure getTestFailure(final String testMethodName) {
    return testFailures.get(testMethodName);
  }
}
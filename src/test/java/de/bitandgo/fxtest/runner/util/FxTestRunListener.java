package de.bitandgo.fxtest.runner.util;

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
   * Execution state of the testMethods annotated with {@link de.bitandgo.fxtest.annotation.FxTest}.
   */
  private Map<String, FxTestState> testState = new HashMap<>();

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
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void testIgnored(Description description) throws Exception {
    testState.put(description.getMethodName(), FxTestState.IGNORED);
  }

  /**
   * Returns the state of the last test executed.
   *
   * @param testMethodName {@link FxTestState} of which method should be returned
   *
   * @return {@link FxTestState} of the last test
   */
  public FxTestState getTestState(final String testMethodName) {
    return testState.get(testMethodName);
  }
}
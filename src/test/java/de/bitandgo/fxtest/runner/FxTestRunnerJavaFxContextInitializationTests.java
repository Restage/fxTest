package de.bitandgo.fxtest.runner;

import de.bitandgo.fxtest.runner.examples.FxTestCreateNodeTest;
import de.bitandgo.fxtest.util.FxTestRunListener;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.notification.RunNotifier;

/**
 * JUnit Tests for the {@code FxTestRunner} class, which checks that the JavaFX context gets initialized correctly.
 *
 * @author Kaufmann, Richard
 * @since 0.1.0
 */
public class FxTestRunnerJavaFxContextInitializationTests {

  /**
   * Ensures that the JavaFX context gets initialized correctly by creating a {@link javafx.scene.control.Button}. This must be possible without any
   * {@link Exception} thrown. Additionally the test state of the test gets verified.
   */
  @Test
  public void testCreatingButtonResultsInSuccessState() {
    final FxTestRunner testRunner      = new FxTestRunner(FxTestCreateNodeTest.class);
    final RunNotifier  testRunNotifier = new RunNotifier();
    final FxTestRunListener testRunListener = new FxTestRunListener();

    testRunNotifier.addListener(testRunListener);
    testRunner.run(testRunNotifier);

    Assert.assertEquals("Creating a button in a fxTest results in test state SUCCESS",
                        FxTestRunListener.FxTestState.SUCCESS,
                        testRunListener.getTestState("testMethod"));
  }
}
package de.bitandgo.fxtest.runner;

import de.bitandgo.fxtest.runner.examples.fxNodeTestClass;
import de.bitandgo.fxtest.util.FxTestRunListener;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.notification.RunNotifier;

/**
 * JUnit Tests for the {@code FxTestRunner} class, which proof the correct handling of embedding {@link javafx.scene.Node Nodes} in fxTests.
 *
 * @author Kaufmann, Richard
 * @since 0.1.0
 */
public class FxTestRunnerButtonTests {
  /**
   * The {@link FxTestRunListener} gives information about the state of the executed fxTest.
   */
  private static FxTestRunListener testRunListener;

  /**
   * Setup before the first test is executed.
   */
  @BeforeClass
  public static void setupClass() {
    final FxTestRunner testRunner      = new FxTestRunner(fxNodeTestClass.class);
    final RunNotifier  testRunNotifier = new RunNotifier();

    testRunListener = new FxTestRunListener();
    testRunNotifier.addListener(testRunListener);
    testRunner.run(testRunNotifier);
  }

  /**
   * Test to ensure that a fxTest can handle the creation of a {@link javafx.scene.control.Button}.
   */
  @Test
  public void testCreatingButtonResultsInSuccessState() {
    Assert.assertEquals("Creating a button in a fxTest results in test state SUCCESS",
                        FxTestRunListener.FxTestState.SUCCESS,
                        testRunListener.getTestState("createFxButtonTest"));
  }
}
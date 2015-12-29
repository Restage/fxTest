package de.bitandgo.fxtest.annotation;

import de.bitandgo.fxtest.annotation.examples.*;
import de.bitandgo.fxtest.exception.FxTestConfigurationException;
import de.bitandgo.fxtest.runner.FxTestRunner;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * JUnit Tests to ensure the correct behaviour of the {@link de.bitandgo.fxtest.runner.FxTestRunner} class, when processing the
 * {@link de.bitandgo.fxtest.annotation.FxTestObject} annotation.
 *
 * @author Kaufmann, Richard
 * @since 0.1.0
 */
public class FxTestObjectAnnotationTests {

  /**
   * Used in the tests to ensure the expected exceptions are thrown.
   */
  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  /**
   * Ensures that a FxTest class with no FxTest method and no FxTestObject is a valid configuration (although it tests absolutely nothing).
   */
  @Test
  public void testNoFxTestAndNoFxTestObjectAnnotationPresent() {
    new FxTestRunner(NoFxTestAndNoFxTestObjectAnnotationPresent.class);
    Assert.assertTrue("With no FxTest and no FxTestObject present no error must be thrown",
                      true);
  }

  /**
   * Ensures that a FxTest class with no FxTest method but one FxTestObject is a valid configuration (although it tests absolutely nothing).
   */
  @Test
  public void testNoFxTestButFxTestObjectAnnotationPresent() {
    new FxTestRunner(NoFxTestButFxTestObjectAnnotationPresent.class);
    Assert.assertTrue("With no FxTest but one FxTestObject present no error must be thrown",
                      true);
  }

  /**
   * Ensures that a FxTest class with a FxTest method but no FxTestObject present, throws an error during the initialization phase of the test. It is
   * no valid configuration since the test does not know which element to test.
   */
  @Test
  public void testFxTestButNoFxTestObjectAnnotationPresent() {
    expectedException.expect(FxTestConfigurationException.class);
    expectedException.expectMessage("Class has a FxTest but no FxTestObject is configured");

    new FxTestRunner(FxTestButNoFxTestObjectAnnotationPresent.class);
  }

  /**
   * Ensures that a FxTest class with a FxTest method and a FxTestObject present, is a valid test configuration.
   */
  @Test
  public void testFxTestWithOneFxTestObjectAnnotationPresent() {
    new FxTestRunner(FxTestWithOneFxTestObjectAnnotationPresent.class);
  }

  /**
   * Ensures that a FxTest class with a FxTest method and more than one FxTestObject present, throws an error during the initialization phase of the
   * test. This is not a valid configuration, because the test would not no which one to use.
   */
  @Test
  public void testFxTestWithTwoFxTestObjectAnnotationsPresent() {
    expectedException.expect(FxTestConfigurationException.class);
    expectedException.expectMessage("Class has a FxTest with multiple FxTestObjects configured");

    new FxTestRunner(FxTestWithTwoFxTestObjectAnnotationsPresent.class);
  }
}

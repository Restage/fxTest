package de.bitandgo.fxtest.runner;

import de.bitandgo.fxtest.runner.examples.FxTestClassUnderTest;
import de.bitandgo.fxtest.runner.examples.FxTestDescriptionTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.Description;

/**
 * JUnit Tests to ensure that the meta information collected by the {@link FxTestRunner} class is correct.
 *
 * @author Kaufmann, Richard
 * @since 0.1.0
 */
public class FxTestRunnerMetaInformationTests {

  /**
   * Ensures that the class passed to the {@link FxTestRunner} as constructor argument is used as the class under test.
   */
  @Test
  public void testClassUnderTest() {
    final Class classUnderTestName = new FxTestRunner(FxTestClassUnderTest.class).getClassUnderTest();

    Assert.assertEquals("Class name is recognized correctly",
                        classUnderTestName,
                        FxTestClassUnderTest.class);
  }

  /**
   * Ensures that the test gets a {@link Description} which contains the name of the class under test.
   */
  @Test
  public void testTestSuiteDescription() {
    final Description testSuiteDescription = new FxTestRunner(FxTestDescriptionTest.class).getDescription();

    Assert.assertEquals("Description of the test suite is the name of the class under test",
                        FxTestDescriptionTest.class.getName(),
                        testSuiteDescription.getClassName());
  }
}

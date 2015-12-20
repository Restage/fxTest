package de.bitandgo.fxtest.runner;

import de.bitandgo.fxtest.annotation.FxTest;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.Description;

import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;


/**
 * JUnit Tests for the {@code FxTestRunner} class, which proofs the correctness of TestRunner initialization routine.
 *
 * @author Kaufmann, Richard
 * @since 0.1.0
 */
public class FxTestRunnerInitializationTests {

  /**
   * Test fxTest class, whose annotations need to be correctly analysed by the {@link FxTestRunner}.
   */
  class fxAnnotationTestClass {
    @FxTest
    public void fxTestImplicitlyEnabled() {
      // empty
    }

    @FxTest(enabled = true)
    public void fxTestExplicitlyEnabled() {
      // empty
    }

    @FxTest(enabled = false)
    public void fxTestExplicitlyDisabled() {
      // empty
    }
  }

  /**
   * FxTestRunner is the class to be tested.
   */
  private static FxTestRunner testRunner;

  /**
   * Setup before the first test is executed.
   */
  @BeforeClass
  public static void setupClass() {
    testRunner = new FxTestRunner(fxAnnotationTestClass.class);
  }

  /**
   * Tests, that the class under test is correctly recognized.
   */
  @Test
  public void testClassUnderTest() {
    final Class classUnderTest = testRunner.getClassUnderTest();

    Assert.assertEquals("Class name is recognized correctly",
                        fxAnnotationTestClass.class.getName(),
                        classUnderTest.getName());
  }

  /**
   * Tests, that methods which are annotated with {@link FxTest} and implicitly or explicitly enabled, is marked for execution.
   */
  @Test
  public void testFxTestAnnotationImplicitlyEnabled() {
    // sorted list of all methods with the FxTest annotation
    final List<Method> testMethodsToExecute = testRunner.getTestMethodsToExecute()
                                                        .stream()
                                                        .sorted((method1, methode2) -> method1.getName()
                                                                                              .compareTo(methode2.getName()))
                                                        .collect(Collectors.toList());

    Assert.assertEquals("Number of methods marked for execution is correct",
                        2,
                        testMethodsToExecute.size());

    Assert.assertEquals("Name of the marked method is correct",
                        "fxTestExplicitlyEnabled",
                        testMethodsToExecute.get(0)
                                            .getName());

    Assert.assertEquals("Name of the marked method is correct",
                        "fxTestImplicitlyEnabled",
                        testMethodsToExecute.get(1)
                                            .getName());
  }

  /**
   * Tests, that methods which are annotated with {@link FxTest} and excplicilty disabled, are not marked for execution.
   */
  @Test
  public void testFxAnnotationExplicitlyDisabled() {
    final List<Method> testMethodsIgnored = testRunner.getTestMethodsToIgnore();

    Assert.assertEquals("Number of methods is correct",
                        1,
                        testMethodsIgnored.size());

    Assert.assertEquals("Name of the method is correct",
                        "fxTestExplicitlyDisabled",
                        testMethodsIgnored.get(0)
                                          .getName());
  }

  /**
   * Test, that the whole test gets a {@link Description} which contains the name of the class under test.
   */
  @Test
  public void testTestSuiteDescription() {
    final Description testSuiteDescription = testRunner.getDescription();

    Assert.assertEquals("Description of the whole test is the name of the class under test",
                        fxAnnotationTestClass.class.getName(),
                        testSuiteDescription.getClassName());
  }
}
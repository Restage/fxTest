package de.bitandgo.fxtest.runner;

import de.bitandgo.fxtest.annotation.FxTest;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.Description;

import java.lang.reflect.Method;
import java.util.List;


public class FxTestRunnerTests {  
  
  /**
   * Test fxTest class, which has to be correctly analysed by the {@link FxTestRunner}.
   */
  class fxTestClass {
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
    testRunner = new FxTestRunner(fxTestClass.class);
  }
  
  /**
   * Tests, that the class under test is correctly recognized.
   */
  @Test
  public void testClassUnderTest() {    
    final Class classUnderTest = testRunner.getClassUnderTest();
    
    Assert.assertEquals("Class name is recognized correctly",
                        fxTestClass.class.getName(),
                        classUnderTest.getName());
  }
  
  /**
   * Tests, that methods which are annotated with {@link FxTest} and implicitly or explicitly enabled, is marked for execution.
   */
  @Test
  public void testFxTestAnnotationImplicitlyEnabled() {
    final List<Method> testMethodsToExecute = testRunner.getTestMethodsToExecute();
    
    Assert.assertEquals("Number of methods marked for execution is correct",
                        2,
                        testMethodsToExecute.size());

    Assert.assertEquals("Name of the marked method is correct",
                        "fxTestImplicitlyEnabled",
                        testMethodsToExecute.get(0).getName());
    
    Assert.assertEquals("Name of the marked method is correct",
                        "fxTestExplicitlyEnabled",
                        testMethodsToExecute.get(1).getName());
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
                        testMethodsIgnored.get(0).getName());
  }

  /**
   * Test, that the whole test gets a {@link Description} which contains the name of the class under test.
   */
  @Test
  public void testTestSuiteDescription() {
    final Description testSuiteDescription = testRunner.getDescription();

    Assert.assertEquals("Description of the whole test is the name of the class under test",
                        fxTestClass.class.getName(),
                        testSuiteDescription.getClassName());
  }
}

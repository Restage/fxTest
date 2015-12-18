package de.bitandgo.fxtest.runner;

import de.bitandgo.fxtest.annotation.FxTest;
import java.lang.reflect.Method;
import java.util.List;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;


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
   * Tests, that the method which is annotated with @FxTest, is marked for execution.
   */
  @Test
  public void testFxTestAnnotationImplicitlyEnabled() {
    final List<Method> testMethodsToExecute = testRunner.getTestMethodsToExecute();
    
    Assert.assertEquals("Number of marked methods is correct",
                        2,
                        testMethodsToExecute.size());
    
    Assert.assertEquals("Name of the marked method is correct",
                        "fxTestImplicitlyEnabled",
                        testMethodsToExecute.get(0).getName());
    
    Assert.assertEquals("Name of the marked method is correct",
                        "fxTestExplicitlyEnabled",
                        testMethodsToExecute.get(1).getName());
  }
}

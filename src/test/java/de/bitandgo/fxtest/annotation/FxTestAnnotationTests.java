package de.bitandgo.fxtest.annotation;

import de.bitandgo.fxtest.annotation.examples.FxTestExplicitlyDisabled;
import de.bitandgo.fxtest.annotation.examples.FxTestExplicitlyEnabled;
import de.bitandgo.fxtest.annotation.examples.FxTestImplicitlyEnabled;
import de.bitandgo.fxtest.runner.FxTestRunner;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.List;


/**
 * JUnit Tests to ensure the correct behaviour of the {@link FxTestRunner} class, when processing the {@link FxTest} annotation.
 *
 * @author Kaufmann, Richard
 * @since 0.1.0
 */
public class FxTestAnnotationTests {

  /**
   * Ensures, that a method annotated with {@link FxTest} is marked for test execution.
   */
  @Test
  public void testFxTestAnnotationImplicitlyEnabled() {
    final FxTestRunner fxTestRunner = new FxTestRunner(FxTestImplicitlyEnabled.class);

    // sorted list of all methods with the FxTest annotation
    final List<Method> testMethodsToExecute = fxTestRunner.getTestMethodsToExecute();

    Assert.assertEquals("Number of methods marked for execution is correct",
                        1,
                        testMethodsToExecute.size());

    Assert.assertEquals("Name of the marked method is correct",
                        "testMethod",
                        testMethodsToExecute.get(0)
                                            .getName());
  }

  /**
   * Ensures, that a method annotated with {@link FxTest}, that is explicitly enabled via value, is marked for execution.
   */
  @Test
  public void testFxTestAnnotationExplicitlyEnabled() {
    final FxTestRunner fxTestRunner = new FxTestRunner(FxTestExplicitlyEnabled.class);

    // sorted list of all methods with the FxTest annotation
    final List<Method> testMethodsToExecute = fxTestRunner.getTestMethodsToExecute();

    Assert.assertEquals("Number of methods marked for execution is correct",
                        1,
                        testMethodsToExecute.size());

    Assert.assertEquals("Name of the marked method is correct",
                        "testMethod",
                        testMethodsToExecute.get(0)
                                            .getName());
  }

  /**
   * Ensures, that a method annotated with {@link FxTest}, that is explicitly disabled via value, is not marked for execution.
   */
  @Test
  public void testFxAnnotationExplicitlyDisabled() {
    final FxTestRunner fxTestRunner = new FxTestRunner(FxTestExplicitlyDisabled.class);

    // sorted list of all methods with the FxTest annotation
    final List<Method> testMethodsIgnored = fxTestRunner.getTestMethodsToIgnore();

    Assert.assertEquals("Number of methods marked to be ignored is correct",
                        1,
                        testMethodsIgnored.size());

    Assert.assertEquals("Name of the method to ignore is correct",
                        "testMethod",
                        testMethodsIgnored.get(0)
                                          .getName());
  }
}
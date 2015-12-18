package de.bitandgo.fxtest.runner;

import de.bitandgo.fxtest.annotation.FxTest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;

/**
 * fxTest Runner class, which uses JUnit's {@link Runner} to enable the exection of the tests.
 * 
 * @author Kaufmann, Richard
 * 
 * @since 0.1.0
 */
public class FxTestRunner extends Runner {

  /**
   * Class under test.
   */
  private final Class        classUnderTest;
  /**
   * All methods of the class, which must be executed.
   */
  private final List<Method> testMethodsToExecute = new ArrayList<>();
  
  /**
   * Public constructor, which is responsible for detecting all the methods that need to be executed.
   * 
   * @param classUnderTest Class currently under test
   */
  public FxTestRunner(final Class classUnderTest) {
    this.classUnderTest = classUnderTest;
    
    final Method[] allClassMethods = classUnderTest.getDeclaredMethods();
    
    for (final Method classMethod : allClassMethods) {
      final FxTest fxTestAnnotation = classMethod.getAnnotation(FxTest.class);
      
      // is the method annotated with the FxTest annotation
      if(null != fxTestAnnotation && fxTestAnnotation.enabled()) {
        testMethodsToExecute.add(classMethod);
      }
    }
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public Description getDescription() {
    return Description.createSuiteDescription(Class.class);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void run(RunNotifier rn) {
    for (Method methodToExecute : testMethodsToExecute) {
      Description spec = Description.createTestDescription(methodToExecute.getDeclaringClass().getName(),
                                                           methodToExecute.getName());
      rn.fireTestStarted(spec);
      
      try {
        final Object testClassInstance = classUnderTest.newInstance();
        methodToExecute.invoke(testClassInstance, (Object[]) null);
        
        rn.fireTestFinished(spec);
      } catch (InvocationTargetException ex) {
        // if an exception occured during the execution of the target method, it is wrapped in an InvocationTargetException
        rn.fireTestFailure(new Failure(spec, ex.getCause()));
      } catch (Exception ex) {
        // all other exceptions get a generic treatment
        rn.fireTestFailure(new Failure(spec, ex));
      } 
    }
  }

  /**
   * Return the class, which is under test.
   * 
   * @return class the runner has detected for testing
   */
  Class getClassUnderTest() {
    return classUnderTest;
  }

  /**
   * Return the methods, which are marked for test execution.
   * 
   * @return list of methods which are marked for testing
   */
  List<Method> getTestMethodsToExecute() {
    return testMethodsToExecute;
  }
}

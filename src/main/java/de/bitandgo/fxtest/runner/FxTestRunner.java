package de.bitandgo.fxtest.runner;

import com.sun.javafx.application.PlatformImpl;
import de.bitandgo.fxtest.annotation.FxTest;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * fxTest Runner class, which uses JUnit's {@link Runner} to enable the exection of the tests.<p/>
 * It looks for methods annotated with {@link FxTest} and than differentiates between those which are enabled and those whicht are not. The enabled
 * ones are executed via reflection while the disabled ones are ignored.
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
   * All methods of the class, which are annotated with {@link FxTest}, together with the information if they should be executed.
   */
  private final Map<Method, Boolean> testMethods = new HashMap<>();
  
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
      if(null != fxTestAnnotation) {
        testMethods.put(classMethod, fxTestAnnotation.enabled());
      }
    }

    initJavaFxContext();
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public Description getDescription() {
    return Description.createSuiteDescription(classUnderTest);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void run(RunNotifier rn) {
    // to run all the test methods an instance of the class under test is needed
    final Object testClassInstance = createClassUnderTestInstance();

    for (Map.Entry<Method, Boolean> methodToExecute : testMethods.entrySet()) {
      final Method  testMethod  = methodToExecute.getKey();
      final Boolean testEnabled = methodToExecute.getValue();
      
      Description spec = Description.createTestDescription(testMethod.getDeclaringClass().getName(),
                                                           testMethod.getName());

      if(testEnabled && null != testClassInstance) {
        // marked test as started
        rn.fireTestStarted(spec);
        
        try {
          testMethod.invoke(testClassInstance, (Object[]) null);

          // if no exception was thrown, the test can be marked as successfully finished
          rn.fireTestFinished(spec);
        } catch (InvocationTargetException ex) {
          // if an exception occured during the execution of the target method, it is wrapped in an InvocationTargetException
          rn.fireTestFailure(new Failure(spec, ex.getCause()));
        } catch (Exception ex) {
          // all other exceptions get a generic treatment
          rn.fireTestFailure(new Failure(spec, ex));
        }
      } else {
        // all test which are not enabled are marked as ignored
        rn.fireTestIgnored(spec);
      } 
    }
  }

  /**
   * Tries to create an object of the {@link #classUnderTest} fields class type.
   *
   * @return instance of the {@link #classUnderTest} or {@code null} if an exception occured during the instantiation process
   */
  private Object createClassUnderTestInstance() {
    try {
      return classUnderTest.newInstance();
    } catch (InstantiationException|IllegalAccessException ex) {
      return null;
    }
  }

  /**
   * This method initializes the JavaFX context, which is necessary to avoid the {@link IllegalStateException} "Toolkit not initialized" when creating
   * a {@link javafx.scene.Node} elements in a fxTest method.
   */
  private void initJavaFxContext() {
    PlatformImpl.startup(() -> {
      // nothing to be done here
    });
  }

  /**
   * Returns the class, which is under test.
   * 
   * @return class the runner has detected for testing
   */
  Class getClassUnderTest() {
    return classUnderTest;
  }

  /**
   * Returns the methods, which are marked for test execution. These have the value {@code true} in the {@code Map}.
   * 
   * @return list of methods, which are marked for testing
   */
  List<Method> getTestMethodsToExecute() {
    return testMethods.entrySet().
            stream().
            filter(me -> me.getValue()).
            map(Map.Entry::getKey).
            collect(Collectors.toList());
  }

  /**
   * Returns the methods, which are disabled for execution. These have the value {@code false} in the {@code Map}.
   *
   * @return list of methods, which are not marked for testing
   */
  List<Method> getTestMethodsToIgnore() {
    return testMethods.entrySet().
            stream().
            filter(me -> !me.getValue()).
            map(Map.Entry::getKey).
            collect(Collectors.toList());
  }
}

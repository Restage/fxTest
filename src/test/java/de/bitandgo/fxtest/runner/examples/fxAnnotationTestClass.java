package de.bitandgo.fxtest.runner.examples;

import de.bitandgo.fxtest.annotation.FxTest;

/**
 * FxTest test class, which is used to proof the annotations are correctly analysed by the {@link de.bitandgo.fxtest.runner.FxTestRunner}.
 *
 * @author Kaufmann, Richard
 * @since 0.1.0
 */
public class fxAnnotationTestClass {
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

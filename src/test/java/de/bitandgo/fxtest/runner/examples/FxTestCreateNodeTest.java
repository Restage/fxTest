package de.bitandgo.fxtest.runner.examples;

import de.bitandgo.fxtest.annotation.FxTest;
import de.bitandgo.fxtest.runner.FxTestRunner;
import javafx.scene.control.Button;
import org.junit.runner.RunWith;

/**
 * FxTest test class, which is used to proof if JavaFX {@link javafx.scene.Node Nodes} can be created within the context of fxTests.
 *
 * @author Kaufmann, Richard
 * @since 0.1.0
 */
@RunWith(FxTestRunner.class)
public class FxTestCreateNodeTest {

  @FxTest
  public void testMethod() {
    new Button();
  }
}
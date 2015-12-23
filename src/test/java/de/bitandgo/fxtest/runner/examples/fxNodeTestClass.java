package de.bitandgo.fxtest.runner.examples;

import de.bitandgo.fxtest.annotation.FxTest;
import javafx.scene.control.Button;

/**
 * FxTest test class, which is used to proof if JavaFX {@link javafx.scene.Node Nodes} can be created within the context of fxTests.
 *
 * @author Kaufmann, Richard
 * @since 0.1.0
 */
public class fxNodeTestClass {
  @FxTest
  public void createFxButtonTest() {
    Button testButton = new Button();
  }
}
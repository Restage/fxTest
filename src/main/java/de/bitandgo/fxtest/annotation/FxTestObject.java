package de.bitandgo.fxtest.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks one and only one field of the test class as the object to test. Only {@link javafx.scene.Node Nodes} (and subclasses),
 * {@link javafx.scene.Scene Scenes} and {@link javafx.stage.Stage Stages} are valid class types.
 *
 * @author Kaufmann, Richard
 * @since 0.1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FxTestObject {
  // nothing do be done here yet
}

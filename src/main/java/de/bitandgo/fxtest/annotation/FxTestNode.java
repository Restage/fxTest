package java.de.bitandgo.fxtest.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a field as {@link javafx.scene.Node} under test. Exactly one field per FxTest class must me marked as FxTestNode.
 *
 * @author Kaufmann, Richard
 * @since 0.1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FxTestNode {
  // no code needed yet
}

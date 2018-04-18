package es.prueba.jorge.commons;

import java.io.Serializable;

import javax.annotation.Nonnull;

/**
 * @author Jorge García Villanueva &lt;Jorge.garcia@gmail.com&gt;
 * @param <T> tipo del ValueObject.
 */
public interface ValueObject<T> extends Serializable, Comparable<T> {
  /**
   * Método para poder comparar ValueObjects del mismo tipo.
   *
   * @param other ValueObject del mismo tipo con el que comparar.
   * @return {@code true} si los valores de todos los atributos del ValueObject proporcionado y de
   *         este coinciden, {@code false} en caso contrario.
   */
  boolean sameValueAs(@Nonnull T other);
}

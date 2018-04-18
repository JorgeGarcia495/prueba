/**
 *
 */
package es.prueba.jorge.commons;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.collect.ComparisonChain;

import java.io.Serializable;
import java.util.Objects;

import javax.annotation.Nonnull;

/**
 * @author Jorge García Villanueva &lt;Jorgegv95@gmail.com&gt;
 * @param <T> Tipo de la Entity
 * @param <I> Identity de la Entidad
 */
public abstract class Entity<T extends Entity<T, I>, I extends Identity<I>>
    implements
      Serializable,
      Comparable<T> {

  /**
   * Ctor de la Entidad
   *
   * @param identity de la entidad
   */
  protected Entity(@Nonnull final I identity) {
    _identity = identity;
  }

  /**
   * Las {@code Entity} se comparan únicamente por su identidad.
   *
   * @param other {@code Entity} del mismo tipo con la que comparar.
   * @return {@code true} si ambas {@code Entity} tienen la misma identidad (independientemente del
   *         valor del resto de los atributos), {@code false} en caso contrario.
   */
  public boolean sameIdentityAs(@Nonnull final T other) {
    return sameIdentityAs(checkNotNull(other).identity());
  }

  /**
   * Las {@code Entity} se comparan únicamente por su identidad.
   *
   * @param other {@code Identity} del mismo tipo con la que comparar.
   * @return {@code true} si ambas {@code Entity} tienen la misma identidad (independientemente del
   *         valor del resto de los atributos), {@code false} en caso contrario.
   */
  public boolean sameIdentityAs(@Nonnull final I other) {
    return Objects.equals(identity(), checkNotNull(other));
  }


  /**
   * Compara dos {@code Entity} por sus valores.
   *
   * @param other {@code Entity} con la que comparar.
   * @return {@code true} si <strong>todos</strong> los datos (no sólo la {@code identity}) y estado
   *         de ambas {@code entity} son identicos, {@code false} en caso contrario.
   */
  abstract public boolean sameValueAs(@Nonnull final T other);

  /**
   * @return the _identity
   */
  public I identity() {
    return _identity;
  }

  @Override
  public int compareTo(@Nonnull final T that) {
    return ComparisonChain.start().compare(identity(), that.identity()).result();
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean equals(@Nonnull final Object obj) {
    if (this == obj) {
      return true;
    }
    if (Objects.isNull(obj) || (getClass() != obj.getClass())) {
      return false;
    }
    return sameIdentityAs((T) obj);
  }

  @Override
  public int hashCode() {
    if (_hashCode == 0) {
      _hashCode = Objects.hash(identity());
    }
    return _hashCode;
  }

  private static final long serialVersionUID = -6478688621005112446L;
  final private I _identity;
  private transient int _hashCode;
}

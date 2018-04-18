/**
 *
 */
package es.prueba.jorge.model;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Objects.isNull;

import com.google.common.annotations.Beta;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ComparisonChain;
import com.google.errorprone.annotations.Immutable;

import java.time.Instant;
import java.util.Objects;

import javax.annotation.Nonnull;

import es.prueba.jorge.commons.ValueObject;

/**
 * Representa los datos de una operación CRUD.
 * <p>
 * Básicamente contiene los datos de {@link UsuarioId quien} y {@link Instant cuando} ha realizado
 * una operación.
 *
 * @author Jorge Garcia Villanueva &lt;Jorgegv95@gmail.com&gt;
 */
@Beta
@Immutable
public final class DatosCRUD implements ValueObject<DatosCRUD> {
  /**
   * Construye un {@link DatosCRUD} utilizando los distintos argumentos.
   *
   * @param usuarioId que ha realizado la operación.
   * @param instant en el tiempo en que se ha realizado la operación.
   * @throws NullPointerException si alguno de los argumentos es {@code null}.
   */
  public DatosCRUD(@Nonnull final UsuarioId usuarioId, @Nonnull final Instant instant) {
    _usuarioId = checkNotNull(usuarioId);
    _instant = checkNotNull(instant);
  }

  /**
   * @return la {@link UsuarioId identity} del {@link Usuario} que ha realizado esta operación.
   */
  public UsuarioId usuarioId() {
    return _usuarioId;
  }

  /**
   * @return el {@link Instant} en que se ha producido la operación.
   */
  public Instant instant() {
    return _instant;
  }

  @Override
  public int hashCode() {
    if (_hashCode == 0) {
      _hashCode = Objects.hash(usuarioId(), instant());
    }
    return _hashCode;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (Objects.isNull(obj) || (getClass() != obj.getClass())) {
      return false;
    }
    return sameValueAs((DatosCRUD) obj);
  }

  @Override
  public int compareTo(@Nonnull final DatosCRUD that) {
    // La comparación se ordena de la propiedad más discriminante a la menos.
    return ComparisonChain.start().compare(instant(), that.instant())
        .compare(usuarioId(), that.usuarioId()).result();
  }


  @Override
  public boolean sameValueAs(@Nonnull final DatosCRUD other) {
    // La comparación se ordena de la propiedad más discriminante a la menos.
    return Objects.equals(instant(), checkNotNull(other).instant())
        && Objects.equals(usuarioId(), other.usuarioId());
  }

  @Override
  @SuppressWarnings("nls")
  public String toString() {
    if (isNull(_toString)) {
      _toString = MoreObjects.toStringHelper(this).add("usuarioId", usuarioId())
          .add("instant", instant()).toString();
    }
    return _toString;
  }

  private static final long serialVersionUID = 7052887590703490546L;

  private final UsuarioId _usuarioId;

  private final Instant _instant;

  // Internal fields for cache values
  /** <i>HashCode</i> cacheado. */
  private transient int _hashCode;

  /** Salida de {@link #toString()} cacheada. */
  private transient String _toString;

}

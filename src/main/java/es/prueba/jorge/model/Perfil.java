/**
 *
 */
package es.prueba.jorge.model;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Strings.emptyToNull;
import static com.google.common.base.Strings.nullToEmpty;
import static java.util.Objects.isNull;

import com.google.common.annotations.Beta;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.ImmutableList;
import com.google.errorprone.annotations.Immutable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import es.prueba.jorge.commons.ValueObject;

/**
 * Representa el perfil de un {@link Usuario}.
 * <p>
 * Cada {@code Usuario} tiene un único {@code Perfil} y este determina las funcionalidades (casos de
 * uso) que la aplicación le proporciona.
 *
 * @author Jorge García Villanueva &lt;Alberto.Dominguez@ext.docout.es&gt;
 */
@Beta
@Immutable
public final class Perfil implements ValueObject<Perfil> {
  static private final Map<String, Perfil> PERFIL = new HashMap<>(PerfilValues.values().length);
  static {
    for (final PerfilValues perfilValue : PerfilValues.values()) {
      PERFIL.put(perfilValue.name(), new Perfil(perfilValue.name()));
    }
  }

  /** Utilizado para indicar un <i>perfil</i> CLIENTE. */
  public static final Perfil CLIENTE = PERFIL.get(PerfilValues.CLIENTE.name());
  /** Utilizado para indicar un <i>perfil</i> EXTERNO */
  public static final Perfil EXTERNO = PERFIL.get(PerfilValues.EXTERNO.name());

  /**
   * @return Una lista inmutable y ordenada de todos los valores de {@link Perfil} existentes.
   */
  public static ImmutableList<String> getPerfiles() {
    return ImmutableList.sortedCopyOf(PERFIL.keySet());
  }

  /**
   * Informa si el {@code value} pasado como parámetro es válido para construir un {@link Perfil}.
   *
   * @param value a validar.
   * @return {@code true} si el parámetro pasado permitiría crear un {@code Perfil} válido,
   *         {@code false} en caso contrario.
   */
  public static boolean isValid(@Nullable final String value) {
    return !Objects.isNull(value) && PERFIL.containsKey(value.trim());
  }

  /**
   * Obtiene el {@link Perfil} correspondiente a {@code value}.
   *
   * @param value del {@code Perfil} a obtener. Ha de ser uno de los valores retornados por
   *        {@link Perfil#getPerfiles() getPerfiles()}.
   * @throws NullPointerException si {@code value} es nulo, vacío o compuesto de espacios en blanco.
   * @throws IllegalArgumentException si {@code value} no corresponde a ningún {@code Perfil}
   *         válido.
   * @return {@code Perfil} correspondiente a {@code value}.
   */
  public static Perfil valueOf(@Nonnull final String value) {
    checkArgument(isValid(checkNotNull(emptyToNull(nullToEmpty(value).trim()))));
    return PERFIL.get(value.trim());
  }

  /**
   * @return el tipo de {@link Perfil}.
   */
  public String perfil() {
    return _perfil.name();
  }

  @Override
  public int hashCode() {
    if (_hashCode == 0) {
      _hashCode = Objects.hash(perfil());
    }
    return _hashCode;
  }

  @Override
  public boolean equals(@Nullable final Object obj) {
    if (this == obj) {
      return true;
    }
    if (Objects.isNull(obj) || (getClass() != obj.getClass())) {
      return false;
    }
    return sameValueAs((Perfil) obj);
  }

  @Override
  public boolean sameValueAs(@Nonnull final Perfil other) {
    // La comparación se ordena de la propiedad más discriminante y rápida a la menos
    return Objects.equals(perfil(), checkNotNull(other).perfil());
  }

  @Override
  public int compareTo(@Nonnull final Perfil that) {
    return ComparisonChain.start().compare(_perfil, checkNotNull(that)._perfil).result();
  }

  @SuppressWarnings("nls")
  @Override
  public String toString() {
    if (isNull(_toString)) {
      _toString = MoreObjects.toStringHelper(this).add("perfil", perfil()).toString();
    }
    return _toString;
  }

  /**
   * Crea un nuevo {@link Perfil} inicializado con el código especificado en el argumento.
   *
   * @param value del nuevo {@code Perfil} a crear. Ha de ser uno de los códigos retornados por
   *        {@link Perfil#getPerfiles() getPerfiles}.
   * @throws NullPointerException si {@code code} es nulo, vacío o compuesto de espacios en blanco.
   */
  private Perfil(@Nonnull final String value) {
    _perfil = PerfilValues.valueOf(checkNotNull(emptyToNull(nullToEmpty(value).trim())));
  }

  /**
   * Distintos códigos de {@link Perfil} existentes.
   */
  @SuppressWarnings("hiding")
  private enum PerfilValues {
    /** Operador */
    CLIENTE,
    /** Responsable Departamento */
    EXTERNO;
  }

  private final PerfilValues _perfil;

  // Internal fields for cache values
  /** <i>HashCode</i> cacheado. */
  private int _hashCode;

  /** Salida de {@link #toString()} cacheada. */
  private String _toString;

  private static final long serialVersionUID = -472950925098041435L;
}

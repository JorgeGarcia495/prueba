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
 * Representa un tipo de operación CRUD.
 *
 * @author Jorge garcía Villanueva &lt;Jorgegv95@gmail.com&gt;
 * @see DatosCRUD
 */
@Beta
@Immutable
public final class TipoCRUD implements ValueObject<TipoCRUD> {
  private static Map<String, TipoCRUD> TIPO_CRUD = new HashMap<>(TipoCRUDValues.values().length);
  static {
    for (final TipoCRUDValues tipoCRUDValues : TipoCRUDValues.values()) {
      TIPO_CRUD.put(tipoCRUDValues.name(), new TipoCRUD(tipoCRUDValues.name()));
    }
  }

  /** Constante para una operación de {@code ALTA}. */
  static public TipoCRUD ALTA = TIPO_CRUD.get(TipoCRUDValues.ALTA.name());

  /** Constante para una operación de {@code BAJA}. */
  static public TipoCRUD BAJA = TIPO_CRUD.get(TipoCRUDValues.BAJA.name());

  /** Constante para una operación de {@code MOD}ificación. */
  static public TipoCRUD MOD = TIPO_CRUD.get(TipoCRUDValues.MOD.name());

  /**
   * @return Una lista ordenada e inmutable de todos los valores de {@link TipoCRUD} existentes.
   */
  public static ImmutableList<String> getTiposCRUD() {
    return ImmutableList.sortedCopyOf(TIPO_CRUD.keySet());
  }

  /**
   * Informa si el {@code value} pasado como parámetro es válido para construir un {@link TipoCRUD}.
   *
   * @param value a validar.
   * @return {@code true} si el parámetro pasado permitiría crear un {@code TipoCRUD} válido,
   *         {@code false} en caso contrario.
   */
  public static boolean isValid(@Nullable final String value) {
    return TIPO_CRUD.containsKey(nullToEmpty(value).trim());
  }

  /**
   * Obtiene el {@link TipoCRUD} correspondiente a {@code value}.
   *
   * @param value del {@code TipoCRUD} a obtener. Ha de ser uno de los valores retornados por
   *        {@link #getTiposCRUD()}.
   * @return {@code TipoCRUD} correspondiente a {@code value}.
   * @throws NullPointerException si {@code value} es nulo, vacío o compuesto de espacios en blanco.
   * @throws IllegalArgumentException si {@code value} no corresponde a ningún {@code TipoCRUD}
   *         válido.
   */
  public static TipoCRUD valueOf(@Nonnull final String value) {
    checkArgument(isValid(checkNotNull(emptyToNull(nullToEmpty(value).trim()))));
    return TIPO_CRUD.get(value.trim());
  }

  /**
   * @return el {@code value} del {@link TipoCRUD}.
   */
  public String value() {
    return _tipoCRUD.name();
  }

  @Override
  public int hashCode() {
    if (_hashCode == 0) {
      _hashCode = Objects.hash(value());
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
    return sameValueAs((TipoCRUD) obj);
  }

  @Override
  public boolean sameValueAs(@Nonnull final TipoCRUD other) {
    return Objects.equals(value(), checkNotNull(other).value());
  }

  @Override
  public int compareTo(@Nonnull final TipoCRUD that) {
    return ComparisonChain.start().compare(_tipoCRUD, that._tipoCRUD).result();
  }

  @SuppressWarnings("nls")
  @Override
  public String toString() {
    if (isNull(_toString)) {
      _toString = MoreObjects.toStringHelper(this).add("value", value()).toString();
    }
    return _toString;
  }

  /**
   * Crea un nuevo {@link TipoCRUD} inicializado con el valor especificado en el argumento.
   *
   * @param value del nuevo {@code TipoCRUD} a crear. Ha de ser uno de los valores retornados por
   *        {@link #getTiposCRUD()}.
   * @throws NullPointerException si {@code value} es nulo, vacío o compuesto de espacios en blanco.
   * @throws IllegalArgumentException si {@code value} no corresponde a uno de los códigos
   *         existentes.
   */
  private TipoCRUD(@Nonnull final String value) {
    _tipoCRUD = TipoCRUDValues.valueOf(checkNotNull(emptyToNull(nullToEmpty(value).trim())));
  }

  /**
   * Distintos códigos de {@link TipoCRUD} existentes.
   */
  @SuppressWarnings("hiding")
  private enum TipoCRUDValues {
    /** Alta */
    ALTA,
    /** Baja */
    BAJA,
    /** Modificación */
    MOD
  }

  private static final long serialVersionUID = 3965349244579867632L;

  private final TipoCRUDValues _tipoCRUD;

  // Internal fields for cache values
  /** <i>HashCode</i> cacheado. */
  private int _hashCode;

  /** Salida de {@link #toString()} cacheada. */
  private String _toString;
}

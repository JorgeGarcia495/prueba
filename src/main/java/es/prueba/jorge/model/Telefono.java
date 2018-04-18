package es.prueba.jorge.model;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Strings.emptyToNull;
import static com.google.common.base.Strings.nullToEmpty;
import static java.util.Objects.isNull;

import com.google.common.annotations.Beta;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ComparisonChain;
import com.google.errorprone.annotations.Immutable;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import es.prueba.jorge.commons.ValueObject;

/**
 * Representa únicamente un número teléfono.
 *
 * @author Jorge García Villanueva &lt;Jorgegv95@gmail.com&gt;
 */
@Beta
@Immutable
public final class Telefono implements ValueObject<Telefono> {
  /** Longitud del teléfono. */
  public static final int TELEFONO_LENGTH = 9;

  /**
   * Comprueba si la cadena {@code value} pasada cumple con las características necesarias para ser
   * válido. Un teléfono será válido cuando tenga como longitud {@value #TELEFONO_LENGTH}, no
   * contenga espacios, y no sea nulo. Si se cumple devolverá {@code true}, y si no {@code false}.
   *
   * @param value pasado como argumento.
   * @return {@code true} cuando {@code value} cumple con el formato correcto o {@code false} cuando
   *         no lo cumple.
   */
  public static boolean isValid(@Nullable final String value) {
    final String noSpaces = emptyToNull(nullToEmpty(value).trim());
    /*
     * Creamos el pattern para comprobar si el {@code telefono} pasado es válido "[1-9][0-9]{n}".
     * Donde n es el numero de longitud del teléfono -1, ya que el teléfono no puede empezar por 0 y
     * por eso se repite tantas veces como la longitud -1.
     */
    final Pattern pattern = Pattern.compile(
        new StringBuilder("[1-9][0-9]{").append(TELEFONO_LENGTH - 1).append("}").toString()); //$NON-NLS-1$//$NON-NLS-2$

    final Matcher matcher = pattern.matcher(noSpaces);
    return matcher.matches();
  }

  /**
   * @return una cadena con formato {@code Telefono}.
   */
  public String telefono() {
    return _value;
  }

  @Override
  public int hashCode() {
    if (_hashCode == 0) {
      _hashCode = Objects.hash(telefono());
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
    return sameValueAs((Telefono) obj);
  }

  @Override
  public int compareTo(@Nonnull final Telefono that) {
    return ComparisonChain.start().compare(_value, that._value).result();
  }


  @Override
  public boolean sameValueAs(@Nonnull final Telefono other) {
    return Objects.equals(telefono(), checkNotNull(other).telefono());
  }

  @Override
  @SuppressWarnings("nls")
  public String toString() {
    if (isNull(_toString)) {
      _toString =
          MoreObjects.toStringHelper(this).omitNullValues().add("_value", telefono()).toString();
    }
    return _toString;
  }

  /**
   * Construye un {@link Telefono} utilizando el {@code value} pasado como argumento.
   *
   * @param value del {@code Telefono} a construir.
   * @throws IllegalArgumentException si {@code value} no es una cadena de teléfono válida.
   */
  public Telefono(@Nonnull final String value) {
    final String noNull = checkNotNull(emptyToNull(nullToEmpty(value).trim()));
    checkArgument(Telefono.isValid(noNull));
    _value = noNull;
  }

  private static final long serialVersionUID = -2942809812240299434L;

  private final String _value;

  // Internal fields for cache values
  /** <i>HashCode</i> cacheado. */
  private transient int _hashCode;

  /** Salida de {@link #toString()} cacheada. */
  private transient String _toString;

}

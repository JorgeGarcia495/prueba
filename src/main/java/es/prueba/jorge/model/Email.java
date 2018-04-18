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
import com.google.errorprone.annotations.Immutable;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import es.prueba.jorge.commons.ValueObject;

/**
 * Representa una direccion de correo electrónico válida. una dirección de correo electrónico válida
 * es una dirección compuesta por dos partes, un nombre de usuario y un nombre de dominio separados
 * por @ sin espacios, y con una longitud no superior a {@value #MAX_EMAIL_LENGTH}. Se presenta así:
 * nombredeusuario@nombrededominio.extensióndominio
 *
 * @author Jorge García Villanueva &lt;Jorgegv95@gmail.com&gt;
 */
@Beta
@Immutable
public final class Email implements ValueObject<Email> {
  /** Longitud maxima del email. */
  public static final int MAX_EMAIL_LENGTH = 100;

  /**
   * Comprueba si la cadena de {@link Email} pasada cumple con las caracteristicas necesarias para
   * ser válida. Si se cumple devolvera {@code true}, y si no {@code false}.
   *
   * @param value cadena de un email a validar
   * @return {@code true} si el email pasado es correcto, o {@code false} si no lo es.
   * @throws IllegalArgumentException si {@code value} tiene una longitud mayor de la permitida
   */
  public static boolean isValid(@Nullable final String value) {
    final String noSpaces = emptyToNull(nullToEmpty(value).trim());
    checkArgument(!isNull(noSpaces) && (noSpaces.length() < MAX_EMAIL_LENGTH)
        && (noSpaces.indexOf(' ') == -1));

    final Pattern pattern = Pattern.compile(PATTERN_EMAIL);

    final Matcher matcher = pattern.matcher(noSpaces);
    return matcher.matches();
  }

  /**
   * Construye un {@link Email} utilizando el {@code value} pasado como argumento, siempre que este
   * sea válido.
   *
   * @param value del {@code Email} a construir.
   */
  public Email(@Nonnull final String value) {
    final String noNull = checkNotNull(emptyToNull(nullToEmpty(value).trim()));
    checkArgument(Email.isValid(noNull));
    _value = noNull;
  }

  /**
   * @return una cadena con formato {@code Email}.
   */
  public String email() {
    return _value;
  }

  @Override
  public int hashCode() {
    if (_hashCode == 0) {
      _hashCode = Objects.hash(email());
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
    return sameValueAs((Email) obj);
  }

  @Override
  public int compareTo(@Nonnull final Email that) {
    return ComparisonChain.start().compare(_value, that._value).result();
  }

  @Override
  public boolean sameValueAs(@Nonnull final Email other) {
    return Objects.equals(email(), checkNotNull(other).email());
  }

  @Override
  @SuppressWarnings("nls")
  public String toString() {
    if (isNull(_toString)) {
      _toString =
          MoreObjects.toStringHelper(this).omitNullValues().add("email", email()).toString();
    }
    return _toString;
  }

  /**
   * Patrón para validar si el formato de la cadena de email pasada al constructor cumple con las
   * características para ser una dirección de correo válida.
   */
  @SuppressWarnings("nls")
  private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
      + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

  private static final long serialVersionUID = -2942809812240299434L;

  private final String _value;

  // Internal fields for cache values
  /** <i>HashCode</i> cacheado. */
  private transient int _hashCode;

  /** Salida de {@link #toString()} cacheada. */
  private transient String _toString;

}

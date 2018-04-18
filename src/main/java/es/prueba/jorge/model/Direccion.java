package es.prueba.jorge.model;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ComparisonChain;

import java.util.Objects;

import javax.annotation.Nonnull;

import es.prueba.jorge.commons.ValueObject;

/**
 * Representa una dirección completa.
 *
 * @author Jorge García Villanueva &lt;jorgegv95@gmail.com&gt;
 */
public class Direccion implements ValueObject<Direccion> {
  /**
   * @return una referencia al <i>Builder</i> de la {@link Direccion} para facilitar su
   *         construcción.
   */
  public static final Builder builder() {
    return new Builder();
  }

  /**
   * @return the direccion.
   */
  public String direccion() {
    return _datos.direccion;
  }

  /**
   * @return the codigoPostal.
   */
  public Integer codigoPostal() {
    return _datos.codigoPostal;
  }

  /**
   * @return the poblacion.
   */
  public String poblacion() {
    return _datos.poblacion;
  }

  /**
   * @return the provincia.
   */
  public String provincia() {
    return _datos.provincia;
  }

  @Override
  public int compareTo(final Direccion that) {
    return ComparisonChain.start().compare(direccion(), that.direccion())
        .compare(codigoPostal(), that.codigoPostal()).compare(poblacion(), that.poblacion())
        .compare(provincia(), that.provincia()).result();
  }

  @Override
  public boolean sameValueAs(final Direccion other) {
    return Objects.equals(direccion(), other.direccion())
        && Objects.equals(codigoPostal(), other.codigoPostal())
        && Objects.equals(poblacion(), other.poblacion())
        && Objects.equals(provincia(), other.provincia());
  }

  @Override
  public boolean equals(@Nonnull final Object obj) {
    if (this == obj) {
      return true;
    }
    if (Objects.isNull(obj) || (getClass() != obj.getClass())) {
      return false;
    }
    return sameValueAs((Direccion) obj);
  }

  @SuppressWarnings("nls")
  @Override
  public String toString() {
    if (Objects.isNull(_toString)) {
      return MoreObjects.toStringHelper(this).add("direccion", direccion())
          .add("codigoPostal", codigoPostal()).add("poblacion", poblacion())
          .add("provincia", provincia()).toString();
    }
    return _toString;
  }

  @Override
  public int hashCode() {
    if (_hashCode == 0) {
      return Objects.hash(direccion(), codigoPostal(), poblacion(), provincia());
    }
    return _hashCode;
  }

  /**
   * Ctor por defecto
   *
   * @param direccion existente
   * @param codPostal de la {@link Direccion}.
   * @param poblacion de la {@code Direccion}.
   * @param provincia de la {@code Direccion}.
   */
  Direccion(@Nonnull final String direccion, @Nonnull final Integer codPostal,
      @Nonnull final String poblacion, @Nonnull final String provincia) {
    _datos.direccion = checkNotNull(direccion);
    _datos.codigoPostal = checkNotNull(codPostal);
    _datos.poblacion = checkNotNull(poblacion);
    _datos.provincia = checkNotNull(provincia);
  }

  private static final long serialVersionUID = 7307651629496247970L;
  private final Data _datos = new Data();
  private transient int _hashCode;
  private transient String _toString;

  private static final class Data {
    /** Representa una dirección existente */
    String direccion;
    /** Código postal de la dirección */
    Integer codigoPostal;
    /** Población de la dirección */
    String poblacion;
    /** Provincia de la dirección */
    String provincia;

    /** Ctor por defecto */
    Data() {
      // Nothing to do
    }
  }

  /**
   * Builder de {@link Direccion} para facilitar su contrucción.
   */
  public static final class Builder {
    /**
     * @return un objecto {@link Direccion} con los datos inicializados en este <i>Builder</i>
     */
    public Direccion build() {
      return new Direccion(_item.direccion, _item.codigoPostal, _item.poblacion, _item.provincia);
    }

    /**
     * Establece la {@code direccion} en la construcción del objeto.
     *
     * @param direccion a establecer.
     * @return una referencia a este mismo <i>builder</i> para poder seguir encadenando las
     *         llamadas.
     */
    public Builder withDireccion(@Nonnull final String direccion) {
      _item.direccion = checkNotNull(direccion);
      return this;
    }

    /**
     * Establece el {@code codigoPostal} en la construcción del objeto.
     *
     * @param codigoPostal a establecer.
     * @return una referencia a este mismo <i>builder</i> para poder seguir encadenando las
     *         llamadas.
     */
    public Builder withCodigoPostal(@Nonnull final Integer codigoPostal) {
      _item.codigoPostal = checkNotNull(codigoPostal);
      return this;
    }

    /**
     * Establece la {@code poblacion} en la construcción del objeto.
     *
     * @param poblacion a establecer.
     * @return una referencia a este mismo <i>builder</i> para poder seguir encadenando las
     *         llamadas.
     */
    public Builder withPoblacion(@Nonnull final String poblacion) {
      _item.poblacion = checkNotNull(poblacion);
      return this;
    }

    /**
     * Establece la {@code provincia} en la construcción del objeto.
     *
     * @param provincia a establecer.
     * @return una referencia a este mismo <i>builder</i> para poder seguir encadenando las
     *         llamadas.
     */
    public Builder withProvincia(@Nonnull final String provincia) {
      _item.provincia = checkNotNull(provincia);
      return this;
    }

    /**
     * Ctor del <i>Builder<i>. Solamente crea una nueva estructura de datos.
     */
    Builder() {
      _item = new Data();
    }

    private final Data _item;
  }
}

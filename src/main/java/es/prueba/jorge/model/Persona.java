package es.prueba.jorge.model;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ComparisonChain;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

import javax.annotation.Nonnull;

import es.prueba.jorge.commons.ValueObject;

/**
 * Representa una única persona mediante su nombre y apellidos.
 *
 * @author Jorge García Villanueva &lt;Jorgegv95@gmail.com&gt;
 */
public class Persona implements ValueObject<Persona> {
  /**
   * @return una referencia al <i>Builder</i> de está clase para facilitar su construcción.
   */
  public static Builder builder() {
    return new Builder();
  }

  /**
   * @return the nombre
   */
  public String nombre() {
    return _datos.nombre;
  }

  /**
   * @return the apellido1
   */
  public String apellido1() {
    return _datos.apellido1;
  }

  /**
   * @return the apellido2
   */
  public Optional<String> apellido2() {
    return Optional.ofNullable(_datos.apellido2);
  }

  @Override
  public int compareTo(final Persona that) {
    return ComparisonChain.start().compare(nombre(), that.nombre())
        .compare(apellido1(), that.apellido1())
        .compare(apellido2().orElse(null), that.apellido2().orElse(null)).result();
  }

  @Override
  public boolean sameValueAs(final Persona other) {
    return Objects.equals(nombre(), other.nombre())
        && Objects.equals(apellido1(), other.apellido1())
        && Objects.equals(apellido2().orElse(null), other.apellido2().orElse(null));
  }

  @Override
  public boolean equals(@Nonnull final Object obj) {
    if (this == obj) {
      return true;
    }
    if (Objects.isNull(obj) || (getClass() != obj.getClass())) {
      return false;
    }
    return sameValueAs((Persona) obj);
  }

  @SuppressWarnings("nls")
  @Override
  public String toString() {
    if (Objects.isNull(_toString)) {
      return MoreObjects.toStringHelper(this).add("nombre", nombre()).add("apellido1", apellido1())
          .add("apellido2", apellido2().orElse(null)).toString();
    }
    return _toString;
  }

  @Override
  public int hashCode() {
    if (_hashCode == 0) {
      return Objects.hash(nombre(), apellido1(), apellido2());
    }
    return _hashCode;
  }

  /**
   * Contructor para faciltar las llamadas
   *
   * @param nombre de la persona
   * @param apellido1 de la persona
   * @param apellido2 de la persona
   */
  Persona(@Nonnull final String nombre, @Nonnull final String apellido1,
      @Nonnull final String apellido2) {
    _datos.nombre = checkNotNull(nombre);
    _datos.apellido1 = checkNotNull(apellido1);
    _datos.apellido2 = apellido2;
  }

  private static final long serialVersionUID = 8234662989628840201L;
  private final Data _datos = new Data();
  private int _hashCode;
  private transient String _toString;

  private static final class Data implements Serializable {
    /** Nombre de la persona */
    String nombre;
    /** Primer apellido de la persona */
    String apellido1;
    /** Segundo apellido de la persona */
    String apellido2;

    /** Ctor por defecto */
    Data() {
      // Nothing to do
    }

    private static final long serialVersionUID = 663096387065300975L;

  }

  /**
   * Builder para la clase {@link Persona}
   */
  public static final class Builder {
    /**
     * @return un nuevo objeto {@link Persona} con los datos inicializados en este <i>Builder</i>
     */
    public Persona build() {
      return new Persona(_item.nombre, _item.apellido1, _item.apellido2);
    }

    /**
     * Establece el nombre de la {@link Persona}
     *
     * @param nombre a establecer.
     * @return una referencia a este mismo <i>Builder</i> para poder seguir encadenando las
     *         llamadas.
     */
    public Builder withNombre(@Nonnull final String nombre) {
      _item.nombre = checkNotNull(nombre);
      return this;
    }

    /**
     * Establece el primer apellido de la {@link Persona}
     *
     * @param apellido1 a establecer.
     * @return una referencia a este mismo <i>Builder</i> para poder seguir encadenando las
     *         llamadas.
     */
    public Builder withApellido1(@Nonnull final String apellido1) {
      _item.apellido1 = checkNotNull(apellido1);
      return this;
    }

    /**
     * Establece el segundo apellido de la {@link Persona}
     *
     * @param apellido2 a establecer.
     * @return una referencia a este mismo <i>Builder</i> para poder seguir encadenando las
     *         llamadas.
     */
    public Builder withApellido2(@Nonnull final String apellido2) {
      _item.apellido2 = checkNotNull(apellido2);
      return this;
    }

    /**
     * Ctor del <i>Builder</i>. Crea una nueva estructura de datos.
     */
    Builder() {
      _item = new Data();
    }

    private final Data _item;
  }
}

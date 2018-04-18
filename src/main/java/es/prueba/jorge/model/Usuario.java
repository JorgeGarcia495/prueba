package es.prueba.jorge.model;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.MoreObjects;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.annotation.Nonnull;

import es.prueba.jorge.commons.Entity;

/**
 * Representa un usuario con acceso a la aplicación.
 *
 * @author Jorge García Villanueva &lt;Jorgegv95@gmail.com&gt;
 */
public class Usuario extends Entity<Usuario, UsuarioId> {
  /**
   * Crea y retorna un {@link Builder} con un {@link Usuario} creado con el constructor por defecto.
   *
   * @param usuarioId identity del {@code Usuario} a construir.
   * @return un {@code Builder} con un {@code Usuario} creado con el constructor por defecto.
   */
  public static Builder builder(@Nonnull final UsuarioId usuarioId) {
    return new Usuario.Builder(checkNotNull(usuarioId));
  }

  /**
   * @return the persona
   */
  public Persona persona() {
    return _datos.persona;
  }

  /**
   * @return the perfil
   */
  public Perfil perfil() {
    return _datos.perfil;
  }

  /**
   * @return the email
   */
  public Email email() {
    return _datos.email;
  }

  /**
   * @return the email
   */
  public Telefono telefono() {
    return _datos.telefono;
  }

  /**
   * @return the ultimaVisita
   */
  public Optional<Instant> ultimaVisita() {
    return Optional.ofNullable(_datos.ultimaVisita);
  }

  /**
   * @return the alta
   */
  public DatosCRUD alta() {
    return _datos.operaciones.get(TipoCRUD.ALTA);
  }

  /**
   * @return the baja
   */
  public Optional<DatosCRUD> baja() {
    return Optional.ofNullable(_datos.operaciones.get(TipoCRUD.BAJA));
  }

  /**
   * @return the modificacion
   */
  public Optional<DatosCRUD> modificacion() {
    return Optional.ofNullable(_datos.operaciones.get(TipoCRUD.MOD));
  }

  @Override
  public boolean sameValueAs(final Usuario other) {
    return Objects.equals(identity(), other.identity())
        && Objects.equals(persona(), other.persona()) && Objects.equals(perfil(), other.perfil())
        && Objects.equals(ultimaVisita(), other.ultimaVisita())
        && Objects.equals(alta(), other.alta()) && Objects.equals(baja(), other.baja())
        && Objects.equals(modificacion(), other.modificacion());
  }

  @SuppressWarnings("nls")
  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("identity", identity()).add("persona", persona())
        .add("perfil", perfil().toString()).add("ultimaVisita", ultimaVisita())
        .add("alta", alta().toString()).add("baja", baja().toString())
        .add("modificacion", modificacion().toString()).toString();
  }

  /**
   * Ctor del {@link Usuario}
   *
   * @param identity del {@code Usuario}
   * @param persona a representar del {@code usuario}
   * @param perfil del {@code usuario}
   * @param telefono del {@code usuario}
   * @param email del {@code usuario}
   * @param ultimaVisita del {@code usuario}
   * @param operaciones realizadas por el {@code usuario}
   */
  Usuario(@Nonnull final UsuarioId identity, @Nonnull final Persona persona,
      @Nonnull final Perfil perfil, @Nonnull final Telefono telefono, @Nonnull final Email email,
      @Nonnull final Instant ultimaVisita, @Nonnull final Map<TipoCRUD, DatosCRUD> operaciones) {
    super(identity);
    _datos.persona = checkNotNull(persona);
    _datos.perfil = checkNotNull(perfil);
    _datos.telefono = checkNotNull(telefono);
    _datos.email = checkNotNull(email);
    _datos.ultimaVisita = ultimaVisita;
    _datos.operaciones.putAll(operaciones);
  }

  private static final long serialVersionUID = -8431449940515179676L;
  private final Data _datos = new Data();

  private static final class Data {
    /** {@link Persona} asociada al {@link UsuarioId} */
    Persona persona;
    /** {@link Perfil} del usuario */
    Perfil perfil;
    /** Teléfono de contacto del Usuario */
    Telefono telefono;
    /** Email del Usuario */
    Email email;
    /** Último acceso del usuario */
    Instant ultimaVisita;
    /** Datos de operaciones realizadas */
    // Operaciones globales
    final Map<TipoCRUD, DatosCRUD> operaciones = new HashMap<>(TipoCRUD.getTiposCRUD().size());

    Data() {
      // Nothing to do
    }
  }

  /**
   * Clase para facilitar la construcción de un {@link Usuario}
   */
  public static final class Builder {
    /**
     * @return un {@link Usuario} con los datos inicializados en este <i>Builder</i>
     */
    public Usuario build() {
      return new Usuario(uid, _item.persona, _item.perfil, _item.telefono, _item.email,
          _item.ultimaVisita, _item.operaciones);
    }

    /**
     * Establece la {@link Persona} asociada al {@link Usuario} que se está construyendo.
     *
     * @param value a establecer.
     * @return una referencia a este mismo <i>builder</i> para poder seguir encadenando las
     *         llamadas.
     */
    public Builder withPersona(@Nonnull final Persona value) {
      _item.persona = checkNotNull(value);
      return this;
    }

    /**
     * Establece el {@link Perfil} del {@link Usuario} que se está construyendo.
     *
     * @param value a establecer.
     * @return una referencia a este mismo <i>builder</i> para poder seguir encadenando las
     *         llamadas.
     */
    public Builder withPerfil(@Nonnull final Perfil value) {
      _item.perfil = checkNotNull(value);
      return this;
    }

    /**
     * Establece el {@link Telefono} del {@link Usuario} que se está construyendo.
     *
     * @param value a establecer.
     * @return una referencia a este mismo <i>builder</i> para poder seguir encadenando las
     *         llamadas.
     */
    public Builder withTelefono(@Nonnull final Telefono value) {
      _item.telefono = checkNotNull(value);
      return this;
    }

    /**
     * Establece el {@link Email} del {@link Usuario} que se está construyendo.
     *
     * @param value a establecer.
     * @return una referencia a este mismo <i>builder</i> para poder seguir encadenando las
     *         llamadas.
     */
    public Builder withEmail(@Nonnull final Email value) {
      _item.email = checkNotNull(value);
      return this;
    }

    /**
     * Establece la {@code ultimaVisita} del {@link Usuario} que se está construyendo.
     *
     * @param value a establecer.
     * @return una referencia a este mismo <i>builder</i> para poder seguir encadenando las
     *         llamadas.
     */
    public Builder withUltimaVisita(@Nonnull final Instant value) {
      _item.ultimaVisita = checkNotNull(value);
      return this;
    }

    /**
     * Establece los {@link DatosCRUD} del alta del {@link Usuario} que se está construyendo.
     *
     * @param value {@link DatosCRUD} a establecer. No puede ser {@code null}.
     * @return una referencia a este mismo {@link Usuario.Builder Builder} para poder encadenar las
     *         llamadas.
     * @throws NullPointerException si {@code value} es {@code null}.
     */
    public Builder withDatosAlta(@Nonnull final DatosCRUD value) {
      _item.operaciones.put(TipoCRUD.ALTA, checkNotNull(value));

      return this;
    }

    /**
     * Establece los {@link DatosCRUD} de la baja del {@link Usuario} que se está construyendo.
     *
     * @param value {@link DatosCRUD} a establecer. No puede ser {@code null}.
     * @return una referencia a este mismo {@link Usuario.Builder Builder} para poder encadenar las
     *         llamadas.
     */
    public Builder withDatosBaja(@Nonnull final DatosCRUD value) {
      _item.operaciones.put(TipoCRUD.BAJA, checkNotNull(value));

      return this;
    }

    /**
     * Establece los {@link DatosCRUD} de última modificación del {@link Usuario} que se está
     * construyendo.
     *
     * @param value {@link DatosCRUD} a establecer. No puede ser {@code null}.
     * @return una referencia a este mismo {@link Usuario.Builder Builder} para poder encadenar las
     *         llamadas.
     */
    public Builder withDatosUltimaModificacion(@Nonnull final DatosCRUD value) {
      _item.operaciones.put(TipoCRUD.MOD, checkNotNull(value));

      return this;
    }

    /**
     * Ctor del <i>Builder. Solamente crea una nueva estructura de datos.
     *
     * @param usuarioId Identity del {@link Usuario} que se está construyendo
     */
    Builder(@Nonnull final UsuarioId usuarioId) {
      uid = checkNotNull(usuarioId);
      _item = new Usuario.Data();
    }

    private final UsuarioId uid;
    private final Data _item;
  }
}

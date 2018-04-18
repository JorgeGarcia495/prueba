package es.prueba.jorge.model;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ComparisonChain;

import java.util.Objects;

import javax.annotation.Nonnull;

import es.prueba.jorge.commons.Identity;

/**
 * @author Jorge García Villanueva &lt;Jorgegv95@gmail.com&gt;
 */
public class UsuarioId implements Identity<UsuarioId> {
  /**
   * Ctor de la Identidad
   *
   * @param id identity única de un {@link Usuario}
   */
  public UsuarioId(@Nonnull final Integer id) {
    this.id = id;
  }


  /**
   * @return the id
   */
  public Integer id() {
    return id;
  }


  @Override
  public boolean equals(@Nonnull final Object obj) {
    if (this == obj) {
      return true;
    }
    if (Objects.isNull(obj) || (getClass() != obj.getClass())) {
      return false;
    }
    return sameValueAs((UsuarioId) obj);
  }

  @Override
  public boolean sameValueAs(final UsuarioId other) {
    return Objects.equals(id(), checkNotNull(other).id());
  }

  @Override
  public int compareTo(final UsuarioId that) {
    return ComparisonChain.start().compare(id(), checkNotNull(that).id()).result();
  }

  @Override
  public String toString() {
    if (Objects.isNull(_toString)) {
      return MoreObjects.toStringHelper(this).add("id", id()).toString(); //$NON-NLS-1$
    }
    return _toString;
  }

  @Override
  public int hashCode() {
    if (_hashCode == 0) {
      return Objects.hash(id());
    }
    return _hashCode;
  }

  private static final long serialVersionUID = -3855615029485728532L;
  private final Integer id;

  private transient int _hashCode;
  private transient String _toString;
}

package es.prueba.jorge.model;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nonnull;

import es.prueba.jorge.commons.Entity;

/**
 * Representa una solicitud
 *
 * @author Jorge García Villanueva &lt;Jorgegv95@gmail.com&gt;
 */
public class Solicitud extends Entity<Solicitud, SolicitudId> {


  /**
   * @param identity de la solicitud
   */
  private Solicitud(@Nonnull final SolicitudId identity, @Nonnull final String solicitante,
      @Nonnull final Boolean repartir) {
    super(identity);
    _datos.solicitante = checkNotNull(solicitante);
    _datos.repartir = repartir;


    // TODO Auto-generated constructor stub
  }

  @Override
  public boolean sameValueAs(final Solicitud other) {
    // TODO Auto-generated method stub
    return false;
  }

  private Data _datos;
  private static final long serialVersionUID = 813220847548412111L;

  private final class Data {
    Boolean repartir;
    String solicitante;

  }

}

package es.prueba.jorge.commons;

/**
 * Representa la identidad de una Entidad. Equivale a un ValueObject.
 *
 * @author Jorge García Villanueva &lt;Jorgegv95@gmail.com&gt;
 * @param <T> el tipo (clase) de la identidad.
 */
public interface Identity<T> extends ValueObject<T> {
  // Nothing to do
}

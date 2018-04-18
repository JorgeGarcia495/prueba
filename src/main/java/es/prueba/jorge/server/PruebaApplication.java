package es.prueba.jorge.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Punto de <i>Bootstrap</i> de la aplicación.
 *
 * @author Jorge García Villanueva &lt;Jorgegv95@gmail.com&gt;
 */
@SpringBootApplication
public class PruebaApplication {
  /**
   * Punto de entrada estándar para una aplicación java.
   *
   * @param args especificados en la línea de comandos.
   */
  public static void main(final String[] args) {
    SpringApplication.run(PruebaApplication.class, args);
  }
}

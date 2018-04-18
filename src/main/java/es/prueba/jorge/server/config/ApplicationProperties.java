/**
 *
 */
package es.prueba.jorge.server.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Clase de configuración para acceder de manera ordenada a las propiedades de la aplicación.
 *
 * @author Jorge García villanueva &lt;Jorgegv95@gmail.com&gt;
 */
@Configuration
@ConfigurationProperties()
public class ApplicationProperties {
  // Nothing to declare
}

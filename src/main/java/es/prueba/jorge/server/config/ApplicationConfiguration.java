package es.prueba.jorge.server.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.concurrent.Executors;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author Jorge García Villanueva &lt;jorgegv95@gmail.com&gt;
 */
@Configuration
@ConfigurationProperties()
public class ApplicationConfiguration implements SchedulingConfigurer {
  /**
   * {@code Bean} para configurar Jackson.
   *
   * @return Un {@link Jackson2ObjectMapperBuilder} configurado para la creación de
   *         {@link ObjectMapper}.
   */
  @SuppressWarnings("static-method")
  @Bean
  public Jackson2ObjectMapperBuilder jacksonBuilder() {
    final Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
    builder.featuresToEnable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS);
    return builder;
  }

  /**
   * {@code Bean} para configurar CORS.
   *
   * @return un {@link CorsFilter} configurado para añadir la cabecera
   *         {@code Access-Control-Allow-Origin}.
   */
  @Bean
  @SuppressWarnings({"static-method", "nls"})
  public CorsFilter corsFilter() {
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    final CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(Boolean.TRUE);
    config.addAllowedOrigin("*");
    config.addAllowedHeader("*");
    config.addAllowedMethod("OPTIONS");
    config.addAllowedMethod("GET");
    config.addAllowedMethod("POST");
    config.addAllowedMethod("PUT");
    config.addAllowedMethod("DELETE");
    source.registerCorsConfiguration("/**", config);
    return new CorsFilter(source);
  }

  @Override
  public void configureTasks(final ScheduledTaskRegistrar taskRegistrar) {
    taskRegistrar.setScheduler(taskExecutor());
  }

  @Bean
  @SuppressWarnings({"static-method", "javadoc"})
  public Object taskExecutor() {
    return Executors.newScheduledThreadPool(14);
  }

}

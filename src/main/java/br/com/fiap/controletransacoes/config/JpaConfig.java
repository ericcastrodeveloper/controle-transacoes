package br.com.fiap.controletransacoes.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Class setting up jpa auditing
 *
 */
@Configuration
@EnableJpaAuditing
public class JpaConfig {
}

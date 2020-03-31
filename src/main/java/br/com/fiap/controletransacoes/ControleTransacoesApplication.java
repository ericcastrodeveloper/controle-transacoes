package br.com.fiap.controletransacoes;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.SQLException;

@SpringBootApplication
public class ControleTransacoesApplication {

	private static String portaH2 = "9090";

	public static void main(String[] args) {
		SpringApplication.run(ControleTransacoesApplication.class, args);
	}


	@Bean(initMethod = "start", destroyMethod = "stop")
	public Server inMemoryH2DatabaseaServer() throws SQLException {
		return Server.createTcpServer(
				"-tcp", "-tcpAllowOthers", "-tcpPort", portaH2);
	}

	public String getPortaH2() {
		return portaH2;
	}

	public void setPortaH2(String portaH2) {
		this.portaH2 = portaH2;
	}
}

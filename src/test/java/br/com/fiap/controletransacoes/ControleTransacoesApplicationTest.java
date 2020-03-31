package br.com.fiap.controletransacoes;

import org.junit.Test;

public class ControleTransacoesApplicationTest {

    @Test
    public void testMain(){

        ControleTransacoesApplication main = new ControleTransacoesApplication();
        main.setPortaH2("9091");
        main.getPortaH2();

        String[] args = {};
        ControleTransacoesApplication.main(args);
    }
}

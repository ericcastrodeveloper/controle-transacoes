package br.com.fiap.controletransacoes.utils;

import br.com.fiap.controletransacoes.dto.*;
import br.com.fiap.controletransacoes.entity.CartaoEntity;
import br.com.fiap.controletransacoes.entity.ClienteEntity;
import br.com.fiap.controletransacoes.entity.ProdutoEntity;
import br.com.fiap.controletransacoes.entity.TransacaoEntity;
import br.com.fiap.controletransacoes.enums.TipoCartao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MockObjectsUtils {

    public static TransacaoEntity getTransacaoEntityMock(){
        TransacaoEntity transacaoEntity = new TransacaoEntity();
        transacaoEntity.setCliente(getClienteEntityMock());
        transacaoEntity.setListaProduto(getProdutoEntityListMock());
        transacaoEntity.setDataTransacao(new Date());
        transacaoEntity.setValorTotal(BigDecimal.valueOf(5000));
        transacaoEntity.setCartaoEntity(getCartaoEntityMock());
        return transacaoEntity;
    }

    public static CreateTransacaoDTO getCreateTransacaoDTOMock() {
        CreateTransacaoDTO transacaoDTO = new CreateTransacaoDTO();
        transacaoDTO.setListProdutoDTO(getProdutoDTOListMock());
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setCpf("12345678910");
        transacaoDTO.setCpf(clienteDTO.getCpf());
        transacaoDTO.setCartaoDTO(getCartaoDTOMock());
        return transacaoDTO;
    }

    public static TransacaoDTO getTransacaoDTOMock(){
        TransacaoDTO transacaoDTO = new TransacaoDTO();
        transacaoDTO.setListProdutoDTO(getProdutoDTOListMock());
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setCpf("12345678910");
        clienteDTO.setNome("teste");
        transacaoDTO.setClienteDTO(clienteDTO);
        transacaoDTO.setValorTotal(BigDecimal.valueOf(5000));
        transacaoDTO.setClienteDTO(getClienteDTOMock());
        transacaoDTO.setCartaoDTO(getCartaoDTOMock());
        return transacaoDTO;
    }

    public static List<ProdutoDTO> getProdutoDTOListMock() {

        List<ProdutoDTO> listProdutoDTO = new ArrayList<>();

        for(int i= 0; i < 10; i++){
            ProdutoDTO produtoDTO = new ProdutoDTO();
            produtoDTO.setValor(BigDecimal.valueOf(i));
            produtoDTO.setQuantidade(i);
            produtoDTO.setNome("Produto "+i);
            listProdutoDTO.add(produtoDTO);
        }
        return listProdutoDTO;
    }

    public static List<ProdutoEntity> getProdutoEntityListMock() {

        List<ProdutoEntity> listProdutoEntity = new ArrayList<>();

        for(int i= 0; i < 10; i++){
            ProdutoEntity produtoEntity = new ProdutoEntity();
            produtoEntity.setValor(BigDecimal.valueOf(i));
            produtoEntity.setQuantidade(i);
            produtoEntity.setNome("Produto "+i);
            listProdutoEntity.add(produtoEntity);
        }
        return listProdutoEntity;
    }

    public static ClienteEntity getClienteEntityMock(){
        ClienteEntity clienteEntity = new ClienteEntity();

        clienteEntity.setNome("teste");
        clienteEntity.setCpf("12345678910");
        return clienteEntity;
    }

    public static List<ClienteEntity> getClienteEntityListMock(){

        List<ClienteEntity> listaClienteEntity = new ArrayList<>();

        for(int i = 0; i < 10; i++){
            ClienteEntity clienteEntity = new ClienteEntity();
            clienteEntity.setCpf("1234567891"+i);
            clienteEntity.setNome("Teste_"+i);
            listaClienteEntity.add(clienteEntity);
        }
        return listaClienteEntity;
    }

    public static List<ClienteDTO> getClienteDTOListMock(){

        List<ClienteDTO> listaClienteEntity = new ArrayList<>();

        for(int i = 0; i < 10; i++){
            ClienteDTO clienteDTO = new ClienteDTO();
            clienteDTO.setCpf("1234567891"+i);
            clienteDTO.setNome("Teste_"+i);
            listaClienteEntity.add(clienteDTO);
        }
        return listaClienteEntity;
    }

    public static ProdutoEntity getProdutoEntityMock(){
        ProdutoEntity produtoEntity = new ProdutoEntity();
        produtoEntity.setQuantidade(5);
        produtoEntity.setValor(BigDecimal.valueOf(5000.00));
        produtoEntity.setNome("Teste");
        produtoEntity.setId(1);
        produtoEntity.setListTransacaoEntity(new ArrayList<>());
        produtoEntity.getListTransacaoEntity();
        return produtoEntity;
    }

    public static ProdutoDTO getProdutoDTOMock(){
        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setNome("Teste");
        produtoDTO.setQuantidade(5);
        produtoDTO.setValor(BigDecimal.valueOf(5000.00));
        produtoDTO.toString();
        return produtoDTO;
    }

    public static ClienteDTO getClienteDTOMock(){
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setCpf("12345678910");
        clienteDTO.setNome("Teste");
        return clienteDTO;
    }

    public static CartaoDTO getCartaoDTOMock(){
        CartaoDTO cartaoDTO = new CartaoDTO();
        cartaoDTO.setAgencia("");
        cartaoDTO.setConta("");
        cartaoDTO.setBandeira("");
        cartaoDTO.setCodigoSeguranca("");
        cartaoDTO.setMesAnoValidade("");
        cartaoDTO.setNomeTitular("");
        cartaoDTO.setNumero("1");
        cartaoDTO.setTipoCartao(TipoCartao.CREDITO);
        return cartaoDTO;
    }

    public static CartaoEntity getCartaoEntityMock(){
        CartaoEntity cartaoEntity = new CartaoEntity();
        cartaoEntity.setAgencia("");
        cartaoEntity.setConta("");
        cartaoEntity.setBandeira("");
        cartaoEntity.setCodigoSeguranca("");
        cartaoEntity.setMesAnoValidade("");
        cartaoEntity.setNomeTitular("");
        cartaoEntity.setNumero("1");
        cartaoEntity.setTipoCartao(TipoCartao.CREDITO);
        return cartaoEntity;
    }
}

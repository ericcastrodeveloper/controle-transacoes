package br.com.fiap.controletransacoes.controller;

import br.com.fiap.controletransacoes.dto.ClienteDTO;
import br.com.fiap.controletransacoes.dto.ProdutoDTO;
import br.com.fiap.controletransacoes.dto.TransacaoDTO;
import br.com.fiap.controletransacoes.entity.ClienteEntity;
import br.com.fiap.controletransacoes.entity.ProdutoEntity;
import br.com.fiap.controletransacoes.entity.TransacaoEntity;
import br.com.fiap.controletransacoes.repository.ClienteRepository;
import br.com.fiap.controletransacoes.repository.TransacaoRepository;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties ="spring.datasource.url=jdbc:h2:mem:db;DB_CLOSE_ON_EXIT=FALSE")
public class TransacaoControllerTest {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TransacaoController transacaoController;

    @Before
    public void setUp(){
        clienteRepository.save(getClienteEntityMock());
    }

    @Test
    public void getAllTest() throws Exception {

        TransacaoEntity transacaoEntity = getTransacaoEntityMock();
        transacaoRepository.save(transacaoEntity);

        this.mockMvc.perform(get("/transacoes")
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$[0].clienteDTO.cpf", is("12345678910")))
                .andExpect(status().isOk());
    }

    @Test
    public void getByIdTest() throws Exception {

        TransacaoEntity transacaoEntity = getTransacaoEntityMock();
        transacaoRepository.save(transacaoEntity);

        this.mockMvc.perform(get("/transacoes/12345678910")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$[0].clienteDTO.cpf", is("12345678910")))
                .andExpect(status().isOk());
    }

    @Test
    public void salvarTransacaoTest() throws Exception {

//        TransacaoEntity transacaoEntity = getTransacaoEntityMock();
//        transacaoRepository.save(transacaoEntity);

        Gson gson = new Gson();

        String transacaoDTOString = gson.toJson(getTransacaoDTOMock());

        this.mockMvc.perform(post("/transacoes/")
                .content(transacaoDTOString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.clienteDTO.cpf", is("12345678910")))
                .andExpect(status().isOk());
    }

    @Test
    public void atualizarTransacaoTest() throws Exception {

        TransacaoEntity transacaoEntity = getTransacaoEntityMock();
        transacaoEntity = transacaoRepository.save(transacaoEntity);

        Gson gson = new Gson();

        String transacaoDTOString = gson.toJson(getTransacaoDTOMock());

        this.mockMvc.perform(put("/transacoes/{id}", transacaoEntity.getId())
                .content(transacaoDTOString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.clienteDTO.cpf", is("12345678910")))
                .andExpect(status().isOk());
    }

    @Test
    public void deletarTransacaoTest() throws Exception {

        TransacaoEntity transacaoEntity = getTransacaoEntityMock();
        transacaoEntity = transacaoRepository.save(transacaoEntity);

        Gson gson = new Gson();

        String transacaoDTOString = gson.toJson(getTransacaoDTOMock());

        this.mockMvc.perform(delete("/transacoes/{id}", transacaoEntity.getId())
                .content(transacaoDTOString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getExtratoTest() throws Exception {

        TransacaoEntity transacaoEntity = getTransacaoEntityMock();
        transacaoEntity = transacaoRepository.save(transacaoEntity);


        this.mockMvc.perform(get("/transacoes/download-extrato/{cpf}", transacaoEntity.getCliente().getCpf())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getExtratoTestFail() throws Exception {

        this.mockMvc.perform(get("/transacoes/download-extrato/{cpf}", "122345678910")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }



    private TransacaoEntity getTransacaoEntityMock(){
        TransacaoEntity transacaoEntity = new TransacaoEntity();
        transacaoEntity.setCliente(getClienteEntityMock());
        transacaoEntity.setListaProduto(getProdutoEntityListMock());
        transacaoEntity.setDataTransacao(new Date());
        return transacaoEntity;
    }

    private TransacaoDTO getTransacaoDTOMock(){
        TransacaoDTO transacaoDTO = new TransacaoDTO();
        transacaoDTO.setListProdutoDTO(getProdutoDTOListMock());
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setCpf("12345678910");
        clienteDTO.setNome("teste");
        transacaoDTO.setClienteDTO(clienteDTO);
        return transacaoDTO;
    }

    private List<ProdutoDTO> getProdutoDTOListMock() {

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

    private List<ProdutoEntity> getProdutoEntityListMock() {

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

    private ClienteEntity getClienteEntityMock(){
        ClienteEntity clienteEntity = new ClienteEntity();

        clienteEntity.setNome("teste");
        clienteEntity.setCpf("12345678910");
        return clienteEntity;
    }

}

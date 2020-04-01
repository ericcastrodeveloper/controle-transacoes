package br.com.fiap.controletransacoes.controller;

import br.com.fiap.controletransacoes.dto.ClienteDTO;
import br.com.fiap.controletransacoes.dto.CreateTransacaoDTO;
import br.com.fiap.controletransacoes.dto.ProdutoDTO;
import br.com.fiap.controletransacoes.dto.TransacaoDTO;
import br.com.fiap.controletransacoes.entity.ClienteEntity;
import br.com.fiap.controletransacoes.entity.ProdutoEntity;
import br.com.fiap.controletransacoes.entity.TransacaoEntity;
import br.com.fiap.controletransacoes.repository.ClienteRepository;
import br.com.fiap.controletransacoes.repository.TransacaoRepository;
import br.com.fiap.controletransacoes.utils.MockObjectsUtils;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
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
        clienteRepository.save(MockObjectsUtils.getClienteEntityMock());
    }

    @Test
    public void getAllTest() throws Exception {

        TransacaoEntity transacaoEntity = MockObjectsUtils.getTransacaoEntityMock();
        transacaoEntity = transacaoRepository.save(transacaoEntity);

        this.mockMvc.perform(get("/transacoes")
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getByIdTest() throws Exception {

        TransacaoEntity transacaoEntity = MockObjectsUtils.getTransacaoEntityMock();
        transacaoRepository.save(transacaoEntity);

        this.mockMvc.perform(get("/transacoes/12345678910")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$[0].cliente.cpf", is("12345678910")))
                .andExpect(status().isOk());
    }

    @Test
    public void salvarTransacaoTest() throws Exception {
        TransacaoEntity transacaoEntity = MockObjectsUtils.getTransacaoEntityMock();
        transacaoRepository.save(transacaoEntity);
        Gson gson = new Gson();
        String transacaoDTOString = gson.toJson(MockObjectsUtils.getCreateTransacaoDTOMock());

        this.mockMvc.perform(post("/transacoes/")
                .content(transacaoDTOString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.cliente.cpf", is("12345678910")))
                .andExpect(status().isCreated());
    }

    @Test
    public void atualizarTransacaoTest() throws Exception {

        TransacaoEntity transacaoEntity = transacaoRepository.findByClienteCpf("12345678910").get(1);
        transacaoEntity = transacaoRepository.save(transacaoEntity);

        Gson gson = new Gson();

        String transacaoDTOString = gson.toJson(MockObjectsUtils.getTransacaoDTOMock());

        this.mockMvc.perform(put("/transacoes/{id}", transacaoEntity.getId())
                .content(transacaoDTOString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.cliente.cpf", is("12345678910")))
                .andExpect(status().isOk());
    }

    @Test
    public void deletarTransacaoTest() throws Exception {

        TransacaoEntity transacaoEntity = MockObjectsUtils.getTransacaoEntityMock();
        transacaoEntity = transacaoRepository.save(transacaoEntity);

        Gson gson = new Gson();

        String transacaoDTOString = gson.toJson(MockObjectsUtils.getTransacaoDTOMock());

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

        TransacaoEntity transacaoEntity = MockObjectsUtils.getTransacaoEntityMock();
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

    @Test
    public void getExtratoCartaoTest() throws Exception {

        TransacaoEntity transacaoEntity = MockObjectsUtils.getTransacaoEntityMock();
        transacaoEntity = transacaoRepository.save(transacaoEntity);

        List<TransacaoEntity> listTransacaoEntity = transacaoEntity.getCartaoEntity().getListaTransacoes();

        this.mockMvc.perform(get("/transacoes/download-extrato-cartao/{id}", transacaoEntity.getCartaoEntity().getId())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getExtratoCartaoTestFail() throws Exception {

        this.mockMvc.perform(get("/transacoes/download-extrato-cartao/{id}", 20)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

}

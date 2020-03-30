package br.com.fiap.controletransacoes.controller;

import br.com.fiap.controletransacoes.dto.ProdutoDTO;
import br.com.fiap.controletransacoes.entity.ProdutoEntity;
import br.com.fiap.controletransacoes.entity.TransacaoEntity;
import br.com.fiap.controletransacoes.repository.ProdutoRepository;
import br.com.fiap.controletransacoes.repository.TransacaoRepository;
import com.google.gson.Gson;
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
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties ="spring.datasource.url=jdbc:h2:mem:db;DB_CLOSE_ON_EXIT=FALSE")
public class ProdutoControllerTest {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ProdutoController produtoController;


    @Test
    public void getAllTest() throws Exception {

        ProdutoEntity produtoEntity = getProdutoEntityMock();
        produtoRepository.save(produtoEntity);

        this.mockMvc.perform(get("/produtos")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getByIdTest() throws Exception {

        ProdutoEntity produtoEntity = getProdutoEntityMock();
        produtoRepository.save(produtoEntity);

        this.mockMvc.perform(get("/produtos/1")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.nome", is(produtoEntity.getNome())))
                .andExpect(status().isOk());
    }

    @Test
    public void salvarProdutoTest() throws Exception {

        Gson gson = new Gson();

        ProdutoDTO produtoDTO = getProdutoDTOMock();
        String produtoDTOString = gson.toJson(produtoDTO);

        this.mockMvc.perform(post("/produtos/")
                .content(produtoDTOString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.nome", is(produtoDTO.getNome())))
                .andExpect(status().isOk());
    }

    @Test
    public void atualizarProdutoTest() throws Exception {

        ProdutoEntity produtoEntity = getProdutoEntityMock();
        produtoEntity = produtoRepository.save(produtoEntity);

        Gson gson = new Gson();

        String produtoDTOString = gson.toJson(getProdutoDTOMock());

        this.mockMvc.perform(put("/produtos/{id}", produtoEntity.getId())
                .content(produtoDTOString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.nome", is(produtoEntity.getNome())))
                .andExpect(status().isOk());
    }

    @Test
    public void deletarProduto() throws Exception {

        ProdutoEntity produtoEntity = getProdutoEntityMock();
        produtoEntity = produtoRepository.save(produtoEntity);

        this.mockMvc.perform(delete("/produtos/{id}", produtoEntity.getId())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
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

    private ProdutoEntity getProdutoEntityMock(){
        ProdutoEntity produtoEntity = new ProdutoEntity();
        produtoEntity.setQuantidade(5);
        produtoEntity.setValor(BigDecimal.valueOf(5000.00));
        produtoEntity.setNome("Teste");
        produtoEntity.setId(1);
        return produtoEntity;
    }

    private ProdutoDTO getProdutoDTOMock(){
        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setNome("Teste");
        produtoDTO.setQuantidade(5);
        produtoDTO.setValor(BigDecimal.valueOf(5000.00));
        return produtoDTO;
    }
}

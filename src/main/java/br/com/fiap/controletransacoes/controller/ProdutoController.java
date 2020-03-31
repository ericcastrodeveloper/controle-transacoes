package br.com.fiap.controletransacoes.controller;

import br.com.fiap.controletransacoes.dto.ProdutoDTO;
import br.com.fiap.controletransacoes.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
/**
 * Produto Rest Controller
 * @name ProdutoController
 * @contextPath /produtos
 */
@RestController
@RequestMapping("produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    /**
     * Retrieves information about all Produto.
     */
    @GetMapping
    public List<ProdutoDTO> getAll() {
        return produtoService.getAll();
    }

    /**
     * Retrieves information about a Cliente.
     * @param id identifier to be retrieved
     */
    @GetMapping("{id}")
    public ProdutoDTO getById(@PathVariable Integer id) {
        return produtoService.getById(id);
    }

    /**
     * Add a new Produto.
     * @param produtoDTO value of produto to be stored
     *
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoDTO salvarProduto(@RequestBody @Valid ProdutoDTO produtoDTO) {
        return produtoService.salvarProduto(produtoDTO);
    }

    /**
     * Update a Produto.
     * @param id Id of the Produto to updated
     * @param produtoDTO value of produto to be stored
     *
     */
    @PutMapping("{id}")
    public ProdutoDTO atualizarProduto(@PathVariable Integer id, @RequestBody ProdutoDTO produtoDTO) {
        return produtoService.atualizarProduto(id, produtoDTO);
    }

    /**
     * Delete a Cliente.
     * @param id Id of the Produto to deleted
     *
     */
    @DeleteMapping("{id}")
    public void deletarProduto(@PathVariable Integer id) {
        produtoService.deletarProduto(id);
    }
}

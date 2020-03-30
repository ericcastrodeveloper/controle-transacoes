package br.com.fiap.controletransacoes.controller;

import br.com.fiap.controletransacoes.dto.ProdutoDTO;
import br.com.fiap.controletransacoes.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public List<ProdutoDTO> getAll() {
        return produtoService.getAll();
    }

    @GetMapping("{id}")
    public ProdutoDTO getById(@PathVariable Integer id) {
        return produtoService.getById(id);
    }

    @PostMapping
    public ProdutoDTO salvarProduto(@RequestBody ProdutoDTO produtoDTO) {
        return produtoService.salvarProduto(produtoDTO);
    }

    @PutMapping("{id}")
    public ProdutoDTO atualizarProduto(@PathVariable Integer id, @RequestBody ProdutoDTO produtoDTO) {
        return produtoService.atualizarProduto(id, produtoDTO);
    }

    @DeleteMapping("{id}")
    public void deletarProduto(@PathVariable Integer id) {
        produtoService.deletarProduto(id);
    }
}

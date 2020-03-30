package br.com.fiap.controletransacoes.service;

import br.com.fiap.controletransacoes.dto.ProdutoDTO;

import java.util.List;

public interface ProdutoService {

    List<ProdutoDTO> getAll();
    ProdutoDTO getById(Integer id);
    ProdutoDTO salvarProduto(ProdutoDTO produtoDTO);
    ProdutoDTO atualizarProduto(Integer id, ProdutoDTO produtoDTO);
    void deletarProduto(Integer id);
}

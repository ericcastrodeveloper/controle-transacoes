package br.com.fiap.controletransacoes.service.impl;

import br.com.fiap.controletransacoes.dto.ProdutoDTO;
import br.com.fiap.controletransacoes.entity.ProdutoEntity;
import br.com.fiap.controletransacoes.repository.ProdutoRepository;
import br.com.fiap.controletransacoes.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class that implements Produto operations
 *
 */
@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public List<ProdutoDTO> getAll() {
        return produtoRepository.findAll().stream().map(ProdutoDTO:: new).collect(Collectors.toList());
    }

    @Override
    public ProdutoDTO getById(Integer id) {
        return new ProdutoDTO(produtoRepository.findById(id).get());
    }

    @Override
    public ProdutoDTO salvarProduto(ProdutoDTO produtoDTO) {
        ProdutoEntity produtoEntity = produtoRepository.save(new ProdutoEntity(produtoDTO));
        return new ProdutoDTO(produtoEntity);
    }

    @Override
    public ProdutoDTO atualizarProduto(Integer id, ProdutoDTO produtoDTO) {
        ProdutoEntity produtoEntity = produtoRepository.findById(id).get();
        produtoEntity.setValor(produtoDTO.getValor());
        produtoEntity.setNome(produtoDTO.getNome());
        produtoEntity.setQuantidade(produtoDTO.getQuantidade());
        produtoRepository.save(produtoEntity);

        return new ProdutoDTO(produtoEntity);
    }

    @Override
    public void deletarProduto(Integer id) {
        produtoRepository.deleteById(id);
    }
}

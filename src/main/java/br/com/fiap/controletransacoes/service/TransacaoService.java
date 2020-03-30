package br.com.fiap.controletransacoes.service;

import br.com.fiap.controletransacoes.dto.TransacaoDTO;
import org.springframework.core.io.InputStreamResource;

import java.util.List;

public interface TransacaoService {

    List<TransacaoDTO> getAll();
    List<TransacaoDTO> getByCliente(String cpf);
    TransacaoDTO salvarTransacao(TransacaoDTO transacaoDTO);
    TransacaoDTO atualizarTransacao( Integer id, TransacaoDTO transacaoDTO);
    void deletarTransacao( Integer id);
    InputStreamResource getExtrato(String cpf);
}

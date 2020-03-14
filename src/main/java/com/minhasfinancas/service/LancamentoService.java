package com.minhasfinancas.service;

import com.minhasfinancas.model.entity.Lancamento;
import com.minhasfinancas.model.enums.StatusLancamento;

import java.util.List;
import java.util.Optional;

public interface LancamentoService {

    Lancamento salvar(Lancamento lancamento);
    Lancamento atualizar(Lancamento lancamento);
    void deletar(Lancamento lancamento);
    List<Lancamento> buscar(Lancamento lancamentoFiltro);
    void atualizarStatus(Lancamento lancamento, StatusLancamento statusLancamento);
    Optional<Lancamento> obterPorID(Long id);

}

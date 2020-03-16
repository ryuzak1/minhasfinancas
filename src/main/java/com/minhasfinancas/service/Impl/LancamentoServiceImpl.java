package com.minhasfinancas.service.Impl;

import com.minhasfinancas.excepiton.RegraNegocioExcepiton;
import com.minhasfinancas.model.entity.Lancamento;
import com.minhasfinancas.model.enums.StatusLancamento;
import com.minhasfinancas.model.respository.LancamentoRepository;
import com.minhasfinancas.service.LancamentoService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class LancamentoServiceImpl implements LancamentoService {
    private LancamentoRepository repository;

    public LancamentoServiceImpl(LancamentoRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Lancamento salvar(Lancamento lancamento) {
        validar(lancamento);
        lancamento.setStatus(StatusLancamento.PENDENTE);
        return repository.save(lancamento);
    }

    @Override
    @Transactional
    public Lancamento atualizar(Lancamento lancamento) {

        Objects.requireNonNull(lancamento.getId());
        return repository.save(lancamento);
    }

    @Override
    @Transactional
    public void deletar(Lancamento lancamento) {
        Objects.requireNonNull(lancamento.getId());
        repository.delete(lancamento);

    }

    @Override
    @Transactional(readOnly = true)
    public List<Lancamento> buscar(Lancamento lancamentoFiltro) {
        Example example = Example.of(lancamentoFiltro,
                ExampleMatcher.matching().
                        withIgnoreCase().
                        withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));


        return repository.findAll(example);
    }

    @Override
    public void atualizarStatus(Lancamento lancamento, StatusLancamento  statusLancamento) {
        lancamento.setStatus(statusLancamento);
        atualizar(lancamento);

    }

    @Override
    public Optional<Lancamento> obterPorID(Long id) {

        return repository.findById(id);
    }

    public void validar(Lancamento lancamento){
        if(lancamento.getDescricao()==null || lancamento.getDescricao().isEmpty()){
            throw new RegraNegocioExcepiton("Informe uma Descrição válida");
        }
        if(lancamento.getMes()==null || lancamento.getMes()>12 || lancamento.getMes()<1){

            throw new RegraNegocioExcepiton("Informe um Mês válido");
        }
        if(lancamento.getAno() == null || lancamento.getAno().toString().length()!=4){
            throw new RegraNegocioExcepiton("Informe um Ano válido");
        }
        if(lancamento.getUsuario() == null || lancamento.getUsuario().getId()==null){
            throw new RegraNegocioExcepiton("Informe um Usuário válido");
        }
        if(lancamento.getValor() == null || lancamento.getValor().compareTo(BigDecimal.ZERO)<1){
            System.out.println(lancamento.getValor());
            throw new RegraNegocioExcepiton("Informe um Valor válido");

        }
        if(lancamento.getTipo() == null){
            throw new RegraNegocioExcepiton("Informe um Tipo válido");
        }


    }
}

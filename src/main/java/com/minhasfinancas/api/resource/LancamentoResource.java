package com.minhasfinancas.api.resource;

import com.minhasfinancas.api.dto.LancamentoDTO;
import com.minhasfinancas.excepiton.RegraNegocioExcepiton;
import com.minhasfinancas.model.entity.Lancamento;
import com.minhasfinancas.model.entity.Usuario;
import com.minhasfinancas.model.enums.StatusLancamento;
import com.minhasfinancas.model.enums.TipoLancamento;
import com.minhasfinancas.service.LancamentoService;
import com.minhasfinancas.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lancamentos")
@RequiredArgsConstructor
public class LancamentoResource {

    private final LancamentoService service;
    private final UsuarioService usuarioService;



    @PostMapping
    public ResponseEntity salvar(@RequestBody LancamentoDTO lancamentoDTO) {
        try {
            Lancamento entidade = converte(lancamentoDTO);
            entidade = service.salvar(entidade);
            return new ResponseEntity(entidade, HttpStatus.CREATED);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody LancamentoDTO lancamentoDTO) {
        return service.obterPorID(id).map(entity -> {
            try {
                Lancamento lancamento = converte(lancamentoDTO);
                lancamento.setId(entity.getId());
                service.atualizar(lancamento);
                return ResponseEntity.ok(lancamento);

            } catch (RegraNegocioExcepiton e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }

        }).orElseGet(() -> new ResponseEntity("Lançamento não encontrado", HttpStatus.BAD_REQUEST));
    }
    @GetMapping
    public ResponseEntity buscar(
            @RequestParam(value = "descricao",required = false) String descricao,
            @RequestParam(value = "mes",required = false) Integer mes,
            @RequestParam(value = "ano",required = false) Integer ano,
            @RequestParam("usuario") Long idUsuario){

        Lancamento lancamentoFiltro = new Lancamento();
        lancamentoFiltro.setDescricao(descricao);
        lancamentoFiltro.setMes(mes);
        lancamentoFiltro.setAno(ano);
        Optional<Usuario> usuario = usuarioService.obterPorID(idUsuario);
        if(!usuario.isPresent()){
            return ResponseEntity.badRequest().body("Usuario não encontrado pelo id inforado");

        }else {
            lancamentoFiltro.setUsuario(usuario.get());
        }
        List<Lancamento> lancamentos = service.buscar(lancamentoFiltro);
        return ResponseEntity.ok(lancamentos);


    }

    @DeleteMapping("{id}")
    public ResponseEntity deletar(@PathVariable("id") Long id) {
        return service.obterPorID(id).map(entidade -> {
            service.deletar(entidade);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }).orElseGet(() -> new ResponseEntity("Lancaçamento não encontrado na base de dados", HttpStatus.BAD_REQUEST));

    }

    private Lancamento converte(LancamentoDTO dto) {
        Lancamento lancamento = new Lancamento();
        lancamento.setId(dto.getId());
        lancamento.setDescricao(dto.getDescricao());
        lancamento.setValor(dto.getValor());
        lancamento.setAno(dto.getAno());
        lancamento.setMes(dto.getMes());


        Usuario usuario = usuarioService.obterPorID(dto.getUsuario()).
                orElseThrow(() -> new RuntimeException("Usuario não encontrado pelo id inforado"));

        lancamento.setUsuario(usuario);

        if(dto.getTipo()!=null){
            lancamento.setTipo(TipoLancamento.valueOf(dto.getTipo()));

        }
        if(dto.getStatus()!=null){
            lancamento.setStatus(StatusLancamento.valueOf(dto.getStatus().toString()));
        }

        return lancamento;

    }


}

package com.minhasfinancas.api.dto;

import com.minhasfinancas.model.entity.Usuario;
import com.minhasfinancas.model.enums.StatusLancamento;
import com.minhasfinancas.model.enums.TipoLancamento;
import lombok.Builder;
import lombok.Data;


import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
public class LancamentoDTO {

    private Long id;

    private Integer mes;


    private Integer ano;


    private BigDecimal valor;


    private String descricao;

    private Long usuario;


    private Date dataCadastro;


    private String tipo;

    private StatusLancamento status;
}

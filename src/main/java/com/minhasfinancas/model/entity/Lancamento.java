package com.minhasfinancas.model.entity;

import com.minhasfinancas.model.enums.StatusLancamento;
import com.minhasfinancas.model.enums.TipoLancamento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "lancamento",schema = "financas")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Lancamento {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mes")
    private Integer mes;

    @Column(name = "ano")
    private Integer ano;

    @Column(name = "valor")
    private BigDecimal valor;


    @Column(name = "descricao")
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Column(name = "data_cadastro")
    private Date dataCadastro;

    @Column(name = "tipo")
    @Enumerated(value = EnumType.STRING)
    private TipoLancamento tipo;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private StatusLancamento status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public TipoLancamento getTipo() {
        return tipo;
    }

    public void setTipo(TipoLancamento tipo) {
        this.tipo = tipo;
    }

    public StatusLancamento getStatus() {
        return status;
    }

    public void setStatus(StatusLancamento status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lancamento that = (Lancamento) o;

        if (!id.equals(that.id)) return false;
        if (!mes.equals(that.mes)) return false;
        if (!ano.equals(that.ano)) return false;
        if (!valor.equals(that.valor)) return false;
        if (!descricao.equals(that.descricao)) return false;
        if (!usuario.equals(that.usuario)) return false;
        if (!dataCadastro.equals(that.dataCadastro)) return false;
        if (tipo != that.tipo) return false;
        return status == that.status;
    }

    @Override
    public String toString() {
        return "Lancamento{" +
                "id=" + id +
                ", mes=" + mes +
                ", ano=" + ano +
                ", valor=" + valor +
                ", descricao='" + descricao + '\'' +
                ", usuario=" + usuario +
                ", dataCadastro=" + dataCadastro +
                ", tipo=" + tipo +
                ", status=" + status +
                '}';
    }
}

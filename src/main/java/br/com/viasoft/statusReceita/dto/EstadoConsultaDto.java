package br.com.viasoft.statusReceita.dto;

import java.util.Date;

public class EstadoConsultaDto {

    private String descricao;
    private Date dataConsulta;
    private String status;

    public EstadoConsultaDto(String descricao, Date dataConsulta, String status) {
        this.descricao = descricao;
        this.dataConsulta = dataConsulta;
        this.status = status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(Date dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static EstadoConsultaDto converter(Object[] obj) {
        try {
            System.out.println("classe: " + obj.getClass());
            System.out.println("tamanho: " + obj.toString());
            
            return new EstadoConsultaDto((String)obj[0], (Date) obj[1], obj[2].toString());
        } catch (Exception e) {
            System.out.println("erro "+e.getMessage());
        }
        return null;
    }
}

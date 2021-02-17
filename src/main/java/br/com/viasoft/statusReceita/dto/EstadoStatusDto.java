package br.com.viasoft.statusReceita.dto;

import br.com.viasoft.statusReceita.entity.Estado;
import java.util.List;

public class EstadoStatusDto{

    private Estado estado;
    private List<Object[]> statusList;

    public EstadoStatusDto(Estado estado, List<Object[]> statusList) {
       this.estado = estado;
       this.statusList = statusList;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<Object[]> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<Object[]> statusList) {
        this.statusList = statusList;
    }


}

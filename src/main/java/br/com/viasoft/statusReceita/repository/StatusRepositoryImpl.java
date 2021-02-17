/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.viasoft.statusReceita.repository;

import br.com.viasoft.statusReceita.dto.EstadoStatusDto;
import br.com.viasoft.statusReceita.entity.Estado;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Alessandro
 */

@Component("statusRepositoryImpl")
public class StatusRepositoryImpl implements StatusRepositoryCustom{
    
    @Autowired
    private EstadoRepository estadoRepository;
    
    @Autowired
    private StatusRepository statusRepository;
    
   @Override
    public List<EstadoStatusDto> consultaStatusData(Date data){
        List<EstadoStatusDto> resultado = new ArrayList<>();
        
        List<Estado> estados = estadoRepository.findAll();
        estados.stream().map((estado) -> new EstadoStatusDto(estado, statusRepository.findByDataConsultaAndEstado(data, estado.getId()))).forEachOrdered((result) -> {
            resultado.add(result);
        });
        return resultado;
    }
    
}

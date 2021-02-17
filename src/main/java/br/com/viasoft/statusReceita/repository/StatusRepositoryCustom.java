package br.com.viasoft.statusReceita.repository;

import br.com.viasoft.statusReceita.dto.EstadoStatusDto;
import java.util.Date;
import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;

public interface StatusRepositoryCustom {
    
     public List<EstadoStatusDto> consultaStatusData(@RequestParam(required = false) Date data);
     
}

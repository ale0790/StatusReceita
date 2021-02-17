package br.com.viasoft.statusReceita.repository;

import br.com.viasoft.statusReceita.entity.Status;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

public interface StatusRepository extends JpaRepository<Status, Long> {

    @Query(value = "select estado.descricao,data_consulta,status_servico from status"
            + " inner join estado on estado.id = status.estado_id"
            + " where status.id = (select s.id from status s where status.estado_id = s.estado_id order by s.data_consulta desc limit 1)"
            + " AND (:estado IS NULL OR estado.descricao = :estado)"
            + " group by estado.id"
            + " order by estado.descricao", nativeQuery = true)
    List<Object[]> consultaSituacaoEstados(@RequestParam(required = false) String estado);

    @Query(value = "SELECT status.id,data_consulta,status_servico FROM status"
            + " inner join estado on estado.id = status.estado_id"
            + " WHERE cast(status.data_consulta as date) = cast(:dataConsulta as date) AND estado.id = :estadoCodigo", nativeQuery = true)
    List<Object[]> findByDataConsultaAndEstado(Date dataConsulta, Long estadoCodigo);
    
    @Query(value = "SELECT estado.descricao, count(estado_id)FROM status" +
                    " inner join estado on estado.id = status.estado_id where status_servico = 'OFF'"
                 +  " group by estado_id order by count(estado_id) desc limit 1", nativeQuery = true)
    List<Object[]> findEstadoMaiorIndisponibilidade();
    
    

    
    
}

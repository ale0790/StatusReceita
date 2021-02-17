package br.com.viasoft.statusReceita.repository;

import br.com.viasoft.statusReceita.entity.Estado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoRepository extends JpaRepository<Estado, Long> {
        
        Estado findOneByDescricao(String description);
        
        Page<Estado> findByDescricao(String descricao, Pageable paginacao);

}

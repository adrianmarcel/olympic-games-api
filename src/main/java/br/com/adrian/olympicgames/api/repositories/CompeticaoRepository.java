package br.com.adrian.olympicgames.api.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.adrian.olympicgames.api.entities.Competicao;
import br.com.adrian.olympicgames.api.entities.Modalidade;

@Transactional(readOnly = true)
@NamedQueries({
	@NamedQuery(name = "CompeticaoRepository.findByModalidadeIdOrLocalId",
			    query = "SELECT * FROM competicao comp"),
//				  	  + " WHERE comp.id_modalidade = :modalidadeId "
//				  	  + "   AND comp.id_local = :localId "
//				  	  + "   AND comp.dt_inicio "
//				  	  + "   BETWEEN :dataInicio AND :dataTermino"),
	
	@NamedQuery(name = "CompeticaoRepository.findByLocalIdOrDataInicio",
	            query = "SELECT COUNT(*) "
				  	  + "  FROM competicao comp"
				  	  + " WHERE comp.id_local = :localId "
				  	  + "   AND TRUNC(comp.dt_inicio) = TRUNC(:dataInicio)")
})
public interface CompeticaoRepository extends JpaRepository<Competicao, Long>{
	
	Optional<Competicao> findByModalidadeIdOrLocalId(@Param("modalidadeId") Long modalidadeId,
			                                         @Param("localId") Long localId,
			                                         @Param("dataInicio") Date dataInicio,
			                                         @Param("dataTermino") Date dataTermino);
	
	Integer findByLocalIdOrDataInicio(@Param("localId") Long localId,
			                          @Param("dataInicio") Date dataInicio);
	
	List<Competicao> findByModalidade(Modalidade modalidade, Sort sort);
}
package br.com.techlead.registropassagem.api.repository.painel;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.techlead.registropassagem.api.model.Painel;
import br.com.techlead.registropassagem.api.model.dto.LinhaDTO;
import br.com.techlead.registropassagem.api.model.dto.PainelResumoDTO;

public class PainelQueryImpl implements PainelQuery {

	@PersistenceContext
	private EntityManager manager;
	
	
	
	@Override
	public List<PainelResumoDTO> consultarPainel() {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<PainelResumoDTO> criteria = builder.createQuery(PainelResumoDTO.class);
		Root<Painel> root = criteria.from(Painel.class);
		
		criteria.select(builder.construct(PainelResumoDTO.class
				, root.get("idPainel"), root.get("descricao")
				, root.get("nome"), root.get("image")));
		
		
		criteria.where(builder.equal(root.get("ativo"), true)).orderBy(builder.desc(root.get("idPainel")));
		
		TypedQuery<PainelResumoDTO> query = manager.createQuery(criteria);
		return query.getResultList();
		
		
	}
	public List<LinhaDTO> carregarTabela(Long idIndicadorAlerta, boolean tipoIndicador) {
		StoredProcedureQuery q=manager.createStoredProcedureQuery("POC_TECHLEAD.PCK_APRESENTACAO.stp_tabela", "LinhaDTO");
		q.registerStoredProcedureParameter(1, Long.class, ParameterMode.IN);
		q.setParameter(1, idIndicadorAlerta);
		q.registerStoredProcedureParameter(2, Long.class, ParameterMode.IN);
		q.setParameter(2, tipoIndicador ? 1L : 2L);
		q.registerStoredProcedureParameter(3, void.class, ParameterMode.REF_CURSOR);
		q.execute();
		return q.getResultList();
		
		
	}
	
	

}

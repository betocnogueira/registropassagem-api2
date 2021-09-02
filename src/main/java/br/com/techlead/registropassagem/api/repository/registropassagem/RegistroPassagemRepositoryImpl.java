package br.com.techlead.registropassagem.api.repository.registropassagem;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;


import br.com.techlead.registropassagem.api.model.RegistroPassagem;
import br.com.techlead.registropassagem.api.model.dto.RegistroPassagemResumoDTO;
import br.com.techlead.registropassagem.api.repository.filter.RegistroPassagemFilter;

public class RegistroPassagemRepositoryImpl implements RegistroPassagemQuery {

	@PersistenceContext
	private EntityManager manager;
	
	private static final String DATA_ENVIO="dataEnvio";
	private static final String REGISTRADOR="registrador";
	
	@Override
	public Page<RegistroPassagemResumoDTO> resumir(RegistroPassagemFilter lancamentoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<RegistroPassagemResumoDTO> criteria = builder.createQuery(RegistroPassagemResumoDTO.class);
		Root<RegistroPassagem> root = criteria.from(RegistroPassagem.class);
		
		criteria.select(builder.construct(RegistroPassagemResumoDTO.class
				, root.get("idRegistroPassagem"), root.get("tipoRegistro")
				, root.get("placa"), root.get("chaveMdfe")
				, root.get("dataCriacao"), root.get(DATA_ENVIO)
				, root.get("status"), root.get(REGISTRADOR).get("tipo")
				, root.get(REGISTRADOR).get("descricao")));
		
		Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, root);
		criteria.where(predicates).orderBy(builder.desc(root.get("dataCriacao")));
		
		TypedQuery<RegistroPassagemResumoDTO> query = manager.createQuery(criteria);
		adicionarRestricoesPaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(lancamentoFilter));
	}
	
	public void enviarReceitaFederal(RegistroPassagem registro) {
		manager.flush();
		StoredProcedureQuery q=manager.createStoredProcedureQuery("POC_TECHLEAD.STP_PROC_REGPASS");
		q.registerStoredProcedureParameter(1, Long.class, ParameterMode.IN);
		q.setParameter(1, registro.getIdRegistroPassagem());
		q.execute();
	}
	
	public void processarBatch(Long tempo, Long registrador) {
		StoredProcedureQuery q=manager.createStoredProcedureQuery("POC_TECHLEAD.STP_PROC_BATCH_REGPASS");
		q.registerStoredProcedureParameter(1, Long.class, ParameterMode.IN);
		q.setParameter(1, tempo);
		q.registerStoredProcedureParameter(2, Long.class, ParameterMode.IN);
		q.setParameter(2, registrador);
		q.execute();
	}
	
	private Long total(RegistroPassagemFilter filtro) {
		
		CriteriaBuilder builder=manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria=builder.createQuery(Long.class);
		Root<RegistroPassagem> root=criteria.from(RegistroPassagem.class);
		Predicate[] predicates=this.criarRestricoes(filtro, builder, root);
		criteria.where(predicates);
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

	private void adicionarRestricoesPaginacao(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual=pageable.getPageNumber();
		int totalRegistrosPorPagina=pageable.getPageSize();
		int primeiroRegistroDaPagina=paginaAtual*totalRegistrosPorPagina;
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}

	private Predicate[] criarRestricoes(RegistroPassagemFilter filtro, CriteriaBuilder builder, Root<RegistroPassagem> root) {
		
		List<Predicate> lista=new ArrayList<>();
		if(!org.apache.commons.lang3.StringUtils.isEmpty(filtro.getNomeAntena())) {
			lista.add(builder.like(builder.lower(root.get(REGISTRADOR).get("descricao")), "%"+filtro.getNomeAntena().toLowerCase()+"%"));
		}
		
		if(!org.apache.commons.lang3.StringUtils.isEmpty(filtro.getPlaca())) {
			lista.add(builder.equal(builder.lower(root.get("placa")), filtro.getPlaca().toLowerCase()));
		}
		
		if(!org.apache.commons.lang3.StringUtils.isEmpty(filtro.getChaveDfe())) {
			lista.add(builder.equal(builder.lower(root.get("chaveMdfe")), filtro.getChaveDfe().toLowerCase()));
		}
		if(filtro.getStatus()!=null) {
			lista.add(builder.equal(root.get("status"), filtro.getStatus()));
		}
		
		if(filtro.getDataFim()!=null) {
			lista.add(builder.lessThanOrEqualTo(root.get(DATA_ENVIO), filtro.getDataFim()));
		}
		
		if(filtro.getDataInicio()!=null) {
			lista.add(builder.greaterThanOrEqualTo(root.get(DATA_ENVIO), filtro.getDataInicio()));
		}
		
		return lista.toArray(new Predicate[lista.size()]);
	}

}

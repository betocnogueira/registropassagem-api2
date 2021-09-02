package br.com.techlead.registropassagem.api.repository.alerta;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.techlead.registropassagem.api.model.AlertaMedicao;
import br.com.techlead.registropassagem.api.repository.filter.AlertaFilter;

public class AlertaQueryImpl implements AlertaQuery {

	@PersistenceContext
	private EntityManager manager;
	
	
	private static final String DATAALERTA="dataAlerta";
	private static final String ALERTA="alerta";
	@Override
	public List<AlertaMedicao> consultarAlertas(AlertaFilter filtro) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<AlertaMedicao> criteria = builder.createQuery(AlertaMedicao.class);
		Root<AlertaMedicao> root = criteria.from(AlertaMedicao.class);
		
		criteria.select(root);
		
		Predicate[] predicates = criarRestricoes(filtro, builder, root);
		criteria.where(predicates).orderBy(builder.desc(root.get(DATAALERTA)));
		
		TypedQuery<AlertaMedicao> query = manager.createQuery(criteria);
		
		
		return query.getResultList();
	}
	
	
private Predicate[] criarRestricoes(AlertaFilter filtro, CriteriaBuilder builder, Root<AlertaMedicao> root) {
		
		List<Predicate> lista=new ArrayList<>();
		if(!org.apache.commons.lang3.StringUtils.isEmpty(filtro.getDescricao())) {
			lista.add(builder.like(builder.lower(root.get(ALERTA).get("descricao")), "%"+filtro.getDescricao().toLowerCase()+"%"));
		}
		
		if(filtro.getTipoMedicao()!=null) {
			lista.add(builder.equal(root.get(ALERTA).get("tipoMedicao"), filtro.getTipoMedicao()));
		}
		
		if(filtro.getTipoNotificacao()!=null) {
			lista.add(builder.equal(root.get(ALERTA).get("destinoAlerta"), filtro.getTipoNotificacao()));
		}
		
		if(filtro.getIdRegistrador()!=null) {
			lista.add(builder.equal(root.get(ALERTA).get("registrador").get("idRegistrador"), filtro.getIdRegistrador()));
		}
		
		
		if(filtro.getDataFim()!=null) {
			lista.add(builder.lessThanOrEqualTo(root.get(DATAALERTA), filtro.getDataFim()));
		}
		
		if(filtro.getDataInicio()!=null) {
			lista.add(builder.greaterThanOrEqualTo(root.get(DATAALERTA), filtro.getDataInicio()));
		}
		
		return lista.toArray(new Predicate[lista.size()]);
	}
	
}

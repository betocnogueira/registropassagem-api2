package br.com.techlead.registropassagem.api.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.techlead.registropassagem.api.model.Indicador;
import br.com.techlead.registropassagem.api.model.Painel;
import br.com.techlead.registropassagem.api.model.PainelItem;
import br.com.techlead.registropassagem.api.model.dto.ApresentacaoPainelDTO;
import br.com.techlead.registropassagem.api.model.dto.ColunaDTO;
import br.com.techlead.registropassagem.api.model.dto.LinhaDTO;
import br.com.techlead.registropassagem.api.model.dto.PainelResumoDTO;
import br.com.techlead.registropassagem.api.model.dto.SeriesDTO;
import br.com.techlead.registropassagem.api.model.dto.TabelaDTO;
import br.com.techlead.registropassagem.api.model.enums.TipoPainelItem;
import br.com.techlead.registropassagem.api.repository.PainelItemRepository;
import br.com.techlead.registropassagem.api.repository.PainelRepository;


@Service
public class PainelService {

	@Autowired
	private PainelRepository painelRepository;
	@Autowired
	private PainelItemRepository painelItemRepository;
	private static final String POSTO="POSTO";
	private static final String ANTENA_OCR="ANTENA_OCR";
	private static final String LEITOR_RFID="LEITOR_RFID";
	private static final String APLICACAO_EXTERNA="APLICACAO_EXTERNA" ;
	public List<PainelResumoDTO> consultarPaineis(){
		return painelRepository.consultarPainel();
	}
	
	public Painel criarPainel(Painel painel) {
		painel.setIdPainel(null);
		painel.setAtivo(true);
		if(painel.getItens()!=null)
			for(PainelItem aux : painel.getItens())
				aux.setPainel(painel);
		return painelRepository.save(painel);
		
	}
	
	public Painel alterarPainel(Painel painel, Long codigo) {
		Painel antigo=this.buscarPainelPeloCodigo(codigo);
		HashSet<Long> idsItens=new HashSet<>();
		for(PainelItem aux : painel.getItens()) {
			idsItens.add(aux.getIdPainelItem());
		}
		for(PainelItem aux : antigo.getItens()) {
			
			if(!idsItens.contains(aux.getIdPainelItem())) {
				painelItemRepository.deleteById(aux.getIdPainelItem());
			}
		}
		antigo.getItens().clear();
		antigo.getItens().addAll(painel.getItens());
		for(PainelItem aux : antigo.getItens()) {
			aux.setPainel(antigo);
		}
		BeanUtils.copyProperties(painel, antigo, "idPainel", "itens");

		
		
		return painelRepository.save(antigo);
		
	}
	
	public void excluirPainel(Long codigo) {
		Painel painel=this.buscarPainelPeloCodigo(codigo);
		painel.setAtivo(false);
		painelRepository.save(painel);
		
	}
	
	public Painel buscarPainelPeloCodigo(Long codigo) {
		Painel painel=painelRepository.findById(codigo).orElse(null);
		if(painel==null ||painel.getAtivo()==null || !painel.getAtivo().booleanValue()) throw new EmptyResultDataAccessException(1);
		return painel;
	}
	
	
	public ApresentacaoPainelDTO buscarItemDashBoard(Long idPainelItem) {
		PainelItem item=painelItemRepository.findById(idPainelItem).orElse(null);
		if(item==null) throw new EmptyResultDataAccessException(1);
		boolean indicador=item.getTipoPainelItem()==TipoPainelItem.INDICADOR;
		List<LinhaDTO> linhas=painelRepository.carregarTabela(indicador ?item.getIndicador().getIdIndicador() : item.getAlerta().getIdAlerta(), indicador);
		TabelaDTO tabela=new TabelaDTO();
		tabela.setLinhas(linhas);
		if(indicador)
			this.preencherBooleanosTabela(tabela, item.getIndicador());
		
		ApresentacaoPainelDTO retorno=new ApresentacaoPainelDTO();
		retorno.setTabela(tabela);
		
		if(indicador && (item.getIndicador().getTipoAmostra()!=1 || item.getIndicador().getTipoAmostra()!=5)){
			retorno.setColuna(this.converterTabelaEmGrafico(tabela, item.getIndicador().getTipoAmostra(), item.getIndicador().getUnidadeTempo()));
		}
		return retorno;
		
		
	}
	
	private void preencherBooleanosTabela(TabelaDTO tabela, Indicador indicador) {
		if(indicador.getAgrupamento().contains("TEMPO"))
			tabela.setTempo(true);
		if(indicador.getAgrupamento().contains("REGISTRADOR"))
			tabela.setRegistrador(true);
		if(indicador.getAgrupamento().contains("DISPOSITIVO"))
			tabela.setTipo(true);
		
	}
	
	
	private ColunaDTO converterTabelaEmGrafico(TabelaDTO tabela, int tipo, String unidadeTempo) {
		ColunaDTO retorno=new ColunaDTO();
		retorno.setDatasets(new ArrayList<>());
		retorno.setLabels(this.carregarLabels(tabela, unidadeTempo));
		if(tipo==2)
			retorno.getDatasets().add(this.carregarSeriePizza(tabela));
		else if(tipo ==3 || tipo==4) {
			
			retorno.getDatasets().addAll(this.carregarSerieColuna(tabela, retorno.getLabels()));
		}
		return retorno;
	}
	
	private List<SeriesDTO> carregarSerieColuna(TabelaDTO tabela, List<String> campos) {
		List<SeriesDTO> retorno=new ArrayList<>();
		HashMap<String, List<Long>> valores=new HashMap<>();
		
		
		if(tabela.isTempo() && (tabela.isRegistrador() || tabela.isTipo())) {
			carregarValoresMultiploTipo(tabela, campos, valores);
		}else if(tabela.isRegistrador() && tabela.isTipo()) {
			processarValorRegistradorOuTipoReg(tabela, campos, valores);
		}else {
			processarValorTipoUnico(tabela, campos, valores);
		}int i=0;
		for(Map.Entry<String,List<Long>> entry  : valores.entrySet()) {
			String label=entry.getKey();
			SeriesDTO data=new SeriesDTO();
			data.setBackgroundColor(new ArrayList<>());
			data.setBorderColor(new ArrayList<>());
			data.getBorderColor().add(SeriesDTO.getCores()[i]);
			data.getBackgroundColor().add(SeriesDTO.getCores()[i]);
			for(int j=1; j<=31; j++) {
				data.getBackgroundColor().add(SeriesDTO.getCores()[i]);
				
			}
			data.setLabel(label);
			data.setData(valores.get(label));
			retorno.add(data);
			i++;
		}
		
		
		return retorno;
	}

	private void processarValorRegistradorOuTipoReg(TabelaDTO tabela, List<String> campos,
			HashMap<String, List<Long>> valores) {
		for(LinhaDTO aux: tabela.getLinhas()) {
			if(!valores.containsKey(aux.getRegistrador())) {
				List<Long> indices=new ArrayList<>();
				for(long i=0; i<=campos.size(); i++) indices.add(0L);
				valores.put(aux.getRegistrador() , indices);
			}
			valores.get(aux.getRegistrador()).set(this.getIndiceTipo(aux)
				, aux.getValor());
		}
	}

	private void processarValorTipoUnico(TabelaDTO tabela, List<String> campos, HashMap<String, List<Long>> valores) {
		List<Long> indices=new ArrayList<>();
		for(long i=0; i<=campos.size(); i++) indices.add(0L);
		valores.put("Total" , indices);
		int cont=0;
		for(LinhaDTO aux: tabela.getLinhas()) {
			if(tabela.isTempo())
				indices.set(Integer.parseInt(aux.getTempo()), aux.getValor());
			if(tabela.isTipo())
				indices.set(this.getIndiceTipo(aux),  aux.getValor());
			if(tabela.isRegistrador())
				indices.set(cont, aux.getValor()); cont++;
		}
	}

	private void carregarValoresMultiploTipo(TabelaDTO tabela, List<String> campos,
			HashMap<String, List<Long>> valores) {
		for(LinhaDTO aux: tabela.getLinhas()) {
			if(
					(tabela.isRegistrador() && !valores.containsKey(aux.getRegistrador()))
										||
							(tabela.isTipo() && !valores.containsKey(aux.getTipo()))
			  ) {
				List<Long> indices=new ArrayList<>();
				for(long i=0; i<=campos.size(); i++) indices.add(0L);
				valores.put(tabela.isRegistrador() ? aux.getRegistrador() : aux.getTipo(), indices);
			
			}
			List<Long> indices=valores.get(tabela.isRegistrador() ? aux.getRegistrador() : aux.getTipo());
			indices.set(Integer.parseInt(aux.getTempo()), aux.getValor());
			
		}
	}
	
	
	
	private int getIndiceTipo(LinhaDTO aux) {
		int retorno=0;
		if(aux.getTipo().equals(POSTO))
			retorno=0;
		else if(aux.getTipo().equals(ANTENA_OCR))
			retorno=1;
		else if(aux.getTipo().equals(LEITOR_RFID))
			retorno=2;
		else if(aux.getTipo().equals(APLICACAO_EXTERNA))
			retorno=3;
		else
			retorno=4;
		return retorno;
		
	}
	
	private SeriesDTO carregarSeriePizza(TabelaDTO tabela) {
		SeriesDTO retorno=new SeriesDTO();
		retorno.setData(new ArrayList<>());
		for(LinhaDTO aux: tabela.getLinhas()) {
			retorno.getData().add(aux.getValor());
		}
		return retorno;
	}
	private List<String> carregarLabels(TabelaDTO tabela, String unidadeTempo){
		List<String> nomes=new ArrayList<>();
		if(tabela.isTempo()) {
			nomes=this.carregarLabelsTempo(unidadeTempo);
			
			
		}else if(tabela.isTipo()) {
			String[] tipos=new String[] {POSTO,ANTENA_OCR,LEITOR_RFID,APLICACAO_EXTERNA,"OUTROS"};
			 nomes= Arrays.asList( tipos);
		}else if(tabela.isRegistrador()) {
			for(LinhaDTO aux: tabela.getLinhas()) {
				nomes.add(aux.getRegistrador());
			}
		}else {
			nomes.add("total");
			
		}return nomes;
		
		
	}
	private List<String> carregarLabelsTempo(String unidadeTempo){
		List<String> nomes=new ArrayList<>();
		if(unidadeTempo.equals("MINUTO"))
			for(int i=0; i<=59; i++) nomes.add("minuto "+i);
		if(unidadeTempo.equals("HORA"))
			for(int i=0; i<=23; i++) nomes.add(i+" horas");
		if(unidadeTempo.equals("DIA"))
			for(int i=1; i<=31; i++) nomes.add("dia "+i);
		return nomes;
		
	}
	
	
}

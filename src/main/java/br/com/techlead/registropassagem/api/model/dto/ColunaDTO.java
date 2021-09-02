package br.com.techlead.registropassagem.api.model.dto;

import java.util.ArrayList;
import java.util.List;

public class ColunaDTO {

	private List<String> labels=new ArrayList<>();
	private List<SeriesDTO> datasets;
	public List<String> getLabels() {
		return labels;
	}
	public void setLabels(List<String> labels) {
		this.labels = labels;
	}
	public List<SeriesDTO> getDatasets() {
		return datasets;
	}
	public void setDatasets(List<SeriesDTO> datasets) {
		this.datasets = datasets;
	}
}

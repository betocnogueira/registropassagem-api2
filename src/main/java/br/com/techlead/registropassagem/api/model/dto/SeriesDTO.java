package br.com.techlead.registropassagem.api.model.dto;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SeriesDTO {

	private String label;
	private List<String> backgroundColor;
	private List<Long> data; 
	private List<String> borderColor;
	private double tension=0.4;
	private boolean fill=false;
	
	public SeriesDTO() {
		this.backgroundColor=Arrays.asList(SeriesDTO.getCores());
		this.borderColor=Arrays.asList(SeriesDTO.getCores());		
	}
	public static String[] getCores() {
		return new String[] {"#2f7ed8", "#0d233a", "#8bbc21", "#910000", "#1aadce",
		                      "#492970", "#f28f43", "#77a1e5", "#c42525", "#a6c96a",
		                      "#4572A7", "#AA4643", "#89A54E", "#80699B", "#3D96AE",
		                      "#DB843D", "#92A8CD", "#A47D7C", "#B5CA92"};
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	public List<Long> getData() {
		return data;
	}
	public void setData(List<Long> data) {
		this.data = data;
	}
	
	public double getTension() {
		return tension;
	}
	public void setTension(double tension) {
		this.tension = tension;
	}
	public boolean isFill() {
		return fill;
	}
	public void setFill(boolean fill) {
		this.fill = fill;
	}
	@Override
	public int hashCode() {
		return Objects.hash(label);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SeriesDTO other = (SeriesDTO) obj;
		return Objects.equals(label, other.label);
	}
	public List<String> getBackgroundColor() {
		return backgroundColor;
	}
	public void setBackgroundColor(List<String> backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	public List<String> getBorderColor() {
		return borderColor;
	}
	public void setBorderColor(List<String> borderColor) {
		this.borderColor = borderColor;
	}
}

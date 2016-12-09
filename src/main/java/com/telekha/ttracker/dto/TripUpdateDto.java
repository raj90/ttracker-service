package com.telekha.ttracker.dto;

import javax.validation.constraints.NotNull;

public class TripUpdateDto {

	//trip id
	@NotNull
	private Long i;
	//latitude
	@NotNull
	private Double a;
	//longitude
	@NotNull
	private Double o;
	//sequence
	@NotNull
	private Long s;
	public Long getI() {
		return i;
	}
	public void setI(Long i) {
		this.i = i;
	}
	public Double getA() {
		return a;
	}
	public void setA(Double a) {
		this.a = a;
	}
	public Double getO() {
		return o;
	}
	public void setO(Double o) {
		this.o = o;
	}
	public Long getS() {
		return s;
	}
	public void setS(Long s) {
		this.s = s;
	}
	
	
}

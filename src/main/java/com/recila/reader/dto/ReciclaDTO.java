package com.recila.reader.dto;

import lombok.Data;

@Data
public class ReciclaDTO {
	private String carimboDeDatahora;
	private String residencia;
	private String barcode;
	private String fabricante;
	private String tiporeciclavel;
	private Integer quantidade;
	private Integer peso;
	private String ecoponto;
	private String bairro;
	private String data;

	public ReciclaDTO() {
	}

	public String getCarimboDeDatahora() {
		return carimboDeDatahora;
	}

	public void setCarimboDeDatahora(String carimboDeDatahora) {
		this.carimboDeDatahora = carimboDeDatahora;
	}

	public String getResidencia() {
		return residencia;
	}

	public void setResidencia(String residencia) {
		this.residencia = residencia;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public String getTiporeciclavel() {
		return tiporeciclavel;
	}

	public void setTiporeciclavel(String tiporeciclavel) {
		this.tiporeciclavel = tiporeciclavel;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}

	public String getEcoponto() {
		return ecoponto;
	}

	public void setEcoponto(String ecoponto) {
		this.ecoponto = ecoponto;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ReciclaDTO [carimboDeDatahora=" + carimboDeDatahora + ", residencia=" + residencia + ", barcode="
				+ barcode + ", fabricante=" + fabricante + ", tiporeciclavel=" + tiporeciclavel + ", quantidade="
				+ quantidade + ", peso=" + peso + ", ecoponto=" + ecoponto + ", bairro=" + bairro + ", data=" + data
				+ "]";
	}

}

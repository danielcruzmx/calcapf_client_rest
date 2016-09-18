package main;

import java.io.Serializable;

public class Pension implements Serializable{
	private static final long serialVersionUID = 1L;
    
    private Integer numero;
    private String beneficiario;
    private double monto;
    private double porcentaje;
    private String conceptos;
    
    public Pension(){
    }

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getBeneficiario() {
		return beneficiario;
	}

	public void setBeneficiario(String beneficiario) {
		this.beneficiario = beneficiario;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public double getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(double porcentaje) {
		this.porcentaje = porcentaje;
	}

	public String getConceptos() {
		return conceptos;
	}

	public void setConceptos(String conceptos) {
		this.conceptos = conceptos;
	}
    

}

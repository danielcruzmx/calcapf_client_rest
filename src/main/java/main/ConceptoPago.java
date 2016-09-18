package main;

import java.io.Serializable;

public class ConceptoPago implements Serializable {
	private static final long serialVersionUID = 1L;
	
    private java.lang.String tipo;
    private java.lang.String concepto;
    private java.lang.Double valor;
	
	public java.lang.String getTipo() {
		return tipo;
	}
	public void setTipo(java.lang.String idTipoCpto) {
		this.tipo = idTipoCpto;
	}
	public java.lang.String getConcepto() {
		return concepto;
	}
	public void setConcepto(java.lang.String idConcepto) {
		this.concepto = idConcepto;
	}
	public java.lang.Double getValor() {
		return valor;
	}
	public void setValor(java.lang.Double monto) {
		this.valor = monto;
	}
}

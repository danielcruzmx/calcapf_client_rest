package main;

import java.io.Serializable;

public class ConceptoPagado implements Serializable {
	private static final long serialVersionUID = 1L;
	
    private java.lang.String tipo;
    private java.lang.String concepto;
    private java.lang.String descripcion;
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
		return valor / 2.0;
	}
	public void setValor(java.lang.Double monto) {
		this.valor = monto;
	}
	public java.lang.String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(java.lang.String descipcion) {
		this.descripcion = descipcion;
	}
	
	public String to_String(){
		return this.getTipo() + this.getConcepto() + "-" + this.getDescripcion() + " -> " + this.getValor().toString();
	}
}
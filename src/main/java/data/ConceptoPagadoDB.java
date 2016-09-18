package data;

import java.io.Serializable;

public class ConceptoPagadoDB implements Serializable {
	 	private static final long serialVersionUID = 1L;
	 	
		private Integer numero;
		private String  tipo;
	    private String  cpto;
	    private Double  monto;
	    
	    public ConceptoPagadoDB() {
	    }
		public String getTipo() {
			return tipo;
		}
		public void setTipo(String tipo) {
			this.tipo = tipo;
		}
		public String getCpto() {
			return cpto;
		}
		public void setCpto(String cpto) {
			this.cpto = cpto;
		}
		public Double getMonto() {
			return monto;
		}
		public void setMonto(Double monto) {
			this.monto = monto;
		}
		public Integer getNumero() {
			return numero;
		}
		public void setNumero(Integer numero) {
			this.numero = numero;
		}
}

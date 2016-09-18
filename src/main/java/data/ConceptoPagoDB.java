package data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@NamedNativeQuery( name="ConceptoPago.RFCfind",query = "" + 
"select tn_movto_concepto_pago.MCP_RFC, " +
"       tn_movto_concepto_pago.ID_MOVTO_CONCEPTO_PAGO, " +
"       tn_movto_concepto_pago.ID_TIPO_CPTO, " +
"       tn_movto_concepto_pago.ID_CONCEPTO, "  +
"     case when tn_movto_concepto_pago.mcp_PORCENTAJE > 0 " +
"          then tn_movto_concepto_pago.mcp_PORCENTAJE / 100.00 " +
"          else tn_movto_concepto_pago.mcp_MONTO " +
"          end as mcp_MONTO, " +
"       tn_movto_concepto_pago.mcp_PORCENTAJE " +
"from   sireh.tn_movto_concepto_pago " +
"where  tn_movto_concepto_pago.ID_SIT_CAPTURA = 'V' " +
"and    tn_movto_concepto_pago.ID_TIPO_CPTO <> 'C' " +
"and    tn_movto_concepto_pago.MCP_RFC like ? " +
"and    tn_movto_concepto_pago.MCP_RFC in ( " +
"                select  td_plaza.PLAZA_RFC " +
"                from    sireh.td_plaza  " +
"                where   td_plaza.ID_SIT_PLAZA like 'O%') ", resultClass=ConceptoPagoDB.class)

@Entity
public class ConceptoPagoDB implements Serializable {
	private static final long serialVersionUID = 1L;
	public final static String FIND_RFC = "ConceptoPago.RFCfind";
	
	@Column(name="MCP_RFC")
    private java.lang.String rfc;
	
	@Column(name="ID_MOVTO_CONCEPTO_PAGO")    
	@Id
    private java.lang.Integer idMovtoConceptoPago;
	
	@Column(name="ID_TIPO_CPTO")
    private java.lang.String tipo;
	
	@Column(name="ID_CONCEPTO")
    private java.lang.String concepto;
	
	@Column(name="mcp_MONTO")
    private java.lang.Double monto;
	
	@Column(name="mcp_PORCENTAJE")
    private java.lang.Float porcentaje;
    
	public java.lang.String getRfc() {
		return rfc;
	}
	public void setRfc(java.lang.String rfc) {
		this.rfc = rfc;
	}
	public java.lang.Integer getIdMovtoConceptoPago() {
		return idMovtoConceptoPago;
	}
	public void setIdMovtoConceptoPago(java.lang.Integer idMovtoConceptoPago) {
		this.idMovtoConceptoPago = idMovtoConceptoPago;
	}
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
	public java.lang.Double getMonto() {
		return monto;
	}
	public void setMonto(java.lang.Double monto) {
		this.monto = monto;
	}
	public java.lang.Float getPorcentaje() {
		return porcentaje;
	}
	public void setPorcentaje(java.lang.Float porcentaje) {
		this.porcentaje = porcentaje;
	}
	
}

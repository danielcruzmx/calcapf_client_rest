package data;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@NamedNativeQuery( name="Pension.RFCfind",query = "" + 
"        select    " +
"		 td_pension.ID_PENSION, " +		
"		 td_pension.RFC_PENSION, " +
"        td_pension.NUM_PENSION, " +
"        td_pension.BENEFICIARIA_PENSION, "+
"        td_pension.MONTO_PENSION, " +
"        td_pension.PORCENTAJE_PENSION, " +
"        td_pension.MONTO_ADEUDO, " +
"        td_pension.QNA_APLICA_ADEUDO, " +
"        td_pension.QNA_DESC_ADEUDO,  " +
"        td_pension.CPTOS_PORCENTAJE " +
" from   sireh.td_pension " +
" where  td_pension.STATUS_PENSION = 'V' " +
" and    td_pension.RFC_PENSION in ( " +
"                 select  td_plaza.PLAZA_RFC " +
"                 from    sireh.td_plaza "  +
"                 where   td_plaza.ID_SIT_PLAZA like 'O%' " + 
"                 and td_plaza.PLAZA_RFC = ?) " , resultClass=PensionDB.class)

@Entity
public class PensionDB implements Serializable {
	private static final long serialVersionUID = 1L;
	public final static String FIND_RFC = "Pension.RFCfind";

	@Column(name="ID_PENSION")    
	@Id
	private java.lang.Long idPension;
	
	@Column(name="RFC_PENSION")
    private java.lang.String rfcPension;
	
	@Column(name="NUM_PENSION")
    private int numPension;
	
	@Column(name="BENEFICIARIA_PENSION")
    private java.lang.String beneficiariaPension;
	
	@Column(name="MONTO_PENSION")
    private java.lang.Double montoPension;
	
	@Column(name="PORCENTAJE_PENSION")
    private java.lang.Float porcentajePension;
	
	@Column(name="MONTO_ADEUDO")
    private java.lang.Double montoAdeudo;
	
	@Column(name="QNA_APLICA_ADEUDO")
    private java.lang.Integer qnaAplicaAdeudo;
	
	@Column(name="QNA_DESC_ADEUDO")
    private java.lang.Integer qnaDescAdeudo;
	
	@Column(name="CPTOS_PORCENTAJE")
    private java.lang.String cptosPorcentaje;
    
	
	public double calculaMonto(List<ConceptoPagadoDB> cptosPagados) {
		Double acumulado = 0.0;
		String cpto  = null;
		String cptosPension = null; 
		// ajustes para conceptos compuestos
		if(this.cptosPorcentaje.contains("P32")){
			cptosPension= this.cptosPorcentaje + "PPV" + "P5E" + "P4E" + "P3G" ;
		} else {
			cptosPension= this.cptosPorcentaje;
		}
		if(this.montoPension > 0.0 && this.porcentajePension == 0) return this.montoPension;
		for(ConceptoPagadoDB c: cptosPagados){
			cpto = c.getTipo().trim() + c.getCpto().trim();
			if(cptosPension.contains(cpto)){
				if(c.getTipo().trim().equals("P")){
					acumulado = acumulado + c.getMonto();
				}
				if(c.getTipo().trim().equals("D")){
					acumulado = acumulado - c.getMonto();
				}
			}
		}
		return acumulado * this.porcentajePension / 100.00 + (this.montoAdeudo / this.qnaDescAdeudo);
	}
    
	public java.lang.Long getIdPension() {
		return idPension;
	}
	public void setIdPension(java.lang.Long idPension) {
		this.idPension = idPension;
	}
	public java.lang.String getRfcPension() {
		return rfcPension;
	}
	public void setRfcPension(java.lang.String rfcPension) {
		this.rfcPension = rfcPension;
	}
	public int getNumPension() {
		return numPension;
	}
	public void setNumPension(int numPension) {
		this.numPension = numPension;
	}
	public java.lang.String getBeneficiariaPension() {
		return beneficiariaPension;
	}
	public void setBeneficiariaPension(java.lang.String beneficiariaPension) {
		this.beneficiariaPension = beneficiariaPension;
	}
	public java.lang.Double getMontoPension() {
		return montoPension;
	}
	public void setMontoPension(java.lang.Double montoPension) {
		this.montoPension = montoPension;
	}
	public java.lang.Float getPorcentajePension() {
		return porcentajePension;
	}
	public void setPorcentajePension(java.lang.Float porcentajePension) {
		this.porcentajePension = porcentajePension;
	}
	public java.lang.Double getMontoAdeudo() {
		return montoAdeudo;
	}
	public void setMontoAdeudo(java.lang.Double montoAdeudo) {
		this.montoAdeudo = montoAdeudo;
	}
	public java.lang.Integer getQnaDescAdeudo() {
		return qnaDescAdeudo;
	}
	public void setQnaDescAdeudo(java.lang.Integer qnaDescAdeudo) {
		this.qnaDescAdeudo = qnaDescAdeudo;
	}
	public java.lang.Integer getQnaAplicaAdeudo() {
		return qnaAplicaAdeudo;
	}
	public void setQnaAplicaAdeudo(java.lang.Integer qnaAplicaAdeudo) {
		this.qnaAplicaAdeudo = qnaAplicaAdeudo;
	}
	public java.lang.String getCptosPorcentaje() {
		return cptosPorcentaje;
	}
	public void setCptosPorcentaje(java.lang.String cptosPorcentaje) {
		this.cptosPorcentaje = cptosPorcentaje;
	}
}

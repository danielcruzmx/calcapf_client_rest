package data;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.persistence.NamedNativeQuery;

@NamedNativeQuery( name="Pago.RFCfind",query = "" + 
"select td_empleado.RFC_EMPLEADO, " +
"       td_plaza.ID_PLAZA, " +
"       td_plaza.ID_UNIDAD_NOM, " +
"		lower(tc_grupo_pago.DESC_GRUPO_PAGO) as DESC_GRUPO_PAGO, " +
"       tc_atributo_puesto_nom.ID_NIVEL_PTO, " +
"	    lower(tc_nombramiento.DESC_NOMBRAMIENTO) as DESC_NOMBRAMIENTO, " +
"	    lower(tc_jerarquia.DESC_JERARQUIA) as DESC_JERARQUIA, " +
"       tc_tabulador_nom.TAB_SUELDO, " +
"       tc_tabulador_nom.TAB_COMPENSACION, " +
"		tc_atributo_puesto_nom.AP_INCENTIVO " +
"from   sireh.td_plaza, " +
"       sireh.td_empleado, "  +
"       sireh.tc_atributo_puesto tc_atributo_puesto_nom, " +
"       sireh.tc_tabulador       tc_tabulador_nom, " +
"       sireh.tc_atributo_puesto tc_atributo_puesto_pre, " +
"       sireh.tc_tabulador       tc_tabulador_pre, " +
"		sireh.tc_grupo_pago, " +
"		sireh.tc_nombramiento, " +
"		sireh.tc_jerarquia, " + 
"		sireh.tc_tipo_pago " + 
"where  td_plaza.PLAZA_RFC                  = td_empleado.RFC_EMPLEADO " +
"and    td_plaza.ID_PUESTO_NOM              = tc_atributo_puesto_nom.ID_ATRIBUTO_PUESTO " +
"and    tc_atributo_puesto_nom.AP_FIN       = to_date('01/01/2099') " +
"and    tc_tabulador_nom.TAB_FIN            = to_date('01/01/2099') " +
"and    tc_atributo_puesto_nom.ID_NIVEL_PTO = tc_tabulador_nom.ID_NIVEL_PTO " +
"and    td_plaza.ID_RANGO_NOM               = tc_tabulador_nom.ID_RANGO " +
"and    td_plaza.ID_ZONA_ECO_NOM            = tc_tabulador_nom.ID_ZONA_ECON " +
"and    td_plaza.ID_PUESTO_PRE              = tc_atributo_puesto_pre.ID_ATRIBUTO_PUESTO " +
"and    tc_atributo_puesto_pre.AP_FIN       = to_date('01/01/2099') " +
"and    tc_tabulador_pre.TAB_FIN            = to_date('01/01/2099') " +
"and    tc_atributo_puesto_pre.ID_NIVEL_PTO = tc_tabulador_pre.ID_NIVEL_PTO " +
"and    td_plaza.ID_RANGO_PRE               = tc_tabulador_pre.ID_RANGO " +
"and    td_plaza.ID_ZONA_ECO_PRE            = tc_tabulador_pre.ID_ZONA_ECON " +
"and    td_plaza.ID_SIT_PLAZA like 'O%' " +
"and    td_plaza.PLAZA_RFC like ? "  +
"and    td_plaza.ID_GRUPO_PAGO                 = tc_grupo_pago.ID_GRUPO_PAGO " +
"and    td_plaza.ID_GRUPO_PAGO NOT IN ('H') " +
"and    tc_atributo_puesto_nom.ID_NOMBRAMIENTO = tc_nombramiento.ID_NOMBRAMIENTO " +
"and    tc_atributo_puesto_nom.ID_JERARQUIA    = tc_jerarquia.ID_JERARQUIA " +
"and    td_empleado.ID_TIPO_PAGO               = tc_tipo_pago.ID_TIPO_PAGO ", resultClass=PagoDB.class)

@Entity
public class PagoDB implements Serializable{
	private static final long serialVersionUID = 1L;
	public final static String FIND_RFC = "Pago.RFCfind";
    
    @Column(name="RFC_EMPLEADO")
    private String rfc;
    
    @Column(name="ID_PLAZA")
    @Id
    private Integer plaza;
    
    @Column(name="ID_UNIDAD_NOM")
    private Integer unidad;
    
    @Column(name="DESC_GRUPO_PAGO")
    private String grupo;
    
    @Column(name="ID_NIVEL_PTO")
    private String nivel;
    
    @Column(name="DESC_NOMBRAMIENTO")
    private String nombramiento;
    
    @Column(name="DESC_JERARQUIA")
    private String jerarquia;
    
    @Column(name="TAB_SUELDO")
    private double sueldo;
    
    @Column(name="TAB_COMPENSACION")
    private double compensacion;
    
    @Column(name="AP_INCENTIVO")
    private double sobresueldo;

    @Transient
    private List<ConceptoPagoDB> conceptos;
    
    @Transient
    private List<PensionDB> pensiones;
    
    @Transient
    private List<ConceptoPagadoDB> conceptosPagados;

    public PagoDB(){
    }
    
    public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfcEmpleado) {
		this.rfc = rfcEmpleado;
	}
	public Integer getPlaza() {
		return plaza;
	}
	public void setPlaza(Integer idPlaza) {
		this.plaza = idPlaza;
	}
	public Integer getUnidad() {
		return unidad;
	}
	public void setUnidad(Integer idUnidadNomb) {
		this.unidad = idUnidadNomb;
	}
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String descGrupoPago) {
		this.grupo = descGrupoPago;
	}
	public String getNivel() {
		return nivel;
	}
	public void setNivel(String idNivelPto) {
		this.nivel = idNivelPto;
	}
	public String getNombramiento() {
		return nombramiento;
	}
	public void setNombramiento(String descNombramiento) {
		this.nombramiento = descNombramiento;
	}
	public String getJerarquia() {
		return jerarquia;
	}
	public void setJerarquia(String descJerarquia) {
		this.jerarquia = descJerarquia;
	}
	public double getSueldo() {
		return sueldo;
	}
	public void setSueldo(double tabSueldo) {
		this.sueldo = tabSueldo;
	}
	public double getCompensacion() {
		return compensacion;
	}
	public void setCompensacion(double tabCompensacion) {
		this.compensacion = tabCompensacion;
	}
	
	public Double getSobresueldo() {
		return sobresueldo;
	}

	public void setSobresueldo(Double incentivo) {
		this.sobresueldo = incentivo;
	}

	
	public List <ConceptoPagoDB> getConceptos() {
		return conceptos;
	}

	public void setConceptos(List <ConceptoPagoDB> conceptos) {
		this.conceptos = conceptos;
	}

	public List <ConceptoPagadoDB> getConceptosPagados() {
		return conceptosPagados;
	}

	public void setConceptosPagados(List <ConceptoPagadoDB> conceptosPagados) {
		this.conceptosPagados = conceptosPagados;
	}

	public void setPensiones(List <PensionDB> pensiones) {
		this.pensiones = pensiones;
	}
	
	public List <PensionDB> getPensiones() {
		return pensiones;
	}

}

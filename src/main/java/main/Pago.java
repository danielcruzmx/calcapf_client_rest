package main;

import java.io.Serializable;
import java.util.List;

public class Pago implements Serializable{
	private static final long serialVersionUID = 1L;
    
	private Integer id;
    private String rfc;
    private Integer plaza;
    private Integer unidad;
    private String grupo;
    private String nivel;
    private String nombramiento;
    private String jerarquia;
    private double sueldo;
    private double compensacion;
    private double sobresueldo;
    private List<ConceptoPago> conceptospago;
    private List<ConceptoPagado> conceptospagados;
    private List<Pension> pensiones;
    
    public Pago(){
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

	public void setSobresueldo(Double  incentivo) {
		this.sobresueldo =  incentivo;
	}

	
	public List <ConceptoPago> getConceptospago() {
		return conceptospago;
	}

	public void setConceptospago(List <ConceptoPago> conceptos) {
		this.conceptospago = conceptos;
	}

	public List <ConceptoPagado> getConceptospagados() {
		return conceptospagados;
	}

	public void setConceptospagados(List<ConceptoPagado> conceptospagados) {
		this.conceptospagados = conceptospagados;
	}

	public List <Pension> getPensiones() {
		return pensiones;
	}
	
	public void setPensiones(List<Pension> pensiones) {
		this.pensiones = pensiones;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String to_String(){
		return "Empleado ->" + this.rfc + " Jerarquia->" + this.jerarquia + " Nivel->" + this.nivel + " Nombramiento->" + this.nombramiento;
	}

}

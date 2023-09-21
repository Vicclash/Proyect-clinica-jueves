package com.project.consorcio.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_medico")
public class Medico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cod_med")
	private Integer codigo;
	
	@Column(name="nom_med")
	private String nombre;
	
	@Column(name="ape_med")
	private String apellido;
	
	@Column(name="fec_nac_med")
	private LocalDate fechaNacimiento;
	
	@Column(name="sexo_med")
	private String sexo;
	
	@Column(name="est_civ_med")
	private String estadoCivil;
	
	@Column(name="dni_med")
	private int dni;
	
	@Column(name="sue_med")
	private int sueldo;
	
	@Column(name="dir_med")
	private String direccion;
	
	//
	@ManyToOne
	@JoinColumn(name="cod_espe")
	private Especialidad Especialidad;
	
	@ManyToOne
	@JoinColumn(name="cod_sede")
	private Sede Sede;
	
	@ManyToOne
	@JoinColumn(name="cod_dis")
	private Distrito Distrito;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public int getSueldo() {
		return sueldo;
	}

	public void setSueldo(int sueldo) {
		this.sueldo = sueldo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Especialidad getEspecialidad() {
		return Especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		Especialidad = especialidad;
	}

	public Sede getSede() {
		return Sede;
	}

	public void setSede(Sede sede) {
		Sede = sede;
	}

	public Distrito getDistrito() {
		return Distrito;
	}

	public void setDistrito(Distrito distrito) {
		Distrito = distrito;
	}

	
}

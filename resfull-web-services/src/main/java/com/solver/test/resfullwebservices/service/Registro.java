package com.solver.test.resfullwebservices.service;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Registro {
	
	@Id
	@GeneratedValue
	private Long numero;
	
	private String cedula;
	private String nombreArchivo;
	private Date fechaEjecucion;
	
	protected Registro() {
	}

	public Registro(long numero, String cedula, String nombreArchivo, Date fechaEjecucion) {
		super();
		this.numero = numero;
		this.cedula = cedula;
		this.nombreArchivo = nombreArchivo;
		this.fechaEjecucion = fechaEjecucion;
	}
	
	
	public Long getNumero() {
		return numero;
	}
	public void setNumero(Long numero) {
		this.numero = numero;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getNombreArchivo() {
		return nombreArchivo;
	}
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	public Date getFechaEjecucion() {
		return fechaEjecucion;
	}
	public void setFechaEjecucion(Date fechaEjecucion) {
		this.fechaEjecucion = fechaEjecucion;
	}
	
	
	@Override
	public String toString() {
		return "Registro [numero=" + numero + ", cedula=" + cedula + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cedula == null) ? 0 : cedula.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Registro other = (Registro) obj;
		if (cedula == null) {
			if (other.cedula != null)
				return false;
		} else if (!cedula.equals(other.cedula))
			return false;
		return true;
	}

}

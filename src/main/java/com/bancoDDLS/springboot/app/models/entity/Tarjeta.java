package com.bancoDDLS.springboot.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "tarjetas")
public class Tarjeta implements Serializable {

	private static final long serialVersionUID = 6504848105110978268L;
	
	@Id
	@Column(name = "id_tarjeta")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTarjeta;
	
	@Column(name = "numero_tarjeta")
	@NotEmpty
	private String numeroTarjeta;
	
	@JoinColumn(name = "id_cuenta", referencedColumnName="id_cuenta", nullable = false)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Cuenta idCuenta;
	
	@Column
	@NotEmpty
	private String icv;
	
	@Column(name = "tipo_tarjeta")
	@NotEmpty
	private String tipoTarjeta;

	public Long getIdTarjeta() {
		return idTarjeta;
	}

	public void setIdTarjeta(Long idTarjeta) {
		this.idTarjeta = idTarjeta;
	}

	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	
	public Cuenta getIdCuenta() {
		return idCuenta;
	}

	public void setIdCuenta(Cuenta idCuenta) {
		this.idCuenta = idCuenta;
	}

	public String getIcv() {
		return icv;
	}

	public void setIcv(String icv) {
		this.icv = icv;
	}

	public String getTipoTarjeta() {
		return tipoTarjeta;
	}

	public void setTipoTarjeta(String tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}

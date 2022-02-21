package com.bancoDDLS.springboot.app.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "cuentas")
public class Cuenta implements Serializable{

	private static final long serialVersionUID = -7607262087312650369L;
	
	@Id
	@Column(name = "id_cuenta")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCuenta;
	
	@JoinColumn(name = "id_cliente", /*referencedColumnName = "idCliente",*/ nullable = false)
	@OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	private Cliente idCliente;
	
	@Column(name = "saldo_actual")
	@NotEmpty
	private double saldoActual;
	
	@Column
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date creacion;

	public Long getIdCuenta() {
		return idCuenta;
	}

	public void setIdCuenta(Long idCuenta) {
		this.idCuenta = idCuenta;
	}

	public Cliente getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Cliente idCliente) {
		this.idCliente = idCliente;
	}
	
	

	public double getSaldoActual() {
		return saldoActual;
	}

	public void setSaldoActual(double saldoActual) {
		this.saldoActual = saldoActual;
	}

	public Date getCreacion() {
		return creacion;
	}

	public void setCreacion(Date creacion) {
		this.creacion = creacion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}

package com.bancoDDLS.springboot.app.models.entity;

import java.io.Serializable;

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
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 6558114116653843355L;
	
	@Id
	@Column(name = "id_cliente")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotEmpty
	private Long idCliente;
	
	@Column
	@NotEmpty
	private String nombre;
	
	@Column
	@NotEmpty
	private String apellido;
	
	@Column(name = "numero_telefonico")
	@NotEmpty
	private String numeroTelefonico;
	
	@Column
	@NotEmpty
	private String email;
	
	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
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

	public String getNumeroTelefonico() {
		return numeroTelefonico;
	}

	public void setNumeroTelefonico(String numeroTelefonico) {
		this.numeroTelefonico = numeroTelefonico;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}

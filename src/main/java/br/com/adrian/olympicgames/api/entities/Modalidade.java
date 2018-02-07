package br.com.adrian.olympicgames.api.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "modalidade")
public class Modalidade implements Serializable {

	private static final long serialVersionUID = 3413569997020188484L;
	
	private Long id;
	private String descricao;
	
	public Modalidade() {
		
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "descricao", nullable = false)
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public String toString() {
		return "Modalidade [id = " + id + ", descricao = " + descricao + "]";
	}
}
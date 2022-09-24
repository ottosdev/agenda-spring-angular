package com.ottosouza.agenda.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_contato")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contato {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 150,  nullable = false)
	private String nome;
	
	@Column(unique = true, nullable = false)
	private String email;
	
	@Column
	private Boolean favorito;
	
	@Column
	@Lob
	private byte[] foto;

}

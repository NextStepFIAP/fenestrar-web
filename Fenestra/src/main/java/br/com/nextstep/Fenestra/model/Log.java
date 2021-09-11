package br.com.nextstep.Fenestra.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity(name = "TB_LOG")
@SequenceGenerator(name="log", sequenceName = "SQ_TB_LOG", allocationSize = 1)
public class Log {

	@Id @GeneratedValue(generator = "log", strategy = GenerationType.SEQUENCE)
	@Column(name = "CD_REGISTRO", nullable = false)
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_REGISTRO", nullable = false)	
	private LocalDate dateRegistro;
	
	@Column(name = "DS_REGISTRO", nullable = false, length = 64)
	private String description;
}

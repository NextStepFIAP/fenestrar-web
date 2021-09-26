package br.com.nextstep.Fenestra.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity(name = "TB_LOG_DEVOPS")
@SequenceGenerator(name="log", sequenceName = "SQ_TB_LOG_DEVOPS", allocationSize = 1)
public class Log {

	@Id @GeneratedValue(generator = "log", strategy = GenerationType.SEQUENCE)
	@Column(name = "CD_REGISTRO", nullable = false)
	private Long id;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "DT_REGISTRO", nullable = false)	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date dateRegistro;
	
	@Column(name = "DS_REGISTRO", nullable = false, length = 64)
	private String description;
}

package com.quevinfreire.financialapp.entities;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Transaction implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 30)
	private String description; 
	@Column(nullable = false, unique = true, length = 20)
	private String type;
	@Column(nullable = false)
	private Double amount;
	@Column(nullable = false)
	private LocalDateTime moment = LocalDateTime.now();
	@Column(nullable = false)
	private Date dueDate; 
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@Column(nullable = false)
	private User user;
}

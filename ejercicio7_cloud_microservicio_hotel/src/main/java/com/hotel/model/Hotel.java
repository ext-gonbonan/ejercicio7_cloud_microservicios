package com.hotel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "hoteles")
public class Hotel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "El nombre del hotel no puede estar vacío")
	@Size(max = 100, message = "El nombre del hotel no puede exceder los 100 caracteres")
	private String nombre;

	@NotBlank(message = "La categoría del hotel no puede estar vacía")
	@Size(max = 50, message = "La categoría del hotel no puede exceder los 50 caracteres")
	private String categoria;

	@DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor que 0")
	@DecimalMax(value = "10000.0", message = "El precio máximo permitido es 10000")
	private Double precio;

	@NotNull(message = "La disponibilidad no puede estar vacía")
	private Boolean disponible;

}
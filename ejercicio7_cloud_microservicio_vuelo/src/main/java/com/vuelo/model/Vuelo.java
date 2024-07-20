package com.vuelo.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "vuelos")
public class Vuelo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idVuelo;

	@NotBlank(message = "La compañía no puede estar vacía")
	private String compania;

	@NotNull(message = "La fecha del vuelo no puede estar vacía")
	private LocalDate fechaVuelo;

	@NotNull(message = "El precio no puede estar vacío")
	private Double precio;

	@NotNull(message = "Las plazas disponibles no pueden estar vacías")
	private Integer plazasDisponibles;

}

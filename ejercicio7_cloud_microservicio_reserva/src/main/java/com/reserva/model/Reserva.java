package com.reserva.model;

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
@Table(name = "reservas")
public class Reserva {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idReserva;

	@NotBlank(message = "El nombre del cliente no puede estar vacío")
	private String nombreCliente;

	@NotBlank(message = "El DNI no puede estar vacío")
	private String dni;

	@NotNull(message = "El identificador del hotel no puede estar vacío")
	private Long idHotel;

	@NotNull(message = "El identificador del vuelo no puede estar vacío")
	private Long idVuelo;

}
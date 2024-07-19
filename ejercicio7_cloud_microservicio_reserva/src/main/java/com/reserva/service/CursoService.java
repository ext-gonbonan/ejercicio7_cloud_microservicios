package com.reserva.service;

import java.util.List;

import com.reserva.model.Curso;

public interface CursoService {
	
	List<Curso> obtenerCursos();

	List<Curso> altaCurso(Curso curso);

	List<Curso> eliminarCurso(String codCurso);

	void actualizarDuracion(String codCurso, int duracion);

	Curso buscarCurso(String codCurso);

	List<Curso> cursosPorPrecio(double precioMin, double precioMax);
	
	List<Curso> cursosPorPrecio2(double precioMin, double precioMax);
	
}
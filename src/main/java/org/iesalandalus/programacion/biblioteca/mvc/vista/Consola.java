package org.iesalandalus.programacion.biblioteca.mvc.vista;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.*;
import org.iesalandalus.programacion.utilidades.Entrada;

/**
 * @author: Jonathan Simón Sánchez
 * @version: v0
 **/
public class Consola {

	private Consola() {
		// Constructor vacío
	}

	// Métodos
	public static void mostrarMenu() {
		mostrarCabecera("MENÚ DE OPCIONES");
		for (Opcion opcion : Opcion.values()) {
			System.out.println(opcion);
		}
	}

	/**
	 * Devolverá la cadena y en la siguiente línea su longitud en guiones '-'
	 * 
	 * @param: string
	 **/
	public static void mostrarCabecera(String cadena) {
		System.out.println(cadena);
		for (int i = 0; i < cadena.length(); i++) {
			System.out.print("-");
		}
		System.out.println("\n");
	}

	public static int elegirOpcion() {
		int opcion;
		do {
			System.out.print("ELIJA UNA OPCIÓN: ");
			opcion = Entrada.entero();
			System.out.println();
			if (!Opcion.esOrdinalValido(opcion)) {
				System.out.println("La opción elegida no es válida.");
				System.out.println("Marque una opción correcta.");
			}
		} while (!Opcion.esOrdinalValido(opcion));
		return opcion;
	}

	public static Alumno leerAlumno() {
		String nombre;
		String correo;
		int opcion;
		System.out.println("Introduce el nombre del alumno:");
		nombre = Entrada.cadena();
		System.out.println("Introduce el correo del alumno:");
		correo = Entrada.cadena();
		do {
			System.out.println("Introduce el curso del alumno:");
			System.out.println("1º - 0");
			System.out.println("2º - 1");
			System.out.println("3º - 2");
			System.out.println("4º - 3");
			opcion = Entrada.entero();
		} while (opcion < 0 || opcion > 3);
		Curso curso = Curso.values()[opcion];
		return new Alumno(nombre, correo, curso);
	}

	public static Alumno leerAlumnoFicticio() {
		String correo;
		System.out.println("Introduzca el correo del alumno ficticio: ");
		correo = Entrada.cadena();
		return Alumno.getAlumnoFicticio(correo);
	}

	public static Libro leerLibro() {
		String titulo;
		String autor;
		int numPaginas;
		System.out.println("Introduce el título del libro: ");
		titulo = Entrada.cadena();
		System.out.println("Introduce el autor del libro: ");
		autor = Entrada.cadena();
		System.out.println("Introduce las páginas del libro del libro: ");
		numPaginas = Entrada.entero();
		return new Libro(titulo, autor, numPaginas);

	}

	public static Libro leerLibroFicticio() {
		String titulo;
		String autor;
			System.out.println("Introduce el título del libro: ");
			titulo = Entrada.cadena();
			System.out.println("Introduce el autor del libro: ");
			autor = Entrada.cadena();
		return Libro.getLibroFicticio(titulo, autor);
	}

	public static LocalDate leerFecha(String cadenaFecha) {
		LocalDate fecha;
		fecha = LocalDate.parse(cadenaFecha, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		return fecha;
	}

	public static Prestamo leerPrestamo() {
		Alumno alumno = leerAlumno();
		Libro libro = leerLibro();
		String cadenaFecha;

		do {
			System.out.println("Introduce la fecha (DD/MM/AAAA): ");
			cadenaFecha = Entrada.cadena();
		} while (cadenaFecha == null || cadenaFecha.equals("")
				|| cadenaFecha.matches("[0-3][0-9]\\/[0-1][0.3]\\/20[0-9][0-9]"));

		LocalDate fecha = leerFecha(cadenaFecha);

		return new Prestamo(alumno, libro, fecha);
	}

	public static Prestamo leerPrestamoFicticio() {
		return Prestamo.getPrestamoFicticio(leerAlumno(), leerLibro());
	}

}

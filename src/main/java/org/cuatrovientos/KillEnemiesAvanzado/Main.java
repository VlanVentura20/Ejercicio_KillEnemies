package org.cuatrovientos.KillEnemiesAvanzado;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
	
	static ArrayList<Character> misPersonajes = new ArrayList<Character>();
	
	public void contadorPersonajes() {
		System.out.println("Hay " + misPersonajes.size() + " personajes");
	}
	
	public void mostrarPersonajes() {
		for (int i = 0; i <= misPersonajes.size() - 1; i++) {
			if (misPersonajes.get(i).isEnemy()) {
				System.out.println("Personaje Nº" + i + ": es un enemigo");
			} else {
				System.out.println("Personaje Nº" + i + ": es un amigo");
			}
		}
	}
	
	public static void nuevoJuego( ) {
		
		misPersonajes.clear();
		
		misPersonajes.add(new Friend());
		misPersonajes.add(new Friend());
		misPersonajes.add(new Friend());
		misPersonajes.add(new Friend());
		misPersonajes.add(new Friend());
		
		misPersonajes.add(new Enemy());
		misPersonajes.add(new Enemy());
		misPersonajes.add(new Enemy());
		misPersonajes.add(new Enemy());
		misPersonajes.add(new Enemy());
		
		Collections.shuffle(misPersonajes);
	}
	
	public static void juegoExistente( ) {
		try {
			ObjectInputStream input = new ObjectInputStream(
				    new FileInputStream("partida.dat")
				);

				misPersonajes = (ArrayList<Character>) input.readObject();

				input.close();
				
		} catch (FileNotFoundException e) {
		    System.out.println("No se ha encontrado el archivo de partida.");
		} catch (IOException e) {
	        System.out.println("Ha ocurrido un error al leer la partida.");
	        System.out.println("Creando una pertida nueva");
	        nuevoJuego();
		} catch (ClassNotFoundException e) {
	        System.out.println("No se ha encontrado la clase de un personaje.");
	    }
		
	}
	
	public static void main(String[] args) {
		
		try {
			
			Scanner readConsole = new Scanner(System.in);
			String leerConsola, opcionJuego;
			

			if (new File("partida.dat").exists()) {
				juegoExistente();
				System.out.println("Cargando partida existente");
			} else {
			    nuevoJuego();
			    System.out.println("No se ha encontrado partida, creando una nueva");
			}
			
			

			do {
			    System.out.println("1. Jugar");
			    System.out.println("2. Guardar partida");
			    System.out.println("3. Nueva partida");
			    System.out.println("4. Salir");
			    System.out.print("Elige una opción: ");

			    opcionJuego = readConsole.nextLine();

			    if (opcionJuego.equals("1")) {
	///////////////////////////////////////////////////

	/////////////////////  OPCION 1  //////////////////

	///////////////////////////////////////////////////
			    	ArrayList<Character> misPersonajesCopia = new ArrayList<>(misPersonajes);

			    	for (int i = 0; i < misPersonajesCopia.size(); i++) {

			    	    Character personaje = misPersonajesCopia.get(i);

			    	    if (personaje.isEnemy()) {
			    	        System.out.println("El personaje Nº" + i + " es un enemigo! ¡Mátalo!");
			    	    } else {
			    	        System.out.println("El personaje Nº" + i + " es un amigo!");
			    	    }

			    	    do {
			    	        System.out.print("¿Qué quieres hacer? (curar/atacar/nada): ");

			    	        leerConsola = readConsole.nextLine();

			    	        if (!leerConsola.toLowerCase().equals("curar")
			    	                && !leerConsola.toLowerCase().equals("atacar")
			    	                && !leerConsola.toLowerCase().equals("nada")) {

			    	            System.out.println("Eso no es una opción correcta");
			    	        }

			    	    } while (!leerConsola.toLowerCase().equals("curar")
			    	            && !leerConsola.toLowerCase().equals("atacar")
			    	            && !leerConsola.toLowerCase().equals("nada"));

			    	    if (personaje.isEnemy()) {

			    	        if (leerConsola.toLowerCase().equals("curar")) {

			    	            misPersonajesCopia.add(personaje);

			    	        } else if (leerConsola.toLowerCase().equals("atacar")) {

			    	            Enemy miEnemigo = (Enemy) personaje;
			    	            miEnemigo.kill();
			    	            misPersonajesCopia.remove(personaje);
			    	            i--;

			    	        }

			    	    } else {

			    	        if (leerConsola.toLowerCase().equals("curar")) {

			    	            Friend miAmigo = (Friend) personaje;
			    	            miAmigo.heal();

			    	        } else if (leerConsola.toLowerCase().equals("atacar")) {

			    	            misPersonajesCopia.remove(personaje);
			    	            i--;

			    	        }
			    	    }
			    	}

			    	misPersonajes = misPersonajesCopia;
			        
			    } else if (opcionJuego.equals("2")) {
	///////////////////////////////////////////////////

	/////////////////////  OPCION 2  //////////////////

	///////////////////////////////////////////////////
			    	ObjectOutputStream output = new ObjectOutputStream(
			    		    new FileOutputStream("partida.dat")
			    		);

			    		output.writeObject(misPersonajes);

			    		output.close();
			    		
			    		System.out.println("Partida guardada correctamente.");
			    	
			    } else if (opcionJuego.equals("3")) {
	///////////////////////////////////////////////////

	/////////////////////  OPCION 3  //////////////////

	///////////////////////////////////////////////////
			    	File archivo = new File("partida.dat");

			    	if (archivo.exists()) {
			    	    archivo.delete();
			    	    System.out.println("Partida eliminada.");
			    	} else {
			    	    System.out.println("No existe ninguna partida guardada.");
			    	}
			    	
			    	misPersonajes.clear();
			    	nuevoJuego();
			    	
			    	
			    } else if (opcionJuego.equals("4")) {
	///////////////////////////////////////////////////

	/////////////////////  OPCION 4  //////////////////

	///////////////////////////////////////////////////
			    	break;
				} else {
			        System.out.println("Opción no válida");
			    }

			    System.out.println();
			} while (!opcionJuego.equals("4"));
			
			
			
		} catch (Exception e) {
			
			System.out.println("Ha ocurrido un error inesperado.");
		    System.out.println("Error: " + e.getMessage());
			
		}
		

		
	}

}

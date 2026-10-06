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
	static Hero miHeroe = new Hero();

	public static void contadorPersonajes() {

		int enemigos = 0;
		int amigos = 0;

		for (Character personaje : misPersonajes) {

			if (personaje instanceof Enemy) {
				enemigos++;
			} else if (personaje instanceof Friend) {
				amigos++;
			}
		}

		System.out.println("Hay " + enemigos + " enemigos");
		System.out.println("Hay " + amigos + " amigos");
	}

	public static void mostrarPersonajes() {

		System.out.println("Tu heroe tiene " + miHeroe.getVida() + " de vida");
		
		for (int i = 0; i <= misPersonajes.size() - 1; i++) {

			if (misPersonajes.get(i).isEnemy()) {
				System.out.println(
						"Personaje Nº" + i + ": es un enemigo, tiene " + misPersonajes.get(i).getVida() + " de vida");
			} else {
				System.out.println(
						"Personaje Nº" + i + ": es un amigo, tiene " + misPersonajes.get(i).getVida() + " de vida");
			}
		}
	}

	public static void nuevoJuego() {

		misPersonajes.clear();
		miHeroe = new Hero();

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

	public static void juegoExistente() {
		try {
			ObjectInputStream input = new ObjectInputStream(new FileInputStream("partida.dat"));

			misPersonajes = (ArrayList<Character>) input.readObject();
			miHeroe = (Hero) input.readObject();

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
				System.out.println("2. Ver personajes");
				System.out.println("3. Guardar partida");
				System.out.println("4. Nueva partida");
				System.out.println("5. Salir");
				System.out.print("Elige una opción: ");

				opcionJuego = readConsole.nextLine();

				if (opcionJuego.equals("1")) {

///////////////////////////////////////////////////

////////////////////// OPCION 1 ///////////////////

///////////////////////////////////////////////////

					if (miHeroe.getVida() <= 0) {

						System.out.println("Has perdido, tu heroe ha muerto");

					} else {

						int enemigos = 0;

						for (Character personaje : misPersonajes) {

							if (personaje instanceof Enemy) {
								enemigos++;
							}
						}

						if (enemigos == 0) {

							System.out.println("Has ganado");

						} else {

							ArrayList<Character> misPersonajesCopia = new ArrayList<>(misPersonajes);

							int i = 0;

							while (i < misPersonajesCopia.size()) {

							    Character personaje = misPersonajesCopia.get(i);

							    System.out.println("Tu heroe tiene " + miHeroe.getVida() + " de vida");
							    
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

							            if (miEnemigo.kill()) {
							                misPersonajesCopia.remove(i);
							                continue;
							            }
							            

							            miHeroe.setVida(miHeroe.getVida() - 1);
							        }

							    } else {

							        if (leerConsola.toLowerCase().equals("curar")) {

							            Friend miAmigo = (Friend) personaje;
							            miAmigo.heal();

							            miHeroe.setVida(miHeroe.getVida() - 1);

							        } else if (leerConsola.toLowerCase().equals("atacar")) {

							        	Friend miAmigo = (Friend) personaje;
							            miAmigo.kill();
							        	
							            misPersonajesCopia.remove(i);

							            miHeroe.setVida(miHeroe.getVida() - 3);

							            continue;
							        }
							    }

							    if (miHeroe.getVida() <= 0) {
							        System.out.println("Has perdido, tu heroe ha muerto");
							        break;
							    }

							    i++;
							}

							misPersonajes = misPersonajesCopia;
						}
					}

				} else if (opcionJuego.equals("2")) {
///////////////////////////////////////////////////

////////////////////// OPCION 2 ///////////////////

///////////////////////////////////////////////////
					contadorPersonajes();
					mostrarPersonajes();

				} else if (opcionJuego.equals("3")) {
///////////////////////////////////////////////////

////////////////////// OPCION 3 ///////////////////

///////////////////////////////////////////////////
					ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("partida.dat"));

					output.writeObject(misPersonajes);
					output.writeObject(miHeroe);

					output.close();

					System.out.println("Partida guardada correctamente.");

				} else if (opcionJuego.equals("4")) {
///////////////////////////////////////////////////

////////////////////// OPCION 4 ///////////////////

///////////////////////////////////////////////////
					File archivo = new File("partida.dat");

					if (archivo.exists()) {
						archivo.delete();
					}

					misPersonajes.clear();
					nuevoJuego();
					
					System.out.println("Se ha creado una partida nueva");

				} else if (opcionJuego.equals("5")) {
///////////////////////////////////////////////////

////////////////////// OPCION 5 ///////////////////

///////////////////////////////////////////////////
					break;
				} else {
					System.out.println("Opción no válida");
				}

				System.out.println();
			} while (!opcionJuego.equals("5"));

		} catch (Exception e) {

			System.out.println("Ha ocurrido un error inesperado.");
			System.out.println("Error: " + e.getMessage());

		}

	}

}

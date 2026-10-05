package org.cuatrovientos.KillEnemiesAvanzado;

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
	
	public static void main(String[] args) {
		
		Scanner readConsole = new Scanner(System.in);
		String leerConsola;
		
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
		
		for (int i = 0; i <= misPersonajes.size() - 1; i++) {
			if (misPersonajes.get(i).isEnemy()) {
				System.out.println("El personaje " + i + " es un enemigo! ¡Matalo!");
				
				do {
					System.out.println("¿Quieres matar al enemigo? (s/n): ");
					leerConsola = readConsole.nextLine();
					if (leerConsola.toLowerCase() != "n" && leerConsola.toLowerCase() != "s") {
						System.out.println("Eso no es una opcion correcta");
					}
				} while (leerConsola.toLowerCase() != "n" && leerConsola.toLowerCase() != "s");

				if (leerConsola.toLowerCase() != "s") {
					Enemy miEnemigo = (Enemy) misPersonajes.get(i);
					miEnemigo.kill();
				}
				
			} else {
				System.out.println("El personaje " + i + " es un amigo!");
				
				do {
					System.out.println("¿Quieres curar al amigo? (s/n): ");
					leerConsola = readConsole.nextLine();
					if (leerConsola.toLowerCase() != "n" && leerConsola.toLowerCase() != "s") {
						System.out.println("Eso no es una opcion correcta");
					}
				} while (leerConsola.toLowerCase() != "n" && leerConsola.toLowerCase() != "s");
				
				if (leerConsola.toLowerCase() != "s") {
					Friend miAmigo = (Friend) misPersonajes.get(i);
					miAmigo.heal();
				}
			}
			
		}
		
	}

}

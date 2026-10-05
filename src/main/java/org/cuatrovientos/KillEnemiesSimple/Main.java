package org.cuatrovientos.KillEnemiesSimple;

import java.util.ArrayList;
import java.util.Collections;

public class Main {

	public static void main(String[] args) {

		ArrayList<Character> misPersonajes = new ArrayList<Character>();
		
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
				Enemy miEnemigo = (Enemy) misPersonajes.get(i);
				miEnemigo.kill();
			} else {
				System.out.println("El personaje " + i + " es un amigo!");
			}
			
		}
		
	}

}

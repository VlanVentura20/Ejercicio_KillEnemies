package org.cuatrovientos.KillEnemiesAvanzado;

import java.util.Random;

public class Friend implements Character {

	private int vida;

	public Friend() {
	    Random random = new Random();
	    this.vida = random.nextInt(1,11);
	}

	public int getVida() {
		if (vida > 10) {
			vida = 10;
		}
	    return vida;
	}

	@Override
	public boolean isEnemy() {
		return false;
	}
	
	public void heal() {
		vida = vida + 2;
		System.out.println("¡Te he curado!");
	}
	
	public boolean kill () {
		vida = vida - 10;
		if (vida <= 0) {
			System.out.println("Ahhhggg, me mataste, bastardo!");
			return true;
		}
		return false;	
	}
 
}

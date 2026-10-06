package org.cuatrovientos.KillEnemiesAvanzado;

import java.util.Random;

public class Enemy implements Character {

	private int vida;
	
	public Enemy() {
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
		return true;
	}
	
	public boolean kill () {
		vida = vida - 3;
		if (vida <= 0) {
			System.out.println("Ahhhggg, me mataste, bastardo!");
			return true;
		}
		return false;	
	}

}

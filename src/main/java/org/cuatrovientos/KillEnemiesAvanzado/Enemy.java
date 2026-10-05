package org.cuatrovientos.KillEnemiesAvanzado;

public class Enemy implements Character {

	@Override
	public boolean isEnemy() {
		return true;
	}
	
	public void kill () {
		System.out.println("Ahhhggg, me mataste, bastardo!");
	}

}

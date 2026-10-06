package org.cuatrovientos.KillEnemiesAvanzado;

public class Hero implements Character {

	private int vida;
	
	public Hero() {
		this.vida = 100;
	}

	private int cntEnemigosMatados;
	private int cntAmigosDefendidos;
	
	public int getCntEnemigosMatados() {
		return cntEnemigosMatados;
	}

	public int getCntAmigosDefendidos() {
		return cntAmigosDefendidos;
	}

	@Override
	public boolean isEnemy() {
		return false;
	}
	
	public void attack(Enemy enemy) {
		System.out.println("¡He atacado a un enemigo!");
		enemy.kill();
		cntEnemigosMatados++;
	}
	
	public void defend(Friend friend) {
		System.out.println("¡He defendido a un amigo!");
		cntAmigosDefendidos++;
	}

	@Override
	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

}

package edu.eci.arsw.highlandersim;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Immortal extends Thread {

	private ImmortalUpdateReportCallback updateCallback = null;

	private AtomicInteger health;

	private int defaultDamageValue;

	private final List<Immortal> immortalsPopulation;

	private final String name;

	private final Random r = new Random(System.currentTimeMillis());

	private boolean detenidos = true;

	private boolean alive = true;
	
	public Immortal(String name, List<Immortal> immortalsPopulation, int health, int defaultDamageValue,
			ImmortalUpdateReportCallback ucb) {
		super(name);
		this.updateCallback = ucb;
		this.name = name;
		this.immortalsPopulation = immortalsPopulation;
		this.health = new AtomicInteger(health);
		this.defaultDamageValue = defaultDamageValue;
	}

	public void run() {
		while (alive) {
			synchronized (this) {
				if (detenidos) {
					try {
						wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				Immortal im;
				
				
				if(immortalsPopulation.size() > 1) {
					im = oponente();
				}
				else {
					break;
				}

				this.fight(im);

				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void fight(Immortal i2) {
		
		
		if (i2.getHealth() > 0) {
			// System.out.println(immortalsPopulation.size());
			// i2.changeHealth(i2.getHealth() - defaultDamageValue);
			i2.health.addAndGet(-defaultDamageValue);
			this.health.addAndGet(defaultDamageValue);
			if(updateCallback!=null) updateCallback.processReport("Fight: " + this + " vs " + i2 + "\n");
			if(this.getHealth()<=0) {
				if(updateCallback!=null) updateCallback.processReport(i2 + " says:" + this + " is already dead!\n");
				this.pausar();
				alive = false;
				notify();
				immortalsPopulation.remove(this);
				
			}
		} else {
			if(updateCallback!=null) updateCallback.processReport(this + " says:" + i2 + " is already dead!\n");

		}
		

	}
	
	private Immortal oponente() {
		int myIndex = immortalsPopulation.indexOf(this);
		int nextFighterIndex = r.nextInt(immortalsPopulation.size());
		// avoid self-fight
		if (nextFighterIndex == myIndex) {
			nextFighterIndex = ((nextFighterIndex + 1) % immortalsPopulation.size());
		}
		return immortalsPopulation.get(nextFighterIndex);
	}

	/**
	 * public void changeHealth(int v) { health = v; }
	 */

	public int getHealth() {
		return health.get();
	}

	@Override
	public String toString() {

		return name + "[" + health + "]";
	}
	
	public boolean getDetenidos() {
		return detenidos;
	}

	public synchronized void changeAlive() {
		this.alive = false;
	}
	
	public synchronized void iniciar() {
		this.detenidos = false;
		this.start();
		notify();
	}

	public synchronized void pausar() {
		this.detenidos = true;
		notify();
	}

	public synchronized void resumir() {
		this.detenidos = false;
		notify();
	}
	
	public String lista() {
		return immortalsPopulation.toString();
	}

}

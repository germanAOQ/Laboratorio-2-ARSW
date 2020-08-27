package edu.eci.arsw;

import org.junit.Before;
import org.junit.Test;

import edu.eci.arsw.highlandersim.Immortal;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

public class pruebas {

	List<Immortal> inmortalLista = new LinkedList<Immortal>();

	public pruebas() {
		for (int i = 0; i < 3; i++) {
			Immortal im = new Immortal("inm" + i, inmortalLista, 100, 10, null);
			inmortalLista.add(im);
		}

	}

	@Test
	public void iniciarInmortales() {
		int cont = 0;

		for (int i = 0; i < 3; i++) {
			inmortalLista.get(i).iniciar();
		}

		for (int i = 0; i < 3; i++) {
			if (inmortalLista.get(i).isAlive() == true)
				cont++;
		}
		assertEquals(3, cont);
	}

	@Test
	public void detenerInmortales() {
		int cont = 0;
		for (int i = 0; i < 3; i++) {
			inmortalLista.get(i).pausar();

		}
		for (int i = 0; i < 3; i++) {
			if (inmortalLista.get(i).getDetenidos() == true)
				cont++;
		}
		assertEquals(3, cont);
	}

	@Test
	public void checkInvariante() {
		int inv = 300;
		int total = 0;
		for (int i = 0; i < 3; i++) {
			total += inmortalLista.get(i).getHealth();
		}
		assertEquals(inv, total);
	}

}

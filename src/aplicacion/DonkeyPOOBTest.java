package aplicacion;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class DonkeyPOOBTest{
	
	private Escenario juego;
	
	@Before
	public void prepareElementos() {
		try {
			juego = new Escenario(1, "Un jugador", "Mario", null);
		} catch (DonkeyPOOBException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void deberiaAgregarPersonajesAlCrearElEscenario() {
		try {
			juego = new Escenario(1, "Un jugador", "Mario", null);
			assertEquals(juego.getPersonajes().size(), 3);
			juego = new Escenario(2, "Dos jugadores", "Mario", "Luigi");
			assertEquals(juego.getPersonajes().size(), 4);
			juego = new Escenario(2, "Jugador contra maquina", "Mario", "Luigi-Temeroso");
			assertEquals(juego.getPersonajes().size(), 4);
			juego = new Escenario(2, "Maquina contra maquina", "Mario-Protector", "Luigi-Temeroso");
			assertEquals(juego.getPersonajes().size(), 4);
		}catch(DonkeyPOOBException e) {
			fail("Lanzó excepcion");
		}
	}
	
	@Test
	public void deberiaLanzarExcepcionSiLosPersonajesNoCorrespondenAlModoDeJuego() {
		try {
			juego = new Escenario(1, "Un jugador", "Mario", "Luigi");
			fail("No lanzó excepcion");
		}catch(DonkeyPOOBException e) {
			assertEquals(e.getMessage(), DonkeyPOOBException.PERSONAJES_NO_CORRESPONDEN_A_MODO_DE_JUEGO);
		}
	}
	
	@Test
	public void deberiaAñadirElementosAlCrearElEscenario() {
		try {
			juego = new Escenario(1, "Un jugador", "Mario", null);
			assertTrue(juego.getElementos().size() > 0);
		}catch(DonkeyPOOBException e) {
			fail("Lanzó excepción");
		}
	}
	
	@Test
	public void deberiaCambiarPosicionEnXCuandoSePresionaLaFlechaDerechaYFlechaIzquierda() {
		try {
			juego = new Escenario(1, "Un jugador", "Mario", null);
			ArrayList<Elemento> elementosInteractuar = juego.getPersonajes().get("Mario:1").elementosInteractuar("R");
			int posx1= juego.getPersonajes().get("Mario:1").cuantificable.getPosx();
			juego.getPersonajes().get("Mario:1").interactue(elementosInteractuar, "R");
			int posx2= juego.getPersonajes().get("Mario:1").cuantificable.getPosx();
			assertTrue(posx1 < posx2);
			elementosInteractuar = juego.getPersonajes().get("Mario:1").elementosInteractuar("L");
			juego.getPersonajes().get("Mario:1").interactue(elementosInteractuar, "L");
			assertTrue(posx2 > juego.getPersonajes().get("Mario:1").cuantificable.getPosx());
		}catch(DonkeyPOOBException e) {
			fail("Lanzó excepción");
		}
	}
	
	@Test
	public void losJugadoresConPersfilesAutomaticosDeberianMoverseSolos() {
		try {
			juego = new Escenario(1, "Jugador contra maquina", "Mario", "Luigi-Temeroso");
			Jumpman luigi = (Jumpman) juego.getPersonajes().get("Luigi:1");
			int posx = luigi.cuantificable.getPosx();
			int posy = luigi.cuantificable.getPosy();
			Thread.sleep(1000);
			assertTrue(posx != luigi.cuantificable.getPosx() || posy != luigi.cuantificable.getPosy());
		}catch(DonkeyPOOBException e) {
			fail("Lanzó excepción");
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void elJugadorPierdeOGanaVidasAlColisionarConBarriles() {
		try {
			juego = new Escenario(1, "Un jugador", "Mario", null);
			Jumpman mario = (Jumpman) juego.getPersonajes().get("Mario:1");
			int vidas = mario.vidas;
			juego.getElementos().put("BarrilRojo:1", new BarrilRojo(mario.cuantificable.getPosx(), mario.cuantificable.getPosy(), 25, 25));
			Barril b = (Barril) (juego.getElementos().get("BarrilRojo:1"));
			b.muevase(630);
			Thread.sleep(1000);
			int vidas2 = mario.vidas;
			assertTrue(vidas > vidas2);
			juego.getElementos().put("BarrilVerde:1", new BarrilVerde(mario.cuantificable.getPosx(), mario.cuantificable.getPosy(), 25, 25));
			Barril b2 = (Barril) (juego.getElementos().get("BarrilVerde:1"));
			b2.muevase(630);
			Thread.sleep(1000);
			assertTrue(vidas2 < mario.vidas);
		}catch(DonkeyPOOBException e) {
			fail("Lanzó excepción");
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Shot {
	double x;
	double y;
	double angulo;
	Entorno e;
	Image horse;

	
	public Shot(double x, double y, Entorno e) {
		this.x = x;
		this.y = y;
		this.horse = Herramientas.cargarImagen("horse.gif");
		this.e = e;
	}
	
	public void dibujar(Entorno entorno) {
			entorno.dibujarImagen(horse, x, y, this.angulo, 3);
		
	}
	public boolean estarEnEntorno() {
		if (!(this.x < -e.ancho()*0.2 || this.x > e.ancho()*1.2)) {
			return true;
		}
		return false;
	}
	
	public void movimiento() {
			this.x -= 15;

	}
	
}

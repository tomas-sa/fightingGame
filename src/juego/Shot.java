package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Shot {
	double x;
	double y;
	double angulo;
	Entorno e;
	Image shot;
	Image shot2;
	
	public Shot(double x, double y, Entorno e) {
		this.x = x;
		this.y = y;
		this.shot = Herramientas.cargarImagen("horse.gif");
		this.shot2 = Herramientas.cargarImagen("shot2.png");
		this.e = e;
	}
	
	public void dibujar(Entorno entorno, boolean direccion) {
		if(direccion) {
			entorno.dibujarImagen(shot, x, y, this.angulo, 3);
		}else {
			entorno.dibujarImagen(shot2, x, y, this.angulo, 3);
		}
		
	}
	public boolean estarEnEntorno() {
		if (!(this.x < -e.ancho()*0.2 || this.x > e.ancho()*1.2)) {
			return true;
		}
		return false;
	}
	
	public void movimiento(boolean direccion) {
		if(direccion) {
			this.x -= 15;
		}else {
			this.x += 15;
		}
		
	}
	
}

package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Bar {
	double x;
	double y;
	double angulo;
	Image bar;
	Entorno e;
	int vida;
	
	public void vidaContador() {
		if(vida == 9) {
			this.bar = Herramientas.cargarImagen("bar1.png");
		}else if(vida == 8) {
			this.bar = Herramientas.cargarImagen("bar2.png");
		}else if(vida == 7) {
			this.bar = Herramientas.cargarImagen("bar3.png");
		}
		else if(vida == 6) {
			this.bar = Herramientas.cargarImagen("bar4.png");
		}
		else if(vida == 5) {
			this.bar = Herramientas.cargarImagen("bar5.png");
		}
		else if(vida == 4) {
			this.bar = Herramientas.cargarImagen("bar6.png");
		}
		else if(vida == 3) {
			this.bar = Herramientas.cargarImagen("bar7.png");
		}
		else if(vida == 2) {
			this.bar = Herramientas.cargarImagen("bar8.png");
		}
		else {
			this.bar = Herramientas.cargarImagen("bar9.png");
		}
	}
	
	public Bar(double x, double y, Entorno e, int vida) {
		this.x = x;
		this.y = y;
		this.vida = vida;
		vidaContador();
		this.e = e;
	}
	
	public void dibujar(Entorno entorno) {
		entorno.dibujarImagen(bar, x, y, this.angulo, 1);
	}
}

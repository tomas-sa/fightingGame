package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Skill {
	double x;
	double y;
	double angulo;
	Image skill;
	Image skill2;
	Entorno e;
	
	public Skill(double x, double y, double angulo, Entorno e) {
		this.x = x;
		this.y = y;
		this.skill = Herramientas.cargarImagen("skill.gif");
		this.skill2 = Herramientas.cargarImagen("skill2.gif");
		this.e = e;
	}
	
	public void dibujar(Entorno entorno, boolean direccion) {
		if(direccion) {
			entorno.dibujarImagen(skill, x, y, this.angulo, 0.3);
		}else {
			entorno.dibujarImagen(skill2, x, y, this.angulo, 0.3);
		}
	}
	
	public void movimiento(boolean direccion) {
		if(direccion) {
			this.x +=15;
		}else {
			this.x -=15;
		}
	}
	
	public boolean estarEnEntorno() {
		if (!(this.x < -e.ancho()*0.2 || this.x > e.ancho()*1.2)) {
			return true;
		}
		return false;
	}
	
}

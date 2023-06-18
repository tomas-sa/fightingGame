package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Nito {
	double x;
	double y;
	double angulo;
	Image nito;
	Image nito2;
	Image nitoAtacando;
	Entorno e;
	
	public Nito(double x, double y, Entorno e) {
		this.x = x;
		this.y = y;
		this.angulo = 0;
		this.nito = Herramientas.cargarImagen("demon-idle.gif");
		this.nito2 = Herramientas.cargarImagen("demon-idle2.gif");
		this.nitoAtacando = Herramientas.cargarImagen("demon-attack.gif");
		this.e = e;
	}
	
	public void dibujar(Entorno entorno, boolean direccion, boolean atacando) {
		if(atacando) {
			entorno.dibujarImagen(nitoAtacando, x, y, 0, 1.5);
		}else {
			if(direccion) {
				entorno.dibujarImagen(nito, x, y, 0, 1.5);
			}else {
				entorno.dibujarImagen(nito2, x, y, 0, 1.5);
			}
		}
	}
	
	public void moverAdelante() {
		this.x = this.x - 4;
	}
	
	public void moverAtras() {
		this.x = this.x +4;
	}
	public void moverArriba() {
		this.y = this.y - 4;
	}
	
	public void moverAbajo() {
		this.y = this.y + 4;
	}
	
	public boolean estarEnEntorno() {
		if (this.y > 150 && this.y < e.alto() - 200 && this.x > 80 && this.x < e.ancho() -80) {
			return true;
		}
		return false;
	}
	
	
	
	public void mover() {
		this.x += Math.cos(this.angulo)*3;
		//this.y += Math.sin(this.angulo)*2;
	}
	
	public void cambiarAngulo(double x2, double y2){
		if(x2 < this.x) {
			this.angulo = Math.atan2(y2 - this.y, x2 - (this.x -100));
		}else {
			this.angulo = Math.atan2(y2 - this.y, x2 - (this.x +100));
		}
	}
	
	
	
}

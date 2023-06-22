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
	Image nitoAtacando2;
	Image nitoShield;
	Image nitoShield2;
	Image nitoHit;
	Image nitoHit2;
	Image nitoCaminando;
	Image nitoCaminando2;
	Image nitoDeath;
	Image nitoDeath2;
	Entorno e;
	
	public Nito(double x, double y, Entorno e) {
		this.x = x;
		this.y = y;
		this.angulo = 0;
		this.nito = Herramientas.cargarImagen("demon-idle.gif");
		this.nito2 = Herramientas.cargarImagen("demon-idle2.gif");
		this.nitoAtacando = Herramientas.cargarImagen("demon-attack.gif");
		this.nitoAtacando2 = Herramientas.cargarImagen("demon-attack2.gif");
		this.nitoShield = Herramientas.cargarImagen("shield.gif");
		this.nitoShield2 = Herramientas.cargarImagen("shield2.gif");
		this.nitoHit = Herramientas.cargarImagen("demonHit.gif");
		this.nitoHit2 = Herramientas.cargarImagen("demonHit2.gif");
		this.nitoCaminando = Herramientas.cargarImagen("demon-walk.gif");
		this.nitoCaminando2 = Herramientas.cargarImagen("demon-walk2.gif");
		this.nitoDeath = Herramientas.cargarImagen("deathEnemy.gif");
		this.nitoDeath2 = Herramientas.cargarImagen("deathEnemy2.gif");
		this.e = e;
	}
	
	public void dibujar(Entorno entorno, boolean direccion, boolean atacando, boolean cubriendo, boolean hit, boolean caminando, boolean death) {
		if(death) {
			if(direccion) {
				entorno.dibujarImagen(nitoDeath, x, (y +20), 0, 3.5);
			}else {
				entorno.dibujarImagen(nitoDeath2, x, (y +20), 0, 3.5);
			}
			
		}else if(cubriendo) {
			if(direccion) {
				entorno.dibujarImagen(nitoShield, x, (y +20), 0, 3.5);
			}else {
				entorno.dibujarImagen(nitoShield2, x, (y +20), 0, 3.5);
			}
		}else if(hit) {
			if(direccion) {
				entorno.dibujarImagen(nitoHit, x, y, 0, 3.5);
			}else{
				entorno.dibujarImagen(nitoHit2, x, y, 0, 3.5);
			}
			
		}else if(atacando) {
			if(direccion) {
				entorno.dibujarImagen(nitoAtacando, x, y, 0, 3.5);
			}else {
				entorno.dibujarImagen(nitoAtacando2, x, y, 0, 3.5);
			}
			
		}else if(caminando) {
			if(direccion) {
				entorno.dibujarImagen(nitoCaminando, x, y, 0, 3.5);
			}else {
				entorno.dibujarImagen(nitoCaminando2, x, y, 0, 3.5);
			}
		}else {
			if(direccion) {
				entorno.dibujarImagen(nito, x, y, 0, 3.5);
			}else {
				entorno.dibujarImagen(nito2, x, y, 0, 3.5);
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

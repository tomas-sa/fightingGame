package juego;

import java.awt.Image;
import java.util.Timer;
import java.util.TimerTask;

import entorno.Entorno;
import entorno.Herramientas;

public class Knight {
	double x;
	double y;
	double angulo;
	Image knight;
	Image knightRun;
	Image knightAttack;
	Image knightJump;
	Image knight2;
	Image knightRun2;
	Image knightAttack2;
	Image knightJump2;
	Image knightHit;
	Image knightHit2;
	Image cover;
	Entorno e;
	Timer temporizadorSalto;
	boolean saltando;
	boolean atacando;
	Timer timer = new Timer();
	
	public Knight(double x, double y, Entorno e) {
		this.x = x;
		this.y = y;
		this.knight = Herramientas.cargarImagen("knightParado.gif");
		this.knightRun = Herramientas.cargarImagen("knightCorriendo.gif");
		this.knightAttack = Herramientas.cargarImagen("knightAttack.gif");
		this.knightJump = Herramientas.cargarImagen("knightJump.gif");
		this.knight2 = Herramientas.cargarImagen("knightParado2.gif");
		this.knightRun2 = Herramientas.cargarImagen("knightCorriendo2.gif");
		this.knightAttack2 = Herramientas.cargarImagen("knightAttack2.gif");
		this.knightJump2 = Herramientas.cargarImagen("knightJump2.gif");
		this.knightHit = Herramientas.cargarImagen("hit.gif");
		this.knightHit2 = Herramientas.cargarImagen("hit2.gif");
		this.cover = Herramientas.cargarImagen("cover.gif");
		this.e = e;
		temporizadorSalto = new Timer();
        saltando = false;
	}
	
	public void dibujar(Entorno entorno, boolean direccion, boolean fighting, boolean golpeado) {
		if(fighting && !golpeado) {
			if ((entorno.estaPresionada(entorno.TECLA_IZQUIERDA) || entorno.estaPresionada(entorno.TECLA_DERECHA)) && !entorno.estaPresionada(entorno.TECLA_ARRIBA)) {
				if(direccion) {
					entorno.dibujarImagen(knightRun, x, y, this.angulo, 4);
				}else {
					entorno.dibujarImagen(knightRun2, x, y, this.angulo, 4);
				}
				
			}else if(entorno.estaPresionada(entorno.TECLA_CTRL)){
				
				entorno.dibujarImagen(cover, x, y, this.angulo, 4);
				
			}else if(entorno.sePresiono(entorno.TECLA_ESPACIO)){
				
				atacando = true;
				
				
				TimerTask task = new TimerTask() {
		            @Override
		            public void run() {
		            	atacando = false;
		            }
		        };
		        timer.schedule(task, 300);
				
			}else if(entorno.estaPresionada(entorno.TECLA_ARRIBA)) {
				if(direccion) {
					entorno.dibujarImagen(knightJump, x, y, angulo, 4);
				}else {
					entorno.dibujarImagen(knightJump2, x, y, angulo, 4);
				}
				
			}else {
				if(atacando) {
					if(direccion) {
						entorno.dibujarImagen(knightAttack, x, y, angulo, 4);
					}else {
						entorno.dibujarImagen(knightAttack2, x, y, angulo, 4);
					}
				}else {
					if(direccion) {
						entorno.dibujarImagen(knight, x, y, this.angulo, 4);
					}else {
						entorno.dibujarImagen(knight2, x, y, this.angulo, 4);
					}
				}
				
				
			}
		}else if(golpeado) {
			if(direccion) {
				entorno.dibujarImagen(knightHit, x, y, this.angulo, 4);
			}else {
				entorno.dibujarImagen(knightHit2, x, y, this.angulo, 4);
			}
			
		}
		else {
			entorno.dibujarImagen(knight, x, y, this.angulo, 4);
		}
	}
	
	public void moverAdelante() {
		this.x = this.x + 8;
	}
	
	public void moverAtras() {
		this.x = this.x + -8;
	}
	public void saltar(double k) {
		this.y = this.y - k;
	}
	public void bajarSalto() {
		this.y = this.y +10;
	}
	public boolean estarEnEntorno() {
		if (!(this.x < -e.ancho()*0.2 || this.x > e.ancho()*1.2)) {
			return true;
		}
		return false;
	}
}

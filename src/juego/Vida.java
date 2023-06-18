package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;

public class Vida {
	double x;
	double y;
	int alto;
	int ancho;
	Image barra;
	Entorno e;
	
	public Vida(int alto, int ancho, double y, int jugador, Entorno e) {
		if(jugador == 1) {
			this.x = (ancho / 2) +80;
		}else {
			this.x = (ancho / 2) + 840;
		}
		this.alto = alto;
		this.ancho = ancho;
		this.y = y;
		this.e = e;
	}
	
	public void dibujarse(Entorno entorno, int color) 
	{
		if(color == 1) {
			entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.red);
		}else {
			entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.green);
		}
		
		
	}
}

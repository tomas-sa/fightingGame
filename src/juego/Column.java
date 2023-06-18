package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Column {
	double x;
	double y;
	Image column;
	Entorno e;
	
	public Column(double x, double y, Entorno e){
		this.x = x;
		this.y = y;
		this.e = e;
		this.column = Herramientas.cargarImagen("column.png");
	}
	
	public void dibujar(Entorno entorno) {
			entorno.dibujarImagen(column, x, y, 0, 1);
		
	}

}

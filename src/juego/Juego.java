package juego;


import java.awt.Image;
import java.util.Timer;
import java.util.TimerTask;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	Image fondo;
	Image fondoInicio;
	Image titulo;
	Image graveyard;
	Image nitoTitulo;
	Image seed;
	Image flame;
	Image fondoFinal;
	Image tituloGameOver;
	Image prepareFight;
	Image lightning;
	Column column;
	Bar bar;
	Knight knight;
	Nito nito;
	Skill skill;
	Shot shot;
	Vida vida;
	Vida stamina;
	Vida poder;
	Vida vida2;
	Vida stamina2;
	boolean juegoNuevo;
	int vidaJugador;
	int staminaJugador;
	int poderJugador;
	int vidaNito;
	int staminaNito;
	boolean estarEnSuelo;
	boolean estarEnTecho;
	boolean enemigoSubiendo;
	boolean enemigoBajando;
	boolean nitoAtacando;
	boolean mirandoAdelante;
	boolean nitoAdelante;
	boolean enemigoCubriendo;
	boolean enemigoHit;
	boolean golpe;
	boolean caminando;
	boolean gameOver;
	boolean juegoTerminado;
	boolean fighting;
	boolean jugadorGolpeado;
	boolean poderEjecutando;
	double gravedad = 15;
	
	Timer timer = new Timer();
	
	

	// Variables y métodos propios de cada grupo
	// ...

	Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Fight Game", 1200, 600);
		this.fondo = Herramientas.cargarImagen("background.png");
		this.titulo = Herramientas.cargarImagen("DoomedSouls.png");
		this.fondoInicio = Herramientas.cargarImagen("fondoInicio.png");
		this.graveyard = Herramientas.cargarImagen("graveyard.png");
		this.nitoTitulo = Herramientas.cargarImagen("nitoName.png");
		this.seed = Herramientas.cargarImagen("seed.png");
		this.flame = Herramientas.cargarImagen("flame.gif");
		this.fondoFinal = Herramientas.cargarImagen("fondoFinal.png");
		this.tituloGameOver = Herramientas.cargarImagen("gameOver.png");
		this.prepareFight = Herramientas.cargarImagen("fighting.gif");
		this.lightning = Herramientas.cargarImagen("Lightning.gif");
		this.nitoAtacando = false;
		juegoNuevo = false;
		vidaNito = 300;
		vidaJugador = 300;
		poderJugador = 300;
		staminaJugador = 300;
		staminaNito = 300;
		knight = new Knight(350, 350, entorno);
		nito = new Nito(800, 420, entorno);
		bar = new Bar(600,550, entorno, vidaNito);
		column = new Column(250, 420, entorno);
		
		estarEnSuelo = true;
		estarEnTecho = false;
		enemigoSubiendo = true;
		enemigoBajando = false;
		mirandoAdelante = true;
		nitoAdelante = true;
		enemigoCubriendo = false;
		golpe = true;
		

		// Inicia el juego!
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y por lo
	 * tanto es el método más importante de esta clase. Aquí se debe actualizar el
	 * estado interno del juego para simular el paso del tiempo (ver el enunciado
	 * del TP para mayor detalle).
	 */
	public void tick() {
		
		vida = new Vida(20, vidaJugador, 50, 1, entorno);
		stamina = new Vida(10, staminaJugador, 75, 1, entorno);
		poder = new Vida(10, poderJugador, 100, 1, entorno);
		vida2 = new Vida(20, vidaNito, 50, 2, entorno);
		stamina2 = new Vida(10, staminaNito, 75,2 , entorno);
		
		if(juegoTerminado) {
			
			entorno.dibujarImagen(this.fondoFinal, 600, 300, 0, 1);
			entorno.dibujarImagen(this.tituloGameOver, 600, 300, 0, 1);
			
			if(entorno.sePresiono(entorno.TECLA_ESPACIO)) {
				knight = new Knight(350, 350, entorno);
				nito = new Nito(800, 420, entorno);
				column = new Column(250, 420, entorno);
				vidaNito = 300;
				vidaJugador = 300;
				staminaJugador = 300;
				poderJugador = 200;
				staminaNito = 300;
				juegoTerminado = false;
				
				
			}
		}else {
			if(!juegoNuevo) {
				entorno.dibujarImagen(this.fondoInicio, 600, 300, 0, 1);
				entorno.dibujarImagen(this.titulo, 600, 130, 0, 1);
				entorno.dibujarImagen(this.flame, 740, 380, 0, 1.6);
				if(entorno.sePresiono(entorno.TECLA_ENTER)) {
					juegoNuevo = true;
				}
			}else {

				entorno.dibujarImagen(this.fondo, 600, 300, 0, 3.1);
				entorno.dibujarImagen(this.graveyard, 600, 410, 0, 3.1);
				entorno.dibujarImagen(this.seed, 600, 530, 0, 1);
				//entorno.dibujarImagen(this.nitoTitulo, 600, 60, 0);
				if(!fighting) {
					entorno.dibujarImagen(this.prepareFight, 600, 250, 0, 1);
				}
				
				
				TimerTask taskFight = new TimerTask() {
		            @Override
		            public void run() {
		                fighting = true;
		            }
		        };
		        timer.schedule(taskFight, 1200);
				
				if(vidaNito <= 0 || vidaJugador <= 0) {
					juegoTerminado = true;
					juegoNuevo = false;
					fighting = false;
					knight = null;
					nito = null;
				}
				
				//movimiento de caballero
				
				if(knight != null && fighting && !jugadorGolpeado) {
					
					if (entorno.estaPresionada(entorno.TECLA_DERECHA)) {
						if(skill == null) {
							mirandoAdelante = true;
						}
			
						if(!(knight.x >= 1160)) {
							knight.moverAdelante();
						}
					}

					if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {
						if(skill == null) {
							mirandoAdelante = false;
						}
						
						if(!(knight.x <= 60)) {
							knight.moverAtras();
						}
					}
					
					//logica de salto
					
					
					if(entorno.estaPresionada(entorno.TECLA_ARRIBA) && estarEnSuelo == true && estarEnTecho == false) {
						
						if(knight.y <= 120) {
							estarEnTecho = true;
							estarEnSuelo = false;
							
						}else if(estarEnTecho == false) {
							gravedad -= 0.3;
							knight.saltar(gravedad);
						}
						if(knight.y >= 350) {
							gravedad = 12;
						}
						
					}else if(!entorno.estaPresionada(entorno.TECLA_ARRIBA)){
						if(knight.y <= 350) {
							if((knight.x > column.x - 60) && (knight.x < column.x +60)) {
								
							}else {
								estarEnSuelo = false;
								knight.bajarSalto();
							}
							
							
						}else {
							estarEnSuelo = true;
							estarEnTecho = false;
							gravedad = 15;
						}
						
					}else if(entorno.estaPresionada(entorno.TECLA_ARRIBA) && estarEnSuelo == false && knight.y < 350){
						if((knight.x > column.x - 60) && (knight.x < column.x +60)) {
							
						}else {
							knight.bajarSalto();
						}
						
					}
				}
				
				// disparar hechizo
				
				//if(entorno.sePresiono(entorno.TECLA_ESPACIO) && skill == null) {
				//	skill = new Skill(this.knight.x, this.knight.y + 50, 0, entorno);
				//}
				//if(skill != null && skill.estarEnEntorno()) {
				//	skill.dibujar(entorno, mirandoAdelante);
				//	skill.movimiento(mirandoAdelante);
				//}else {
				///	skill = null;
				///}
				//colision
				
				//if(skill != null && (skill.x >= nito.x -50 && skill.x <= nito.x +50 )) {
					
				//	if((skill.y <= nito.y && skill.y >= nito.y - 50) || (skill.y >= nito.y && skill.y <= nito.y + 80)) {
						
				//		skill = null;
				//		if(!(this.vidaNito <= 1)) {
				//			this.vidaNito -= 30;
				//			bar = null;
				//		}
				//	}
					
				//}
				if(knight != null && nito != null) {
					if(shot != null && (shot.x >= knight.x -100 && shot.x <= knight.x +100 )) {
						
						if((shot.y <= knight.y && shot.y >= knight.y - 50) || (shot.y >= knight.y && shot.y <= knight.y + 80)) {
							
							shot = null;
							
							if((!(this.vidaJugador <= 1) && !entorno.estaPresionada(entorno.TECLA_CTRL)) || staminaJugador < 50) {
								this.vidaJugador -= 30;
								
								if(poderJugador <= 300) {
									//NO ENTIENDO PORQUE NO SUMA
									this.poderJugador ++;
								}
							} else if(!(this.vidaJugador <= 1) && entorno.estaPresionada(entorno.TECLA_CTRL)) {
								this.staminaJugador -= 40;
							}
						}
						
					}
					//PODER FINAL
					if(poderJugador >= 200) {
						if(entorno.estaPresionada(entorno.TECLA_SHIFT)) {
							
							vidaNito -= 100;
							poderJugador = 20;
							poderEjecutando = true;
							enemigoHit = true;
							
							
						}
					}
					if(poderEjecutando) {
						entorno.dibujarImagen(lightning, nito.x, (nito.y -150), 0, 3.5);
						TimerTask taskPoder = new TimerTask() {
				            @Override
				            public void run() {
				                poderEjecutando = false;
				                enemigoHit = false;
				            }
				        };
				        timer.schedule(taskPoder, 1000);
					}
					
					// disparos de Nito
					
					
					
					//if(shot == null) {
						//shot = new Shot(this.nito.x - 30, this.nito.y, entorno);
					//}
					//if(shot != null && shot.estarEnEntorno()) {
					//	shot.dibujar(entorno, nitoAdelante);
					//	shot.movimiento(nitoAdelante);
					//}else {
					//	shot = null;
					//	if(nito.x > knight.x) {
					//		nitoAdelante = true;
					//	}else {
					//		nitoAdelante = false;
					//	}
					//}
					
					//nito.mover();
					//nito.cambiarAngulo(knight.x, knight.y);
					
					if(knight.x > nito.x) {
						nitoAdelante = false;
					}else {
						nitoAdelante = true;
					}
								
					//CONTROL DE STAMINA JUGADOR
					
					if(staminaJugador < 300 && !entorno.estaPresionada(entorno.TECLA_CTRL)) {
						staminaJugador ++;
					}
					
					//INTELIGENCIA ENEMIGO
					
					
					
					nito.cambiarAngulo(knight.x, knight.y);
					
					//if(knight.x >= nito.x -50 && knight.x <= nito.x +50 ) {
					//	vidaJugador --;
					//}
					
					if(nito.x >= knight.x -160 && nito.x <= knight.x +160 ) {
						caminando = false;
						
						if((nito.y <= knight.y && nito.y >= knight.y - 50) || (nito.y >= knight.y && nito.y <= knight.y + 80)) {
							
							nitoAtacando = true;
							TimerTask taskNito = new TimerTask() {
					            @Override
					            public void run() {
					                nitoAtacando = false;
					            }
					        };
					        timer.schedule(taskNito, 2000);
							
							if((!(this.vidaJugador <= 0) && !entorno.estaPresionada(entorno.TECLA_CTRL)) || staminaJugador < 50) {
								if(!enemigoCubriendo && !enemigoHit) {
									double random = Math.random();
									if(random > 0.8) {
										jugadorGolpeado = true;
										vidaJugador -=1;
										TimerTask taskGolpe = new TimerTask() {
								            @Override
								            public void run() {
								                jugadorGolpeado = false;
								            }
								        };
								        timer.schedule(taskGolpe, 1300);
									}
									
								}
									//if(golpe) {
									//	TimerTask taskGolpe = new TimerTask() {
									//		int count = 0;
									//           @Override
									//           public void run() {
									//        	   if(count < 5) {
									//        		   vidaJugador -=20;
									//        		   count ++;
									//        	   }else {
									//        		   timer.cancel();
									//        		   golpe = true;
									//        	   }
									//            }
									//        };
									//        startTimer(timer, taskGolpe);
									//        golpe = false;
									//}
								//}
								
								
							} else if(!(this.vidaJugador <= 1) && entorno.estaPresionada(entorno.TECLA_CTRL)) {
								this.staminaJugador -=2;
							}
							
							//defendiendo de ataques de jugador
							
							if(entorno.sePresiono(entorno.TECLA_ESPACIO) && !jugadorGolpeado) {
								double random = Math.random();
								
								if(random > 0.70) {
									enemigoCubriendo = true;
									 TimerTask task = new TimerTask() {
								            @Override
								            public void run() {
								                enemigoCubriendo = false;
								            }
								        };
								        timer.schedule(task, 2000);
								}
								if(!enemigoCubriendo || staminaNito <=10) {
									//para lograr animacion de golpe enemigo
									enemigoHit = true;
									vidaNito -= 20;
									this.poderJugador +=20;
									TimerTask task = new TimerTask() {
							            @Override
							            public void run() {
							                enemigoHit = false;
							            }
							        };
							        timer.schedule(task, 400);
									
								}else if(enemigoCubriendo) {
									if(staminaNito >= 0) {
										staminaNito-=20;
									}
									
								}
							}else {
								
								if(staminaNito < 300 && enemigoCubriendo == false) {
									staminaNito +=1;
								}
							}
							
						}
						//REVISAR ESTE ELSE YA QUE PUEDE QUE ESTA LINEA SEA INNECESARIA
						//linea que asegura que enemigo deje de cubrirse cuando jugador se aleja
					}else {
						if(fighting) {
							caminando = true;
							nito.mover();
							enemigoCubriendo = false;
							if(staminaNito < 300) {
								staminaNito +=1;
							}
						}
						
					}
					
					
					
					//INICIANDO OBJETOS
					
					knight.dibujar(entorno, mirandoAdelante, fighting, jugadorGolpeado);
					nito.dibujar(entorno, nitoAdelante, nitoAtacando, enemigoCubriendo, enemigoHit, caminando);
					//bar.dibujar(entorno);
					column.dibujar(entorno);
					vida.dibujarse(entorno, 1);
					stamina.dibujarse(entorno, 2);
					poder.dibujarse(entorno, 3);
					vida2.dibujarse(entorno, 1);
					stamina2.dibujarse(entorno, 2);
				}
				
				
			}
			}
		}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}

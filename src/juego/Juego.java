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
	Image pressSpace;
	Image lightning;
	Image spaceGif;
	Image spaceKey;
	Image shiftKey;
	Image ctrlKey;
	Image arrowDown;
	Image arrowLeft;
	Image arrowRight;
	Image arrowUp;
	Image attackTag;
	Image ultimateTag;
	Image shieldTag;
	Image movementTag;
	Image pressEnter;
	Image youWin;
	Image youreDead;
	Image playAgain;
	Image playAgainSelect;
	Image exit;
	Image exitSelect;
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
	Vida poder2;
	boolean juegoNuevo;
	int vidaJugador;
	int staminaJugador;
	int poderJugador;
	int vidaNito;
	int staminaNito;
	int poderNito;
	int selected;
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
	boolean controles;
	boolean deathEnemy;
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
		this.pressSpace = Herramientas.cargarImagen("pressSpace.png");
		this.spaceGif = Herramientas.cargarImagen("spaceGif.gif");
		this.lightning = Herramientas.cargarImagen("Lightning.gif");
		this.spaceKey = Herramientas.cargarImagen("SPACE.gif");
		this.shiftKey = Herramientas.cargarImagen("SHIFT.gif");
		this.ctrlKey = Herramientas.cargarImagen("CTRL.gif");
		this.arrowDown = Herramientas.cargarImagen("ARROWDOWN.gif");
		this.arrowLeft = Herramientas.cargarImagen("ARROWLEFT.gif");
		this.arrowRight = Herramientas.cargarImagen("ARROWRIGHT.gif");
		this.arrowUp = Herramientas.cargarImagen("ARROWUP.gif");
		this.attackTag = Herramientas.cargarImagen("attackTag.png");
		this.ultimateTag = Herramientas.cargarImagen("ultimateTag.png");
		this.shieldTag = Herramientas.cargarImagen("shieldTag.png");
		this.movementTag = Herramientas.cargarImagen("movementTag.png");
		this.pressEnter = Herramientas.cargarImagen("pressEnter.png");
		this.youWin = Herramientas.cargarImagen("youWin.png");
		this.youreDead = Herramientas.cargarImagen("youreDead.png");
		this.playAgain = Herramientas.cargarImagen("playAgain.png");
		this.playAgainSelect = Herramientas.cargarImagen("playAgainSelect.png");
		this.exit = Herramientas.cargarImagen("exit.png");
		this.exitSelect = Herramientas.cargarImagen("exitSelect.png");
		this.nitoAtacando = false;
		juegoNuevo = false;
		controles = false;
		vidaNito = 300;
		vidaJugador = 300;
		poderJugador = 50;
		staminaJugador = 300;
		staminaNito = 300;
		poderNito = 50;
		this.selected = 1;
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
		deathEnemy = false;
		

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
		poder2 = new Vida(10, poderNito, 100, 2, entorno);
		
		if(juegoTerminado) {
			if(vidaJugador >= vidaNito) {
				entorno.dibujarImagen(this.fondoFinal, 600, 300, 0, 1);
				entorno.dibujarImagen(this.youWin, 600, 220, 0, 1);
			}else {
				entorno.dibujarImagen(this.fondoFinal, 600, 300, 0, 1);
				entorno.dibujarImagen(this.youreDead, 600, 220, 0, 1);
			}
			
			if(selected == 1) {
				entorno.dibujarImagen(this.playAgainSelect, 600, 340, 0, 1);
				entorno.dibujarImagen(this.exit, 600, 400, 0, 1);
			}else {
				entorno.dibujarImagen(this.playAgain, 600, 340, 0, 1);
				entorno.dibujarImagen(this.exitSelect, 600, 400, 0, 1);
			}
			
			if(entorno.sePresiono(entorno.TECLA_ABAJO) && selected != 2) {
				selected ++;
			}else if(entorno.sePresiono(entorno.TECLA_ARRIBA) && selected != 1) {
				selected --;
			}
			
			
			if(entorno.sePresiono(entorno.TECLA_ENTER) && selected == 1) {
				knight = new Knight(350, 350, entorno);
				nito = new Nito(800, 420, entorno);
				column = new Column(250, 420, entorno);
				vidaNito = 300;
				vidaJugador = 300;
				staminaJugador = 300;
				poderJugador = 50;
				staminaNito = 300;
				poderNito = 50;
				juegoTerminado = false;
			}else if(entorno.sePresiono(entorno.TECLA_ENTER) && selected == 2) {
				System.exit(0);
			}
		}else {
			if(!juegoNuevo && !controles) {
				entorno.dibujarImagen(this.fondoInicio, 600, 300, 0, 1);
				entorno.dibujarImagen(this.titulo, 600, 130, 0, 1);
				entorno.dibujarImagen(this.flame, 740, 380, 0, 1.6);
				if(this.pressSpace != null) {
					entorno.dibujarImagen(this.pressSpace, 600, 230, 0, 1);
				}else {
					entorno.dibujarImagen(this.spaceGif, 600, 230, 0, 1);
				}
				if(entorno.sePresiono(entorno.TECLA_ESPACIO)) {
					this.pressSpace = null;
					TimerTask taskInicio = new TimerTask() {
			            @Override
			            public void run() {
			                controles = true;
			            }
			        };
			        timer.schedule(taskInicio, 1000);
					
				}
			}else if(controles && !juegoNuevo) {
				entorno.dibujarImagen(spaceKey, 200, 100, 0, 3);
				entorno.dibujarImagen(shiftKey, 200, 250, 0, 3);
				entorno.dibujarImagen(ctrlKey, 200, 400, 0, 3);
				
				entorno.dibujarImagen(arrowDown, 950, 340, 0, 3);
				entorno.dibujarImagen(arrowLeft, 850, 340, 0, 3);
				entorno.dibujarImagen(arrowRight, 1050, 340, 0, 3);
				entorno.dibujarImagen(arrowUp, 950, 250, 0, 3);
				
				entorno.dibujarImagen(attackTag, 200, 160, 0, 1);
				entorno.dibujarImagen(ultimateTag, 200, 320, 0, 1);
				entorno.dibujarImagen(shieldTag, 200, 480, 0, 1);
				entorno.dibujarImagen(movementTag, 950, 420, 0, 1);
				
				entorno.dibujarImagen(pressEnter, 600, 80, 0, 0.8);
				
				if(entorno.sePresiono(entorno.TECLA_ENTER)) {
					juegoNuevo = true;
				}
			}
			else {

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
				
				if(vidaNito <= 0) {
					deathEnemy = true;
					

					TimerTask taskDeath = new TimerTask() {
			            @Override
			            public void run() {
			            	juegoTerminado = true;
			                juegoNuevo = false;
							fighting = false;
							knight = null;
							nito = null;
							controles = false;
							deathEnemy = false;
							
			            }
			        };
			        timer.schedule(taskDeath, 1800);
					this.pressSpace = Herramientas.cargarImagen("pressSpace.png");
					
				}else if(vidaJugador <= 0) {
					
					juegoTerminado = true;
			        juegoNuevo = false;
					fighting = false;
					knight = null;
					nito = null;
					controles = false;
					this.pressSpace = Herramientas.cargarImagen("pressSpace.png");
					
					
					//deathEnemy = true;

					//TimerTask taskDeath = new TimerTask() {
			        //    @Override
			        //    public void run() {
			        //    	juegoTerminado = true;
			        //      juegoNuevo = false;
					//		fighting = false;
					//		knight = null;
					//		nito = null;
					//		controles = false;
					//		
			        //    }
			        //};
			        //timer.schedule(taskDeath, 1800);
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
								if(knight.y < 130) {
									knight.bajarSalto();
								}
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
				
				
				if(knight != null && nito != null) {
					if(shot != null && (shot.x >= knight.x -100 && shot.x <= knight.x +100 )) {
						
						if((shot.y <= knight.y && shot.y >= knight.y - 50) || (shot.y >= knight.y && shot.y <= knight.y + 80)) {
							
							
							if((!(this.vidaJugador <= 1) && !entorno.estaPresionada(entorno.TECLA_CTRL)) || staminaJugador < 50) {
								this.vidaJugador -=5;
								
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
					
					
					
					if(shot == null && poderNito > 200 && vidaNito < 200) {
						double random = Math.random();
						if(random > 0.98) {
							shot = new Shot(1300, 350, entorno);
						}
					}
					if(shot != null && shot.estarEnEntorno()) {
						poderNito = 20;
						shot.dibujar(entorno);
						shot.movimiento();
					}else {
						shot = null;
						if(nito.x > knight.x) {
							nitoAdelante = true;
						}else {
							nitoAdelante = false;
						}
					}
					
					
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
								if(!enemigoCubriendo && !enemigoHit && !deathEnemy) {
									double random = Math.random();
									if(random > 0.8) {
										jugadorGolpeado = true;
										vidaJugador -=1;
										if(poderNito <= 300) {
											this.poderNito +=2;
										}
										
										TimerTask taskGolpe = new TimerTask() {
								            @Override
								            public void run() {
								                jugadorGolpeado = false;
								            }
								        };
								        timer.schedule(taskGolpe, 1300);
									}
									
								}
									
								
								
							} else if(!(this.vidaJugador <= 1) && entorno.estaPresionada(entorno.TECLA_CTRL)) {
								this.staminaJugador -=2;
							}
							
							//defendiendo de ataques de jugador
							
							if(entorno.sePresiono(entorno.TECLA_ESPACIO) && !jugadorGolpeado) {
								double random = Math.random();
								double randomTimer = Math.random();
								randomTimer = randomTimer * 10000;
								
								int number = (int)randomTimer;
								
								if(random > 0.70) {
									enemigoCubriendo = true;
									 TimerTask task = new TimerTask() {
								            @Override
								            public void run() {
								                enemigoCubriendo = false;
								            }
								        };
								        timer.schedule(task, number);
								}
								if(!enemigoCubriendo || staminaNito <=20) {
									//para lograr animacion de golpe enemigo
									enemigoHit = true;
									vidaNito -= 20;
									if(poderJugador <= 280) {
										this.poderJugador +=20;
									}
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
						if(fighting && !poderEjecutando && !deathEnemy) {
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
					nito.dibujar(entorno, nitoAdelante, nitoAtacando, enemigoCubriendo, enemigoHit, caminando, deathEnemy);
					//bar.dibujar(entorno);
					column.dibujar(entorno);
					vida.dibujarse(entorno, 1);
					stamina.dibujarse(entorno, 2);
					poder.dibujarse(entorno, 3);
					vida2.dibujarse(entorno, 1);
					stamina2.dibujarse(entorno, 2);
					poder2.dibujarse(entorno, 3);
				}
				
				
			}
			}
		}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}

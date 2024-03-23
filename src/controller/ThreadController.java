package controller;

import java.util.concurrent.Semaphore;

public class ThreadController extends Thread {
    private int idCarro, escudeira, corrida;
	private static int indice = 0, controle = 0;;
    private Semaphore semaforo;
	private static double[] rank = new double[42];
    private static Semaphore semaforo1 = new Semaphore(1), semaforo2 = new Semaphore(1), semaforo3 = new Semaphore(1), semaforo4 = new Semaphore(1), semaforo5 = new Semaphore(1),semaforo6 = new Semaphore(1), semaforo7 = new Semaphore(1);
    private double tempoini, tempofim , total;
    private static double cont = 1;
    public ThreadController(int idCarro, Semaphore semaforo) {
        super();
        this.idCarro = idCarro;
        this.semaforo = semaforo;
    }

    @Override
    public void run() {
        Escudeira();
        switch (escudeira) {
        case 1:
        	try {
				semaforo1.acquire();
				Espera();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaforo1.release();
				System.out.println("Escudeira 1 liberada\n");
			}
        	break;
        case 2:
        	try {
				semaforo2.acquire();
				Espera();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaforo2.release();
				System.out.println("Escudeira 2 liberada\n");
			}
        	break;
        case 3:
        	try {
				semaforo3.acquire();
				Espera();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaforo3.release();
				System.out.println("Escudeira 3 liberada\n");
			}
        	break;
        case 4:
        	try {
				semaforo4.acquire();
				Espera();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaforo4.release();
				System.out.println("Escudeira 4 liberada\n");
			}
        	break;
        case 5:
        	try {
				semaforo5.acquire();
				Espera();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaforo5.release();
				System.out.println("Escudeira 5 liberada\n");
			}
        	break;
        case 6:
        	try {
				semaforo6.acquire();
				Espera();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaforo6.release();
				System.out.println("Escudeira 6 liberada\n");
			}
        	break;
        case 7:
        	try {
				semaforo7.acquire();
				Espera();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaforo7.release();
				System.out.println("Escudeira 7 liberada\n");
			}
        	break;
        }

		if (indice == 42) {
			Rank();
		}
    }

    private void Rank() {
		double aux;
		for (int i = 0; i < rank.length; i++) {
			for (int j = 0; j < rank.length - 1; j++) {
				if (rank[j] > rank[j + 1]) {
					aux = rank[j];
					rank[j] = rank[j + 1];
					rank[j + 1] = aux;
				}
			}
		}
		for (int i = 0; i < rank.length; i++) {
			System.out.println((i+1) + "° melhor volta teve o tempo de: " + rank[i] + " segundos");
		}
	}

	private void Escudeira() {
        escudeira = (int)cont;
        cont += 0.5;
    }
    
    private void Espera() {
    	try {
			semaforo.acquire();
			Corrida();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
			System.out.println("Pista liberada\n");
		}
    }

    private void Corrida() {
        System.out.println("O carro " + idCarro + " da escudeira " + escudeira + " começou a correr\n");
        for (int i = 0; i < 3; i++) {
            corrida = 0;
			tempoini = System.nanoTime();
            while (corrida < 500) {
                corrida += (int)(Math.random() * 3001) + 1000;
                System.out.println("O carro " + idCarro + " da escudeira " + escudeira + " correu " + corrida + " metros\n");
                try {
                    sleep ((long) ((Math.random() * 1000) + 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
			tempofim = System.nanoTime();
        total = tempofim - tempoini;
		total = total / Math.pow(10, 9);
		rank[indice] = total;
		indice++;
            System.out.println("O carro " + idCarro + " da escudeira " + escudeira + " terminou a " + (i + 1) + "° volta em um tempo de " + total + " segundos\n");
        }
        System.out.println("O carro " + idCarro + " da escudeira " + escudeira + " terminou as 3 voltas\n");
		controle++;
    }
}
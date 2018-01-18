package org.ufla.multithreadedwebserver.concurrent;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

/**
 * Implementação de uma fila encadeada, onde suas operações são realizadas de
 * forma sincronizada para que possa operar com múltiplas threads. Esta
 * implementação segue o modelo produtor/consumidor de forma que quando a fila
 * atinge a capacidade máxima os produtores são impedidos de produzir até que
 * consumidores liberem espaço na fila. Os consumidores, por sua vez podem
 * consumir desde que tenha itens a ser consumido na fila, se a fila estiver
 * vazia os consumidores devem esperar até que um produtor produza um item.
 * 
 * @author andre
 * @author caio
 * @author carlos
 *
 * @param <E>
 *            tipo genérico dos objetos da fila.
 */
public class MyBlockingQueueImp<E> implements MyBlockingQueue<E> {

	/**
	 * Fila interna não sincronizada.
	 */
	private Queue<E> queue;
	/**
	 * Semáforo que controla a quantidade de itens produzidos. A cada item produzido
	 * insere um passe no semáforo e antes de consumir um item remove um passe.
	 */
	private Semaphore fillCount;
	/**
	 * Semáforo que controla a quantidade de espaço na fila. Antes de produzir cada
	 * item remove um passe do semáforo e a cada item consumido inseri um passe.
	 */
	private Semaphore emptyCount;

	public MyBlockingQueueImp() {
		queue = new LinkedList<>();
		emptyCount = new Semaphore(Integer.MAX_VALUE);
		fillCount = new Semaphore(0);
	}

	/**
	 * Construtor definindo a capacidade máxima da fila.
	 * 
	 * @param capacity
	 *            capacidade máxima da fila.
	 */
	public MyBlockingQueueImp(int capacity) {
		this();
		emptyCount = new Semaphore(capacity);
	}

	/**
	 * Insere um elemento na fila de forma sincronizada. Caso a fila esteja cheia
	 * (capacidade máxima), espera até que um elemento seja consumido.
	 * 
	 * @param elem
	 *            elemento a ser inserido.
	 */
	public void put(E elem) {
		try {
			emptyCount.acquire();
			synchronized (queue) {
				queue.offer(elem);
			}
			fillCount.release();
		} catch (InterruptedException e) {
			System.out.println("InterruptedException caught");
		}
	}

	/**
	 * Recupera um elemento da fila de forma sincronizada. Caso não tenha elementos
	 * na fila, espera até que um elemento seja produzido.
	 * 
	 * @return elemento a ser recuperado da fila.
	 */
	public E take() {
		E elem = null;
		try {

			fillCount.acquire();
			synchronized (queue) {
				elem = queue.poll();
			}
			emptyCount.release();
		} catch (InterruptedException e) {
			System.out.println("InterruptedException caught");
		}
		return elem;
	}

}

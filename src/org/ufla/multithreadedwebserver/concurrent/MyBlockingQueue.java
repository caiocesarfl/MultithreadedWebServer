package org.ufla.multithreadedwebserver.concurrent;

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
public interface MyBlockingQueue<E> {

	/**
	 * Insere um elemento na fila de forma sincronizada. Caso a fila esteja cheia
	 * (capacidade máxima), espera até que um elemento seja consumido.
	 * 
	 * @param elem
	 *            elemento a ser inserido.
	 */
	void put(E elem);

	/**
	 * Recupera um elemento da fila de forma sincronizada. Caso não tenha elementos
	 * na fila, espera até que um elemento seja produzido.
	 * 
	 * @return elemento a ser recuperado da fila.
	 */
	E take();

}

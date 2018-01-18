package org.ufla.multithreadedwebserver.concurrent;

import java.util.concurrent.Executor;

/**
 * Utiliza um pool de threads para executar tarefas que são submetidas para o
 * objeto.
 * 
 * @author andre
 * @author caio
 * @author carlos
 *
 */
public class MyThreadPoolExecutor implements Executor {

	/**
	 * Fila de tarefas. Fila sincronizada que implementa o modelo
	 * produtor/consumidor.
	 */
	private MyBlockingQueue<Runnable> queue;
	/**
	 * Array de threads que formam o pool de threads
	 */
	private Thread[] threadPool;

	/**
	 * Cria um MyThreadPoolExecutor com um determinado tamanho do pool de threads e
	 * uma determinada capacidade da fila de tarefas.
	 * 
	 * @param poolSize
	 *            tamanho do pool de threads
	 * @param queueCapacity
	 *            capacidade da fila de tarefas
	 */
	public MyThreadPoolExecutor(int poolSize, int queueCapacity) {
		queue = new MyBlockingQueueImp<>(queueCapacity);
		threadPool = new Thread[poolSize];
		MyThreadPoolRunnable threadRunnable = new MyThreadPoolRunnable(queue);
		for (int i = 0; i < poolSize; i++) {
			threadPool[i] = new Thread(threadRunnable, "thread_" + i);
			threadPool[i].start();
		}
	}

	/**
	 * Enfileira uma determinada tarefa na fila de tarefas para ser executado por
	 * alguma thread do pool de threads.
	 * 
	 * @param command
	 *            tarefa a ser enfileirada.
	 */
	@Override
	public void execute(Runnable command) {
		queue.put(command);
	}

}

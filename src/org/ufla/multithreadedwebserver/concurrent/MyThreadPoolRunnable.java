package org.ufla.multithreadedwebserver.concurrent;

/**
 * Responsável por representar o comportamento de execução das threads que devem
 * executar tarefas de uma determinada fila de tarefas. O comportamento de
 * execução é sempre que a fila de tarefas não estiver vazia, remover uma tarefa
 * e executa-lá. Quando não houver tarefas na fila, espera pela inserção de
 * tarefas na fila, para remover e executa-lá.
 * 
 * @author andre
 * @author caio
 * @author carlos
 *
 */
public class MyThreadPoolRunnable implements Runnable {

	/**
	 * Fila de tarefas
	 */
	private final MyBlockingQueue<Runnable> queue;

	/**
	 * Constrói o MyThreadPoolRunnable para uma determinada fila de tarefas.
	 * 
	 * @param queue
	 *            fila de tarefas
	 */
	MyThreadPoolRunnable(MyBlockingQueue<Runnable> queue) {
		this.queue = queue;
	}

	/**
	 * Implementação do comportamento de execução de threads responsáveis por
	 * executar as tarefas de uma determinada fila de tarefas.
	 */
	@Override
	public void run() {
		while (true) {
			Runnable command = queue.take();
			command.run();
		}
	}
}
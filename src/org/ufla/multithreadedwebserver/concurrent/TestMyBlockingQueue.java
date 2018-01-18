package org.ufla.multithreadedwebserver.concurrent;

public class TestMyBlockingQueue {

	public static void main(String args[]) {
		// creating buffer queue
		MyBlockingQueueImp<Integer> q = new MyBlockingQueueImp<Integer>(5);

		// starting consumer thread
		new Consumer(q);
		new Consumer(q);
		new Consumer(q);
		new Consumer(q);
		new Producer(q);
		new Producer(q);
		// starting producer thread

	}

}

// Producer class
class Producer implements Runnable {
	static int id = 0;
	static int MAX = 10;
	int myid;
	MyBlockingQueueImp<Integer> q;

	Producer(MyBlockingQueueImp<Integer> q) {
		this.q = q;
		myid = id++;
		new Thread(this, "Producer_" + myid).start();
	}

	public void run() {
		for (int i = myid * MAX; i < (myid + 1) * MAX; i++) {
			// producer put items
			q.put(i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

// Consumer class
class Consumer implements Runnable {
	static int id = 0;
	MyBlockingQueueImp<Integer> q;

	Consumer(MyBlockingQueueImp<Integer> q) {
		this.q = q;
		new Thread(this, "Consumer_" + id).start();
		id++;
	}

	public void run() {
		for (int i = 0; i < 5; i++) {
			// consumer get items
			q.take();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

package parkingHouse;

import java.util.LinkedList;

/**
 * This class is the waiting queue when the cars are waiting for
 * access to the paringHouse. 
 * @author Oscar Arréhn Ågren
 *
 */
public class CarEnter extends Thread{
	private LinkedList<Car> entranceQueue;
	private int queueLength;
	
	/**
	 * Constructor
	 * @param queueLength
	 */
	public CarEnter(int queueLength) {
		this.entranceQueue = new LinkedList<Car>();
		this.queueLength = queueLength;
	}
	
	/**
	 * When a want to enter the parkingHouse they have to wait in line first.
	 * @param car - the car who wants to enter the parking lot
	 * @return true if the queue is not full, else false.
	 */
	public synchronized boolean enterEntranceQueue(Car car) {
		if(entranceQueue.size() < queueLength) {
			entranceQueue.add(car);
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * This is the thread starting method that puts 
	 * one car at the time in to the parking house as 
	 * long as it is not full.
	 */
	public void run() {
		while(true) {
			if(entranceQueue.isEmpty()) {
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}else{
				entranceQueue.removeFirst().releaseeEnterQueue();;
			}
		}
	}
}

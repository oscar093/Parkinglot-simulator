package parkingHouse;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Drives to the parking house looking for a space.
 * @author Oscar Arréhn Ågren
 * 
 */
public class Car implements Runnable {
	private String carName;
	private int regNbr;
	private int[] pHousePosition;
	private CarEnter carEnter;
	private Semaphore enterQueue;
	private ParkingHouse pHouse;

	/**
	 * Constructor
	 * @param name
	 * @param regNbr
	 * @param carEnter
	 * @param pHouse
	 */
	public Car(String name, int regNbr, CarEnter carEnter, ParkingHouse pHouse) {
		this.carName = name;
		this.regNbr = regNbr;
		this.carEnter = carEnter;
		this.pHouse = pHouse;
	}

	/**
	 * Automatically called when car thread is started.
	 */
	@Override
	public void run() {
		drive();
	}

	/**
	 * Car starts and tries to find parkinglot.
	 */
	private void drive() {
		try {
			if (carEnter.enterEntranceQueue(this)) {
				enterQueue = new Semaphore(0);
				enterQueue.acquire();
				this.pHousePosition = pHouse.getParkingSpace(this);
				if (pHousePosition[0] > 0) {
					Long parkingTime = (long) (Math.random() * 3.0);
					TimeUnit.SECONDS.sleep(parkingTime);
					pHouse.leavePHouse(pHousePosition, this);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @return name of the car
	 */
	public String getCarName() {
		return this.carName;
	}

	/**
	 * 
	 * @return registered number of the car.
	 */
	public int getRegNbr() {
		return this.regNbr;
	}
	
	/**
	 * If there is place in the parking queue this should be released.
	 */
	public void releaseeEnterQueue() {
		enterQueue.release();
	}
	
	/**
	 * 
	 * @return the parkinglot position.
	 */
	public int[] getPHousePosition() {
		return this.pHousePosition;
	}
}
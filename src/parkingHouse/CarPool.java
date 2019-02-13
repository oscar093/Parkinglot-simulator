package parkingHouse;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * CarPool starts the cars(Runnables) using ThreadPoolExecutor class. The CarPool then maintain 
 * a fixed number of threads to handle the runnables. A new car is created at a random time interval within a second. 
 * 
 * @author Oscar Arréhn Ågren
 *
 */
public class CarPool extends Thread {
	ThreadPoolExecutor carExecutor;
	private int regNbr;
	private CarEnter carEnter;
	private ParkingHouse pHouse;
	private Random rand = new Random();
	
	/**
	 * Constructor
	 * @param threads - Number of threads that will be used to handle the cars.
	 * @param carEnter
	 * @param pHouse
	 */
	public CarPool(int threads, CarEnter carEnter, ParkingHouse pHouse) {
		carExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(threads);
		regNbr = 0;
		this.carEnter = carEnter;
		this.pHouse = pHouse;
	}
	
	/**
	 * Since CarPool is a thread it is started at this method. Here new cars are created 
	 * at a random interval within a second. The threads are executed using a ThreadPoolExecutor.
	 */
	public void run() {
		while(true) {
			Car car = new Car(generateName(), getRegNbr(), carEnter, pHouse);
			carExecutor.execute(car);
			try {
				Thread.sleep(rand.nextInt(500));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * This methon generates a name from a list, so that it will be a tiny bit more 
	 * enjoyable to watch the output. 
	 * @return name of the car
	 */
	public String generateName() {
		int nbr = rand.nextInt(6);
		switch(nbr){
		case 0 :
			return "Mazda";
		case 1 :
			return "Honda";
		case 2: 
			return "Mitsubishi";
		case 3 : 
			return "Volvo";
		case 4 : 
			return "Saab";
		case 5 : 
			return "Opel";
		}
		return "";
	}
	
	/**
	 * Makes sure that every car get a unique id.
	 * @return number to separate the cars from each other.
	 */
	public int getRegNbr() {
		return regNbr++;
	}
}

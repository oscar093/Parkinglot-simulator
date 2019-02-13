package parkingHouse;

/**
 * Main class starts the whole thing. 
 * @author Oscar Arréhn Ågren
 *
 */
public class Main {

	public static void main(String[] args) {
		ParkingHouse pHouse = new ParkingHouse(5,2);
		CarEnter carEnter = new CarEnter(10);
		CarPool carPool = new CarPool(50, carEnter, pHouse);
		carEnter.start();
		carPool.start();
	}
}

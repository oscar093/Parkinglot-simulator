package parkingHouse;

/**
 * This is the parking house. Here car can find parkinglots as soon as they have passed the entranceQueue.
 * @author Oscar Arréhn Ågren
 *
 */
public class ParkingHouse {
	private boolean[][] parkingSpaces;
	private int rows;
	private int columns;
	
	/**
	 * Constructor
	 * The amound of parkinglots is the number of rows times the number of columns.
	 * @param rows
	 * @param columns
	 */
	public ParkingHouse(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		if(sumOfSpaces() > 0 && sumOfSpaces() < 2000) {
			parkingSpaces  = new boolean[rows][columns];
		}else {
			System.out.println("To many or to few parking spaces");
			return;
		}
		
	}
	
	/**
	 * Lets the car drive through the parking to se if there is any parkinglots available.
	 * @param car
	 * @return
	 */
	public  synchronized int[] getParkingSpace(Car car) {
		int[] position = {-1, -1};

		for(int i = 0; i < this.rows; i++) {
			for(int j = 0; j < this.columns; j++) {
				if(!parkingSpaces[i][j]) {
					position[0] = i;
					position[1] = j;
					parkingSpaces[i][j] = true;
					System.out.println("+++++++In: " + car.getCarName() + " " + car.getRegNbr());
					return position;
				}
			}
		}
		System.out.println("| FULL |: " + car.getCarName() + " " + car.getRegNbr());
		return position;
	}
	
	/**
	 * 
	 * @return total amount of parking spaces.
	 */
	public int sumOfSpaces() {
		return rows*columns;
	}
	
	/**
	 * When a car is done parking it calls this method so that another car can park on the parking space.
	 * @param position
	 * @param car
	 */
	public synchronized void leavePHouse(int[] position, Car car) {
		parkingSpaces[position[0]][position[1]] = false;
		System.out.println("-----out: " + car.getCarName() + " " + car.getRegNbr());
	}
}

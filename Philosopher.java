import java.util.Random;

public class Philosopher extends Thread {

	private static final String THINK = "Philosopher %d thinks for %d time units";
	private static final String GET_RIGHT = "Philosopher %d goes for right fork";
	private static final String HAS_RIGHT = "Philosopher %d has right fork";
	private static final String GET_LEFT = "Philosopher %d goes for left fork";
	private static final String HAS_LEFT = "Philosopher %d has left fork";
	private static final String EAT = "Philosopher %d eats for %d time units";
	private static final String RELEASE_RIGHT = "Philosopher %d releases right fork";
	private static final String RELEASE_LEFT = "Philosopher %d releases left fork";

	private int id;
	private Fork left;
	private Fork right;
	private boolean rHanded;
	private int nTimes; // 0 means run forever
	private long thinknMillis; // maximum think time per cycle
	private long eatMillis; // maximum eat time per cycle

	public Philosopher(int id, Fork left, Fork right, boolean rHanded, int nTimes, long thiknMillis, long eatMillis) {
		this.id = id;
		this.left = left;
		this.right = right;
		this.rHanded = rHanded;
		this.nTimes = nTimes;
		this.thinknMillis = thinknMillis;
		this.eatMillis = eatMillis;
	}

	public void run() {
		Random gen = new Random();
		for(int i = 1; i != nTimes; ++i) { // if nTimes is 0 (or less) this will run forever. (by design)
			// think
			int t = thinknMillis == 0 ? 0 : gen.nextInt((int)thinknMillis);
			System.out.println(String.format(THINK, id, t));
			try {
				sleep(t);
			} catch(InterruptedException e) {}
			
			// get forks
			if(rHanded) getRight();
			Thread.yield();
			getLeft();
			Thread.yield();
			if(!rHanded) getRight();
			
			// eat
			t = eatMillis == 0 ? 0 : gen.nextInt((int)eatMillis);
			System.out.println(String.format(EAT, id, t));
			try {
				sleep(t);
			} catch(InterruptedException e) {}
			
			// release forks
			right.release();
			System.out.println(String.format(RELEASE_RIGHT, id));
			left.release();
			System.out.println(String.format(RELEASE_LEFT, id));
		}
	}
		
	private void getRight() {
		System.out.println(String.format(GET_RIGHT, id));
		right.acquire();
		System.out.println(String.format(HAS_RIGHT, id));
	}
	
	private void getLeft() {
		System.out.println(String.format(GET_LEFT, id));
		left.acquire();
		System.out.println(String.format(HAS_LEFT, id));
	}
}

public class Driver {
	public static void main(String[] args) {
		//not necessary to initialize them with defaults, but w/e 
		int np = 4;
		int nt = 10;
		long tm = 0;
		long em = 0;
		boolean handedness = false;

		if (args.length > 3) {
			int startingArg = 0;
			if (args[0].equals("-l")) {
				handedness = true;
				startingArg = 1;
			}
			np = Integer.parseInt(args[startingArg++]);
			nt = Integer.parseInt(args[startingArg++]);
			tm = Long.parseLong(args[startingArg++]);
			em = Long.parseLong(args[startingArg]);

			Fork[] forks = new Fork[np];
			for(int i=0; i < np; i++) {
				forks[i] = new Fork();
			}

			for(int i=0; i < np; i++) {
				boolean rightHanded = true;
				if(handedness && i % 2 == 1) {
					rightHanded = false;
				}
				Philosopher philo = new Philosopher(i, forks[i], forks[(np + i - 1) % np], rightHanded, nt, tm, em);
				philo.start();
			}

		} else {
			System.out.println("Invalid args.");
		}
	}

}

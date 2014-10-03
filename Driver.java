public class Driver {
	public static void main(String[] args) {
		int np = 4;
		int nt = 10;
		long tm = 0;
		long em = 0;
		boolean handedness = false;

		int startingArg = 0;
		if (args.length > startingArg && args[startingArg].equals("-l")) {
			handedness = true;
			startingArg++;
		}
		
		if (args.length > startingArg)
			np = Integer.parseInt(args[startingArg++]);
		if (args.length > startingArg)
			nt = Integer.parseInt(args[startingArg++]);
		if (args.length > startingArg)
			tm = Long.parseLong(args[startingArg++]);
		if (args.length > startingArg)
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
	}

}

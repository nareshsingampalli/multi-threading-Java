import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main1 {
	public static final int MAX_PASSWORD = 	9999;
	public static void main(String[] args) {
		Random random = new Random();
		
		Valut valut = new Valut(random.nextInt(MAX_PASSWORD));
		
		List<Thread> threads = new ArrayList<>();
		
		
		threads.add(new AscendingHacker(valut));
		threads.add(new DescendingHacker(valut));
		threads.add(new PoliceThread());
		
		for(Thread thread: threads) {
			thread.start();
		}
		
			
	}
	private static class Valut{
		private int password;
		
		public Valut(int password) {
			this.password=password;
		}
		public boolean isCorrectPassword(int guess) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return this.password==guess;
			
		}
	}
		
	private static abstract class HackerThread extends Thread{
			protected Valut valut;
			public HackerThread(Valut valut) {
				this.valut= valut;
				this.setName(this.getClass().getSimpleName());
				this.setPriority(Thread.MAX_PRIORITY);
			}
			
			@Override
			public void start() {
				System.out.println("Starting thread:" +this.getName());
				super.start();
				}
		}
	
	private static class AscendingHacker extends HackerThread{

		public AscendingHacker(Valut valut) {
			super(valut);
			// TODO Auto-generated constructor stub
		}
		@Override
		public void run() {
			for(int guess=0;guess< MAX_PASSWORD;guess++) {
				if(valut.isCorrectPassword(guess)) {
					System.out.println(this.getName()+" guessed passowrd" +guess);
					System.exit(0);
				}
			}
		}
			
	}
	
	private static class DescendingHacker extends HackerThread{

		public DescendingHacker(Valut valut) {
			super(valut);
			// TODO Auto-generated constructor stub
		}
		@Override
		public void run() {
			for(int guess= MAX_PASSWORD;guess>=0;guess--) {
				if(valut.isCorrectPassword(guess)) {
					System.out.println(this.getName()+" guessed passowrd" +guess);
					System.exit(0);
				}
			}
		}
			
	}


	private static class PoliceThread extends Thread{
		@Override
		public void run() {
			for(int i = 10;i>0;i--) {
				try {
					// for each i sleep for 1sec
					Thread.sleep(1000);
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(i);
			}
			System.out.println("Game over for you hackers");
			System.exit(0);
		}
	}

}






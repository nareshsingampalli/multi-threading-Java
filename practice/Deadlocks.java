import java.util.Random;

public class Deadlocks {
	
	public static void main(String[] args) {
		Intersection intersection = new Intersection();
		Thread trainAThread = new Thread(new TrainA(intersection));
		Thread trainBThread = new Thread(new TrainB(intersection));
		
		trainAThread.start();
		trainBThread.start();
		
		
	}
	
	
	public static class TrainB implements Runnable{
		private Intersection intersection;
		private Random random = new Random();
		
		public TrainB(Intersection intersection) {
			this.intersection = intersection;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			long sleepingTime = random.nextInt(5);
			try {
				Thread.sleep(sleepingTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			intersection.takeRoadB();
		}
		
		
	}
	
	public static class TrainA implements Runnable{
		private Intersection intersection;
		private Random random = new Random();
		
		public TrainA(Intersection intersection) {
			this.intersection = intersection;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			long sleepingTime = random.nextInt(5);
			try {
				Thread.sleep(sleepingTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			intersection.takeRoadA();
		}
		
		
	}
	
	
	public static class Intersection{
		private Object roadA = new Object();
		private Object roadB = new Object();
		
		public void takeRoadA() {
			synchronized(roadA) {
				System.out.println("Road A is locked: "+ Thread.currentThread().getName());
			
				synchronized(roadB) {
					System.out.println("Train is passing through roadA");
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}		
		}
		public void takeRoadB() {
			synchronized(roadA) {
				System.out.println("Road A is locked: "+ Thread.currentThread().getName());
				
				synchronized(roadB) {
					System.out.println("Train passing through roadB");
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
}

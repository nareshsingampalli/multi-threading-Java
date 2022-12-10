
public class Main {
	public static void main(String[] args) {
		Thread thread = new Thread( new Runnable() {
				public void run() {
					System.out.println("we are in now in Thread:" + Thread.currentThread().getName());
					System.out.println("current thread priority:"+Thread.currentThread().getPriority());
				}
	});
		
	thread.setName("new worker Thread");
	thread.setPriority(Thread.MIN_PRIORITY);
	System.out.println("we are in thread:"+Thread.currentThread().getName() +"before stating thread");
	thread.start();
	System.out.println("we are in thread:"+Thread.currentThread().getName() +"after stating thread");
	try {
		Thread.sleep(10000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
	}
}

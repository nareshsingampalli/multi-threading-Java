import java.io.FileReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class MatrixMultiplication {
	public static void main(String[] args) {
	
	}
	
	public static class MatricesReaderProduce extends Thread{
		private Scanner scanner;
		private ThreadSafeQueue queue;
		public MatricesReaderProduce(FileReader reader,ThreadSafeQueue queue) {
			
			this.scanner = new Scanner(reader);
			this.queue = queue;
		}
		
	}
	public static class ThreadSafeQueue{
		private Queue<MatricesPair> queue = new LinkedList<>();
		private boolean isEmpty =true;
		private boolean isTerminate = false;
		
		public synchronized void add(MatricesPair matricesPair) {
			queue.add(matricesPair);
			isEmpty=false;
			notify();
		}
		public synchronized MatricesPair remove() {
			 while(isEmpty && !isTerminate) {
				 try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
			 }
			 if(queue.size() ==1) {
				 isEmpty =true;
			 }
			 if(queue.size() ==0 && isTerminate) {
				 return null;
			 }
			 System.out.println("queue Size: "+ queue.size());
			 return queue.remove();
			 
		}
		public synchronized void terminate() {
			isTerminate = true;
			notifyAll(); 
		}
	}
	public static class MatricesPair{
		public float[][] matrix1;
		public float[][] matrix2;
	}
}

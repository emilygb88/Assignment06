
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.Random;

public class Main {

	private static BlockingQueue<String> queue = new LinkedBlockingQueue<String>() ;

	  
	public static void main(String[] args) {
	    
	    
		Thread consumer1 = new Thread(new Consumer("Consumer 1"));
	    Thread consumer2 = new Thread(new Consumer("Consumer 2"));
	    Thread consumer3 = new Thread(new Consumer("Consumer 3"));

	
	   Thread producer1 = new Thread(new Producer("Producer 1"));
	   Thread producer2 = new Thread(new Producer("Producer 2"));

	    producer1.start();
	    producer2.start();
	
	    consumer1.start();
	    consumer2.start();
	    consumer3.start();
	  }
	  
	private static class Consumer implements Runnable{
	    
	    private String name;
	    private Vector<String> consumedArray = new Vector<String>();

	    
	    public Consumer(String name){
	        this.name=name;
	    }
	    @Override
	    public void run(){
	    	
	        try{
	        	Thread.sleep(100);
	            while( true ){
	                String item = queue.take();
	                Thread.sleep(1000);
	                consumedArray.add(item);
	                
	                System.out.println(name + ": " + consumedArray );
	                
	            }
	        } catch (Exception err) {
	               err.printStackTrace();
	        }   
	        
	    }
	
	 
	}
	
	private static class Producer implements Runnable {
	    private String name;
	    
	 
	    public Producer(String name) {
	        this.name=name;
	    } 
	    
	    
	    @Override
	    public void run() {
	    	Random rand = new Random();
	        try{
	            while(true){
	                String item = "item #" + String.format("%04d", rand.nextInt(10000));
	                queue.put(item);
	                System.out.println(name + " produced: " + item );
	                Thread.sleep(1000);
	          
	            }
	        } catch (Exception err) {
	               err.printStackTrace();
	        }   

	 }
	}
}


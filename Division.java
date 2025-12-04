package practice;
class Racer extends Thread {
 private String name;
 private int sleepTime; // Time between each step
 public Racer(String name, int sleepTime) {
 this.name = name;
 this.sleepTime = sleepTime;
 }
 // run() defines the race logic
 public void run() {
 for (int step = 1; step <= 5; step++) {
 System.out.println(name + " ran step " + step);
 try {
	 Thread.sleep(null); // Sleep to simulate running time
 } 
 catch (InterruptedException e) {
	 System.out.println(namee + " was interrupted!");
 }
 }
 System.out.println(name + " has finished the race!");
 }
}
// Main class to simulate the race
public class Division {
 public static void main(String[] args) {
 Racer racerA = new Racer("Thread A", 0);
 Racer racerB = new Racer("Thread B", 0);
 
 racerA.start();
 racerB.start();
 
 
 }
}




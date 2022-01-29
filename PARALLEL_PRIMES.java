// Brian Moon
// COP4520
// PA1

import java.util.PriorityQueue;

// Class for finding primes up to 10^8
public class PARALLEL_PRIMES extends Thread {
  // member variables
  static PriorityQueue<Integer> priority_queue = new PriorityQueue<Integer>();
  static boolean[] not_primes = new boolean[100000000];
  public static int numberOfPrimesFound = 1;
  public static int sharedIndex = 2;
  public static long sum = 2;
  
  // main method
  public static void main(String[] args) {
    // ------- Single Thread Solution ---------- //
    // start time
    long startTime = System.currentTimeMillis();
    // number of threads
    int numThreads = 1;
    PARALLEL_PRIMES[] primeThreads = new PARALLEL_PRIMES[numThreads];
    // start 8 threads
    for (int threadNumber = 0; threadNumber < numThreads; ++threadNumber) {
      primeThreads[threadNumber] = new PARALLEL_PRIMES();
      primeThreads[threadNumber].start();
    }
    // join threads
    for (int threadNumber = 0; threadNumber < numThreads; ++threadNumber) {
      try {
        primeThreads[threadNumber].join();
      } catch(Exception e) { e.printStackTrace(); }
    }

    // total found primes and sum, and get largest items
    for (int bool = 3, num = 0; bool < not_primes.length; ++bool) {
      if (not_primes[bool] == false) {
        ++numberOfPrimesFound;
        sum += bool;
        priority_queue.add(bool);
        ++num;
        if (num > 10) {
          priority_queue.poll();
        }
      }
    }
    System.out.println("1 Thread Solution:");
    // print number of primes found and sum
    System.out.println("Prime Count: " + numberOfPrimesFound);
    // get end time for parrallel processing to find primes
    long endTime = System.currentTimeMillis();
    System.out.println("Execution Time (Single Thread): " + (endTime - startTime) + "ms");
    System.out.println("Sum of Primes: " + sum);

    // print out 10 largest primes
    System.out.println("Top Ten Primes:");
    for (int topTen = 0; topTen < 10; ++topTen) {
      System.out.println(priority_queue.poll());
    }

    // ------- 8 Thread Solution ---------- //
    not_primes = new boolean[100000000];
    numberOfPrimesFound = 1;
    sharedIndex = 2;
    sum = 2;
    // start time
    startTime = System.currentTimeMillis();
    // number of threads
    numThreads = 8;
    primeThreads = new PARALLEL_PRIMES[numThreads];
    // start 8 threads
    for (int threadNumber = 0; threadNumber < numThreads; ++threadNumber) {
      primeThreads[threadNumber] = new PARALLEL_PRIMES();
      primeThreads[threadNumber].start();
    }
    // join threads
    for (int threadNumber = 0; threadNumber < numThreads; ++threadNumber) {
      try {
        primeThreads[threadNumber].join();
      } catch(Exception e) { e.printStackTrace(); }
    }

    // total found primes and sum, and get largest items
    for (int bool = 3, num = 0; bool < not_primes.length; ++bool) {
      if (not_primes[bool] == false) {
        ++numberOfPrimesFound;
        sum += bool;
        priority_queue.add(bool);
        ++num;
        if (num > 10) {
          priority_queue.poll();
        }
      }
    }
    System.out.println("8 Thread Solution:");
    // print number of primes found and sum
    System.out.println("\n\n\nPrime Count: " + numberOfPrimesFound);
    // get end time for parrallel processing to find primes
    endTime = System.currentTimeMillis();
    System.out.println("Execution Time (8 Threads): " + (endTime - startTime) + "ms");    
    System.out.println("Sum of Primes: " + sum);

    // print out 10 largest primes
    System.out.println("Top Ten Primes:");
    for (int topTen = 0; topTen < 10; ++topTen) {
      System.out.println(priority_queue.poll());
    }    
  }

  // run method to run each thread's task
  public void run() {
    int number = 100000000;
    numberOfPrimes(number);
  }  
  
  // get and increment number in a synchronized manner
  public synchronized int getAndIncrementNumber() {
    int returnVal = sharedIndex;
    sharedIndex += 1;
    return returnVal;
  }

  // using a homebrewed naive sieve method, I find the primes
  public void numberOfPrimes(int n) {
    int num = getAndIncrementNumber();
    for (int i = num; i <= Math.sqrt(n); i = getAndIncrementNumber()) {
      if (not_primes[i] == false) {
        for (int j = i * i; j < n; j += i) {
          not_primes[j] = true;
        }
      } 
    }
  }
}
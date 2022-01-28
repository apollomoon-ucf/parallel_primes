# Parallel Primes - COP4520 PA1

## Program Info

Programming Assignment 1
Parallel Primes
Brian Moon
COP4520

## Instructions

Build Program: javac PARALLEL_PRIMES.java

Run Program And Output To File: java PARALLEL_PRIMES > primes.txt

## Program Summary

This program finds all prime numbers between 1 and 10^8. Once the prime numbers have been found, the program calculates the number of primes found, the sum of the primes, as well as the the top ten maximum primes, listed in ascending order.

## Program Evaluation

The initial naive approach, was to determine if each number was prime within the given range. This produced results that took way too long for the task we are trying to accomplish. Therefore, a sieve method was implemented to reduce the time needed for identifying the prime numbers. With a single thread, the execution time was ~766ms, which is very good, but, we want to reduce this time by distrubuting the work across eight threads.

With 8 threads, the time was decreased to ~538ms, resulting in a program that is 1.42 times faster. If we use Amdahl's Law to determine the amount of the program that was sped up by using multithreading, or in other words, determine the amount of the program, we see that we get a result of 30.763%. This means that ~31% of the program is parallel. This could be further optimized with more research and refactoring. All output has been verified, the program has been tested, and the code has been pushed to GitHub.

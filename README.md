# CS471Project #

Part 1 is found in CPUSCHED </br>
Part 2 is found in PRODUCER-CONSUMER </br>

</br>

## Project Instructions ##

### Part I ###

Problem 1: CPU Scheduling

This part of the projects simulates a CPU scheduler. Since it is a simulation, there are no real processes/threads to be scheduled. Instead, you simulate the arrival of new processes and threads. Whenever a new process/thread arrives (in simulation) into the ready queue, the CPU scheduler is invoked. Each simulated process (or thread) has the following parameters: <Process ID, Arrival time, Priority, CPU burstunits>.Each of these is an integer parameter. One CPUburst unit is equivalent toCPU time needed to execute the following loop:

    for (int i=0, int temp =0; i < 10000; i++)

      if (i mod 2 ==0) temp= i/2 + 1;
      
      else temp=2*i;} </code>

For example, if a process <5, 100, 2, 20> is read by your simulator, it means a new process P arrived into the system at simulation time 100, with priority 2. It executes the above loop 20 times before finishing and leaving the system. At the time of invocation of the scheduler, the user indicates the type of scheduling to be enforced. You are required to implement the following scheduling types:

1.FIFO

2.SJF withoutpreemption

3.Priority with preemption

Each run will handle scheduling of 10,000 (simulated) processes. In other words, as soon as the number of processes that have completed CPU execution reaches 10,000, you can stop running the program and print the following statistics.Vary the CPU burst between 2 and 30, priority between 1 and 10, and interarrival time between 1 and 100 (in CPU units). Since this is a simulation, you must maintain a logical clock within your program to keep track of current time.All times are expressed in terms of CPU burst units. So they are not actual elapsed time (in msec) but simulate dtimes.

Statistics for the Run

Number of processes: 10,000

Total elapsed time (for the scheduler):

Throughput(Number of processes executed in one unit of CPU burst time):

CPU utilization:

Average waiting time(in CPU burst times):

Average turnaroundtime(in CPU burst times):

Average response time(in CPU burst times):

</br>

### Part II ###

Problem 2: Producer-Consumer Problem

9th Edition Operating Systems Concepts

This follows the description in pages 253-257of the 9th edition of the textbook. Develop the producer-consumer problem using Pthreads or Winn32APIor using any other library of your preference.You can code the program in any language of your choice.Test the program with several inputs (the three parameters to the main program are shown in the textbook). Definitely, test the following parameters. Try with different sleep times. Measure the performance of the time in terms of overall turnaround time. Tabulate your results along with the parameters used. Finally, summarize your results and give an explanation for the results.

Test case   Number of producers   Number of consumers

1                             1                              1

2                             4                              1

3                            16                             1

4                             1                              2

5                             4                              2

6                            16                             2

7                             1                              4

8                             4                              4

9                            16                             4

10                           1                             16

11                           4                             16

12                          16                             16

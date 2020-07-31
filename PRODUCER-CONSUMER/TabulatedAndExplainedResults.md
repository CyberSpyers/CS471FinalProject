### Tabulated Results Table ###
| Test Case | # Producers | # Consumers | Sleep Time (ms) | Observed Results                                                                                   |
|-----------|-------------|-------------|-----------------|----------------------------------------------------------------------------------------------------|
| 1         | 1           | 1           | 1               | Producers & Consumers Perform as Expected </br> Balance Intact </br> Prints in Correct Order       |
| 2         | 1           | 1           | 1000            | Producers & Consumers Perform as Expected </br> Balance Intact </br> Prints in Correct Order       |
| 3         | 1           | 1           | 5000            | Producers & Consumers Perform as Expected </br> Balance Intact </br> Prints in Correct Order       |
| 4         | 4           | 1           | 1               | Producers & Consumers Perform as Expected </br> Balance Intact </br> Prints in Incorrect Order     |
| 5         | 4           | 1           | 1000            | Producers & Consumers Perform as Expected </br> Balance Intact </br> Prints in Incorrect Order     |
| 6         | 4           | 1           | 5000            | Producers & Consumers Perform as Expected </br> Balance Intact </br> Prints in Incorrect Order     |
| 7         | 16          | 1           | 1               | Producers & Consumers Perform as Expected </br> Balance Not Intact </br> Prints in Incorrect Order |
| 8         | 16          | 1           | 1000            | Producers & Consumers Perform as Expected </br> Balance Intact </br> Prints in Incorrect Order     |
| 9         | 16          | 1           | 5000            | Producers & Consumers Perform as Expected </br> Balance Intact </br> Prints in Correct Order       |
| 10        | 1           | 2           | 1               | Producers & Consumers Perform as Expected </br> Balance Intact </br> Prints in Correct Order       |
| 11        | 1           | 2           | 1000            | Producers & Consumers Perform as Expected </br> Balance Intact </br> Prints in Correct Order       |
| 12        | 1           | 2           | 5000            | Producers & Consumers Perform as Expected </br> Balance Intact </br> Prints in Correct Order       |
| 13        | 4           | 2           | 1               | Producers & Consumers Perform as Expected </br> Balance Intact </br> Prints in Incorrect Order     |
| 14        | 4           | 2           | 1000            | Producers & Consumers Perform as Expected </br> Balance Intact </br> Prints in Incorrect Order     |
| 15        | 4           | 2           | 5000            | Producers & Consumers Perform as Expected </br> Balance Intact </br> Prints in Correct Order       |
| 16        | 16          | 2           | 1               | Producers & Consumers Perform as Expected </br> Balance Not Intact </br> Prints in Incorrect Order |
| 17        | 16          | 2           | 1000            | Producers & Consumers Perform as Expected </br> Balance Not Intact </br> Prints in Incorrect Order |
| 18        | 16          | 2           | 5000            | Producers & Consumers Perform as Expected </br> Balance Not Intact </br> Prints in Incorrect Order |
| 19        | 1           | 4           | 1               | Producers & Consumers Perform as Expected </br> Balance Intact </br> Prints in Correct Order       |
| 20        | 1           | 4           | 1000            | Producers & Consumers Perform as Expected </br> Balance Intact </br> Prints in Correct Order       |
| 21        | 1           | 4           | 5000            | Producers & Consumers Perform as Expected </br> Balance Intact </br> Prints in Correct Order       |
| 22        | 4           | 4           | 1               | Producers & Consumers Perform as Expected </br> Balance Intact </br> Prints in Incorrect Order     |
| 23        | 4           | 4           | 1000            | Producers & Consumers Perform as Expected </br> Balance Intact </br> Prints in Incorrect Order     |
| 24        | 4           | 4           | 5000            | Producers & Consumers Perform as Expected </br> Balance Intact </br> Prints in Correct Order       |
| 25        | 16          | 4           | 1               | Producers & Consumers Perform as Expected </br> Balance Intact </br> Prints in Incorrect Order     |
| 26        | 16          | 4           | 1000            | Producers & Consumers Perform as Expected </br> Balance Intact </br> Prints in Incorrect Order     |
| 27        | 16          | 4           | 5000            | Producers & Consumers Perform as Expected </br> Balance Intact </br> Prints in Correct Order       |
| 28        | 1           | 16          | 1               | Producers & Consumers Perform as Expected </br> Balance Intact </br> Prints in Correct Order       |
| 29        | 1           | 16          | 1000            | Producers & Consumers Perform as Expected </br> Balance Intact </br> Prints in Correct Order       |
| 30        | 1           | 16          | 5000            | Producers & Consumers Perform as Expected </br> Balance Intact </br> Prints in Correct Order       |
| 31        | 4           | 16          | 1               | Producers & Consumers Perform as Expected </br> Balance Not Intact </br> Prints in Incorrect Order |
| 32        | 4           | 16          | 1000            | Producers & Consumers Perform as Expected </br> Balance Not Intact </br> Prints in Incorrect Order |
| 33        | 4           | 16          | 5000            | Producers & Consumers Perform as Expected </br> Balance Intact </br> Prints in Correct Order       |
| 34        | 16          | 16          | 1               | Producers & Consumers Perform as Expected </br> Balance Not Intact </br> Prints in Incorrect Order |
| 35        | 16          | 16          | 1000            | Producers & Consumers Perform as Expected </br> Balance Not Intact </br> Prints in Incorrect Order |
| 36        | 16          | 16          | 5000            | Producers & Consumers Perform as Expected </br> Balance Intact </br> Prints in Correct Order       |

</br>
</br>

### Summary ###
It appears that as sleep time increases, things tend to work correctly. </br>
Also, unless the # of producers and consumers are close to one another, </br>
the balance messes up and the print statements come out of order. </br>

</br>
</br>

### Explanation ###
When there are way more producers than consumers, the consumers can't </br>
keep up and vice versa. When there is little sleep time, the print statements </br>
can come out of order as the threads keep going before the statements print, possibly. 

# CIBC Test

## Build Instructions
```shell
mvn clean install
```

## Question 1

### Source Code

The source code for this question is found in classes DiscreteProbabilityDistribution and Estimator. 

### Tests
The tests are found in DiscreteProbabilityDistributionTest and EstimatorTest

#### verifyDistribution
This verifies each estimate of the individual probabilities and ensures it is within 5 standard errors of the expected. 
It should be noted there is an extremely low chance the job will fail but nonetheless it is technically possible.  

#### speedComparison
This compares the speed on the two methods I implemented, namely bisect and secant.   

### Implementation Details

This searches over the cumulative probabilities using modified search algorithm that has a time complexity of O(
log(n)). This can either be set to use a bisection or secant method. 

Within the search function used arrays and primitives to reduce memory overhead and increase speed.

### Conclusion
Bisect is a far simpler search estimator. It only uses the index positions as input. 
The secant method requires far more operations that are potentially dangerous. However, the estimate is far better and 
given the nature of cumulative probability densities a linear approximation is very effective. This starts to become clear when 
there are large number of possible outcomes. The Secant method starts to perform considerably better under this scenario.

### Reference
* [https://en.wikipedia.org/wiki/Standard_error](https://en.wikipedia.org/wiki/Standard_error)
* [https://en.wikipedia.org/wiki/Binomial_distribution](https://en.wikipedia.org/wiki/Binomial_distribution)
* [https://en.wikipedia.org/wiki/Bisection_method](https://en.wikipedia.org/wiki/Bisection_method)
* [https://en.wikipedia.org/wiki/Secant_method](https://en.wikipedia.org/wiki/Secant_method)

## Question 2

### Source Code
The source code for this question is found in class NAppearsNTimes.

### Tests
The tests are found in NAppearsNTimesTest.

#### sampleInputFile
This take a sample test from a file in the text format specified in the question.  

#### maxMalue
This determines the max value the partial sum calculation can be before overflwoing.  

### Implementation Details

#### Background
The partial sum of this sequence can be solved analytically so is constant in time; O(1).

This comes from recognising this sum can also be expressed as a sum of sums of sequential numbers. i.e.
S(n) = sum(1,2,3,...,n) + sum(2,3,4,...,n) ...

This follows from that sum(0,1,2,3,...,n) = n*(n-1)/2. To get the nth sequence we to invert 
and take integer part of the positive real root.  

Therefore, the nth value the sequence is given by floor(0.5 + sqrt(2*n)) it can then be shown that the partial sum is 
((6n + 1)a - a^3) / 6, where a is the nth value in the sequence. See [OEIS A002024({http://oeis.org/A002024) for further details.

### Reference
* [http://oeis.org/A002024](http://oeis.org/A002024)




``


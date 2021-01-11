# Question 1

## Source Code

The source code for this question is found in class UniformDiscreteProbabilityDistribution.

## Tests

The tests are found in

## Implementation Details

This searches over the cumulative probabilities using modified search algorithm that has a time complexity of O(
log(n)). This can either be set to use a bisection or secant method. 

Within the search function used arrays and primitives to reduce memory overhead and increase speed 

##

# Question 2

## Source Code

The source code for this question is found in class NAppearsNTimes.

## Tests

The tests are found in following

keys tests


## Implementation Details

The partial sum of this sequence can be solved analytically so is constant in time; O(1).   

This comes as part of recognising this sum can also be expressed as a sum of sums of sequential numbers. i.e.
S(n) = sum(1,2,3,...,n) + sum(2,3,4,...,n) ... 


This follows from that sum(0,1,2,3,...,n) = n*(n-1)/2. To get the nth sequence we to invert 
and take integer part of the positive real root.  

Therefore, the nth value the sequence is given by floor(0.5 + sqrt(2*n))


 





``


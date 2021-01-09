<style TYPE="text/css">
code.has-jax {font: inherit; font-size: 100%; background: inherit; border: inherit;}
</style>
<script type="text/x-mathjax-config">
MathJax.Hub.Config({
    tex2jax: {
        inlineMath: [['$','$'], ['\\(','\\)']],
        skipTags: ['script', 'noscript', 'style', 'textarea', 'pre'] // removed 'code' entry
    }
});
MathJax.Hub.Queue(function() {
    var all = MathJax.Hub.getAllJax(), i;
    for(i = 0; i < all.length; i += 1) {
        all[i].SourceElement().parentNode.className += ' has-jax';
    }
});
</script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/mathjax/2.7.4/MathJax.js?config=TeX-AMS_HTML-full"></script>

# Question 1

## Source Code

The source code for this question is found in class UniformDiscreteProbabilityDistribution.

## Tests

The tests are found in

## Implementation Details

This searches over the cumulative probabilities using modified binary search algorithm that has a time complexity of O(
log(n)).

# Question 2

## Source Code

The source code for this question is found in class NAppearsNTimes.

## Tests

The tests are found in following

## Implementation Details

The partial sum of this sequence can be solved analytically so is constant in time; O(1).   







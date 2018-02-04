# Algo-PSO
Particle swarm optimization (PSO) is a computational method that optimizes a problem by iteratively trying to improve a candidate solution with regard to a given measure of quality.<br />
The pseudo code of the procedure is as follows
``````````````````````````````````````````````````````
For each particle 
    Initialize particle
END

Do
    For each particle 
        Calculate fitness value
        If the fitness value is better than the best fitness value (pBest) in history
            set current value as the new pBest
    End

    Choose the particle with the best fitness value of all the particles as the gBest
    For each particle 
        Calculate particle velocity according equation (a)
        Update particle position according equation (b)
    End 
While maximum iterations or minimum error criteria is not attained
````````````````````````````````````````````````````````````````````````````
References
`````````````````````````````````````````````````````
https://en.wikipedia.org/wiki/Particle_swarm_optimization
http://www.swarmintelligence.org/tutorials.php

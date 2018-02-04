/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package business;
import java.util.Random;
import java.util.Vector;
/**
 *
 * @author Rebecca1
 */

public class PSOProcess implements PSOConstants {
    private Vector<Particle> swarm = new Vector<Particle>();
    private double[] pBest = new double[SWARM_SIZE];
    private Vector<Location> pBestLocation = new Vector<Location>();
    private double gBest = 9999;
    private Location gBestLocation;
    private double[] fitnessValueList = new double[SWARM_SIZE];
    private Vector<Visitor> visitors = new Vector<Visitor>();
    Random generator = new Random();

    public void execute() {
        initializeSwarm();
        updateFitnessList();
        //Initialize the particle's best known position to its initial position
        for(int i=0; i<SWARM_SIZE; i++) {
                pBest[i] = fitnessValueList[i];
                pBestLocation.add(swarm.get(i).getLocation());
        }
       
        int t = 0;
        double w;
        double delta = 9999;
       // double fitnessValue = 9999;
        visitors = initializeVisitors();
        while(t < MAX_ITERATION && delta > ERR_TOLERANCE) {
            // step 1 - update pBest
//            for(int i=0; i < SWARM_SIZE; i++) {
//                if(fitnessValueList[i] < pBest[i]) {
//                    pBest[i] = fitnessValueList[i];
//                    pBestLocation.set(i, swarm.get(i).getLocation());
//                }
//            }

            // step 2 - update gBest
//            int bestParticleIndex = getMinPos(fitnessValueList);
//            if(t == 0 || fitnessValueList[bestParticleIndex] < gBest) {
//                delta = gBest - fitnessValueList[bestParticleIndex];
//                gBest = fitnessValueList[bestParticleIndex];
//                gBestLocation = swarm.get(bestParticleIndex).getLocation();
//            }

            w = W_UPPERBOUND - (((double) t) / MAX_ITERATION) * (W_UPPERBOUND - W_LOWERBOUND);

            for(int i=0; i<SWARM_SIZE; i++) {
                //pick randome numbers 
                double r1 = generator.nextDouble();
                double r2 = generator.nextDouble();

                Particle p = swarm.get(i);

                // step 3 - update velocity
                double newVelX;
                double newVelY;
                newVelX = (w * p.getVelocity().getX()) + 
                                        (r1 * C1) * (pBestLocation.get(i).getX() - p.getLocation().getX()) +
                                        (r2 * C2) * (gBestLocation.getX() - p.getLocation().getX());
                newVelY = (w * p.getVelocity().getY()) + 
                                        (r1 * C1) * (pBestLocation.get(i).getY() - p.getLocation().getY()) +
                                        (r2 * C2) * (gBestLocation.getY() - p.getLocation().getY());
                Velocity vel = new Velocity(newVelX, newVelY);
                p.setVelocity(vel);

                // step 4 - update location
               
                double newLocX = p.getLocation().getX() + newVelX;
                double newLocY = p.getLocation().getY() + newVelY;
                Location loc = new Location(newLocX, newLocY);
                p.setLocation(loc);
                double fitnessValue = ProblemSet.evaluate(p, visitors);
                p.setFitnessValue(fitnessValue);
                
                //update the particle's best known position
                if(fitnessValueList[i] < pBest[i]) {
                    pBest[i] = fitnessValueList[i];
                    pBestLocation.set(i, swarm.get(i).getLocation());
                }
                
                int bestParticleIndex = getMinPos(fitnessValueList);
                if(t == 0 || fitnessValueList[bestParticleIndex] < gBest) {
                    delta = gBest - fitnessValueList[bestParticleIndex];
                    gBest = fitnessValueList[bestParticleIndex];
                    gBestLocation = swarm.get(bestParticleIndex).getLocation();
                }
                System.out.print("Particle i:" + i);
                System.out.print("     Particle X: " + gBestLocation.getX());
                System.out.print("     Particle Y: " + gBestLocation.getY());
            }//end for
            
            System.out.println("ITERATION " + t + ": ");
            System.out.println("     Best X: " + gBestLocation.getX());
            System.out.println("     Best Y: " + gBestLocation.getY());
           // System.out.println("     Value: " + ProblemSet.evaluate(gBestLocation));
            t++;
            updateFitnessList();
        }//end while
        
        System.out.println("\nSolution found at iteration " + (t - 1) + ", the solutions is:");
        System.out.println("     Best X: " + gBestLocation.getX());
        System.out.println("     Best Y: " + gBestLocation.getY());
    } 

    //initialize particles method
    public void initializeSwarm() {
        Particle p;
        for(int i=0; i<SWARM_SIZE; i++) {
            //p = new Particle();

            // randomize location inside a space defined in Problem Set
            double px;
            double py;
            px = X_LOW + generator.nextDouble() * (X_HIGH - X_LOW);

            py = Y_LOW + generator.nextDouble() * (Y_HIGH - Y_LOW);
            Location pLocation = new Location(px,py);

            // randomize velocity in the range defined in Problem Set
            double vx;
            double vy;
            vx = VEL_LOW + generator.nextDouble() * (VEL_HIGH - VEL_LOW);
            vy = VEL_LOW + generator.nextDouble() * (VEL_HIGH - VEL_LOW);
            Velocity pVelocity = new Velocity(vx, vy);
            p = new Particle(pVelocity, pLocation);
//            p.setLocation(pLocation);
//            p.setVelocity(pVelocity);
            swarm.add(p);
        }
    }

    public Vector initializeVisitors(){
        Random random = new Random();
        Vector<Visitor> visitors = new Vector<Visitor>();
        Visitor v;
        for(int i = 0; i < VISITOR_NUM; i++){
            			
            // randomize location inside a space defined in Problem Set
            double x = X_LOW + random.nextDouble() * (X_HIGH - X_LOW);
            double y = Y_LOW + random.nextDouble() * (Y_HIGH - Y_LOW);
            Location location = new Location(x, y);
            v = new Visitor(location);
            //v.setLocation(location);			
            visitors.add(v);			
        }
        return visitors;
    }

    public void updateFitnessList() {
        for(int i=0; i<SWARM_SIZE; i++) {
            fitnessValueList[i] = swarm.get(i).getFitnessValue();
        }
    }

    public int getMinPos(double[] list) {
        int pos = 0;
        double minValue = list[0];

        for(int i=0; i<list.length; i++) {
            if(list[i] < minValue) {
                pos = i;
                minValue = list[i];
            }
        }
        return pos;
    }
}



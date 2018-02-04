/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.util.Vector;

/**
 *
 * @author Rebecca1
 */
public class ProblemSet implements PSOConstants{
      public static double evaluate(Particle p,  Vector<Visitor> visitors) {
        double result = 0;
        double sum = 0;
        double px = p.getLocation().getX(); // the "x" part of the location
        double py = p.getLocation().getY(); // the "y" part of the location
        double vx;
        double vy;

        for(int i = 0; i < VISITOR_NUM; i++){
            vx = visitors.get(i).getLocation().getX();
            vy = visitors.get(i).getLocation().getY();
            result = Math.sqrt(Math.pow(px-vx,2)+Math.pow(py-vy,2));
            sum += result;
        }
        return sum;
    }              
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

/**
 *
 * @author Rebecca1
 */
public interface PSOConstants {
    int SWARM_SIZE = 30;
    int VISITOR_NUM = 100;
    int MAX_ITERATION = 100;
    int PROBLEM_DIMENSION = 2;
    double C1 = 2.0;
    double C2 = 2.0;
    double W_UPPERBOUND = 1.0;
    double W_LOWERBOUND = 0.0;
        
    double X_LOW = 1;
    double X_HIGH = 4;
    double Y_LOW = -1;
    double Y_HIGH = 1;
    double VEL_LOW = -1;
    double VEL_HIGH = 1;
    double ERR_TOLERANCE = 1E-20; // the smaller the tolerance, the more accurate the result, 

}

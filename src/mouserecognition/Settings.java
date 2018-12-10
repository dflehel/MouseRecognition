/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mouserecognition;

/**
 *
 * @author Denes
 */
public class Settings {
    public static final boolean LOAD_MODEL = true;
    public static final int NUM_ACTIONS = 10;
    public static final int DFL_NUM_FEATURES = 24;
    public static final int AM_NUM_FEATURES = 39;
        //Delimiter used in CSV file
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";
     
    //CSV file header
    public static final String FILE_HEADER = "1\n";
    
      // Constants for feature extraction
     public static final double CURVATURE_THRESHOLD = 5.0E-4;
     public static double EPS = 1.0E-5;
     public static final int NUM_FEATURES = 39;
    
    
}

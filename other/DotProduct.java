/*
 * Return the dot product of the two given vector
 */

import java.util.*;

class DotProduct {
    public static double dotProduct(double[] a, double[] b) {
	return a[0]*b[0] + a[1]*b[1];
    }

    public static void main(String[] args) {
	Scanner scanner = new Scanner(System.in);
	double[] vector1 = new double[2];
	double[] vector2 = new double[2];
	System.out.println("Please enter vector 1: ");
	System.out.println("x1 = : ");
	vector1[0] = scanner.nextDouble();
	System.out.println("y1 = : ");
        vector1[1] = scanner.nextDouble();	
	System.out.println("Please enter vector 2: ");
	System.out.println("x2 = : ");
	vector2[0] = scanner.nextDouble();
	System.out.println("y2 = : ");
        vector2[1] = scanner.nextDouble();	
	double res = dotProduct(vector1, vector2);
	System.out.println(res);
    }
}

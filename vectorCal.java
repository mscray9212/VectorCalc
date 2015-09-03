package vectorCal;

import java.util.Arrays;
import java.util.Scanner;

public class vectorCal {
	
	static double A[] = new double[3];
	static double B[] = new double[3];
	static double C[] = new double[3];
	static double D1[] = new double[3];
	static double D2[] = new double[3];

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final double x1,x2,x3,x4,x5,y1,y2,y3,y4,y5,z1,z2,z3,z4,z5;
		boolean collinear = false;
		
		@SuppressWarnings("resource")
		Scanner userInput = new Scanner(System.in);
		System.out.print("Please enter the x, y, and z values for point A (with a space between):");
		x1 = userInput.nextDouble();
		y1 = userInput.nextDouble();
		z1 = userInput.nextDouble();
		A[0] = x1;
		A[1] = y1;
		A[2] = z1;
		
		System.out.print("Please enter the x, y, and z values for point B (with a space between):");
		x2 = userInput.nextDouble();
		y2 = userInput.nextDouble();
		z2 = userInput.nextDouble();
		B[0] = x2;
		B[1] = y2;
		B[2] = z2;

		System.out.print("Please enter the x, y, and z values for point C (with a space between):");
		x3 = userInput.nextDouble();
		y3 = userInput.nextDouble();
		z3 = userInput.nextDouble();
		C[0] = x3;
		C[1] = y3;
		C[2] = z3;

		System.out.print("Please enter the x, y, and z values for point D1 (with a space between):");
		x4 = userInput.nextDouble();
		y4 = userInput.nextDouble();
		z4 = userInput.nextDouble();
		D1[0] = x4;
		D1[1] = y4;
		D1[2] = z4;

		System.out.print("Please enter the x, y, and z values for point D2 (with a space between):");
		x5 = userInput.nextDouble();
		y5 = userInput.nextDouble();
		z5 = userInput.nextDouble();
		D2[0] = x5;
		D2[1] = y5;
		D2[2] = z5;

		if(isCollinear(A,B,C) == true){
			System.out.println("Points A, B, and C are collinear.");
			collinear = true;
		}
		
		if(isCollinear(A,B,C) == false){
			System.out.println("Points A, B, and C are not collinear.");
			System.out.print("The equation of the plane that contains points A, B, and C: ");
			System.out.println(getPlaneEquation(A,B,C));
			if(dInPlane(A,B,C,D1) == true){
				System.out.println("The x, y, and z values for D1 make it located in the A, B, and C-plane.");
			}
			if(!(dInPlane(A,B,C,D1) == true)){
				System.out.println("The x, y, and z values for D1 make it not located in the A, B, and C-plane.");
			}
			if(dInPlane(A,B,C,D2) == true){
				System.out.println("The x, y, and z values for D2 make it located in the A, B, and C-plane.");
			}
			if(!(dInPlane(A,B,C,D2) == true)){
				System.out.println("The x, y, and z values for D2 make it not located in the A, B, and C-plane.");
			}
			System.out.println("Vector from points D1 and D2 is: " + printDirVec(D1,D2));
//			getLineD(A,B,C,D1,D2);
//			double t1 = getT(D1, result);
			double result[] = new double[3];
			double result1[] = new double[3];
			double result2[] = new double[3];
			result1 = getPlaneEquationR(A,B,C);
			double t1 = getT(D1,result1);
			double t2 = getT(D2,result1);
			result = solveParametricEquation(D1,result1,t1);
			result2 = solveParametricEquation(D2,result1,t2);
			double result3[] = new double[3];
			result3 = getDirVec(result,result2);
			System.out.println("The projection of D1 onto the ABC-plane: " + getParametricEquation(D1,result1));
			System.out.println("Thus, we find t = " + t1);
			System.out.print("Plugging in this value of t, we can find a point D: ");
			System.out.println("D1'["+result[0]+","+result[1]+","+result[2]+"]");
			System.out.println("The projection of D2 onto the ABC-plane: " + getParametricEquation(D2,result1));
			System.out.println("To find the intersection with ABC-plane, plug the previous equations into the equation of ABC.");
			System.out.println("Thus, we find t = " + t2);
			System.out.print("Plugging in this value of t, we can find a Point F: ");
			System.out.println("D2'["+result2[0]+","+result2[1]+","+result2[2]+"]");
			System.out.println("Now we have to find parametric equations for the line passing through D and F.");
			System.out.println("Direction vector of D1 and D2: " + "["+result3[0]+", "+result3[1]+", "+result3[2]+"]");
			System.out.println("Finally, we can plug this value into the equation of the plane.");
			System.out.print("The parametric equation of the orthogonal projection of D1D2-line onto ABC-plane is: ");
			System.out.println(getParametricEquation(result,result2));
			
		}

	}
	
	public static String getPlaneEquation(double a[], double b[], double c[]){
		String equ = "";
		String x = "";
		String y = "";
		String z = "";
		double rab[] = new double[3];
		double rac[] = new double[3];
		double r[] = new double[3];
		rab[0] = (b[0]-a[0]); rab[1] = (b[1]-a[1]); rab[2] = (b[2]-a[2]);
		rac[0] = (c[0]-a[0]); rac[1] = (c[1]-a[1]); rac[2] = (c[2]-a[2]);
		r[0] = (rab[1]*rac[2])-(rab[2]*rac[1]);
		r[1] = ((rab[0]*rac[2])-(rab[2]*rac[0]));
		r[2] = (rab[0]*rac[1])-(rab[1]*rac[0]);
		double aa = ((b[1]-a[1])*(c[2]-a[2]))-((c[1]-a[1])*(b[2]-a[2]));
		double bb = ((b[2]-a[2])*(c[0]-a[0]))-((c[2]-a[2])*(b[0]-a[0]));
		double cc = ((b[0]-a[0])*(c[1]-a[1]))-((c[0]-a[0])*(b[1]-a[1]));
		double product = ((aa*a[0])+(bb*a[1])+(cc*a[2]));
		equ = "(" + r[0] + ")" + "x - " + "(" +  r[1] + ")" + "y + "
				+ "" + "(" + r[2] + ")" + "z "  + "= " + product;
		return equ;
	}
	
	public static boolean dInPlane(double a[],double b[], double c[], double d[]){
		boolean truth = false;
		double product;
		double rab[] = new double[3];
		double rac[] = new double[3];
		double r[] = new double[3];
		rab[0] = (b[0]-a[0]); rab[1] = (b[1]-a[1]); rab[2] = (b[2]-a[2]);
		rac[0] = (c[0]-a[0]); rac[1] = (c[1]-a[1]); rac[2] = (c[2]-a[2]);
		r[0] = (rab[1]*rac[2])-(rab[2]*rac[1]);
		r[1] = -((rab[0]*rac[2])-(rab[2]*rac[0]));
		r[2] = (rab[0]*rac[1])-(rab[1]*rac[0]);
		double aa = ((b[1]-a[1])*(c[2]-a[2]))-((c[1]-a[1])*(b[2]-a[2]));
		double bb = ((b[2]-a[2])*(c[0]-a[0]))-((c[2]-a[2])*(b[0]-a[0]));
		double cc = ((b[0]-a[0])*(c[1]-a[1]))-((c[0]-a[0])*(b[1]-a[1]));
		product = -((aa*a[0])+(bb*a[1])+(cc*a[2]));
		double dValue = (r[0]*d[0])-(r[1]*d[1])+(r[2]*d[2])+product;
		if(dValue == 0){
			truth = true;
		}
		return truth;
	}
	//Find parametric equations of the orthogonal projection of D1D2-line onto A,B,C- plane.
	public static void getLineD(double a[], double b[], double c[], double x[], double y[]){
		double d[] = new double[3];
		double rab[] = new double[3];
		double rac[] = new double[3];
		double r[] = new double[3];
		rab[0] = (b[0]-a[0]); rab[1] = (b[1]-a[1]); rab[2] = (b[2]-a[2]);
		rac[0] = (c[0]-a[0]); rac[1] = (c[1]-a[1]); rac[2] = (c[2]-a[2]);
		r[0] = (rab[1]*rac[2])-(rab[2]*rac[1]);
		r[1] = -((rab[0]*rac[2])-(rab[2]*rac[0]));
		r[2] = (rab[0]*rac[1])-(rab[1]*rac[0]);
		d[0] = (y[0]-x[0]); d[1] = (y[1]-x[1]); d[2] = (y[2]-x[2]);
		System.out.println("[" +d[0] +", "+ d[1] +", " + d[2] + "]");
		System.out.print("The parametric equation of this vector is: ");
		System.out.print("<" + "x = (" + d[0] + " + (" + r[0] + ")t), ");
		System.out.print("y = (" + d[1] + " + (" + r[1] + ")t), ");
		System.out.println("z = (" + d[2] + " + (" + r[2] + ")t)>");
	}
	
	public static boolean isCollinear(double a[], double b[], double c[]){
		boolean isCollinear;
		double product;
		double rab[] = new double[3];
		double rac[] = new double[3];
		double r[] = new double[3];

		//Defines vector AB and AC
		for (int i = 0; i < r.length; i++){
		rab[i] = (b[i]-a[i]); 
		rac[i] = (c[i]-a[i]); 
		}
		//Determines cross product AB x AC
		r[0] = (rab[1]*rac[2])-(rab[2]*rac[1]);
		r[1] = -((rab[0]*rac[2])-(rab[2]*rac[0]));
		r[2] = (rab[0]*rac[1])-(rab[1]*rac[0]);
		product = (r[0]) + (r[1]) + (r[2]);

		if (product == 0){ 
		isCollinear = true;
		}
		else{
		isCollinear = false; 
		}
		return isCollinear;
		}
	public static double[] getDirVec(double a[], double b[]){
		double ab[] = new double[3];
		ab[0] = (b[0]-a[0]); ab[1] = (b[1]-a[1]); ab[2] = (b[2]-a[2]);
		ab[0]=(double)Math.round(ab[0] * 1000)/1000;
		ab[1]=(double)Math.round(ab[1] * 1000)/1000;
		ab[2]=(double)Math.round(ab[2] * 1000)/1000;
		return ab;
	}
	public static String printDirVec(double a[], double b[]){
		String result = "<" + (b[0]-a[0]) + ", " + (b[1]-a[1]) + ", " + (b[2]-a[2])  + ">";
		return result;
	}

	public static double[] getIntersection(double a[], double b[]){
		double ab[] = new double[3];
		double equ[] = getPlaneEquationR(A,B,C);
		double T[] = getDirVec(D1,D2);
		ab[0]= (b[0] + (T[0]*equ[0]));
		ab[1]= (b[1] + (T[1]*equ[1]));
		ab[2]= (b[2] + (T[2]*equ[2]));
		return ab;
	}
	public static String getParametricEquation(double a[], double b[]){
		String result = "";
		result = "<" + "x = " + a[0] + " + (" + b[0] + ")t, " +
		"y = " + a[1] + " + (" + b[1] + ")t, " +"z = " + a[2] + " + (" + b[2] + ")t>";
		return result;
	}
	public static double[] getPlaneEquationR(double a[], double b[], double c[]){
		double rab[] = new double[3];
		double rac[] = new double[3];
		double r[] = new double[3];
		rab[0] = (b[0]-a[0]); rab[1] = (b[1]-a[1]); rab[2] = (b[2]-a[2]);
		rac[0] = (c[0]-a[0]); rac[1] = (c[1]-a[1]); rac[2] = (c[2]-a[2]);
		r[0] = (rab[1]*rac[2])-(rab[2]*rac[1]);
		r[1] = -((rab[0]*rac[2])-(rab[2]*rac[0]));
		r[2] = (rab[0]*rac[1])-(rab[1]*rac[0]);
		return r;
	}
	public static double getEquationProduct(double a[], double b[], double c[]){
		double product;
		double rab[] = new double[3];
		double rac[] = new double[3];
		double r[] = new double[3];
		rab[0] = (b[0]-a[0]); rab[1] = (b[1]-a[1]); rab[2] = (b[2]-a[2]);
		rac[0] = (c[0]-a[0]); rac[1] = (c[1]-a[1]); rac[2] = (c[2]-a[2]);
		r[0] = (rab[1]*rac[2])-(rab[2]*rac[1]);
		r[1] = -((rab[0]*rac[2])-(rab[2]*rac[0]));
		r[2] = (rab[0]*rac[1])-(rab[1]*rac[0]);
		double aa = ((b[1]-a[1])*(c[2]-a[2]))-((c[1]-a[1])*(b[2]-a[2]));
		double bb = ((b[2]-a[2])*(c[0]-a[0]))-((c[2]-a[2])*(b[0]-a[0]));
		double cc = ((b[0]-a[0])*(c[1]-a[1]))-((c[0]-a[0])*(b[1]-a[1]));
		product = -((aa*a[0])+(bb*a[1])+(cc*a[2]));
		return product;
	}
	public static double getT(double x[], double y[]){
		double T[] = new double[3];
		double r[] = new double[3];
		double product;
		T = getDirVec(x,y);
		r = getPlaneEquationR(A,B,C);
		product = getEquationProduct(A,B,C);
		double t = (((x[0]*r[0])+(x[1]*r[1])+(x[2]*r[2])+product)/(-1*((y[0]*r[0])+(y[1]*r[1])+(y[2]*r[2]))));
		t = (double)Math.round(t * 1000)/1000;
		return t;
	}
	public static double[] solveParametricEquation(double[] a, double[] b, double x){
		double r[] = new double[3];
		double result[] = new double[3];
		double t = x;
		r = getPlaneEquationR(A,B,C);
		t = x;
		result[0] = a[0]+(r[0]*t); result[1] = a[1]+(r[1]*t); result[2] = a[2]+(r[2]*t);
		result[0]=(double)Math.round(result[0] * 1000)/1000;
		result[1]=(double)Math.round(result[1] * 1000)/1000;
		result[2]=(double)Math.round(result[2] * 1000)/1000;
		return result; 
	}
}
	

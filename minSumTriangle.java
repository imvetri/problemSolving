package combinatorics;

import java.util.Scanner;

public class minSumTriangle {

	//store the input in a 2 d array
	public static long[][] triangle;
	public static int triangleHeight ;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		readTriangle();
		tests();

	}
	public static void readTriangle(){
		 try{
			 Scanner sc=new Scanner(System.in);  

	         triangleHeight = sc.nextInt();
	         initTriangleArray( triangleHeight );
	      }catch(Exception e){
	         e.printStackTrace();
	      }
	}
	public static void initTriangleArray( int height ){
		triangle = new long[height][];
		for( int i=0; i <height ; i++){
			for(int j=0; j<=i; j++){
				triangle[i] =new long[j+1];
			}
		}
	}

	public static void tests(){
		printTriangle();

	}
	public static void printTriangle(){
		for( int i=0; i <triangle.length ; i++){
			for(int j=0; j<=i; j++){
				System.out.print(triangle[i][j]);
			}
			System.out.println();
		}
		
	}
}

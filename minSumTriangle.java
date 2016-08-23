package combinatorics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
	         // open input stream test.txt for reading purpose.
	         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	         triangleHeight = Integer.parseInt(br.readLine());
	         initTriangleArray( triangleHeight );
	         System.out.println(nextLong(br));
	      }catch(Exception e){
	         e.printStackTrace();
	      }
	}
	/*
	 * reads from stdin and returns a integer
	 */
	public static long nextLong( BufferedReader br )throws IOException{
		long number=0;
		int input = br.read();
		int sign = ((char)input=='-') ? -1 : 1; 
		boolean firstChar = true;
		while(input!=-1 && input!=' ' && input!=13){
			if(firstChar){
				if(sign == -1){
					input= br.read();
				}
				else{
					number = Character.getNumericValue( input );
					input = br.read();
				}
				firstChar = !firstChar;
			}
			else{
				number = number*10 +Character.getNumericValue( input );
				input =br.read();
			}	
		}
		return sign*number;
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

package combinatorics;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;

public class ncr {

	public static int T, N[];
	public static BigInteger[] nCrValues = new BigInteger[1001];
	
	public static void readInput() throws Exception{
		
		//read input
		 try{
	         // open input stream test.txt for reading purpose.
	         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	         T = Integer.parseInt(br.readLine());
	         N = new int[T];
	         for(int i=0; i<T ; i++){
	        	 N[i]=Integer.parseInt(br.readLine());
	        	 solveInput(N[i]);
	         }
	      }catch(Exception e){
	         e.printStackTrace();
	      }
	}
	public static void 		resetnCrValues(){
		Arrays.fill(nCrValues , new BigInteger("0") );
	}
	public static void solveInput( int n ){
		//reset nCrValues values to 0. 
		//find a optimum technique for above
		//calculate ncr for r = 0 to (n+1)/2

		resetnCrValues();
		for(int r=0; r <=n ;r++){
			BigInteger tenPow9 = new BigInteger("1000000000");
			System.out.print( calculateNCR( n , r ).mod( tenPow9 ) +" ");
		}
        System.out.println();

	}
	public static BigInteger calculateNCR( int n, int r  ){
		if(r==0){
			nCrValues[r] = new BigInteger("1");
			return nCrValues[r];
		}
		if( r==1 ){
			nCrValues[r] = new BigInteger(Integer.toString(n) );
			return nCrValues[r];
		}
		if(nCrValues[r].compareTo(new BigInteger(Integer.toString(0)))!=0){
			return nCrValues[r];
		}
		else {
			BigInteger bigNminusR = new BigInteger(Integer.toString(( n - r + 1)));
			BigInteger bigR = new BigInteger(Integer.toString(r));
			nCrValues[r] = calculateNCR( n , r-1).multiply( bigNminusR ).divide( bigR );
			return nCrValues[r];
		}	
	}
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		readInput();
	}

}

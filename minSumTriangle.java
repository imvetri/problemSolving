package combinatorics;

import java.util.Scanner;

public class minSumTriangle {

	//store the input in a 2 d array
	public static long[][] triangle;
	public static int triangleHeight ;
	//store subTrianglesValues?
	public static long[][] subTrianglesSum; //subTriangle[ triangleSize ][ numberOfTriangles ] = sum of TriangleStize 
	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		readTriangle();
		initSubTrianglesSumsArray();
		//tests();


		for(int i=0;i<triangleHeight;i++){
			for(int j=0;j<=i;j++){
				sumTriangleFrom(i , j , triangleHeight-i-1);
			}
		}
	}
	public static void readTriangle() throws Exception{

		 Scanner sc=new Scanner(System.in);  

         triangleHeight = sc.nextInt();
         initTriangleArray( triangleHeight , sc);
     
	}
	public static void initTriangleArray( int height , Scanner sc ){
		triangle = new long[height][];
		for( int i=0; i <height ; i++){
			triangle[i] =new long[i+1];
			for(int j=0; j<=i; j++){
				triangle[i][j] = sc.nextLong();
			}
		}
	}
	public static void initSubTrianglesSumsArray(){
		subTrianglesSum = new long[triangleHeight][];
		for(int i=0; i < triangleHeight ; i++){
			int n = (triangleHeight-i)*(triangleHeight-i+1)/2; // n*(n+1)/2
			subTrianglesSum[i] = new long[n]; 
		}
	}
	
	public static void tests(){
		System.out.println();
		printTriangle();
		//print second line
		printLine(1,0,2); //OUTPUT -14 -7
		System.out.println(sumOfLine(1,0,2)); //OUTPUT -21
		
		//print third line
		printLine(2,0,3); //OUTPUT 20  -13  -5  
		System.out.println(sumOfLine(2,0,3)); //OUTPUT 2
		
		//print triangle
		printTriangleFrom( 0 , 0 , 3 );
		System.out.println();
		/*
		 * 	15  
			-14  -7  
			20  -13  -5 
		 * */
		//find sum of above triangle sum = -4
		//*System.out.println(sumTriangleFrom ( 0 , 0 , 3 ));
		
		//output 15
		//*System.out.println(sumTriangleFrom(0,0,1));
		System.out.println();
		//try printing sums
		//single element
		//System.out.println("single element first 		= " +sumTriangleFrom(0 , 0 , 0)); //15
		//single element last
		//System.out.println("single element last 		= " +sumTriangleFrom(5 , 5 , 0)); //3
		//triangle of depth 1 from (0,0)
		//System.out.println("triangle of depth 1 from (0,0)  = "+sumTriangleFrom(0 , 0 , 1)); //-6
		//triangle of depth 2 from (0,0)
		//System.out.println("triangle of depth 2 from (0,0)  = "+sumTriangleFrom(0 , 0 , 2)); //-4
		//triangle of depth 3 from (0,0)
		//System.out.println("triangle of depth 3 from (0,0)  = "+sumTriangleFrom(0 , 0 , 3)); // -2
		//triangle of depth 4 from (0,0)
		//System.out.println("triangle of depth 4 from (0,0)  = "+sumTriangleFrom(0 , 0 , 4)); // -23
		//triangle of depth 5 from (0,0)
		//System.out.println("triangle of depth 5 from (0,0)  = "+sumTriangleFrom(0 , 0 , 5)); // 34
		
		

		//TODO subTriangleSum is not updated correctly
		//System.out.println(subTrianglesSum[1][0]); //should print 3
	}
	
	public static long sumTriangleFrom( int x , int y , int depth){
		long sum=0; //because i have allocated size array in reverse idiot me
		if(depth==0){
			subTrianglesSum[depth][x+y] = triangle[x][y];
		}
		else if(subTrianglesSum[depth][x+y]==0){
			subTrianglesSum[depth][x+y] = sumTriangleFrom( x, y, depth-1) + sumOfLine( depth , y, depth+1);
		}
		return subTrianglesSum[depth][x+y];
	}
	
	public static void printTriangleFrom( int x , int y , int depth){
		for( int i=x; i < depth ; i++){
			for(int j=x; j<=i; j++){
				System.out.print(triangle[i][j]+"  ");
			}
			System.out.println();
		}
	}
	public static void printTriangle(){
		for( int i=0; i < triangleHeight ; i++){
			for(int j=0; j<=i; j++){
				System.out.print(triangle[i][j]+"  ");
			}
			System.out.println();
		}
	}
	public static void printLine( int x , int y , int length ){
		System.out.println();

		for(int i=y ; i < length ; i++){
			System.out.print(triangle[x][i]+"  ");
		}
	}
	
	/*
	 * sumOfLine(LineNumber-1,position-1,length)
	 */
	public static long sumOfLine( int x , int y , int length ){
		long sum=0;
		for(int i=y ; i < length ; i++){
			sum+=triangle[x][i];
		}         
		return sum;
	}
  }

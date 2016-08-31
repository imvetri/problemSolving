package combinatorics;

import java.util.Scanner;

public class minSumTriangle {

	//store the input in a 2 d array
	public static long[][] triangle;
	public static int triangleHeight ;
	public static MinHeap minHeap;
	public static int noOfSums=0;
	//store subTrianglesValues?
	public static long[][] subTrianglesSum; //subTriangle[ triangleSize ][ numberOfTriangles ] = sum of TriangleStize 
	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		readTriangle();
		initSubTrianglesSumsArray();
		//tests();

		minHeap = new MinHeap(noOfSums);

		for(int i=0;i<triangleHeight;i++){
			for(int j=0;j<=i;j++){
				sumTriangleFrom(i , j , triangleHeight-i-1);
			}
		}

        minHeap.minHeap();
        System.out.println( minHeap.remove() );  
        System.out.println( minHeap.remove() );  
        System.out.println( minHeap.remove() );  
        System.out.println( minHeap.remove() );   
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
			noOfSums+= n ;
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
		minHeap.insert(subTrianglesSum[depth][x+y]);
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


 class MinHeap
{
    private long[] Heap;
    private int size;
    private int maxsize;
 
    private static final int FRONT = 1;
 
    public MinHeap(int maxsize)
    {
        this.maxsize = maxsize;
        this.size = 0;
        Heap = new long[this.maxsize + 1];
        Heap[0] = Integer.MIN_VALUE;
    }
 
    private int parent(int pos)
    {
        return pos / 2;
    }
 
    private int leftChild(int pos)
    {
        return (2 * pos);
    }
 
    private int rightChild(int pos)
    {
        return (2 * pos) + 1;
    }
 
    private boolean isLeaf(int pos)
    {
        if (pos >=  (size / 2)  &&  pos <= size)
        { 
            return true;
        }
        return false;
    }
 
    private void swap(int fpos, int spos)
    {
        long tmp;
        tmp = Heap[fpos];
        Heap[fpos] = Heap[spos];
        Heap[spos] = tmp;
    }
 
    private void minHeapify(int pos)
    {
        if (!isLeaf(pos))
        { 
            if ( Heap[pos] > Heap[leftChild(pos)]  || Heap[pos] > Heap[rightChild(pos)])
            {
                if (Heap[leftChild(pos)] < Heap[rightChild(pos)])
                {
                    swap(pos, leftChild(pos));
                    minHeapify(leftChild(pos));
                }else
                {
                    swap(pos, rightChild(pos));
                    minHeapify(rightChild(pos));
                }
            }
        }
    }
 
    public void insert(long element)
    {
        Heap[++size] = element;
        int current = size;
 
        while (Heap[current] < Heap[parent(current)])
        {
            swap(current,parent(current));
            current = parent(current);
        }	
    }
 
    public void print()
    {
        for (int i = 1; i <= size / 2; i++ )
        {
            System.out.print(" PARENT : " + Heap[i] + " LEFT CHILD : " + Heap[2*i] 
                + " RIGHT CHILD :" + Heap[2 * i  + 1]);
            System.out.println();
        } 
    }
 
    public void minHeap()
    {
        for (int pos = (size / 2); pos >= 1 ; pos--)
        {
            minHeapify(pos);
        }
    }
 
    public long remove()
    {
        long popped = Heap[FRONT];
        Heap[FRONT] = Heap[size--]; 
        minHeapify(FRONT);
        return popped;
    }
 
}

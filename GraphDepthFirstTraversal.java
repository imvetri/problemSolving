/* IMPORTANT: Multiple classes and nested static classes are supported */

import java.io.BufferedReader;
import java.io.InputStreamReader;


class TestClass {
	public static int n, e , graph[][] , Olevel=1;
	
	
	public static long minCost=999999999;
	public static String minPath ="";
	//test if the graph saved really as a directed graph
	public static void testGraph(){
		/* TEST 1
		 * input format 1 5 9
		 * traverse from 5->1
		 * test graph[5-1][1-1] should be 9 
		 * test graph[1-1][5-1] should be 0
		 * 
		 */
		/*
		 * PASSED
		System.out.println("test graph[5-1][1-1] should be 9 - "+   (graph[5-1][1-1]==9)   );
		System.out.println("test graph[1-1][5-1] should be 0 - "+   (graph[1-1][5-1]==0)   );
		*/
		
		/* 
		 * TEST 2 print graph
		 *  	  draw it 
		 *  	  Check if it matches with site diagram
		 *  
		 *  
		 */
		System.out.print("      ");

		for(int i=0; i<graph[0].length; i++){
			System.out.print(i+1+"  ");
		}
		System.out.println();
		System.out.println();

		
		for(int i=graph[0].length-1; i>=0; i--){
			System.out.print(i+1+" ->  ");
			for(int j=0;j<graph[0].length; j++){
				System.out.print(graph[i][j] +"  ");
			}
			System.out.println();
		}
	}
	public static void printNextNodes( int junction, int cost , String path , int level){
		boolean isLeaf=true;
		for(int node=0;node<graph.length;node++){
			if(graph[junction][node]!=0){
				//System.out.print( cost + graph[junction][node]+"   " );
				printNextNodes(node ,cost + graph[junction][node] , path+ " "+Integer.toString(node+1), level+1);
				isLeaf = false;
			}
		}
		if(isLeaf){
			if(minCost>cost){
				minCost = cost;
				minPath=path;
				Olevel=level;
			}
		}
	}
	
	//get all path from last junction to any of entrances
	public static void pathsFromCounterToEntrances(int junction){
		System.out.println("JUNCTION  "+(junction+1));
		//depth first search and store them
		int length = graph.length;
		for(int i=0;i<graph.length;i++){
			if(graph[junction][i]!=0){
				System.out.print( Integer.toString(graph[junction][i])+" ->  " ) ;
				pathsFromCounterToEntrances(i);
			}
		}
		
	}
	//just read from STDin and save it in global variable. no nothing else
	public static void  readInput()  throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        n = Integer.parseInt(line);
        
        line = br.readLine();
        e = Integer.parseInt(line);
        //2 d graph is not needed here.. but why ? I don't know
        graph = new int[n][n]; //n is the node of the graph
        for (int i = 0; i < e; i++) { //e is the edge of the graph. don't forget it.
            line = br.readLine();
            String[] edgeDetails = line.split(" ");
            
            int START_NODE, END_NODE , COST;
            
            START_NODE = Integer.parseInt(edgeDetails[0])-1;
            END_NODE = Integer.parseInt(edgeDetails[1])-1;
            COST = Integer.parseInt(edgeDetails[2]); //writing a test helped me to get rid of this bug :D yay
            //ayyoo confused here :'(
            graph[END_NODE][START_NODE] = COST;
        }
	}
    public static void main(String args[] ) throws Exception {
    	readInput();
    	printNextNodes(n-1 ,0 , Integer.toString(n) , 1);
    	String s[]=minPath.split(" ");
    	for(int x=s.length -1; x>0 ; x--)
    		System.out.print(s[x]+ " -> ");
    	
    	System.out.print(s[0]);
    	
    	System.out.println(minCost*Olevel);
    	
    }
}

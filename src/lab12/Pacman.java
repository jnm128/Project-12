package lab12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;

public class Pacman {

	/** representation of the graph as a 2D array */
	private Node[][] maze;
	/** input file name */
	private String inputFileName;
	/** output file name */
	private String outputFileName;
	/** height and width of the maze */
	private int height;
	private int width;
	/** starting (X,Y) position of Pacman */
	private int startX;
	private int startY;

	/** A Node is the building block for a Graph. */
	private static class Node {
		/** the content at this location */
	    private char content;
	    /** the row where this location occurs */
	    private int row;
	    /** the column where this location occurs */
	    private int col;
		private boolean visited;
		private Node parent;

	    public Node(int x, int y, char c) {
	        visited = false;
	        content = c;
	        parent =  null;
	        this.row = x;
	        this.col = y;
	    }

	    public boolean isWall() { return content == 'X'; }
	    public boolean isVisited() { return visited; }
	}

	/** constructor */
	public Pacman(String inputFileName, String outputFileName) {
		this.inputFileName = inputFileName;
		this.outputFileName = outputFileName;
		buildGraph();
	}

	//method to write files in
	private boolean inMaze(int index, int bound){
		return index < bound && index >= 0;
	}

	/** helper method to construct the solution path from S to G
	 *  NOTE this path is built in reverse order, starting with the goal G.
	*/
	private void backtrack(Node end){
		// TODO for assignment12
		Node current=end.parent;
		while(current.content!='S') {
			current.content='.';
			current=current.parent;
		}
		
	}

	/** writes the solution to file */
	public void writeOutput() {
		// TODO for lab12
		try {
			PrintWriter output = new PrintWriter(new FileWriter(outputFileName));
			// FILL IN
			output.print(toString());
			output.close()	;
			} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public String toString() {
		String s = "";
		s += height + " " + width + "\n";
		for (int i = 0; i < height; i++){
			for (int j = 0; j < width; j++){
				s += maze[i][j].content + " ";
			}
			s += "\n";
		}
		return s;
	}

	/** helper method to construct a graph from a given input file;
	 *  all member variables (e.g. maze, startX, startY) should be
	 *  initialized by the end of this method
	 */
	private void buildGraph() {
		// TODO for lab12
		try {
			// don't forget to close input when you're done
			BufferedReader input = new BufferedReader(
				new FileReader(inputFileName));
			
			String[]lineDim=input.readLine().split(" ");
			width=Integer.parseInt(lineDim[1]);
			height=Integer.parseInt(lineDim[0]);
			maze= new Node[height][width];
			int x=0;
			String Line=input.readLine();
			
			while(Line!=null) {
				for(int y=0;y<Line.length();y++) {
					char current=Line.charAt(y);
					maze[x][y]=new Node(x,y,current);
					if(current=='S') {
						startX=x;
						startY=y;
					}	
				
				}
				
				x++;
				Line=input.readLine();
			}
			input.close();
			System.out.println(toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/** obtains all neighboring nodes that have *not* been
	 *  visited yet from a given node when looking at the four
	 *  cardinal directions: North, South, West, East (IN THIS ORDER!)
	 *
	 * @param currentNode the given node
	 * @return an ArrayList of the neighbors (i.e., successors)
	 */
	public ArrayList<Node> getNeighbors(Node currentNode) {
		// TODO for assignment12
		Node north, south, east, west;
		// 0. Initialize an ArrayList of nodes
		ArrayList<Node> neighbor= new ArrayList<Node>();

		// 1. Inspect the north neighbor
		north=maze[currentNode.row-1][currentNode.col];
		
		if(inMaze(currentNode.col,width)&& inMaze(currentNode.row-1,height)) {
			if((!north.visited) &&north.content!='X') {
				north.visited=true;
				north.parent=currentNode;
				neighbor.add(north);
				
			}
		}

		// 2. Inspect the south neighbor
		south= maze[currentNode.row+1][currentNode.col];
		if(inMaze(currentNode.col,width)&& inMaze(currentNode.row+1,height)) {
			if((!south.visited) &&south.content!='X') {
				south.visited=true;
				south.parent=currentNode;
				neighbor.add(south);
				
			}
		}
		
		// 3. Inspect the west neighbor
		west= maze[currentNode.row][currentNode.col-1];
		if(inMaze(currentNode.col-1,width)&& inMaze(currentNode.row,height)) {
			if((!west.visited) &&west.content!='X') {
				west.visited=true;
				west.parent=currentNode;
				neighbor.add(west);
				
			}
		}
		
		

		// 4. Inspect the east neighbor
		east= maze[currentNode.row][currentNode.col+1];
		if(inMaze(currentNode.col+1,width)&& inMaze(currentNode.row,height)) {
			if((!east.visited) &&east.content!='X') {
				east.visited=true;
				east.parent=currentNode;
				neighbor.add(east);
				
			}
		}

		return neighbor;
	}

	/** Pacman uses BFS strategy to solve the maze */
	public void solveBFS() {
		// TODO for assignment12
		Queue<Node> bfsQueue= new LinkedList<Node>();
		Node Start=maze[startX][startY];
		Node x;
		Start.visited=true;
		bfsQueue.add(Start);
		
		while(!bfsQueue.isEmpty()) {
			x=bfsQueue.poll();
			ArrayList<Node>neighbor=getNeighbors(x);
			for(int i=0;i<neighbor.size();i++) {
				bfsQueue.add(neighbor.get(i));
			}
			if(x.content=='G') {
				backtrack(x);
				writeOutput();
			    System.out.println(toString());
				
				return;
				}
		}
		writeOutput();
		
		
	}

	/** Pacman uses DFS strategy to solve the maze */
	public void solveDFS() {
		// TODO for assignment12
		Stack<Node> dfsStack= new Stack<Node>();
		Node x;
		Node Start=maze[startX][startY];
		Start.visited=true;
		
		dfsStack.push(Start);
		
		while(!dfsStack.isEmpty()) {
			x=dfsStack.pop();
			
			ArrayList<Node>neighbor=getNeighbors(x);
			for(int i=0;i<neighbor.size();i++) {
				dfsStack.push(neighbor.get(i));
			}
			
			if(x.content=='G') {
				backtrack(x);
				writeOutput();
			    System.out.println(toString());
				
				return;
				}
			
		}
		writeOutput();
		
		}

}

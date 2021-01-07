package lab12;

public class PacmanTester {
  
   public static void main(String[] args) {
      /* Pacman Maze1 = new Pacman("classic.txt", "output.txt");
       Maze1.solveDFS();
       Maze1.solveBFS();
       Maze1.writeOutput();
      
       // Maze.getNeighbors(currentNode);
       // Maze.solveBFS();
       //System.out.println("MAZE2\n");
      Pacman Maze2 = new Pacman("classic.txt", "output.txt");
      Maze2.solveBFS();
      Maze2.writeOutput();
    */  
      System.out.println("MAZE3\n");
   
     // Pacman Maze3 = new Pacman("bigMaze.txt", "output.txt");
      //Pacman Maze3 = new Pacman("demoMaze.txt", "output.txt");
      Pacman Maze3 = new Pacman("mediumMaze.txt", "output.txt");
     // Pacman Maze3 = new Pacman("randomMaze.txt", "output.txt");
      //Pacman Maze3 = new Pacman("straight.txt", "output.txt");
      //Pacman Maze3 = new Pacman("tinyMaze.txt", "output.txt");
      //Pacman Maze3 = new Pacman("turn.txt", "output.txt");
      //Pacman Maze3 = new Pacman("unsolvable.txt", "output.txt");
     // System.out.println("DFS\n");
      System.out.println("BFS\n");
     Maze3.solveDFS(); 
      //Maze3.solveBFS();
    
      Maze3.writeOutput();
}
 }
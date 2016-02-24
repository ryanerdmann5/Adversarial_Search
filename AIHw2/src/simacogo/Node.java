package simacogo;

import java.util.ArrayList;

//Used for holding the board and depth level for each node in the min max tree
public class Node {
	public Node parent; //the parent node
	public Board state; //the state of the board at this node
	public int score=Integer.MAX_VALUE; //the score assigned to this node by the min max algorithm
	public int level; //the depth in the tree
	public int slot; //which slot the piece was dropped in for this state
	
	
	//constructor for root
	public Node(Board state, Node parent){
		this.state=state;
		this.parent=parent;
		this.level=0;
	}
	
	//constructor for the children
	public Node(Board state, Node parent, int level, int slot){
		this.state=state;
		this.parent=parent;
		this.level=level;
		this.slot=slot;
	}
	
	//gets all the possible children from this state
	public ArrayList<Node> getChildren(){
		ArrayList<Node> a=new ArrayList<Node>();
		for(int i=0;i<=8;i++){
			if(state.canPlace(i)){
				a.add(new Node(state.AICanPlace(i),this, level+1,i));
			}
		}
		return a;
	}
	
	//get the score for a leaf node by using the AI score minus the Human score
	public void getScore(){
		this.score=state.getAIScore()-state.getHumanScore();
	}


	
	
	
	

}

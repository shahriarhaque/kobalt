package collection;

import java.util.HashSet;



public class QuadTree<E> {
	
	public QuadNode<E> root = null;
	
	public QuadTree(int width, int height, int max_level){
		//root = createTree(root, width, height, 0, max_level);
		
		
	}
	
	
	
	private void createTree(QuadNode<E> node, int width, int height, int level,
			int maxLevel) {
		
		node = new QuadNode<E>();
		if(level == maxLevel) return;
		
		
	}



	private class QuadNode<E>{
		public boolean isLeaf = false;
		public int level = 0;
		public int[] xcorners = new int[4];
		public int[] ycorners = new int[4];
		public QuadNode<E>[] children = (QuadNode<E>[]) new Object[4];
		HashSet<E> set = new HashSet<E>();
		
		
		public QuadNode(){}
	}

}


import java.util.ArrayList;
import java.util.Iterator;
public class KDTree implements Iterable<Datum>{ 

	KDNode 		rootNode;
	int    		k; 
	int			numLeaves;
	
	// constructor

	public KDTree(ArrayList<Datum> datalist) throws Exception {

		Datum[]  dataListArray  = new Datum[ datalist.size() ]; 

		if (datalist.size() == 0) {
			throw new Exception("Trying to create a KD tree with no data");
		}
		else
			this.k = datalist.get(0).x.length;

		int ct=0;
		for (Datum d :  datalist) {
			dataListArray[ct] = datalist.get(ct);
			ct++;
		}
		
	//   Construct a KDNode that is the root node of the KDTree.

		rootNode = new KDNode(dataListArray);
	}
	
	//   KDTree methods
	
	public Datum nearestPoint(Datum queryPoint) {
		return rootNode.nearestPointInNode(queryPoint);
	}
	

	public int height() {
		return this.rootNode.height();	
	}

	public int countNodes() {
		return this.rootNode.countNodes();	
	}
	
	public int size() {
		return this.numLeaves;	
	}

	//-------------------  helper methods for KDTree   ------------------------------

	public static long distSquared(Datum d1, Datum d2) {

		long result = 0;
		for (int dim = 0; dim < d1.x.length; dim++) {
			result +=  (d1.x[dim] - d2.x[dim])*((long) (d1.x[dim] - d2.x[dim]));
		}
		// if the Datum coordinate values are large then we can easily exceed the limit of 'int'.
		return result;
	}

	public double meanDepth(){
		int[] sumdepths_numLeaves =  this.rootNode.sumDepths_numLeaves();
		return 1.0 * sumdepths_numLeaves[0] / sumdepths_numLeaves[1];
	}
	
	class KDNode { 

		boolean leaf;
		Datum leafDatum;           //  only stores Datum if this is a leaf
		
		//  the next two variables are only defined if node is not a leaf

		int splitDim;      // the dimension we will split on
		int splitValue;    // datum is in low if value in splitDim <= splitValue, and high if value in splitDim > splitValue  

		KDNode lowChild, highChild;   //  the low and high child of a particular node (null if leaf)
		  //  You may think of them as "left" and "right" instead of "low" and "high", respectively

		KDNode(Datum[] datalist) throws Exception{

			/*
			 *  This method takes in an array of Datum and returns
			 *  the calling KDNode object as the root of a sub-tree containing
			 *  the above fields.
			 */



			//1。如果array只有一个数据点，那么这个KDNode是叶子节点
			if(datalist.length == 1) {
				this.leaf= true;
			}
			//2.如果一个节点的datalist中所有datum都相等那就只保留一个并且视为叶子节点
			this.leaf = true;
			for(int i =0 ; i<datalist.length;++i){
				if(!datalist[i].equals(datalist[0])){
					this.leaf =false;

				}
			}
			//如果确定是叶子节点那就把他的leafDatum 设为datalist【0】并且把儿子设为null
			if(this.leaf){
				numLeaves++;
				this.leafDatum = datalist[0];
				this.highChild= null;
				this.lowChild=null;
				this.leaf=true;
			}else {
				//3.此节点为非叶子节点，将数据分为两个子集，datum is in low if value in splitDim <= splitValue, and high if value in splitDim > splitValue
				//4。确定splitDim 和 splitValue
				double largestDifference = 0;
				splitDim = 0;
				//遍历所有维度i
				for(int i =0; i< datalist[0].x.length;++i){
					int largestNum = Integer.MIN_VALUE;
					int smallestNum = Integer.MAX_VALUE;
					//遍历datumlist中第j个datum的第i维的数  找出最大值最小值
					for(int j=0;j<datalist.length;++j){
						if(datalist[j].x[i] > largestNum)
						{largestNum=datalist[j].x[i];}
						if(datalist[j].x[i] < smallestNum)
						{smallestNum=datalist[j].x[i];}
					}
					//如果第i维的difference更大就把splitDim设为i

					if(largestNum - smallestNum >largestDifference ){
						largestDifference =largestNum - smallestNum;

						splitDim = i;
					}
				}

				//找到了splitDim  找splitvalue
				int largestNum = Integer.MIN_VALUE;
				double smallestNum = Double.MAX_VALUE;
				//System.out.println("datalist长度："+datalist.length);
				for(int i =0;i<datalist.length;i++) {
					if (datalist[i].x[splitDim] > largestNum) {
						largestNum = datalist[i].x[splitDim];
					}
					if (datalist[i].x[splitDim] < smallestNum) {
						smallestNum = datalist[i].x[splitDim];
					}
				}
				double splitValueDouble = (largestNum+smallestNum)/2;

				//5。把该datalist中splitDim的value的值小于等于的存进 lowChild的数组 大于的存进highChild的数组
				int sizeForLow =0;
				int sizeForHigh =0;


				//Datum[] lowsList
				for(int i = 0; i<datalist.length ; i++ ){
					if(datalist[i].x[splitDim] <= splitValueDouble){
						sizeForLow++;
					}else {
						sizeForHigh++;
					}
				}
				Datum[] lowsList=new Datum[sizeForLow];
				Datum[] highsList = new Datum[sizeForHigh];

				for(int i = 0,j = 0,k = 0 ; i<datalist.length; i++){
					if(datalist[i].x[splitDim] <= splitValueDouble){
						lowsList[j]=datalist[i];
						j++;
					}else {
						highsList[k]=datalist[i];
						k++;
					}
				}

				this.splitValue = (int) splitValueDouble;
				this.lowChild=new KDNode(lowsList);
				this.highChild=new KDNode(highsList);
				this.leaf=false;
			}

		}

		public Datum nearestPointInNode(Datum queryPoint) {
			Datum nearestPoint, nearestPoint_otherSide ,closerPoint=null;
			//如果不是叶子
			if(!this.leaf){

				//如果查询点的splitDim的值  小于等于 本节点的splitvalue
				if(queryPoint.x[this.splitDim] <= this.splitValue &&this.lowChild!=null)
				{
				nearestPoint=this.lowChild.nearestPointInNode(queryPoint);
				if(distSquared(nearestPoint,queryPoint)<Math.pow(queryPoint.x[this.splitDim]-this.splitValue,2))
				{
					closerPoint = nearestPoint;
				}else{
					nearestPoint_otherSide = this.highChild.nearestPointInNode(queryPoint);
					closerPoint = closerDatum(nearestPoint,nearestPoint_otherSide,queryPoint);
				}
				return closerPoint;

				}else{
					//如果查询点的splitDim的值  大于 本节点的splitvalue
					if(this.highChild!=null)
					{
						nearestPoint=this.highChild.nearestPointInNode(queryPoint);
						if(distSquared(nearestPoint,queryPoint)<Math.pow(queryPoint.x[this.splitDim]-this.splitValue,2))
						{
							closerPoint=nearestPoint;
						}else {
							nearestPoint_otherSide = this.lowChild.nearestPointInNode(queryPoint);
							closerPoint = closerDatum(nearestPoint,nearestPoint_otherSide,queryPoint);
						}
						return  closerPoint;
					}
				}
			}

			//现在temp就是那个按区域划分找到的点 如果querypoint 和temp的 距离小于querypoint和边界的距离就成了

             if(this.leaf) {
             	return this.leafDatum;
			 }
            return closerPoint;

		}





		public Datum closerDatum(Datum nearestPoint,Datum otherSideNearestPoint,Datum queryPoint){
			if(distSquared(nearestPoint,queryPoint)<distSquared(otherSideNearestPoint,queryPoint))
			{
				return  nearestPoint;
			}
			return otherSideNearestPoint;
		}


		
		// -----------------  KDNode helper methods (might be useful for debugging) -------------------

		public int height() {
			if (this.leaf) 	
				return 0;
			else {
				return 1 + Math.max( this.lowChild.height(), this.highChild.height());
			}
		}

		public int countNodes() {
			if (this.leaf)
				return 1;
			else
				return 1 + this.lowChild.countNodes() + this.highChild.countNodes();
		}
		
		/*  
		 * Returns a 2D array of ints.  The first element is the sum of the depths of leaves
		 * of the subtree rooted at this KDNode.   The second element is the number of leaves
		 * this subtree.    Hence,  I call the variables  sumDepth_size_*  where sumDepth refers
		 * to element 0 and size refers to element 1.
		 */
				
		public int[] sumDepths_numLeaves(){
			int[] sumDepths_numLeaves_low, sumDepths_numLeaves_high;
			int[] return_sumDepths_numLeaves = new int[2];
			
			/*     
			 *  The sum of the depths of the leaves is the sum of the depth of the leaves of the subtrees, 
			 *  plus the number of leaves (size) since each leaf defines a path and the depth of each leaf 
			 *  is one greater than the depth of each leaf in the subtree.
			 */
			
			if (this.leaf) {  // base case
				return_sumDepths_numLeaves[0] = 0;
				return_sumDepths_numLeaves[1] = 1;
			}
			else {
				sumDepths_numLeaves_low  = this.lowChild.sumDepths_numLeaves();
				sumDepths_numLeaves_high = this.highChild.sumDepths_numLeaves();
				return_sumDepths_numLeaves[0] = sumDepths_numLeaves_low[0] + sumDepths_numLeaves_high[0] + sumDepths_numLeaves_low[1] + sumDepths_numLeaves_high[1];
				return_sumDepths_numLeaves[1] = sumDepths_numLeaves_low[1] + sumDepths_numLeaves_high[1];
			}	
			return return_sumDepths_numLeaves;
		}
		
	}


	public Iterator<Datum> iterator() {
		return new KDTreeIterator();
	}

	private class KDTreeIterator implements Iterator<Datum> {
		ArrayList<Datum> datumArrayList;
		int size;
		int index = 0;

		public KDTreeIterator() {
			datumArrayList = new ArrayList<>();
			inOrder(rootNode);
			size = datumArrayList.size();
		}

		//rootNode.inOrder
		public void inOrder(KDNode node) {
			if (node.lowChild != null) {
				inOrder(node.lowChild);
			}
			if (node.leaf) {
				datumArrayList.add(node.leafDatum);
			}
			if (node.highChild != null) {
				inOrder(node.highChild);
			}
		}

		@Override
		public boolean hasNext() {
			return size > index;
		}

		@Override
		public Datum next() {
			return datumArrayList.get(index++);
		}

	}
}


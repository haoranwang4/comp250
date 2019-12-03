import java.util.HashMap;
import java.util.ArrayList;

public class SearchEngine {
	public HashMap<String, ArrayList<String> > wordIndex;   // this will contain a set of pairs (String, LinkedList of Strings)	
	public MyWebGraph internet;
	public XmlParser parser;

	public SearchEngine(String filename) throws Exception{
		this.wordIndex = new HashMap<String, ArrayList<String>>();
		this.internet = new MyWebGraph();
		this.parser = new XmlParser(filename);
	}
	
	/* 
	 * This does a graph traversal of the web, starting at the given url.
	 * For each new page seen, it updates the wordIndex, the web graph,
	 * and the set of visited vertices.
	 * 
	 * 	This method will fit in about 30-50 lines (or less)
	 */
	public void crawlAndIndex(String url) throws Exception {
		ArrayList<String> linkArray = parser.getLinks(url);
		internet.addVertex(url);
		internet.setVisited(url,true);
		//全部content都复制到answers里面
		ArrayList<String> content = parser.getContent(url);
		//记录结果
		ArrayList<String> results = new ArrayList<>();
		results.add(content.get(0));

		for(int i =1 ; i <content.size();++i){
			boolean isContained = false;
			for(int j = 0; j < results.size() ; j++){
				if(results.get(j).equalsIgnoreCase(content.get(i))){
					isContained = true;
					break;
				}
			}
			if(!isContained){
				results.add(content.get(i));
			}
		}

		wordIndex.put(url,results);

		for(int i = 0; i < linkArray.size();i++){

			if(!internet.getVisited(linkArray.get(i))){
				crawlAndIndex(linkArray.get(i));
			}
			internet.addEdge(url,linkArray.get(i));
		}

	}


	
	
	/* 
	 * This computes the pageRanks for every vertex in the web graph.
	 * It will only be called after the graph has been constructed using
	 * crawlAndIndex(). 
	 * To implement this method, refer to the algorithm described in the 
	 * assignment pdf. 
	 * 
	 * This method will probably fit in about 30 lines.
	 */

	public void assignPageRanks(double epsilon) {
		ArrayList<String> list = internet.getVertices();
		ArrayList<Double> prev = new ArrayList<Double>();
		for (int k = 0; k < list.size();++k) {//generate a prev arraylist for compare.
			prev.add(1.0);
		}
		for (int i = 0; i< list.size();++i) {
			internet.setPageRank(list.get(i), 1.0);
		}


		while(true){
			ArrayList<Double> next = computeRanks(list);
			if(findMaxDiff(prev,next)>=epsilon)
			{System.out.println("prev:"+prev);
			prev = next;
				System.out.println("Next:"+prev);
			for (int i = 0; i< list.size();++i) {
			internet.setPageRank(list.get(i), prev.get(i));
			}
			}else {
				break;
			}

		}
	}

	public double findMaxDiff (ArrayList<Double> a, ArrayList<Double> b) {
		double maxDiff = 0.0;
		for(int i = 0 ; i < a.size();i++) {
			if(Math.abs((a.get(i)-b.get(i)))> maxDiff   ) {
				maxDiff=Math.abs(a.get(i)-b.get(i));
			}
		}
		return maxDiff;
	}


	/*
	 * The method takes as input an ArrayList<String> representing the urls in the web graph
	 * and returns an ArrayList<double> representing the newly computed ranks for those urls.
	 * Note that the double in the output list is matched to the url in the input list using
	 * their position in the list.
	 */
	public ArrayList<Double> computeRanks(ArrayList<String> vertices) {
		ArrayList<Double> results = new ArrayList<Double>();

		for (int i = 0; i < vertices.size(); i++) {
			double temp = 0.5;
			//第0个url的目标url
			ArrayList<String> targets = internet.getEdgesInto(vertices.get(i));
			for(int j = 0;j< targets.size();++j ){
				temp+=(0.5)*(internet.getPageRank(targets.get(j)))/(internet.getOutDegree(targets.get(j)));
			}
			results.add(temp);

		}

		return results;
	}


	/* Returns a list of urls containing the query, ordered by rank
	 * Returns an empty list if no web site contains the query.
	 *
	 * This method should take about 25 lines of code.
	 */
	public ArrayList<String> getResults(String query) {
//store all url containning quert in a arraylist
		ArrayList<String> urlContainQuery= new ArrayList<>();
		ArrayList<String> urlList = internet.getVertices();
		for(int j = 0 ; j< urlList.size();j++){
			if(parser.getContent(urlList.get(j)).contains(query)){
				urlContainQuery.add(urlList.get(j));
			}
		}
		// make a hashmap with comparable Double rank
		HashMap <String,Double> results = new HashMap<>();
		for(int i = 0 ; i < urlContainQuery.size();++i){
		results.put(urlContainQuery.get(i),internet.getPageRank(urlContainQuery.get(i)));
		}
		return Sorting.fastSort(results);


	}
}


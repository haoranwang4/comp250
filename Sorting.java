import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry; // You may need it to implement fastSort

public class Sorting {

	/*
	 * This method takes as input an HashMap with values that are Comparable. 
	 * It returns an ArrayList containing all the keys from the map, ordered 
	 * in descending order based on the values they mapped to. 
	 * 
	 * The time complexity for this method is O(n^2) as it uses bubble sort, where n is the number 
	 * of pairs in the map. 
	 */
    public static <K, V extends Comparable> ArrayList<K> slowSort (HashMap<K, V> results) {
        ArrayList<K> sortedUrls = new ArrayList<K>();
        sortedUrls.addAll(results.keySet());	//Start with unsorted list of urls

        int N = sortedUrls.size();
        for(int i=0; i<N-1; i++)
        {
			for(int j=0; j<N-i-1; j++)
			{
				if(results.get(sortedUrls.get(j)).compareTo(results.get(sortedUrls.get(j+1)))<0)
				{
					K temp = sortedUrls.get(j);
					sortedUrls.set(j, sortedUrls.get(j+1));
					sortedUrls.set(j+1, temp);					
				}
			}
        }
        return sortedUrls;                    
    }
    
    
	/*
	 * This method takes as input an HashMap with values that are Comparable. 
	 * It returns an ArrayList containing all the keys from the map, ordered 
	 * in descending order based on the values they mapped to. 
	 * 
	 * The time complexity for this method is O(n*log(n)), where n is the number 
	 * of pairs in the map. 
	 */
    public static <K, V extends Comparable> ArrayList<K> fastSort(HashMap<K, V> results)
	{
		ArrayList<K> sortedUrls = new ArrayList<K>();
		sortedUrls.addAll(results.keySet());
		int N = sortedUrls.size();
		mergeSort(sortedUrls,0,N-1,results);
		return  sortedUrls;
    }

    public static <K, V extends Comparable> void mergeSort (ArrayList<K> arr,int start,int end,HashMap <K,V> map){
    	if(start<end)
    	{
    		int middle = (start+end)/2;
    		mergeSort(arr,start,middle,map);
    		mergeSort(arr,middle+1,end,map);
    		merge(arr,start,middle,end,map);


		}

	}
	public  static <K, V extends Comparable> void merge(ArrayList <K> arr, int start,int middle,int end,HashMap <K,V> map){
    	ArrayList<K> temp = new ArrayList<K>();
    	int i = start,j = middle+1;
    	while (i <= middle && j <= end)
    	{
    		if( map.get(arr.get(i)).compareTo(map.get(arr.get(j))) >= 0 )
    		{
    			temp.add(arr.get(i));
    			i++;
			}
    		else if(map.get(arr.get(i)).compareTo(map.get(arr.get(j))) < 0)
			{
				temp.add(arr.get(j));
				j++;
			}
		}
    	while (j<=end)
    	{
    		temp.add(arr.get(j));
    		j++;
		}
    	while (i<=middle)
    	{
    		temp.add(arr.get(i));
    		i++;
		}
    	for(int k=0; k<temp.size();k++)
    	{
    		arr.set(k+start,temp.get(k));
		}
    }
    

}
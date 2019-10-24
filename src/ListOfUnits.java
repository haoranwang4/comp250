import java.util.Arrays;

public class ListOfUnits {
    private Unit[] arr;//array used to store units

    private int sizeOfUnits;//# of units stored in arr



    //constructor arr:initial length 10    now:zero unit is stored
    public ListOfUnits() {
        arr = new Unit[10];
        this.sizeOfUnits =0;
    }


    public int size(){
        return sizeOfUnits;
    }


    //get a list excluding null elements
    public Unit[] getUnits(){
        Unit[] newArr = new Unit[sizeOfUnits];
        for(int i =0;i<newArr.length;i++){
            newArr[i]=arr[i];
        }
        return newArr;
    }


    //get unit in index position
    public Unit get(int index){
        if(index<0 || index >= sizeOfUnits){
            throw new IndexOutOfBoundsException("Your Index is Out Of Bounds!");
        }
        return arr[index];
    }


    //add one unit to the arr
    public void add(Unit unit){
        if(arr.length<=sizeOfUnits){
            largerArr();
        }
        arr[sizeOfUnits] = unit;
        sizeOfUnits++;
    }

    //my own method to enlarge the size of the arr
    private void largerArr (){
        Unit[] newArr = new Unit[arr.length+(arr.length/2)+1];

        for(int i =0;i<arr.length;i++){
            newArr[i]=arr[i];
        }
        arr=newArr;
    }


    public int indexOf(Unit unit){
        int index = -1;
        for(int i=0;i<arr.length;++i){
            if(arr[i].equals(unit)){
                index=i;
                return index;
            }
        }
        return index;
    }

    public boolean remove(Unit unit){
        int index = indexOf(unit);  //index is the index for the target element unit

        if (index != -1){
            //exists!-->delete  arr[index]  and return true

            Unit[] newArr = new Unit[arr.length];
            for (int i=0;i<newArr.length-1;++i)
            {
                if(i<index){
                    newArr[i]=arr[i];
                }else{
                    newArr[i]=arr[i+1];
                }
            }
            //in order not to change the size of this list,set the last element to null after deleting one element
            //newArr[newArr.length-1]=null;
            arr=newArr;
            //size decreases
            sizeOfUnits--;
            return true;
        }
        //index=-1 cannot find unit
        return false;
    }


    //if is militaryunit    it is either warrior or archer
    public MilitaryUnit[] getArmy (){
        int count=0;
        for(int i=0;i<arr.length;++i){
            if(arr[i] instanceof  MilitaryUnit)
            {
                count++;
            }

        }
        MilitaryUnit[] newArr = new  MilitaryUnit[count];
        int index =0;
        for(int i=0;i<arr.length;++i){
            if(arr[i] instanceof MilitaryUnit){
                newArr[index]=(MilitaryUnit) arr[i];
                index++;
            }
        }
        return newArr;
    }


}

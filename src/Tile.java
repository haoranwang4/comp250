

public class Tile {
    private int xCoor;
    private int yCoor;
    private boolean isCityBuilt;
    private boolean isImprovementsReceived;
    private ListOfUnits unitsPositioned;



    //constructor
    public  Tile(int x,int y){
        this.xCoor=x;
        this.yCoor=y;
        isCityBuilt=false;
        isImprovementsReceived=false;
        unitsPositioned=new ListOfUnits();
    }


    public int getX() {
        return xCoor;
    }


    public int getY() {
        return yCoor;
    }

    public  boolean isCity(){
        return isCityBuilt;
    }

    public boolean isImproved(){
        return isImprovementsReceived;
    }

    public void foundCity (){
        if(!isCityBuilt)
        this.isCityBuilt=true;
    }

    public void buildImprovement (){
        if(!isImprovementsReceived){
            this.isImprovementsReceived=true;
        }
    }



    public boolean addUnit(Unit unit){
        if (unit instanceof MilitaryUnit) {
            for (int i=0;i<unitsPositioned.size();i++){
                if(unitsPositioned.get(i) instanceof MilitaryUnit){
                    if(!unitsPositioned.get(i).getFaction().equals(unit.getFaction()))
                        return false;
                }
            }
            //true add!
            unitsPositioned.add(unit);
            return true;
        } else{
            //true add!
            unitsPositioned.add(unit);
            return true;
        }
    }


    public boolean removeUnit(Unit unit){

        return unitsPositioned.remove(unit);

        }


    public Unit selectWeakEnemy (String faction){

        //set lowest hp to the hp of the first unit in the list
        int lowestIndex=-1;
        double lowestHp = Double.MAX_VALUE;
        for(int i=0;i<unitsPositioned.size();++i){
            if(! unitsPositioned.get(i).getFaction().equals(faction)&&unitsPositioned.get(i).getHP()<lowestHp){
                lowestIndex = i;
            }
        }
        if(lowestIndex == -1){
            return  null;
        }
        return unitsPositioned.get(lowestIndex);

    }


    public static double getDistance(Tile tile1,Tile tile2){
        int x1=tile1.xCoor;
        int y1=tile1.yCoor;
        int x2=tile2.xCoor;
        int y2=tile2.yCoor;
        double distance = Math.sqrt(Math.pow((x1-x2),2)+Math.pow((y1-y2),2));
        return distance;
    }




}

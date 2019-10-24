

public class Settler extends Unit{

    public Settler(Tile position, double hp,  String faction) {
        super(position,hp,2,faction);

    }

    @Override
    public void takeAction(Tile tile) {
        //if the same positon and is not a city         then is a city
        if(this.getPosition().getX()==tile.getX()&&this.getPosition().getY()==tile.getY()&&!tile.isCity()){
            tile.foundCity();
            this.getPosition().removeUnit(this);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Settler)
        {
            //use equal method from Unit
            return super.equals(obj);
        }
        //if not a settler
        return false;
    }






}

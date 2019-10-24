

public abstract class Unit {
private Tile position;
private double hp;
private int movingRange;
private String faction;

public Unit(Tile position,double hp,int movingRange,String faction){
    this.faction =faction;
    this.movingRange=movingRange;
    this.hp=hp;
    this.position=position;
    if(! position.addUnit(this)){
     throw new IllegalArgumentException("Illegal Argument!");
    }
}


    public final Tile getPosition(){
    return this.position;
}

    public final double getHP(){
    return this.hp;
}

    public final String getFaction() {
        return this.faction;
    }

    public boolean moveTo (Tile tile){
    if((this.position.getDistance(this.position,tile)) < (this.movingRange+1) && tile.addUnit(this)) {
        this.position.removeUnit(this);
        position=tile;
        return true;
    }
    return false;
    }

    public  void receiveDamage (double damage){
    if (this.position.isCity()){
        damage*=0.9;
    }
    this.hp-=damage;
    if(this.hp<=0){
        this.position.removeUnit(this);
    }
}

    public abstract void takeAction(Tile tile);

    @Override
    public boolean equals(Object obj) {
        //if is unit,has same hp/position/faction then true
        if(obj instanceof Unit&&((Unit) obj).getPosition().getX()==this.getPosition().getX()&&((Unit) obj).getPosition().getY()==this.getPosition().getY()&&((Unit) obj).getHP()==this.getHP()&&((Unit) obj).getFaction().equals(this.getFaction())){
            return true;
        }
        return false;
    }



}

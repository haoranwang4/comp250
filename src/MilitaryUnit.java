

public abstract class MilitaryUnit extends Unit {
    private double attackDamage;
    private int attackRange;
    private int armor;

    public MilitaryUnit(Tile position,double hp,int movingRange,String faction,double attackDamage,int attackRange,int armor){
        super(position,hp,1,faction);
        this.attackDamage=attackDamage;
        this.attackRange=attackRange;
        this.armor=armor;
    }

    @Override
    public void takeAction(Tile tile) {
        double damage =this.attackDamage;
        if(tile.selectWeakEnemy(this.getFaction())!=null){
        //double damage =this.attackDamage;
        if(!(this.getPosition().getDistance(this.getPosition(),tile) >=  this.attackRange+1)){
            //if is improved,damage should times 1.05
            if(this.getPosition().isImproved()){
                damage=this.attackDamage *1.05;
            }
            tile.selectWeakEnemy(this.getFaction()).receiveDamage(damage);
        }
    }
    }



    @Override
    public void receiveDamage(double damage) {
        damage*=100/(100+this.armor);
        super.receiveDamage(damage);
    }
}

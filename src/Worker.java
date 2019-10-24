

public class Worker extends Unit {
    private int numOfJobsPerformed ;


    public Worker(Tile position,double hp,String faction){
        super(position,hp,2,faction);
        this.numOfJobsPerformed=0;
    }

    @Override
    public void takeAction(Tile tile) {
        if(this.getPosition().getX()==tile.getX()&&this.getPosition().getY()==tile.getY()&&!tile.isImproved()){
            tile.buildImprovement();
            this.numOfJobsPerformed++;
            if(this.numOfJobsPerformed == 10){
                this.getPosition().removeUnit(this);
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Worker && ((Worker) obj).numOfJobsPerformed == this.numOfJobsPerformed)
        {
            return super.equals(obj);
    }
        return false;
    }



}

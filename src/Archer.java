

public class Archer extends MilitaryUnit {

    private int numOfArrows;


    public Archer(Tile position, double hp, String faction) {
        super(position, hp, 2, faction, 15.0, 2, 0);
        this.numOfArrows = 5;
    }


    @Override
    public void takeAction(Tile tile) {
        if (this.numOfArrows == 0) {
            this.numOfArrows = 5;
        } else {
            super.takeAction(tile);
            this.numOfArrows -= 1;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Archer && this.numOfArrows == ((Archer) obj).numOfArrows) {
            return super.equals(obj);
        }
        return false;
    }


}
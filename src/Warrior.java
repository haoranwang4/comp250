

public class Warrior extends MilitaryUnit {
    private double attackDamage;
    private int attackRange;
    private int armor;

    public Warrior(Tile position, double hp, String faction) {
        super(position, hp, 1, faction, 20.0, 1, 25);

    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Warrior) {
            return super.equals(obj);
        }
        return false;
    }

}
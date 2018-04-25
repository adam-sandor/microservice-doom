package doom.doomengine;

public enum DemonType {

    Imp(25), Revenant(40), Mancubus(60), Cacodemon(30);

    DemonType(int damage) {
        this.damage = damage;
    }

    int damage;
}

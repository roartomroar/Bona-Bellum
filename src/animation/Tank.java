package animation;


/*
 * Really basic as of yet
 */
public class Tank {

    private int x, y, hp, dolards;
    private double fuel;

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
        this.hp = 100;
        this.fuel = 50;
        this.dolards = 0;
    }
    
    static TanksMap.Ground[][] map;
    
}

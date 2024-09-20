import java.util.ArrayList;

public class GameData {
    private ArrayList<Birds> allBirds = new ArrayList<Birds>();
    private ArrayList<Pigs> allPigs = new ArrayList<Pigs>();
    private ArrayList<Blocks> allblocks = new ArrayList<Blocks>();
    User user;
    public ArrayList<Birds> getAllBirds() {
        return allBirds;
    }

    public ArrayList<Pigs> getAllPigs() {
        return allPigs;
    }

    public ArrayList<Blocks> getAllblocks() {
        return allblocks;
    }

    public void setAllBirds(ArrayList<Birds> allBirds) {
        this.allBirds = allBirds;
    }

    public void setAllPigs(ArrayList<Pigs> allPigs) {
        this.allPigs = allPigs;
    }

    public void setAllblocks(ArrayList<Blocks> allblocks) {
        this.allblocks = allblocks;
    }
    public void saveLevel(Level level) {}



}

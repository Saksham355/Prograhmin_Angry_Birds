import java.util.ArrayList;

public class Level {
    private ArrayList<Birds> birds = new ArrayList<Birds>();
    private ArrayList<Pigs> pigs = new ArrayList<Pigs>();
    private  ArrayList<Blocks> blocks = new ArrayList<>();
    private String structure;
    private int  stars;
    public void setBirds(ArrayList<Birds> birds) {
        this.birds = birds;
    }

    public void setPigs(ArrayList<Pigs> pigs) {
        this.pigs = pigs;
    }

    public void setBlocks(ArrayList<Blocks> blocks) {
        this.blocks = blocks;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }
    public ArrayList<Birds> getBirds() {
        return birds;
    }

    public ArrayList<Pigs> getPigs() {
        return pigs;
    }

    public ArrayList<Blocks> getBlocks() {
        return blocks;
    }

    public String getStructure() {
        return structure;
    }

    public int getStars() {
        return stars;
    }
}

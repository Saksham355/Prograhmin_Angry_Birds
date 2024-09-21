import java.util.ArrayList;

public class User {
    private String name;
    private String id;
    private int level;
    int gems;
    int coins;
    ArrayList<User>friends = new ArrayList<User>();

    public String getId() {
        return id;
    }

    public int getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    void updateRewards(){};
    void buyGems(){};
    void getCoins(){};
    void changeDetails(){};
    void sendInv(){};
    void Logout(){};

}

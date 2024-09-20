import java.util.ArrayList;
public class Menu {

    int displayGems(GameData g){
        return g.user.gems;
    }
    int displayCoins(GameData g){
        return g.user.coins;
    }
    String displayName(GameData g){
        return g.user.getName();
    }
    int displayLevel(GameData g){
        return g.user.getLevel();
    }
    void howtoplay(GameData g){}
    void logout(GameData g){
        g.user.Logout();
    }

}

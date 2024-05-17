package Models;

public class Player {

    private int nbWins;
    private String playerName;

    /***Constructor For The Leaderboard***/
    public Player(String playerName, int nbWins){
        this.playerName = playerName;
        this.nbWins = nbWins;
    }

    /***Constructor For the Adapter***/
    public Player(String playerName){
        this.playerName = playerName;

    }

    /***Empty***/
    public Player(){}

    /***Getters***/
    public int getNbWins() {return nbWins;}
    public String getPlayerName() {return playerName;}

    /***Setters***/
    public void setNbWins(int nbWins) {this.nbWins = nbWins;}
    public void setPlayerName(String playerName) {this.playerName = playerName;}

    @Override
    public String toString() {
        return String.format("{0} wins: {1}",playerName, nbWins);
    }
}

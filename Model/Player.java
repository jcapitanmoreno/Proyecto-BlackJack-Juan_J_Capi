package Model;

import java.util.Objects;

public class Player {

   private String name = " ";
   private int score = 0;



    public Player(String name, int score) {
        this.name = name;
        this.score = score;

    }
    Player(){
        this.name= null;
        this.score= 0;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return score == player.score && Objects.equals(name, player.name);
    }

    @Override
    public String toString() {
        return "Player{" + "name='" + name + '\'' +", score=" + score + '}';
    }
}

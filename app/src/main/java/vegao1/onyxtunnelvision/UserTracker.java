package vegao1.onyxtunnelvision;

public class UserTracker implements Runnable {
    Thread t;
    Player player;
    WorldMap World;
    public UserTracker(Player user1,WorldMap w)
    {
        player=user1;
        World=w;
    }
    @Override
    public void run() {
        while (8<player.getType()&&player.getType()<11){}
        for (int i=0;i<12;i++){
            for (int j =0;j<12;j++){
                World.setPoint(i,j,new DeadPlayer());
            }
        }
    }
}

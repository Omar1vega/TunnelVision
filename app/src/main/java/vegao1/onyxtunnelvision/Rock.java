package vegao1.onyxtunnelvision;
public class Rock extends OnyxPoint {
    private boolean groundBelowMe;
    private WorldMap world;
    public Rock(WorldMap world, int x,int y){
        super(x,y,12);
        this.world = world;
    }
    public void update(){
        groundBelowMe = (((world.getPoint(this.getX(),this.getY()+1)).getType() == 2)||((world.getPoint(this.getX(),this.getY()+1)).getType() == 1));
        if (!groundBelowMe){
            fall();
        }
    }
    public void fall(){
        this.kill();
        (world.getPoint(this.getX(),this.getY()+1)).kill();
    }
}

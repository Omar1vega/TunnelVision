package vegao1.onyxtunnelvision;

/**
 * Created by vegao1 on 5/22/2017.
 */

public class Character extends OnyxPoint {
    protected boolean live;
    protected boolean up;
    protected boolean down;
    protected boolean left;
    protected boolean right;
    protected WorldMap world;

    public Character() {
        super();
    }
    public Character(int x, int y, int i) {
        super(x, y, i);
    }
    public void update() {
    }
    protected void moveLeft(){
        double x=getX();
        if ((x-1)>=0) {
            setX(getX()-1);
            left=true;
            up=false;
            down=false;
            right=false;
        }
    }
    protected void moveRight(){
        double x=getX();
        if ((x+1)<12) {
            setX(getX()+1);
            left=false;
            right=true;
            up=false;
            down=false;
        }
    }//right
    protected void moveUp(){
        double y=getY();
        if ((y-1)>=0){
            setY(getY()-1);
            left=false;
            right=false;
            up=true;
            down=false;
        }
    }
    protected void moveDown(){
        double y=getY();
        if((y+1)<12) {
            setY(getY() + 1);
            left=false;
            right=false;
            up=false;
            down=true;
        }
    }
    //GETTERZ
    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }
}

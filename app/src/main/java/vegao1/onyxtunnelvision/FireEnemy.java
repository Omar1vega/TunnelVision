package vegao1.onyxtunnelvision;

import android.widget.FrameLayout;
import android.widget.Switch;

import java.util.Random;

/**
 * Created by noah_k on 5/23/2017.
 */

public class FireEnemy extends Character{
    private boolean Right;
    private boolean Left;
    private boolean Up;
    private boolean Down;
    protected WorldMap world;
    public FireEnemy(int x, int y, WorldMap w){
        super(x,y,5);
        world=w;
        Right=true;
        Left=true;
        Up=true;
        Down=true;
        left=true;
    }
    @Override
    public void graphic(){
        type=(5==type)?6:5;
    }
    @Override
    public void update(){
        if(live){
            right=true;
            Right=true;
            act();
        }//end live
    }//end update()

    //ask should i move right or left?
    protected void act(){
        int x=getX();
        int y=getY();
        if (Right){
            if(12>1+x) {
                x += 1;
                if (world.getPoint(x,y).getType() != 2) {
                    moveRight();
                    world.getPoint(x,y).kill();
                    return;
                }else {
                    Right=false;
                }
            }
        }//end right
        else if (Left){
            if(0<=x-1) {
                x -= 1;
                if (world.getPoint(x, y).getType() != 2) {
                    moveLeft();
                    world.getPoint(x,y).kill();
                    return;
                }else {
                    Left=false;
                }
            }
        }//end right
        else if (Up){
            if(0<=y-1) {
                y -= 1;
                if (world.getPoint(x, y).getType() != 2) {
                    moveUp();
                    world.getPoint(x,y).kill();
                    return;
                }
                else {
                    Up=false;
                }
            }
        }//end right
        else if (Down){
            if(12>1+y) {
                y += 1;
                if (world.getPoint(x, y).getType() != 2) {
                    moveDown();
                    world.getPoint(x,y).kill();
                    return;
                } else {
                    Down = false;
                }
            }
        }//end right
        else{
            Right=true;
            Left=true;
            Up=true;
            Down=true;
            act();
        }
    }
}

package vegao1.onyxtunnelvision;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by noah_k on 5/11/2017.
 */

public class Enemy extends Character {
    private boolean Right;
    private boolean Left;
    private boolean Up;
    private boolean Down;
    protected WorldMap world;

    //const
    public Enemy(int x,int y, int type, WorldMap w){
        super(x,y,type);
        world=w;
        Right=true;
        Left=true;
        Up=true;
        Down=true;
        live=true;
    }
    @Override
    public void update(){
        if(live){
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

    public void graphic(){
        /*type=(3==type)?4:3;*/
    }
}

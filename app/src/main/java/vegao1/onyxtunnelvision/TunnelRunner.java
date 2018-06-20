package vegao1.onyxtunnelvision;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import java.util.Random;

public class TunnelRunner implements Runnable {
    Thread t;
    TunnelView myView;
    WorldMap world;
    public TunnelRunner(TunnelView view,WorldMap myMap)
    {
        myView=view;
        world=myMap;
    }
    @Override
    public void run() {
        SurfaceHolder sh;
        boolean fireball=true;
        Random rr= new Random();
        Canvas canvas;
        while (true) {
            try{
                Thread.sleep(400);
            }catch (InterruptedException e)
            {}
            sh = myView.getHolder();
            canvas = sh.lockCanvas();
            if (canvas != null) {
                fireball=(2==rr.nextInt(3));
                world.updateGameState();
                world.placePart();
                myView.draw(canvas);
                if (fireball){
                    fireBall(canvas);
                }
                sh.unlockCanvasAndPost(canvas);
            }
        }
    }//end run
    //fireball method for the fireEnemy
    //return the type of the onyx point killed
    public int fireBall(Canvas canvas){
        int x,y,t;
        x=world.fireEnemy.getX();
        y=world.fireEnemy.getY();
        if (world.fireEnemy.isUp()) {
            if(0<=(y-1)){
                y-=1;
                t= burn(canvas,x,y);
                return t;
            }else{return 0;}//end up
        } else if (world.fireEnemy.isDown()) {
            if(12>(y+1)){
                y+=1;
                t=burn(canvas,x,y);
                return t;
            }else{return 0;}//end down
        } else if (world.fireEnemy.isLeft()) {
            if (0<=(x-1)){
                x-=1;
                t=burn(canvas,x,y);
                return t;
            }else{return 0;}
        } else if (world.fireEnemy.isRight()){
            if(12>(x+1)){
                x+=1;
                t=burn(canvas,x,y);
                return t;
            } else{return 0;}
            //end right
        }else{return 0;}//end fire
    }
    //return the type of obj hit with fire, print fire
    private int burn(Canvas canvas,int x,int y){
        int t;
        myView.drawPart(canvas,x,y,7);
        t=world.getPoint(x,y).getType();
        if (t>8){
            if(world.getPoint(x,y)==world.player){
                world.player.type=12;
            }
            world.setPoint(x,y,new Fire());
        }//end killing
        return t;
    }
}

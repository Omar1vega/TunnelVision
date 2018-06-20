package vegao1.onyxtunnelvision;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class PlayerRunner implements Runnable {
    TunnelView myView;
    WorldMap world;
    public PlayerRunner(TunnelView view,WorldMap myMap) {
        myView=view;
        world=myMap;
    }
    @Override
    public void run() {
        SurfaceHolder sh;
        Canvas canvas;
        while (true) {
            sh = myView.getHolder();
            canvas = sh.lockCanvas();
            if (canvas != null) {
                world.updateGameState();
                world.placePart();
                myView.draw(canvas);
                sh.unlockCanvasAndPost(canvas);
            }
        }
    }

}

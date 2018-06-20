package vegao1.onyxtunnelvision;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by noah_k on 5/16/2017.
 */

public class GraphRun extends TunnelRunner {
    Thread t;
    public GraphRun(TunnelView tv, WorldMap map){
        super(tv,map);
    }
    @Override
    public void run(){

        SurfaceHolder sh;
        Canvas canvas;
        while (true) {
            try{
                Thread.sleep(100);
            }catch (InterruptedException e)
            {}
            sh = myView.getHolder();
            canvas = sh.lockCanvas();
            if (canvas != null) {
                world.updateGraphic();
                myView.draw(canvas);
                sh.unlockCanvasAndPost(canvas);
            }
        }//end while
    }
}

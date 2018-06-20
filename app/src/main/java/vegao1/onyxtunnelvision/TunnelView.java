package vegao1.onyxtunnelvision;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.print.PrintAttributes;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.media.JetPlayer;
public class TunnelView extends SurfaceView implements SurfaceHolder.Callback {
    private final int numOfTypes=15;
    private Bitmap[] bitmaps = new Bitmap[numOfTypes];
    private Bitmap[] controls = new Bitmap[3];
    private TunnelRunner tr;
    private GraphRun gr;
    private UserTracker ut;
    private Thread runner3;
    private Thread runner1;
    private Thread runner2;
    private WorldMap world;
    private final int SCALE=97;
    private final int RULE=1;
    private final int START=0;
    private int dpadL;
    private int dpadR;
    private int dpadT;
    private int dpadB;
    private int dpadLength;




    private Paint p = new Paint(Color.RED);

    public TunnelView(Context context)
    {
        super(context);
        getHolder().addCallback(this);
        setFocusable(true);
    }
    public void drawPart(Canvas canvas,int xCord,int yCord,int type){
        int width = getWidth();
        int height = getHeight()-getHeight()/6;
        int rowSize = width/12;
        int columnSize = height/12;
        Rect dst =new Rect();
        dst.set(xCord * rowSize, yCord * columnSize, (xCord + 1) * rowSize, (yCord + 1) * columnSize);
        canvas.drawBitmap(bitmaps[type],null,dst,null);
    }
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(Color.WHITE); // Set the background to black
        Rect dst = new Rect();
        dpadLength = getHeight()/6;
        dpadL = (getWidth()/10);
        dpadT = (getHeight()-getHeight()/6);
        dpadR = dpadL + dpadLength;
        dpadB = dpadT + dpadLength;

        dst.set(dpadL,dpadT,dpadR,dpadB);
        canvas.drawBitmap(controls[0],null,dst,null);
        dst.offsetTo(2*getWidth()/3,dpadT);// shift the rect over to right by the length of the dpad
        canvas.drawBitmap(bitmaps[7],null,dst,null);

        printMap(canvas);
    }
    //prints the map based on each point type

    private void printMap(Canvas canvas){
        int width = getWidth();
        int height = getHeight()-(getHeight()/6);
        int rowSize = width/12;
        int columnSize = height/12;
        int x,y;
        for (int i=0;i<12;i++){
            for (int j=0;j<12;j++){
                Rect dst = new Rect();
                dst.set(i * rowSize, j * columnSize, (i + 1) * rowSize, (j + 1) * columnSize);
                canvas.drawBitmap(bitmaps[world.getPoint(i,j).getType()], null, dst, null); //Draw the bitmap

            }//end y
        }//end x
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        bitmaps[0] = BitmapFactory.decodeResource(getResources(),
                R.drawable.empty);//empty square
        bitmaps[1] = BitmapFactory.decodeResource(getResources(),
                R.drawable.ground1);//ground
        bitmaps[2] = BitmapFactory.decodeResource(getResources(),
                R.drawable.ground2);//ground second pic
        bitmaps[3] = BitmapFactory.decodeResource(getResources(),
                R.drawable.enemy1);//enemy 1st pic
        bitmaps[4] = BitmapFactory.decodeResource(getResources(),
                R.drawable.enemy2);//enemy 2nd pic
        bitmaps[5] = BitmapFactory.decodeResource(getResources(),
                R.drawable.fire_enemy1);//fire enemy
        bitmaps[6] = BitmapFactory.decodeResource(getResources(),
                R.drawable.fire_enemy2);//2nd view of fire enemy
        bitmaps[7] = BitmapFactory.decodeResource(getResources(),
                R.drawable.fire);//fire
        bitmaps[8] = BitmapFactory.decodeResource(getResources(),
                R.drawable.fire_gun);//empty square
        bitmaps[9] = BitmapFactory.decodeResource(getResources(),
                R.drawable.player1);//player 1
        bitmaps[10] = BitmapFactory.decodeResource(getResources(),
                R.drawable.player2);//player view 2
        bitmaps[11] = BitmapFactory.decodeResource(getResources(),
                R.drawable.player_dead);//player when dead
        bitmaps[12] = BitmapFactory.decodeResource(getResources(),
                R.drawable.rock);//rock
        controls[0] = BitmapFactory.decodeResource(getResources(),
                R.drawable.dpad); //directional pad controller
        //meth
        world = new WorldMap();
        tr=new TunnelRunner(this, world);
        gr = new GraphRun(this, world);
        ut = new UserTracker(world.player,world);
        runner1=new Thread(tr);
        runner2=new Thread (gr);
        runner3=new Thread(ut);
        runner1.start();
        runner2.start();
        runner3.start();
        invalidate();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        invalidate();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    }
    @Override
    public boolean onTouchEvent ( MotionEvent e ) {
        System.out.println(""+(int)e.getX()+" "+(int)e.getY());
        switch(e.getAction()){
            case MotionEvent.ACTION_DOWN:
                int x = (int) e.getX();
                int y = (int) e.getY();
                // L or R, have the same Y Boundaries
                if( (y>(dpadT+(dpadLength/3))) && (y<(dpadB-(dpadLength)/3)) ){
                    if(x>dpadL && x<(dpadL+(dpadLength/3))){
                        world.player.moveLeft();
                    }
                    if(x>dpadR-(dpadLength/3) && x<dpadR){
                        world.player.moveRight();
                    }
                }
                // Up / Down have the same X boundaries
                if( (x>(dpadL+(dpadLength/3))) && (x<(dpadR-(dpadLength)/3)) ){
                    if(y>dpadT && y<dpadT+(dpadLength/3)){
                        world.player.moveUp();
                    }
                    if((y>dpadB-(dpadLength/3)) && y<dpadB){
                        world.player.moveDown();
                    }
                }
                //boundaries for 'fire'
                if(y>dpadT && y< dpadB){
                    if(x>(2*getWidth()/3) && x<(2*getWidth()/3)+dpadLength){
                        System.out.println("FIRE");
                        fireGun();
                    }
                }
                System.out.print("CALLING PLACE PART");
                world.placePart();
        }
        return true;
    }//end on touch
    //fire the gun of the player
    public void fireGun(){
        int x,y;
        x=world.player.getX();
        y=world.player.getY();
        if (world.player.isUp()){
            if(0<=y-1){
                y-=1;
                shoot(x,y);
                return;
            }
        }else if (world.player.isDown()){
            if(12>y+1){
                y+=1;
                shoot(x,y);
                return;
            }
        }else if(world.player.isRight()){
            if(0<=x-1){
                x+=1;
                shoot(x,y);
                return;
            }
        }else if(world.player.isLeft()){
            if(12>x+1){
                x-=1;
                shoot(x,y);
                return;
            }
        }else {return;}
    }//end fGun
    private void shoot(int x,int y){
        int t;
        SurfaceHolder ss= this.getHolder();
        Canvas canvas = ss.lockCanvas();
        if (null != canvas){
            drawPart(canvas,x,y,8);
            t=world.getPoint(x,y).getType();
            if (t>2&&t<8){
                world.getPoint(x,y).kill();
                world.setPoint(x,y,new FireGun());
            }//end killing
            ss.unlockCanvasAndPost(canvas);
        }
    }//end shoot
}
//eof
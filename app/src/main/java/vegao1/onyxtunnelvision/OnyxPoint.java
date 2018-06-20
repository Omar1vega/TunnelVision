package vegao1.onyxtunnelvision;

public class OnyxPoint implements TunnelStruct {
    private int x_cord;
    private int y_cord;
    protected int type;
    public OnyxPoint(){
        this(1);
    }
    public OnyxPoint(int i){
        type=i;
    }
    public  OnyxPoint(int x, int y, int t){
        this(t);
        setX(x);
        setY(y);
    }
    public void kill(){
        type = 0;
    }
    @Override
    public void update() {
    }
    @Override
    public void graphic(){
    }
    //GETTER AND SETTER
    @Override
    public int getX() {
        return x_cord;
    }

    @Override
    public int getY() {
        return y_cord;
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public int setX(int x) {
        return x_cord=x;
    }

    @Override
    public int setY(int y) {
        return y_cord=y;
    }

    public void setType(int type) {
        this.type = type;
    }

}

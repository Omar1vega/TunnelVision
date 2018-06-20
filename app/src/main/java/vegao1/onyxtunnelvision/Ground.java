package vegao1.onyxtunnelvision;

/**
 * Created by noah_k on 5/10/2017.
 */

public class Ground extends OnyxPoint {
    public Ground(){
        super(2);
    }
    public Ground(int a,int b){
        this();
        setX(a);
        setY(b);
    }
    @Override
    public void update(){
        super.setType(2);
    }
    public void graphic(){
        type=2;
    }
}

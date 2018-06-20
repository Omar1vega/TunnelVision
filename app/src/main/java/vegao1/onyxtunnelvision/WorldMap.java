package vegao1.onyxtunnelvision;
public class WorldMap {
    private OnyxPoint[][] world;
    private int xLength;
    private int yLength;
    public Player player;
    public Rock rock1;
    public  FireEnemy fireEnemy;
    //constructors
    public WorldMap(int xSize,int ySize){
        world = new OnyxPoint[xSize][ySize];
        xLength=xSize;
        yLength=ySize;
        initialMap();
    }
    public WorldMap(){
        this(12,12);
    }
    //initialize map
    private void initialMap(){
        for (int i=0;i<xLength;i++){
            for (int j=0;j<yLength;j++){
                if (i==5&&j==8) {
                    world[i][j] = new Enemy(i, j,3,this);
                    world[i-1][j]=new emptyBlock();
                }
                else if (i==6&&j==8){
                    world[i][j]=new emptyBlock();
                }
                else if (i==9&&j==2){
                    world[i][j] = new Enemy(i, j,3,this);
                    world[i-1][j]=new emptyBlock();
                }
                else if (i==10&&j==2){
                    world[i][j]=new emptyBlock();
                }
                else if (i==2&&j==6){
                    world[i][j] = new Enemy(i, j,3,this);
                    world[i-1][j]=new emptyBlock();
                }
                else if (i==3&&j==6){
                    world[i][j]=new emptyBlock();
                }
                else if(i==5&& j==10){
                    fireEnemy= new FireEnemy(i,j,this);
                    world[i][j]=fireEnemy;
                    world[i-1][j]=new emptyBlock();
                }
                else if (i==6&&j==10){
                    world[i][j]=new emptyBlock();
                }
                else if(i==4&& j==2){
                    rock1 = new Rock(this,i,j);
                    world[i][j] = rock1;
                }
                else if(i==10&& j==4){
                    rock1 = new Rock(this,i,j);
                    world[i][j] = rock1;
                }
                else if(i==1&& j==8){
                    rock1 = new Rock(this,i,j);
                    world[i][j] = rock1;
                }
                else if (i==3&&j==3) {
                    player = new Player(i, j,9);
                    world[i][j] = player;
                }
                else {
                    world[i][j]=new Ground(i,j);
                }
            }//end y
        }//end x
        /*Ground g = new Ground();
        g.setX(0);
        g.setY(0);
        world[0][0]=g;*/
    }//end initialMap();
    //call update on all tunnelStructures in OnyxPoint list
    public void updateGameState(){
        for (int i=0;i<xLength;i++){
            for (int j=0;j<yLength;j++){
                world[i][j].update();
            }//end y
        }//end x
        System.out.println("gameState: "+world[0][0].getType());
    }
    public void updateGraphic(){
        for (int i=0;i<xLength;i++){
            for (int j=0;j<yLength;j++){
                world[i][j].graphic();
            }//end j
        }//end i
    }
    //place the parts in there updated pos
    public void placePart(){
        System.out.println("before placing:"+world[0][0].getType());
        OnyxPoint temp;
        for (int i=0;i<xLength;i++){
            for (int j=0;j<yLength;j++){
                if( world[i][j].getX()!=i ||  world[i][j].getY()!=j  ) {
                    temp = world[i][j];
                    world[temp.getX()][temp.getY()] = temp;
                    world[i][j] = new emptyBlock();
                }
            }//end y
        }//end x
        System.out.println("after placing:"+world[0][0].getType());

    }
    //GETTERZ
    public OnyxPoint getPoint(int x,int y){
        return world[x][y];
    }
    public int getxLength() {
        return xLength;
    }
    public int getyLength() {
        return yLength;
    }
    //set
    public void setPoint(int x,int y,OnyxPoint ox){world[x][y]=ox;}
}

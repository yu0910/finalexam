public class Player extends Thread{
    private int x;//Player的X座標
    private int y;//Player的Y座標
    private int step;//速度
    private int direct;//方向,1表示向上,2表示向右,3表示向下,4表示向左
    private int who;//哪位使用者
    private boolean isLive = true;//判?是否死亡
    Shoot shoot;//建立Shoot類別的變數
    public Player(int x, int y, int step, int direct,int who,boolean isLive) {
        super();
        this.x = x;
        this.y = y;
        this.step = step;
        this.direct = direct;
        this.who = who;
        this.isLive = isLive;
        System.out.println(this.step);
    }
    public boolean canMove(){//boolean返回值類型.是否移動,判斷如果撞到障礙物.
        if(x<0){
            x=0;
            return false;
        }else if(x>690){
            x=690;
            return false;
        }else if(y<0){
            y=0;
            return false;
        }else if(y>450){
            y=450;
            return false;
        }else if(x>270&&x<370&&y>100&&y<380) {//撞到建築物
            if (x - 270 < 20) {
                x = 270;
            } else {
                x = 370;
            }
            return false;
        }
        return true;
    }
    public void moveUp(){//向上移動
        if(canMove()){
            y-=step;
            direct=1;
        }
    }
    public void moveDown(){//向下移?
        if(canMove()){
            y+=step;
            direct=3;
        }
    }
    public void moveLeft(){//向左移?
        if(canMove()){
            x-=step;
            direct=4;
        }
    }
    public void moveRight(){//向右移?
        if(canMove()){
            x+=step;
            direct=2;
        }
    }
    //    public void shoot(){//每次射??用此函?
//
//    }
    public void shoot(){//每次射??用此函?
        shoot = new Shoot(x,y,direct,step,who);
    }
    /**以下是get() set()函?*/
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getStep() {
        return step;
    }
    public void setStep(int step) {
        this.step = step;
    }
    public int getDirect() {
        return direct;
    }
    public void setDirect(int direct) {
        this.direct = direct;
    }
    public boolean isLive() {
        return isLive;
    }
    public void setLive(boolean isLive) {
        this.isLive = isLive;
    }
    public void setWho(int who) {
        this.who = who;
    }
    public int getWho() {
        return who;
    }

}
//建立PlayerCreate繼承Player
class PlayerCreate extends Player{
    public PlayerCreate(int x, int y, int step, int direct, int who,boolean isLive) {
        super(x, y, step, direct, who,isLive);
    }
}
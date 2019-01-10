public class Shoot {
    static int[] Play_shoot = new int[40];//子彈數據
    private int
            direct, //方向
            who,    //哪位使用者
            ShootX, //調整子彈X座標
            ShootY, //調整子彈Y座標
            StepX,  //子?在x軸上移動的速度
            StepY,  //子?在y軸上移動?的速度
            Speed;  //速度

    //Player子彈索引,數據滿後 IndexPlayer=0;每次加3
    static int IndexPlayer=0;
    public void s(int x,int y){
        if(direct==1){//if else用?調整子?射擊出去的位置
            ShootX=x+30;
            ShootY=y;
            StepX=0;
            StepY=-Speed;
        }else if(direct==2){
            ShootX=x+60;
            ShootY=y+25;
            StepX=Speed;
            StepY=0;
        }else if(direct==3){
            ShootX=x+30;
            ShootY=y+55;
            StepX=0;
            StepY=Speed;
        }else if(direct==4){
            ShootX=x-10;
            ShootY=y+25;
            StepX=-Speed;
            StepY=0;
        }
    }
    public Shoot(int x,int y,int direct,int step,int who){

        Speed=20;//根据Player的速度,設定子?的速度
        this.direct=direct;
        this.who=who;
        if(who==0){
            s(x,y);
        }else if (who==1){
            s(x,y);
        }
        if(IndexPlayer==Play_shoot.length-4){//表示數?滿了
            IndexPlayer=0;
        }
        Play_shoot[IndexPlayer]=ShootX;
        Play_shoot[IndexPlayer+1]=ShootY;
        Play_shoot[IndexPlayer+2]=StepX;
        Play_shoot[IndexPlayer+3]=StepY;
        IndexPlayer+=4;

    }

}
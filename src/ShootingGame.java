import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;

public class ShootingGame extends JFrame{
    public ShootingGame(int color1,int color2,int hp1,int hp2,int speed1,int speed2){
        init(color1,color2,hp1,hp2,speed1,speed2);}
    private void init(int color1,int color2,int hp1,int hp2,int speed1,int speed2){
        Establish establish = new Establish();//建立(Establish)的類別變數
        this.setVisible(true);
        this.setLocation(500,50);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//關閉
        this.setSize(750,530);//視窗大小
        this.add(establish);//把(establish)添加到視窗裡
        establish.action(color1,color2,hp1,hp2,speed1,speed2);//(establish)啟動
    }
//    public static void main(String[] args) {
//        new ShootingGame(2,3,2,2,2,2);
//    }
}
class Establish extends JPanel{
    private boolean W,D,S,A,sH;//定義 Player1 按鍵變數
    private boolean Up,Down,Right,Left,sL;//定義 Player2 按鍵變數
    public static final int WIDTH=750;//地圖寬度
    public static final int HEIGHT=530;//地圖高度
    private boolean gameOver;//Game Over = false 表示還沒?束
    private Timer timer;//時間變數
    private int shootNum1,shootNum2;//可以可射擊次數
    private int LifeNum1;  //Player1生命值
    private int LifeNum2;  //Player2生命值
    private int LifeX[]={18,21,24,27,30,33,36,39,42,45,48,51,54,57,60};  //匯出每格生命值的X座標距離
    private int color1,color2,hp1,hp2,speed1,speed2;
    //    private Image image = Toolkit.getDefaultToolkit().getImage("aa.png");
//    private InputStream in =  new FileInputStream("ManRight.png");
//    private BufferedImage image = ImageIO.read(new File(in.read()))
    Player[] Player=new Player[2];//創建兩個Player

    public void action(int color1,int color2,int hp1,int hp2,int speed1,int speed2){
        this.color1=color1;
        this.color2=color2;
        this.hp1=hp1;
        this.hp2=hp2;
        this.speed1=speed1;
        this.speed2=speed2;
        startGame();//呼叫開始遊戲函式
//      <---鍵盤--->
        KeyAdapter keyboard = new KeyAdapter(){
            public void keyPressed(KeyEvent e){
                int key = e.getKeyCode();
                if(key==KeyEvent.VK_ESCAPE){
                    System.exit(0);//Esc鍵為離開程式
                }
                if(gameOver){//為true時按Enter則可以觸發開始遊戲函式
                    if(key==KeyEvent.VK_ENTER){
                        startGame();
                    }
                    return;
                }
                switch(key){
                    case KeyEvent.VK_W:W=true;break;
                    case KeyEvent.VK_D:D=true;break;
                    case KeyEvent.VK_S:S=true;break;
                    case KeyEvent.VK_A:A=true;break;
                    case KeyEvent.VK_H:sH=true;break;
                    case KeyEvent.VK_UP:Up=true;break;
                    case KeyEvent.VK_RIGHT:Right=true;break;
                    case KeyEvent.VK_DOWN:Down=true;break;
                    case KeyEvent.VK_LEFT:Left=true;break;
                    case KeyEvent.VK_L:sL=true;break;
                }
                repaint();
            }
            public void keyReleased(KeyEvent e) {
                int key = e.getKeyCode();
                switch(key){
                    case KeyEvent.VK_W:W=false;break;
                    case KeyEvent.VK_D:D=false;break;
                    case KeyEvent.VK_S:S=false;break;
                    case KeyEvent.VK_A:A=false;break;
                    case KeyEvent.VK_H:sH=false;break;
                    case KeyEvent.VK_UP:Up=false;break;
                    case KeyEvent.VK_RIGHT:Right=false;break;
                    case KeyEvent.VK_DOWN:Down=false;break;
                    case KeyEvent.VK_LEFT:Left=false;break;
                    case KeyEvent.VK_L:sL=false;break;
                }
            }
        };
        this.requestFocus();
        this.addKeyListener(keyboard);


    }
    public void startGame(){
        cleanShoot();//清空子?
//      <---使用者設定--->
        //子彈射出限制
        shootNum1=5;
        shootNum2=5;
        //生命值
        LifeNum1=hp1;
        LifeNum2=hp2;
        //初始化Player
        Player[0]= new PlayerCreate(100,100,speed1,2,0,true);
        Player[1]= new PlayerCreate(550,370,speed2,4,1,true);
        gameOver=false;
        //啟動使用者
        Player[0].start();
        Player[1].start();

        timer = new Timer();//匿名?部類Timer
        timer.schedule(new TimerTask(){
            public void run(){//重寫run()函?
                repaint();
                shootAndRun();
            }
        }, 0,50);//50毫秒執行一次
    }
    /**檢查是否GameOver*/
    public void checkGameOver(){
        if(Player[0].isLive()&&Player[1].isLive()){
            return;
        }
        gameOver = true;
        Player[0].setLive(true);
        Player[1].setLive(true);
        timer.cancel();
        repaint();
    }
    public void shootAndRun(){
        //如果按下移動按鍵
        if(W){
            Player[0].moveUp();
        }else if(D){
            Player[0].moveRight();
        }else if(S){
            Player[0].moveDown();
        }else if(A){
            Player[0].moveLeft();
        }if(sH&&Player[0].isLive()){//如果sL=true并且坦克是活的,就?行
            if(shootNum1>=0){//如果子彈小於0了，不執行
                Player[0].shoot();
                shootNum1--;//控制子彈發射數量,每射出則--
            }
        }
        if(Up){
            Player[1].moveUp();
        }else if(Right){
            Player[1].moveRight();
        }else if(Left){
            Player[1].moveLeft();
        }else if(Down){
            Player[1].moveDown();
        }if(sL&&Player[1].isLive()){
            if(shootNum2>=0){
                Player[1].shoot();
                shootNum2--;
            }
        }
    }
    public void paint(Graphics g){
        g.setColor(new Color(11, 153, 43));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());//畫背景
        paintWall(g);//畫障礙物
        //畫Player
        paintUser(Player[0].getX(),Player[0].getY(),g,Player[0].getDirect(),Player[0].getWho(),Player[0].isLive(),LifeNum1);
        paintUser(Player[1].getX(),Player[1].getY(),g,Player[1].getDirect(),Player[1].getWho(),Player[1].isLive(),LifeNum2);
        PaintShoot(g);//繪出射擊子彈
        paintEnd(g);
    }
    public void paintEnd(Graphics g){//結束畫面
        if(gameOver){
            g.setColor(Color.lightGray);
            Font f = getFont();
            Font font = new Font(f.getName(),Font.BOLD,35);
            g.setFont(font);
            int x = 180;
            int y = 70;
            String str = "[Esc]Quit!";
            g.drawString(str, x, y);
            x+=190;
            str = "[Enter]Start!";
            g.setColor(Color.ORANGE);
            g.drawString(str, x, y);
        }
    }
    public void paintWall(Graphics g){//畫障礙物
        g.setColor(Color.LIGHT_GRAY);
        g.fill3DRect(WIDTH/2-45,150 , 40, HEIGHT-300, false);
//        g.fill3DRect(130,HEIGHT/2-20 , WIDTH-300, 40, false);
    }
    //      <---畫自己子彈 , 同時判斷有沒有擊中--->
    public void PaintShoot(Graphics g){
        ShootDisappear();
        for (int i = 0; i < Shoot.Play_shoot.length; i+=4) {
            if(Shoot.Play_shoot[i]==0&&Shoot.Play_shoot[i+1]==0){
                continue;
            }
            g.setColor(Color.RED);
            g.fillOval(Shoot.Play_shoot[i], Shoot.Play_shoot[i+1], 5, 5);
            int x = Shoot.Play_shoot[i];
            int y = Shoot.Play_shoot[i+1];
            int mx1 = Player[0].getX();
            int my1 = Player[0].getY();
            int mx2 = Player[1].getX();
            int my2 = Player[1].getY();
            if(x>mx1+15&&x<mx1+50&&y>my1&&y<my1+40){
                LifeNum1--;
                if(LifeNum1==0){
                    Player[0].setLive(false);
                    checkGameOver();
                }
                Shoot.Play_shoot[i]=0;Shoot.Play_shoot[i+1]=0;
                Shoot.Play_shoot[i+2]=0;Shoot.Play_shoot[i+3]=0;
                shootNum2++;
                shootNum1++;
            }
            if(x>mx2+15&&x<mx2+50&&y>my2&&y<my2+40){
                LifeNum2--;
                if(LifeNum2==0){
                    Player[1].setLive(false);
                    checkGameOver();
                }
                Shoot.Play_shoot[i]=0;Shoot.Play_shoot[i+1]=0;
                Shoot.Play_shoot[i+2]=0;Shoot.Play_shoot[i+3]=0;
                shootNum2++;
                shootNum1++;
            }
            Shoot.Play_shoot[i]+=Shoot.Play_shoot[i+2];
            Shoot.Play_shoot[i+1]+=Shoot.Play_shoot[i+3];
        }
    }

    //      <---繪使用者(給值)--->
    public void paintUser(int x,int y,Graphics g,int direct,int who,boolean isLive,int lifeNum){
        Color color = null;//設定顏色為null
        if(isLive){
            if(who==0){//Player(who?)
                switch (color1){
                    case 0:
                        color=Color.black;
                        break;
                    case 1:
                        color=Color.MAGENTA;
                        break;
                    case 2:
                        color=Color.orange;
                        break;
                    case 3:
                        color=Color.blue;
                        break;
                }
            }else if(who==1) {
                switch (color2){
                    case 0:
                        color=Color.black;
                        break;
                    case 1:
                        color=Color.MAGENTA;
                        break;
                    case 2:
                        color=Color.orange;
                        break;
                    case 3:
                        color=Color.blue;
                        break;
                }
            }
            switch(direct){//劃出不同方向
                case 1:{g.setColor(color);paintUp(x,y,lifeNum,g);break;}
                case 2:g.setColor(color);paintRight(x,y,lifeNum,g);break;
                case 3:g.setColor(color);paintDown(x,y,lifeNum,g);break;
                case 4:g.setColor(color);paintLeft(x,y,lifeNum,g);break;
            }
        }
    }
    //      <---繪製使用者(移動位置)--->
    public void paintUp(int x,int y,int lifeNum,Graphics g){
        g.fillOval(x+20, y+18, 25, 25);
        g.setColor(Color.red);
        g.fill3DRect(x+30, y+5, 5, 15, false);
//      <---判斷生命值,並給顏色--->
        if(lifeNum<4){
            g.setColor(Color.RED);
        }else if(lifeNum>=4&&lifeNum<6){
            g.setColor(Color.ORANGE);
        }else {
            g.setColor(Color.GREEN);
        }
        for(int i=0;i<lifeNum;i++){
            g.fill3DRect(x+LifeX[i], y, 2, 6, false);
        }
    }
    public void paintLeft(int x,int y,int lifeNum,Graphics g){
        g.fillOval(x+20, y+18, 25, 25);

        g.setColor(Color.red);
        g.fill3DRect(x+6, y+27, 15, 5, false);

        if(lifeNum<4){
            g.setColor(Color.RED);
        }else if(lifeNum>=4&&lifeNum<6){
            g.setColor(Color.ORANGE);
        }else {
            g.setColor(Color.GREEN);
        }
        for(int i=0;i<lifeNum;i++){
            g.fill3DRect(x+LifeX[i], y, 2, 6, false);
        }
    }
    public void paintDown(int x,int y,int lifeNum,Graphics g){
        g.fillOval(x+20, y+18, 25, 25);

        g.setColor(Color.red);
        g.fill3DRect(x+30, y+40, 5, 15, false);

        if(lifeNum<4){
            g.setColor(Color.RED);
        }else if(lifeNum>=4&&lifeNum<6){
            g.setColor(Color.ORANGE);
        }else {
            g.setColor(Color.GREEN);
        }
        for(int i=0;i<lifeNum;i++){
            g.fill3DRect(x+LifeX[i], y, 2, 6, false);
        }
    }
    public void paintRight(int x,int y,int lifeNum,Graphics g){
        g.fillOval(x+20, y+18, 25, 25);
        g.setColor(Color.red);
        g.fill3DRect(x+45, y+27, 15, 5, false);

        if(lifeNum<4){
            g.setColor(Color.RED);
        }else if(lifeNum>=4&&lifeNum<6){
            g.setColor(Color.ORANGE);
        }else {
            g.setColor(Color.GREEN);
        }
        for(int i=0;i<lifeNum;i++){
            g.fill3DRect(x+LifeX[i], y, 2, 6, false);
        }
    }
    //重新開始時清空子?裡面數據
    public void cleanShoot(){
        for (int i = 0; i < Shoot.Play_shoot.length; i++) {
            Shoot.Play_shoot[i]=0;
        }
    }
    //子?消失
    public void ShootDisappear(){
        //撞到邊緣
        for (int i = 0; i < Shoot.Play_shoot.length; i+=4) {
            if(Shoot.Play_shoot[i]<0||Shoot.Play_shoot[i]>WIDTH||Shoot.Play_shoot[i+1]<0||Shoot.Play_shoot[i+1]>HEIGHT){
                Shoot.Play_shoot[i]=0;Shoot.Play_shoot[i+1]=0;
                Shoot.Play_shoot[i+2]=0;Shoot.Play_shoot[i+3]=0;
                shootNum1++;
                shootNum2++;
            }
            int x=Shoot.Play_shoot[i];
            int y=Shoot.Play_shoot[i+1];
            //撞到柱子
            if((x>330&&x<360&&y>150&&y<380)){
                Shoot.Play_shoot[i]=0;Shoot.Play_shoot[i+1]=0;
                Shoot.Play_shoot[i+2]=0;Shoot.Play_shoot[i+3]=0;
                shootNum1++;
                shootNum2++;
            }
        }
    }
}
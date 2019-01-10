import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Open extends JFrame {

    private JLayeredPane layeredPane = new JLayeredPane();

    private JLabel title = new JLabel("雙人槍戰");
    private Container cp;
    private JPanel Page1 = new JPanel();
    private JPanel Page2 = new JPanel();
    private JPanel Page3 = new JPanel();
    private JLabel
            jLabel_Hp1 =new JLabel("生命值 : 10"),
            jLabel_Speed1 =new JLabel("速度 : 7"),
            jLabel_Count1 =new JLabel("剩餘點數 : 5"),
            jLabel_Hp2 =new JLabel("生命值 : 10"),
            jLabel_Speed2 =new JLabel("速度 : 7"),
            jLabel_Count2 =new JLabel("剩餘點數 : 5");
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int scW = screenSize.width, scH = screenSize.height;
    private int frW = 800, frH = 530;
    private drowpanel drowpanel=new drowpanel();
    private JLabel
            jLabel_Arrow1 =new JLabel(new ImageIcon("a1.png")),//左邊箭頭
            jLabel_Arrow2 =new JLabel(new ImageIcon("a1.png")),//右邊箭頭
            jLabel_Arrow3 =new JLabel(new ImageIcon("a2.png")),//顏色選擇箭頭
            jLabel_Arrow4 =new JLabel(new ImageIcon("a1.png")),//確認進入遊戲箭頭
            jLabel_Start =new JLabel("進入"),
            jLabel_Description =new JLabel("說明"),
            jLabel_Exit =new JLabel("離開");
    private Font font = new Font(null, Font.BOLD, 16);
    private int UserColor1=0,Userhp1=10,Userspeed1=7;
    private int UserColor2=0,Userhp2=10,Userspeed2=7;
    private int Page=0,count1=5,count2=5;
    private Color color_choose1=Color.black,color_choose2=Color.black;
    private boolean
            color=false,
            hp=false,
            speed=false,
            chooseUser=false;
    private int UP_DOWN=0,Left_RIGHT=0;
    private JLabel mo=new JLabel("玩家        攻擊         移動方式");
    private JLabel p1=new JLabel("p1            H           W    A        S       D");
    private JLabel p2=new JLabel("p2            L           Up   Left     Right   Down");

    public Open() {
        init();
    }

    private void init() {
//        JFrame frame = new JFrame("Test");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayeredPane(layeredPane);
        this.setVisible(true);
        this.setResizable(false);
        this.setBounds(scW / 2 - frW / 2, scH / 2 - frH / 2, frW, frH);

        layeredPane.add(Page1, JLayeredPane.MODAL_LAYER);
        layeredPane.add(Page2, JLayeredPane.MODAL_LAYER);
        layeredPane.add(Page3, JLayeredPane.MODAL_LAYER);

        Page1.setBounds(0, 0, frW, frH);
        Page1.setVisible(true);
        Page1.setLayout(null);
        Page1.add(title);
        Page1.add(jLabel_Arrow1);
        Page1.add(jLabel_Start);
        Page1.add(jLabel_Description);
        Page1.add(jLabel_Exit);

        Page2.setBounds(0, 0, frW, frH);
        Page2.setVisible(false);
        Page2.setLayout(null);
        Page3.setBounds(0, 0, frW, frH);
        Page3.setVisible(false);
        Page3.setLayout(null);

        title.setOpaque(false);
        title.setBounds(260, 50, 310, 100);
        title.setFont(new Font("null", Font.BOLD, 50));

        jLabel_Start.setBounds(350, 200, 200, 60);
        jLabel_Start.setOpaque(true);
        jLabel_Start.setFont(font);
        jLabel_Description.setBounds(350, 250, 200, 60);
        jLabel_Description.setOpaque(true);
        jLabel_Description.setFont(font);
        jLabel_Exit.setBounds(350, 300, 200, 60);
        jLabel_Exit.setOpaque(true);
        jLabel_Exit.setFont(font);

        jLabel_Arrow1.setBounds(280,200,70,50);
        jLabel_Arrow2.setBounds(5,200,70,50);
        jLabel_Arrow3.setBounds(70,150,30,50);
        jLabel_Arrow3.setVisible(false);

        jLabel_Hp1.setBounds(40,270,100,50);
        jLabel_Hp1.setFont(font);
        jLabel_Speed1.setBounds(50,355,150,50);
        jLabel_Speed1.setFont(font);
        jLabel_Count1.setBounds(15,430,150,50);
        jLabel_Count1.setFont(font);

        jLabel_Hp2.setBounds(440,270,100,50);
        jLabel_Hp2.setFont(font);
        jLabel_Speed2.setBounds(450,355,150,50);
        jLabel_Speed2.setFont(font);
        jLabel_Count2.setBounds(415,430,150,50);
        jLabel_Count2.setFont(font);

        mo.setBounds(300,100,500,50);
        mo.setFont(font);
        p1.setBounds(300,200,500,50);
        p1.setFont(font);
        p2.setBounds(300,300,500,50);
        p2.setFont(font);

//      --------------------------------------------------------------
        drowpanel.setVisible(true);
        drowpanel.setBounds(0,0,frW,frH);
        Page2.add(jLabel_Hp1 ,JLayeredPane.DEFAULT_LAYER );
        Page2.add(jLabel_Speed1 ,JLayeredPane.DEFAULT_LAYER );
        Page2.add(jLabel_Count1 ,JLayeredPane.DEFAULT_LAYER );

        Page2.add(jLabel_Hp2 ,JLayeredPane.DEFAULT_LAYER );
        Page2.add(jLabel_Speed2 ,JLayeredPane.DEFAULT_LAYER );
        Page2.add(jLabel_Count2 ,JLayeredPane.DEFAULT_LAYER );

        Page2.add(jLabel_Arrow2 ,JLayeredPane.DEFAULT_LAYER );
        Page2.add(jLabel_Arrow3 ,JLayeredPane.DEFAULT_LAYER );
        Page2.add(drowpanel,JLayeredPane.MODAL_LAYER);

//      ---------------------------------------------------------------
        Page3.add(mo);
        Page3.add(p1);
        Page3.add(p2);


        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()){
                    case KeyEvent.VK_UP:
                        if(Page==0){
                            if(UP_DOWN==0){
                                break;
                            }else {
                                UP_DOWN--;
                                arrow1();
                            }
                        }else {
                            if(UP_DOWN==0){
                                break;
                            }else {
                                UP_DOWN--;
                                arrow2();
                            }
                        }
                        break;


                    case KeyEvent.VK_DOWN:
                        if(Page==0){
                            if(UP_DOWN==2){
                                break;
                            }else {
                                UP_DOWN++;
                                arrow1();
                            }
                        }else {
                            if(UP_DOWN==2){
                                break;
                            }else {
                                UP_DOWN++;
                                arrow2();
                            }
                        }
                        break;


                    case KeyEvent.VK_LEFT:
                        if(Page==1){
                            if(!(chooseUser)){
                                if(color){
                                    if(Left_RIGHT==0){
                                        break;
                                    }else {
                                        Left_RIGHT--;
                                        UserColor1=Left_RIGHT;
                                        arrow_color();
                                    }
                                }else if(count1!=5)
                                {
                                    if(hp){
                                        if(Userhp1>10){
                                            count1++;
                                            Userhp1--;
                                            jLabel_Count1.setText("剩餘點數 : "+Integer.toString(count1));
                                            jLabel_Hp1.setText("生命值 : "+Integer.toString(Userhp1));

                                        }
                                    }else if(speed){
                                        if(Userspeed1>7){
                                            count1++;
                                            Userspeed1--;
                                            jLabel_Count1.setText("剩餘點數 : "+Integer.toString(count1));
                                            jLabel_Speed1.setText("速度 : "+Integer.toString(Userspeed1));
                                        }
                                    }
                                }
                            }else {
                                if(color){
                                    if(Left_RIGHT==0){
                                        break;
                                    }else {
                                        Left_RIGHT--;
                                        UserColor2=Left_RIGHT;
                                        arrow_color();
                                    }
                                }else if(count2!=5)
                                {
                                    if(hp){
                                        if(Userhp2>10){
                                            count2++;
                                            Userhp2--;
                                            jLabel_Count2.setText("剩餘點數 : "+Integer.toString(count2));
                                            jLabel_Hp2.setText("生命值 : "+Integer.toString(Userhp2));


                                        }
                                    }else if(speed){
                                        if(Userspeed2>7){
                                            count2++;
                                            Userspeed2--;
                                            jLabel_Count2.setText("剩餘點數 : "+Integer.toString(count2));
                                            jLabel_Speed2.setText("速度 : "+Integer.toString(Userspeed2));
                                        }
                                    }
                                }
                            }
                            repaint();
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if(Page==1){
                            if(!(chooseUser)){
                                if(color){
                                    if(Left_RIGHT==3){
                                        break;
                                    }else {
                                        Left_RIGHT++;
                                        UserColor1=Left_RIGHT;
                                        arrow_color();
                                    }
                                }else if(count1>0)
                                {
                                    if(hp){
                                        if(Userhp1>=10){
                                            count1--;
                                            Userhp1++;
                                            jLabel_Count1.setText("剩餘點數 : "+Integer.toString(count1));
                                            jLabel_Hp1.setText("生命值 : "+Integer.toString(Userhp1));

                                        }
                                    }else if(speed){
                                        if(Userspeed1>=7){
                                            count1--;
                                            Userspeed1++;
                                            jLabel_Count1.setText("剩餘點數 : "+Integer.toString(count1));
                                            jLabel_Speed1.setText("速度 : "+Integer.toString(Userspeed1));


                                        }
                                    }
                                }
                            }else {
                                if(color){
                                    if(Left_RIGHT==3){
                                        break;
                                    }else {
                                        Left_RIGHT++;
                                        UserColor2=Left_RIGHT;
                                        arrow_color();
                                    }
                                }else if(count2>0)
                                {
                                    if(hp){
                                        if(Userhp2>=10){
                                            count2--;
                                            Userhp2++;
                                            jLabel_Count2.setText("剩餘點數 : "+Integer.toString(count2));
                                            jLabel_Hp2.setText("生命值 : "+Integer.toString(Userhp2));

                                        }
                                    }else if(speed){
                                        if(Userspeed2>=7){
                                            count2--;
                                            Userspeed2++;
                                            jLabel_Count2.setText("剩餘點數 : "+Integer.toString(count2));
                                            jLabel_Speed2.setText("速度 : "+Integer.toString(Userspeed2));
                                        }
                                    }
                                }
                            }
                            repaint();
                        }
                        break;

                    case KeyEvent.VK_SPACE:
                        if(Page==1){
                            if(color||hp||speed){
                                break;
                            }else {
                                UP_DOWN=0;
                                chooseUser=!chooseUser;
                                arrow2();
                            }
                        }
                        break;

                    case KeyEvent.VK_ENTER:
                        if(Page==0){
                            switch (UP_DOWN){
                                case 0:
                                    Page1.setVisible(false);
                                    Page2.setVisible(true);
                                    Page=1;
                                    UP_DOWN=0;
                                    break;
                                case 1:
                                    Page1.setVisible(false);
                                    Page3.setVisible(true);
                                    Page=4;

                                    break;
                                case 2:
                                    System.exit(0);
                                    break;
                            }
                        }else if(Page==1){
                            switch (UP_DOWN){
                                case 0:
                                    color=true;
                                    jLabel_Arrow3.setVisible(true);
                                    arrow_color();
                                    break;
                                case 1:
                                    hp=true;
                                    break;
                                case 2:
                                    speed=true;
                                    break;
                            }
                            jLabel_Arrow2.setVisible(false);
                        }if(count1==0&&count2==0){
                        dispose();
                        new ShootingGame(UserColor1,UserColor2,Userhp1,Userhp2,Userspeed1,Userspeed2);
                    }
                        break;
                    case KeyEvent.VK_ESCAPE:
                        if(Page==4){
                            Page1.setVisible(true);
                            Page3.setVisible(false);
                            Page=0;
                        }
                        else if(Page==1){
                            if(color){
                                if(!(chooseUser)){
                                    UserColor1=Left_RIGHT;
                                    switch (UserColor1){
                                        case 0: color_choose1=Color.black;break;
                                        case 1: color_choose1=Color.MAGENTA;break;
                                        case 2: color_choose1=Color.orange;break;
                                        case 3: color_choose1=Color.blue;break;
                                    }
                                    jLabel_Arrow3.setLocation(70,150);
                                    jLabel_Arrow2.setLocation(5,200);
                                }else {
                                    UserColor2=Left_RIGHT;
                                    switch (UserColor2){
                                        case 0: color_choose2=Color.black;break;
                                        case 1: color_choose2=Color.MAGENTA;break;
                                        case 2: color_choose2=Color.orange;break;
                                        case 3: color_choose2=Color.blue;break;
                                    }
                                    jLabel_Arrow3.setLocation(450,150);
                                    jLabel_Arrow2.setLocation(370,200);
                                }
                                repaint();
                                Page=1;
                                jLabel_Arrow3.setVisible(false);
                                color=false;
                                arrow2();
                            }else if(hp){
                                //error
                                hp=false;
                            }else if(speed){
                                speed=false;
                            }
                            jLabel_Arrow2.setVisible(true);
                        }
                        break;
                }
            }
        });
    }
    public void arrow1(){
        int y=0;
        switch (UP_DOWN){
            case 0:
                y=210;
                break;
            case 1:
                y=260;
                break;
            case 2:
                y=310;
                break;
        }
        jLabel_Arrow1.setLocation(280,y);
    }
    public void arrow2(){
        int x=0;
        int y=0;
        if(!(chooseUser)) { x=5; }
        else { x=400; }
        switch (UP_DOWN){
            case 0:
                y=210;
                break;
            case 1:
                y=320;
                break;
            case 2:
                y=400;
                break;
        }

        jLabel_Arrow2.setLocation(x,y);
    }
    public void arrow_color(){
        int x=0;
        if(!(chooseUser)){
            switch (UserColor1){
                case 0:
                    x=70;
                    break;
                case 1:
                    x=150;
                    break;
                case 2:
                    x=230;
                    break;
                case 3:
                    x=310;
                    break;
            }
        }else {

            switch (UserColor2){
                case 0:
                    x=450;
                    break;
                case 1:
                    x=530;
                    break;
                case 2:
                    x=610;
                    break;
                case 3:
                    x=690;
                    break;
            }
        }
        jLabel_Arrow3.setLocation(x,150);
    }

    class drowpanel extends JPanel {
        private int X1[]={100,110,120,130,140,150,160,170,180,190,200,210,220,230,240};
        private int X2[]={500,510,520,530,540,550,560,570,580,590,600,610,620,630,640};

        public void action() {
//      <---鍵盤--->
            this.requestFocus();
        }

        public void paint(Graphics g) {
            g.setColor(new Color(28, 103, 166));
            g.fillRect(0, 0, this.getWidth(), this.getHeight());//畫背景
            g.setColor(Color.BLACK);
            g.drawString("Player1",10,50);
            g.drawString("Player2",500,50);
            paintUser(g);
            paintChooseColor(g);
            paintLine(g);
            paintValue(g);
        }
        public void paintLine(Graphics g) {
            g.setColor(new Color(166, 98, 37));
            g.fillRect((this.getWidth()/2)-2,0,2,this.getHeight());


        }
        public void paintChooseColor(Graphics g){
            g.setColor(Color.black);
            g.fillRect(60,200,50,50);
            g.setColor(Color.MAGENTA);
            g.fillRect(140,200,50,50);
            g.setColor(Color.ORANGE);
            g.fillRect(220,200,50,50);
            g.setColor(Color.BLUE);
            g.fillRect(300,200,50,50);

            g.setColor(Color.black);
            g.fillRect(440,200,50,50);
            g.setColor(Color.MAGENTA);
            g.fillRect(520,200,50,50);
            g.setColor(Color.ORANGE);
            g.fillRect(600,200,50,50);
            g.setColor(Color.blue);
            g.fillRect(680,200,50,50);

        }
        public void paintValue(Graphics g){
            g.setColor(Color.GREEN);
            for(int i=0;i<Userhp1;i++){
                g.fillRect(X1[i],320,5,30);
            }
            for(int i=0;i<Userspeed1;i++){
                g.fillRect(X1[i],400,5,30);
            }
            for(int i=0;i<Userhp2;i++){
                g.fillRect(X2[i],320,5,30);
            }
            for(int i=0;i<Userspeed2;i++){
                g.fillRect(X2[i],400,5,30);
            }
        }
        public void paintUser(Graphics g) {
            g.setColor(color_choose1);
            g.fillOval(125, 10, 150, 150);
            g.setColor(Color.WHITE);
            g.fillOval(200, 40, 50, 50);
            g.setColor(Color.BLACK);
            g.fillOval(230, 55, 15, 15);
            g.setColor(Color.red);
            g.fillRect(200, 80, 120, 30);
            g.fillRect(200, 90, 30, 50);
            g.setColor(Color.GRAY);
            g.fillOval(180, 100, 45, 45);



            g.setColor(color_choose2);
            g.fillOval(550, 10, 150, 150);
            g.setColor(Color.WHITE);
            g.fillOval(580, 40, 50, 50);
            g.setColor(Color.BLACK);
            g.fillOval(590, 55, 15, 15);
            g.setColor(Color.red);
            g.fillRect(510, 80, 120, 30);
            g.fillRect(600, 90, 30, 50);
            g.setColor(Color.GRAY);
            g.fillOval(600, 100, 45, 45);
        }
    }
    public static void main(String[] args) {
        new Main();
    }

}

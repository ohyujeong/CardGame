import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fruit extends JPanel {

    static JButton[] btn = new JButton[16];
    static String[] images={
            "1.png","2.png","3.png","4.png",
            "5.png","6.png","7.png","8.png",
            "1.png","2.png","3.png","4.png",
            "5.png","6.png","7.png","8.png",
    };

    Timer timer;
    static int Open=0;
    static int Score=0;
    static int btn1=0;
    static int btn2=0;
    int limit=0;

    public Fruit(){

        this.setLayout(new GridLayout(4,4,20,20));
        this.setPreferredSize(new Dimension(400,400));
        this.setBackground(Color.GRAY);

        for(int i=0; i<16; i++){
            btn[i] = new JButton();
            btn[i].setBackground(Color.YELLOW);
            btn[i].setIcon(CardImage("question.png"));
            btn[i].addActionListener(this::actionPerformed);
            this.add(btn[i]);
        }
        Shuffle(images);
    }

    public void actionPerformed(ActionEvent e) {
        Thread correct = new Correct_bgm();
        Thread incorrect = new Incorrect_bgm();

        if (Notice.BoardLabel.getText() != "Game Over") {

            JButton Clickbtn = (JButton) e.getSource();
            int index = getButtonIndex(Clickbtn);

            Clickbtn.setIcon(CardImage(images[index]));
            Open++;

            if (Open == 1) {
                btn1 = index;
            } else if (Open == 2) {
                btn2 = index;
                if (btn1 == btn2) {
                    incorrect.start();
                    JOptionPane warning = new JOptionPane();
                    warning.showMessageDialog(null, "Please choose another card");
                    Open--;
                } else {
                    boolean check;
                    if (images[btn1].equals(images[btn2])) {
                        check = true;
                    } else {
                        incorrect.start();
                        check = false;
                    }
                    if (check == true) {
                        correct.start();
                        Open = 0;
                        Score++;
                        Notice.ScoreLabel.setText("Score :" + Score);
                        if (Score == 8) {
                            Notice.ScoreLabel.setText("Score :" + Score);
                            JOptionPane dialog = new JOptionPane();
                            int result = dialog.showConfirmDialog(null, "Game Over! Do you want to RESTART?", "Confrim", JOptionPane.YES_NO_OPTION);
                            if (result == JOptionPane.YES_OPTION) {
                                Open = 0;
                                Score = 0;
                                limit = 0;
                                Notice.ScoreLabel.setText("Score :" + Score);
                                Notice.TimeLabel.setText("Time : 0sec");
                                for (int i = 0; i < 16; i++) {
                                    btn[i].setIcon(CardImage("question.png"));
                                }
                            } else {
                                Notice.BoardLabel.setText("Game Over");
                            }
                        }
                    } else {
                        Back();

                    }
                }
            }
        }else{
            JOptionPane warning = new JOptionPane();
            warning.showMessageDialog(null, "This game has ended. Please restart the program.");
        }
    }

    public void Back(){
        timer = new Timer(200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Open=0;
                btn[btn1].setIcon(CardImage("question.png"));
                btn[btn2].setIcon(CardImage("question.png"));
                timer.stop();
            }
        });
        timer.start();
    }

    public int getButtonIndex(JButton Clickbtn) {
        int index=0;
        for(int i=0; i<16; i++){
            if(btn[i]==Clickbtn){
                index=i;
            }
        }
        return index;
    }

    static ImageIcon CardImage(String fn){
        ImageIcon icon=new ImageIcon("img/"+fn);
        Image change = icon.getImage().getScaledInstance(70,70,Image.SCALE_AREA_AVERAGING);
        ImageIcon change_image = new ImageIcon(change);
        return change_image;
    }

    static String[] Shuffle(String[] arr){
        int r1;
        int r2;
        String temp;
        for(int i=0; i<16; i++){
            r1=(int)(Math.random()*16);
            r2=(int)(Math.random()*16);
            temp=arr[r1];
            arr[r1]=arr[r2];
            arr[r2]=temp;
        }
        return arr;
    }
}

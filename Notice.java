import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Timer;
import java.util.TimerTask;

public class Notice extends JPanel {

    static JLabel ScoreLabel;
    static JLabel TimeLabel;
    static JLabel BoardLabel;
    int limit=0;

    public Notice(){
        this.setBackground(Color.LIGHT_GRAY);
        this.setPreferredSize(new Dimension(400,100));

        Font font = new Font("Arial",Font.BOLD,20);

        ScoreLabel=new JLabel("Score : ",JLabel.RIGHT);
        ScoreLabel.setPreferredSize(new Dimension(400,30));
        ScoreLabel.setForeground(Color.BLACK);
        ScoreLabel.setFont(font);
        this.add(ScoreLabel);

        TimeLabel=new JLabel("Time : ",JLabel.RIGHT);
        TimeLabel.setPreferredSize(new Dimension(400,30));
        TimeLabel.setForeground(Color.BLUE);
        TimeLabel.setFont(font);
        this.add(TimeLabel);

        BoardLabel=new JLabel("Find Same Card",JLabel.CENTER);
        BoardLabel.setForeground(Color.BLACK);
        BoardLabel.setFont(font);
        this.add(BoardLabel);

        JButton start = new JButton("Time limit Mode");
        start.setBackground(Color.PINK);
        start.setForeground(Color.WHITE);
        this.add(start);
        start.addActionListener(this::actionPerformed);
    }

    private void actionPerformed(ActionEvent actionEvent) {
        Timer Time = new Timer();
        TimerTask TimeTask=new TimerTask() {
            @Override
            public void run() {
                TimeLabel.setText("Time : " + limit + "sec");
                limit = limit + 1;
                if (limit == 11) {
                    cancel();
                    JOptionPane dialog = new JOptionPane();
                    dialog.showMessageDialog(null, "Time Over!");
                    Fruit.Open = 0;
                    Fruit.Score = 0;
                    limit = 0;
                    ScoreLabel.setText("Score :" + Fruit.Score);
                    TimeLabel.setText("Time : " + limit + "sec");
                    for (int i = 0; i < 16; i++) {
                        Fruit.btn[i].setIcon(Fruit.CardImage("question.png"));
                        Desert.btn[i].setIcon(Desert.CardImage("question.png"));
                    }
                }
            }
        };
        Time.schedule(TimeTask,1000,1000);
    }
}

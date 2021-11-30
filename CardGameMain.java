import javax.swing.*;
import java.awt.*;

public class CardGameMain {
    JFrame jf;
    public CardGameMain(String title){

        JTabbedPane jtab = new JTabbedPane();
        jf = new JFrame(title);
        jf.setLayout(new BorderLayout());
        jf.setSize(400,500);
        jf.getContentPane().add(new Notice(),"North");
        jf.setResizable(false);

        jtab.addTab("Fruit",new Fruit());
        jtab.addTab("Desert",new Desert());
        jf.add(jtab);

        jf.setVisible(true);
        jf.pack();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Thread bgm=new BGM();
        bgm.start();

    }
    public static void main(String[] args){
            new CardGameMain("37 term project");
    }
}

package org.liky.game.frame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MyChessFrame extends JFrame implements MouseListener{
    public MyChessFrame() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setTitle("五子连珠");
        this.setSize(1000, 1000);
        this.setLocation((width-1000)/2, (height-1000)/2);
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        System.out.println(width);
        System.out.println(height);
        this.addMouseListener(this);


        this.setVisible(true);

    }

    public void paint (Graphics g) {
        // g.drawString("五子连珠", 20, 40);
        // g.drawOval(20, 40, 40, 40 );
        // g.fillOval(40, 60, 40, 40);
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("/Users/liang/Desktop/image/chessboard.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(image, 0, 0, this);
        g.drawOval(20, 40, 40, 40 );
        g.fillOval(40, 60, 40, 40);


    }


    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.print("鼠标点击----" + "\t");
        if (e.getClickCount() == 1) {
            System.out.println("单击！");
        } else if (e.getClickCount() == 2) {
            System.out.println("双击！");
        } else if (e.getClickCount() == 3) {
            System.out.println("三连击！！");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("鼠标按下");
        System.out.println(e.getX());

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("鼠标松开");

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("鼠标已经进入窗体");


    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("鼠标已经移出窗体");
    }
}

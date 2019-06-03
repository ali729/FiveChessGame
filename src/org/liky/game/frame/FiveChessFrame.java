package org.liky.game.frame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FiveChessFrame extends JFrame implements MouseListener {
    int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    int height = Toolkit.getDefaultToolkit().getScreenSize().height;

    BufferedImage bgImage = null;

    int x = 0;
    int y = 0;

    int [][] allChess = new int[21][21]; // 0 - no chess 1 - black piece 2 - white piece
    boolean isBlack = true;
    boolean canPlay = true;

    public FiveChessFrame() {
        this.setTitle("五子连珠");

        this.setSize(1000, 625);

        this.setLocation((width-1000)/2, (height - 625)/2);

        this.setResizable(false);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.addMouseListener(this);

//        for (int i = 0; i<21; i++) {
//            for (int j = 0; j<21; j++) {
//                allChess[i][j] = 0;
//            }
//        }

        this.setVisible(true);

    }

    public void paint(Graphics g){
        try {
            bgImage = ImageIO.read(new File("/Users/liang/Desktop/Work/PersonalProject/Gobang/image/chessboard.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(bgImage, 0, 0, this);
        g.setFont(new Font("黑体", Font.BOLD,30));
        g.setColor(Color.WHITE);
        g.drawString("Game Info", 25, 80);
        g.setFont(new Font("黑体", Font.BOLD,20));
        g.setColor(Color.BLACK);
        g.drawString("Time for Black", 25, 470);
        g.drawString("Time for White", 25, 575);

        System.out.println("original x" + x);
        System.out.println("original y" + y);


//        x = ((x - 250) / 25) * 25 + 250;
//        System.out.println(x);
//        y = ((y - 100) / 25) * 25 + 100;
//        System.out.println(y);
//        g.fillOval(x-7, y-7, 14, 14);

        for (int i = 0; i<21; i++) {
            for (int j = 0; j<21; j++) {
                if(allChess[i][j] == 1) {
                    int temp_x = i * 25 + 250;
                    int temp_y = j * 25 + 100;
                    g.fillOval(temp_x-7, temp_y-7, 14, 14);
                }
                if(allChess[i][j] == 2) {
                    int temp_x = i * 25 + 250;
                    int temp_y = j * 25 + 100;
                    g.setColor(Color.WHITE);
                    g.fillOval(temp_x-7, temp_y-7, 14, 14);
                    g.setColor(Color.BLACK);
                    g.drawOval(temp_x-7, temp_y-7, 14, 14);
                }
            }
        }

        for (int i = 0; i < 21; i++) {
            g.drawLine(250, 100+i*25, 750, 100+i*25);
        }

        for (int i = 0; i < 21; i++) {
            g.drawLine(250+i*25, 100, 250+i*25, 600);
        }


    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

//        System.out.println("x:" + e.getX());
//        System.out.println("y:" + e.getY());

        if (canPlay) {

            x = e.getX();
            y = e.getY();
            if (x >= 250 && x <= 750 && y >= 100 && y<=600) {
                System.out.println("on the board");
                x = ((x - 250) / 25);
                y = ((y - 100) / 25);

                if (allChess[x][y] == 0){
                    if (isBlack) {
                        allChess[x][y] = 1;
                        isBlack = false;
                    } else {
                        allChess[x][y] = 2;
                        isBlack = true;
                    }

                    boolean trigger = this.checkWin(x, y);
                    if (trigger) {
                        JOptionPane.showMessageDialog(this, "Game Over!"
                                +(allChess[x][y]==1?"Black wins!":" White wins!"));
                        canPlay = false;
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Occupied! Try again please!");
                }

                this.repaint();
            }


        }
    }

    private boolean checkWin(int x, int y) {
        boolean flag = false;
        int count = 1;
        int color = allChess[x][y];
//
//        // by x axis
//        int i = 1;
//        while(color == allChess[x+i][y] && x+i >= 0 && x+i<= 20 && y >= 0 && y<= 20) {
//            count++;
//            i++;
//        }
//        i = 1;
//        while(color == allChess[x-i][y]&& x-i >= 0 && x-i<= 20 && y >= 0 && y<= 20) {
//            count++;
//            i++;
//        }
//
//        if (count >= 5) {
//            flag = true;
//        }
//
//        // by y axis
//
//        int j = 1;
//        int count2 = 1;
//        while(color == allChess[x][y+j]&& x >= 0 && x<= 20 && y+j >= 0 && y+j<= 20) {
//            count2++;
//            j++;
//        }
//        i = 1;
//        while(color == allChess[x][y-j]&& x >= 0 && x<= 20 && y-j >= 0 && y-j<= 20) {
//            count2++;
//            j++;
//        }
//
//        if (count2 >= 5) {
//            flag = true;
//        }
//
//        // by x way
//
//        int k = 1;
//        int count3 = 1;
//
//        while(color == allChess[x+k][y-k]&& x+k >= 0 && x+k<= 20 && y-k >= 0 && y-k<= 20) {
//            count3++;
//            k++;
//        }
//        i = 1;
//        while(color == allChess[x-k][y+k] && x-k >= 0 && x-k<= 20 && y+k >= 0 && y+k<= 20) {
//            count3++;
//            k++;
//        }
//
//        if (count3 >= 5) {
//            flag = true;
//        }
//
//        // by x way
//
//        int n = 1;
//        int count4 = 1;
//
//        while(color == allChess[x+n][y+n]&& x+n >= 0 && x+n<= 20 && y+n >= 0 && y+n<= 20) {
//            count4++;
//            n++;
//        }
//        i = 1;
//        while(color == allChess[x-n][y-n]&& x-n >= 0 && x-n<= 20 && y-n >= 0 && y-n<= 20) {
//            count4++;
//            n++;
//        }
//
//        if (count4 >= 5) {
//            flag = true;
//        }

        count = this.checkCount(1, 0, color);
        if (count >= 5) {
            flag = true;
        } else {
            count = this.checkCount(0, 1, color);
            if (count >= 5) {
                flag = true;
            } else {
                count = this.checkCount(1, -1, color);
                if (count >= 5) {
                    flag = true;
                } else {
                    count = this.checkCount(1, 1, color);
                    if (count >= 5) {
                        flag = true;
                    }
                }
            }
        }
             return flag;
    }

    private int checkCount(int xChange, int yChange, int color) {
        int count = 1;
        int tempX = xChange;
        int tempY = yChange;

        while (color == allChess[x + xChange][y +yChange]) {
            count++;
            if (xChange != 0) {
                xChange++;
            }
            if (yChange !=0) {
                if (yChange > 0){
                yChange++;
                } else {
                    yChange--;
                }
            }
        }

        xChange = tempX;
        yChange = tempY;
        while (color == allChess[x - xChange][y - yChange]) {
            count++;
            if (xChange != 0) {
                xChange++;
            }
            if (yChange !=0) {
                if (yChange > 0){
                    yChange++;
                } else {
                    yChange--;
                }
            }
        }
        return count;
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

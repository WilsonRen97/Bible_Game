package com.company;

import com.company.GameView.DisasterView;
import com.company.GameView.GameView;
import com.company.GameView.RedSeaGameView;
import com.company.GameView.TenCommandmentsView;
import com.company.Sprite.DisasterViewSprite.Bug;
import com.company.Sprite.DisasterViewSprite.Frog;
import com.company.Sprite.DisasterViewSprite.Ice;
import com.company.Sprite.DisasterViewSprite.Tombstone;
import com.company.Sprite.Moses;
import com.company.Sprite.RedSeaViewSprite.Anubis;
import com.company.Sprite.RedSeaViewSprite.Cat;
import com.company.Sprite.RedSeaViewSprite.Pharaoh;
import com.company.Sprite.Sprite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main extends JPanel implements KeyListener {
    public static final int CELL = 50;
    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;
    public static final int ROW = HEIGHT / CELL;
    public static final int COLUMN = WIDTH / CELL;
    Moses moses;
    private int level;

    public static GameView gameView;

    public Main() {
        level = 1;
        addKeyListener(this);
        resetGame(new DisasterView());
    }


    public void resetGame(GameView game) {
        moses = new Moses(1, 1);
        gameView = game;
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        gameView.drawView(g);
        moses.draw(g);
        requestFocusInWindow();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
    }

    public static void main(String[] args) {
        JFrame window = new JFrame("Bible Game");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setContentPane(new Main());
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setResizable(false);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    private boolean changeLevel(String result) {
        if (result.equals("Next level")) {
            level++;
            System.out.println(level);
            if (level == 2) {
                resetGame(new RedSeaGameView());
            } else if (level == 3) {
                System.out.println("we are in level 3.");
                resetGame(new TenCommandmentsView());
            }
            return true;
        }
        return false;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Point mosesPoint = moses.getRelativePostition();
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                if (mosesPoint.y > 1) {
                    String result = moses.overlap(mosesPoint.x, mosesPoint.y - 1);
                    if (result.equals("Die")) {
                        level = 1;
                        JOptionPane.showMessageDialog(this, "You die.");
                        resetGame(new DisasterView());
                        return;
                    }
                    if (!result.equals("Cannot move")) {
                        mosesPoint.y -= 1;
                    }
                    if (result.equals("Game over")) {
                        JOptionPane.showMessageDialog(this, "You win the game.");
                        return;
                    }
                    if (changeLevel(result)) return;
                }
                break;
            case KeyEvent.VK_DOWN:
                if (mosesPoint.y < ROW) {
                    String result = moses.overlap(mosesPoint.x, mosesPoint.y + 1);
                    if (result.equals("Die")) {
                        level = 1;
                        JOptionPane.showMessageDialog(this, "You die.");
                        resetGame(new DisasterView());
                        return;
                    }
                    if (!result.equals("Cannot move")) {
                        mosesPoint.y += 1;
                    }
                    if (result.equals("Game over")) {
                        JOptionPane.showMessageDialog(this, "You win the game.");
                        return;
                    }
                    if (changeLevel(result)) return;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (mosesPoint.x < COLUMN) {
                    String result = moses.overlap(mosesPoint.x + 1, mosesPoint.y);
                    if (result.equals("Die")) {
                        level = 1;
                        JOptionPane.showMessageDialog(this, "You die.");
                        resetGame(new DisasterView());
                        return;
                    }
                    if (!result.equals("Cannot move")) {
                        mosesPoint.x += 1;
                    }
                    if (result.equals("Game over")) {
                        JOptionPane.showMessageDialog(this, "You win the game.");
                        return;
                    }
                    if (changeLevel(result)) return;
                }
                break;
            case KeyEvent.VK_LEFT:
                if (mosesPoint.x > 1) {
                    String result = moses.overlap(mosesPoint.x - 1, mosesPoint.y);
                    if (result.equals("Die")) {
                        level = 1;
                        JOptionPane.showMessageDialog(this, "You die.");
                        resetGame(new DisasterView());
                        return;
                    }
                    if (!result.equals("Cannot move")) {
                        mosesPoint.x -= 1;
                    }
                    if (result.equals("Game over")) {
                        JOptionPane.showMessageDialog(this, "You win the game.");
                        return;
                    }
                    if (changeLevel(result)) return;
                }
                break;

            case KeyEvent.VK_W:
                for (int i = mosesPoint.y; i > 0; i--) {
                    for (Sprite s : gameView.getElements()) {
                        if (s.getRelativePostition() != null && s.getRelativePostition().x == mosesPoint.x && s.getRelativePostition().y == i) {
                            // avoid shooting across the sprites that cannot move.
                            if (s instanceof Cat || s instanceof Ice || s instanceof Tombstone) {
                                return;
                            }
                            if (s instanceof Anubis || s instanceof Bug || s instanceof Pharaoh || s instanceof Frog) {
                                s.setNullPosition();
                                repaint();
                                return;
                            }
                        }
                    }
                }
                break;
            case KeyEvent.VK_S:
                for (int i = mosesPoint.y; i <= ROW; i++) {
                    for (Sprite s : gameView.getElements()) {
                        if (s.getRelativePostition() != null && s.getRelativePostition().x == mosesPoint.x && s.getRelativePostition().y == i) {
                            // avoid shooting across the sprites that cannot move.
                            if (s instanceof Cat || s instanceof Ice || s instanceof Tombstone) {
                                return;
                            }
                            if (s instanceof Anubis || s instanceof Bug || s instanceof Pharaoh || s instanceof Frog) {
                                s.setNullPosition();
                                repaint();
                                return;
                            }
                        }
                    }
                }
                break;
            case KeyEvent.VK_D:
                for (int i = mosesPoint.x; i <= COLUMN; i++) {
                    for (Sprite s : gameView.getElements()) {
                        if (s.getRelativePostition() != null && s.getRelativePostition().x == i && s.getRelativePostition().y == mosesPoint.y) {
                            // avoid shooting across the sprites that cannot move.
                            if (s instanceof Cat || s instanceof Ice || s instanceof Tombstone) {
                                return;
                            }
                            if (s instanceof Anubis || s instanceof Bug || s instanceof Pharaoh || s instanceof Frog) {
                                s.setNullPosition();
                                repaint();
                                return;
                            }
                        }
                    }
                }
                break;

            case KeyEvent.VK_A:
                for (int i = mosesPoint.x; i >= 0; i--) {
                    for (Sprite s : gameView.getElements()) {
                        if (s.getRelativePostition() != null && s.getRelativePostition().x == i && s.getRelativePostition().y == mosesPoint.y) {
                            // avoid shooting across the sprites that cannot move.
                            if (s instanceof Cat || s instanceof Ice || s instanceof Tombstone) {
                                return;
                            }
                            if (s instanceof Anubis || s instanceof Bug || s instanceof Pharaoh || s instanceof Frog) {
                                s.setNullPosition();
                                repaint();
                                return;
                            }
                        }
                    }
                }
                break;
        }
        moses.setPosition(mosesPoint);
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}

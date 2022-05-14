package com.company.Sprite;


import com.company.GameView.DisasterView;
import com.company.GameView.RedSeaGameView;
import com.company.GameView.TenCommandmentsView;
import com.company.Main;
import com.company.Sprite.DisasterViewSprite.*;
import com.company.Sprite.RedSeaViewSprite.Anubis;
import com.company.Sprite.RedSeaViewSprite.Cat;
import com.company.Sprite.RedSeaViewSprite.Pharaoh;
import com.company.Sprite.TenCommandmentsSprite.TenCommandment;

import javax.swing.*;
import java.util.ArrayList;

public class Moses extends Sprite {

    public Moses(int x, int y) {
        setPosition(x, y);
        img = new ImageIcon("Moses.png");
    }

    @Override
    public String overlap(int x, int y) {
        if (Main.gameView instanceof DisasterView) {
            // check bugs and frogs
            ArrayList<Frog> frogs = ((DisasterView) Main.gameView).getFrogs();
            ArrayList<Bug> bugs = ((DisasterView) Main.gameView).getBugs();
            for (Frog f : frogs) {
                if (f.getRelativePostition() != null && x == f.getRelativePostition().x && y == f.getRelativePostition().y) {
                    return "Die";
                }
            }
            for (Bug b : bugs) {
                if (b.getRelativePostition() != null && x == b.getRelativePostition().x && y == b.getRelativePostition().y) {
                    return "Die";
                }
            }
            // check for ice and tombstones
            ArrayList<Ice> ices = ((DisasterView) Main.gameView).getIceCubes();
            ArrayList<Tombstone> tombstones = ((DisasterView) Main.gameView).getStones();
            for (Ice ice : ices) {
                if (ice.getRelativePostition() != null && x == ice.getRelativePostition().x && y == ice.getRelativePostition().y) {
                    return "Cannot move";
                }
            }
            for (Tombstone stone : tombstones) {
                if (stone.getRelativePostition() != null && x == stone.getRelativePostition().x && y == stone.getRelativePostition().y) {
                    return "Cannot move";
                }
            }
            // check door
            Door door = Main.gameView.getDoor();
            if (x == door.getRelativePostition().x && y == door.getRelativePostition().y) {
                return "Next level";
            }
        } else if (Main.gameView instanceof RedSeaGameView) {
            // check cats
            ArrayList<Cat> cats = ((RedSeaGameView) Main.gameView).getCats();
            for (Cat c : cats) {
                if (c.getRelativePostition() != null && x == c.getRelativePostition().x && y == c.getRelativePostition().y) {
                    return "Cannot move";
                }
            }
            // check pharaoh and anubis
            ArrayList<Anubis> anubis = ((RedSeaGameView) Main.gameView).getAnubis();
            ArrayList<Pharaoh> pharaohs = ((RedSeaGameView) Main.gameView).getPharaohs();
            for (Anubis a : anubis) {
                if (a.getRelativePostition() != null && x == a.getRelativePostition().x && y == a.getRelativePostition().y) {
                    return "Die";
                }
            }
            for (Pharaoh p : pharaohs) {
                if (p.getRelativePostition() != null && x == p.getRelativePostition().x && y == p.getRelativePostition().y) {
                    return "Die";
                }
            }

            Door door = Main.gameView.getDoor();
            if (x == door.getRelativePostition().x && y == door.getRelativePostition().y) {
                return "Next level";
            }
        } else if (Main.gameView instanceof TenCommandmentsView) {
            TenCommandment stone = ((TenCommandmentsView) Main.gameView).getStone();
            if (x == stone.getRelativePostition().x && y == stone.getRelativePostition().y) {
                return  "Game over";
            }
        }

        return "none";
    }
}

package com.company.Sprite.DisasterViewSprite;

import com.company.Sprite.Sprite;

import javax.swing.*;


public class Tombstone extends Sprite {

    public Tombstone(int x, int y) {
        setPosition(x, y);
        img = new ImageIcon("Tombstone.png");
    }

    @Override
    public String overlap(int x, int y) {
        return "none";
    }
}


package com.github.exformatgames.defender.utils;

public class Filter extends com.badlogic.gdx.physics.box2d.Filter {

    public Filter() {}

    public Filter(short category, short mask, short group) {
        categoryBits = category;
        maskBits = mask;
        groupIndex = group;
    }
}

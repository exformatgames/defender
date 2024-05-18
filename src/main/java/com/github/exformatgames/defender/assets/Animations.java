package com.github.exformatgames.defender.assets;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;

public class Animations {

    private final Array<TextureAtlas.AtlasRegion> animations = new Array<>();
    private final Array<String> animationNames = new Array<>();

    public void add(TextureAtlas.AtlasRegion atlasRegion, String name) {
        animations.add(atlasRegion);
        animationNames.add(name);
    }

    public TextureAtlas.AtlasRegion get(String name) {
        for (int i = 0; i < animationNames.size; i++) {
            if (name.equals(animationNames.get(i))) {
                return animations.get(i);
            }
        }
        return null;
    }
}

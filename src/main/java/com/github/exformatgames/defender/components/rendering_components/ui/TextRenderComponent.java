package com.github.exformatgames.defender.components.rendering_components.ui;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class TextRenderComponent implements Component {
    
    public BitmapFont bitmapFont = null;
    public String text = "";

    public BitmapFont.BitmapFontData bitmapFontData;

    public TextRenderComponent init(BitmapFont bitmapFont, String text) {
        this.bitmapFont = bitmapFont;
        this.text = text;

        bitmapFontData = bitmapFont.getData();

        return this;
    }

    public TextRenderComponent setScale(float scaleXY) {
        bitmapFontData.setScale(scaleXY);
        return this;
    }

    private final static ComponentMapper<TextRenderComponent> mapper = ComponentMapper.getFor(TextRenderComponent.class);

    public static TextRenderComponent getComponent(Entity entity) {        
        return mapper.get(entity);
    }
}

package com.github.exformatgames.defender.components.rendering_components.ui;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class TextRenderComponent implements Component {
    
    public BitmapFont bitmapFont = null;
    public String text = "";
    
    private static ComponentMapper<TextRenderComponent> mapper = ComponentMapper.getFor(TextRenderComponent.class);

    public static TextRenderComponent getComponent(Entity entity) {        
        return mapper.get(entity);
    }
}

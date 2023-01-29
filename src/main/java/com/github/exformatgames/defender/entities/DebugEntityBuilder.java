package com.github.exformatgames.defender.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.github.exformatgames.defender.components.rendering_components.ui.TextRenderComponent;
import com.github.exformatgames.defender.utils.EntityBuilder;

public class DebugEntityBuilder extends EntityBuilder {

    @Override
    public void create() {
        createComponent(TextRenderComponent.class).init(new BitmapFont(Gdx.files.internal("font.fnt")), "")
                .setScale(2);
    }
}

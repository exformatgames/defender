package com.github.exformatgames.defender.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.github.exformatgames.defender.Configurations;
import com.github.exformatgames.defender.components.rendering_components.ui.TextRenderComponent;
import com.github.exformatgames.defender.components.transform_components.PositionComponent;
import com.github.exformatgames.defender.components.util_components.DebugComponent;
import com.github.exformatgames.defender.utils.EntityBuilder;

public class DebugEntityBuilder extends EntityBuilder {

    @Override
    public void create() {
        createComponent(DebugComponent.class);
        createComponent(PositionComponent.class).init(Configurations.UI_WIDTH / 2, Configurations.UI_HEIGHT);
        createComponent(TextRenderComponent.class).init(new BitmapFont(Gdx.files.internal("font.fnt")), "debug")
                .setScale(2);
    }
}

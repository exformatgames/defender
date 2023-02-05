package com.github.exformatgames.defender.systems.rendering_systems.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;

public class AsyncTextRenderSystem extends TextRenderSystem {

    public AsyncTextRenderSystem(Viewport viewport, SpriteBatch spriteBatch) {
        super(viewport, spriteBatch);
    }

    @Override
    public void update(float deltaTime) {
        Gdx.app.postRunnable(() -> {
                    AsyncTextRenderSystem.super.update(deltaTime);
                }
        );
    }
}

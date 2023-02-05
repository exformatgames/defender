package com.github.exformatgames.defender.systems.rendering_systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;

public class AsyncSortRenderSystem extends SortedRenderSystem {

    public AsyncSortRenderSystem(Viewport viewport, SpriteBatch batch) {
        super(viewport, batch);
    }

    @Override
    public void update(float deltaTime) {
        Gdx.app.postRunnable(() -> {
                    AsyncSortRenderSystem.super.update(deltaTime);
                }
        );
    }
}

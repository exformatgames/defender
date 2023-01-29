package com.github.exformatgames.defender.systems.rendering_systems.ui;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.exformatgames.defender.Configurations;
import com.github.exformatgames.defender.components.rendering_components.ui.TextRenderComponent;
import com.github.exformatgames.defender.components.transform_components.PositionComponent;

public class TextRenderSystem extends IteratingSystem {

    private final SpriteBatch spriteBatch;
    private final OrthographicCamera camera;
    private final Viewport viewport;

    public TextRenderSystem(Viewport viewport, SpriteBatch spriteBatch) {
        super(Family.all(TextRenderComponent.class).get());
        this.camera = (OrthographicCamera) viewport.getCamera();
        this.spriteBatch = spriteBatch;
        this.viewport = viewport;
    }

    @Override
    public void startProcessing() {

        viewport.apply(true);

        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
    }

    @Override
    public void endProcessing() {
        spriteBatch.end();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TextRenderComponent textRenderComponent = TextRenderComponent.getComponent(entity);
        PositionComponent positionComponent = PositionComponent.getComponent(entity);

        textRenderComponent.bitmapFont.draw(spriteBatch, textRenderComponent.text, positionComponent.x * Configurations.VIEWPORTS_RATIO, positionComponent.y * Configurations.VIEWPORTS_RATIO);
    }
}

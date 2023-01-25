package com.github.exformatgames.defender.systems.rendering_systems.ui;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.github.exformatgames.defender.components.rendering_components.ui.TextRenderComponent;
import com.github.exformatgames.defender.components.transform_components.PositionComponent;

public class TextRenderSystem extends IteratingSystem {

    private SpriteBatch spriteBatch;
    private OrthographicCamera camera;

    public TextRenderSystem(OrthographicCamera camera, SpriteBatch spriteBatch) {
        super(Family.all(TextRenderComponent.class).get());
        this.camera = camera;
        this.spriteBatch = spriteBatch;
    }

    @Override
    public void startProcessing() {
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

        textRenderComponent.bitmapFont.draw(spriteBatch, textRenderComponent.text, positionComponent.x, positionComponent.y);
    }
}

package com.github.exformatgames.defender.systems.debug;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.math.*;
import com.github.exformatgames.defender.Configurations;
import com.github.exformatgames.defender.components.rendering_components.SpriteComponent;

public class DebugSpriteSystem extends IteratingSystem {

    private final OrthographicCamera worldCamera;
    private final OrthographicCamera uiCamera;
    private final SpriteBatch spriteBatch;
    private final ShapeRenderer shapeRenderer;

    private final BitmapFont bitmapFont;

    private final Vector2 vector = new Vector2();

    public DebugSpriteSystem(OrthographicCamera worldCamera, OrthographicCamera uiCamera, SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        super(Family.all(SpriteComponent.class).get());

        this.worldCamera = worldCamera;
        this.uiCamera = uiCamera;
        this.spriteBatch = spriteBatch;
        this.shapeRenderer = shapeRenderer;

        bitmapFont = new BitmapFont(Gdx.files.internal("font.fnt"));
        bitmapFont.getData().setScale(2);
    }

    @Override
    public void startProcessing() {
        shapeRenderer.setProjectionMatrix(worldCamera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.CORAL);

        spriteBatch.setProjectionMatrix(uiCamera.combined);
        spriteBatch.begin();
    }

    @Override
    public void endProcessing() {
        shapeRenderer.end();
        spriteBatch.end();
    }

    @Override
    protected void processEntity(Entity entity, float dt) {
        SpriteComponent spriteComponent = SpriteComponent.getComponent(entity);
        for (SpriteComponent sprite : spriteComponent.spriteComponentArray) {
            Rectangle rect = sprite.getBoundingRectangle();
            shapeRenderer.rect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
            shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.circle(sprite.x + sprite.originX, sprite.y + sprite.originY, Configurations.WORLD_WIDTH / 100);

            bitmapFont.draw(spriteBatch, "" + rect.getPosition(vector).toString() + " a:" + sprite.rotation, rect.getX(), rect.getY());
        }
    }
}

package com.github.exformatgames.defender.systems.debug;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.exformatgames.defender.Configurations;
import com.github.exformatgames.defender.components.rendering_components.SpriteComponent;
import com.github.exformatgames.defender.components.util_components.DebugComponent;

public class DebugSpriteSystem extends IteratingSystem {

    private final Viewport worldViewport;
    private final Viewport uiViewport;
    private final OrthographicCamera worldCamera;
    private final OrthographicCamera uiCamera;
    private final SpriteBatch spriteBatch;
    private final ShapeRenderer shapeRenderer;

    private final BitmapFont bitmapFont;

    public DebugSpriteSystem(Viewport worldViewport, Viewport uiViewport, SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        super(Family.all(SpriteComponent.class, DebugComponent.class).get());

        this.worldViewport = worldViewport;
        this.uiViewport = uiViewport;
        this.spriteBatch = spriteBatch;
        this.shapeRenderer = shapeRenderer;

        this.worldCamera = (OrthographicCamera) worldViewport.getCamera();
        this.uiCamera = (OrthographicCamera) uiViewport.getCamera();

        bitmapFont = new BitmapFont(Gdx.files.internal("debug-font.fnt"));
        bitmapFont.getData().setScale(2);
    }

    @Override
    public void startProcessing() {
        worldViewport.apply(true);
        shapeRenderer.setProjectionMatrix(worldCamera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.CORAL);
    }

    @Override
    public void endProcessing() {
        shapeRenderer.end();
    }

    @Override
    protected void processEntity(Entity entity, float dt) {
        SpriteComponent spriteComponent = SpriteComponent.getComponent(entity);
        for (int i = 0; i < spriteComponent.spriteComponentArray.size; i++) {
            SpriteComponent sprite = spriteComponent.spriteComponentArray.get(i);

            Rectangle rect = sprite.getBoundingRectangle();
            shapeRenderer.rect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
            shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.circle(sprite.x + sprite.originX, sprite.y + sprite.originY, 0.05f, 20);

            uiViewport.apply(true);
            spriteBatch.setProjectionMatrix(uiCamera.combined);
            spriteBatch.begin();
            String template = "x:%.2f y:%.2f a:%.2f";
            bitmapFont.draw(spriteBatch, String.format(template, sprite.x + sprite.offsetX, sprite.y + sprite.offsetY, sprite.rotation)
                    , (sprite.x + sprite.offsetX) * Configurations.VIEWPORTS_RATIO
                    , (sprite.y + sprite.offsetY) * Configurations.VIEWPORTS_RATIO);
            spriteBatch.end();
        }
    }
}

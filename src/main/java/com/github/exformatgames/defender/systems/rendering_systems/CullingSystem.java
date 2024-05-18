package com.github.exformatgames.defender.systems.rendering_systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.exformatgames.defender.Configurations;
import com.github.exformatgames.defender.components.rendering_components.CullingComponent;
import com.github.exformatgames.defender.components.rendering_components.SpriteComponent;

public class CullingSystem extends IteratingSystem {

    private final Viewport viewport;

    private final Rectangle cameraBounds;

    public CullingSystem(Viewport viewport) {
        super(Family.all(CullingComponent.class).get());

        this.viewport = viewport;

        cameraBounds = new Rectangle();
        cameraBounds.x = viewport.getCamera().position.x;
        cameraBounds.y = viewport.getCamera().position.y;
        cameraBounds.width = viewport.getCamera().viewportWidth;
        cameraBounds.height = viewport.getCamera().viewportHeight;
    }

    @Override
    public void startProcessing() {
        float realViewportWidth = (float) viewport.getScreenWidth() - (Math.abs(viewport.getLeftGutterWidth()) * 2);
        float realViewportHeight = (float) viewport.getScreenHeight() - (Math.abs(viewport.getBottomGutterHeight()) * 2);

        float widthRatio = (float) viewport.getScreenWidth() / Configurations.WORLD_WIDTH;
        float heightRatio = (float) viewport.getScreenHeight() / Configurations.WORLD_HEIGHT;

        float realWorldWidth = realViewportWidth / widthRatio;
        float halfRealWorldWidth = realWorldWidth / 2;

        float realWorldHeight = realViewportHeight / heightRatio;
        float halfRealWorldHeight = realWorldHeight / 2;

        float leftEdge = Configurations.WORLD_WIDTH / 2 - halfRealWorldWidth;

        float bottomEdge = Configurations.WORLD_HEIGHT / 2 - halfRealWorldHeight;

        cameraBounds.x = leftEdge;
        cameraBounds.y = bottomEdge;
        cameraBounds.width = realWorldWidth;
        cameraBounds.height = realWorldHeight;
    }

    @Override
    protected void processEntity(Entity entity, float dt) {
        CullingComponent cullingComponent = CullingComponent.getComponent(entity);
        SpriteComponent spriteComponent = SpriteComponent.getComponent(entity);

        cullingComponent.inViewport = cameraBounds.overlaps(spriteComponent.getBoundingRectangle());
    }
}

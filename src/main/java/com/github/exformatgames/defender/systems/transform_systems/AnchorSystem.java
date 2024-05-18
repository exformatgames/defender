package com.github.exformatgames.defender.systems.transform_systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.exformatgames.defender.Configurations;
import com.github.exformatgames.defender.components.transform_components.AnchorComponent;
import com.github.exformatgames.defender.components.transform_components.NewPositionComponent;
import com.github.exformatgames.defender.components.transform_components.PositionComponent;
import com.github.exformatgames.defender.components.transform_components.SizeComponent;
import com.github.exformatgames.defender.utils.EntityBuilder;

public class AnchorSystem extends IteratingSystem {

    private Viewport viewport;
    private float leftEdge;
    private float rightEdge;
    private float bottomEdge;
    private float topEdge;

    public AnchorSystem(Viewport viewport) {
        super(Family.all(AnchorComponent.class).get());
        this.viewport = viewport;
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

        leftEdge = Configurations.WORLD_WIDTH / 2 - halfRealWorldWidth;
        rightEdge = Configurations.WORLD_WIDTH / 2 + halfRealWorldWidth;

        bottomEdge = Configurations.WORLD_HEIGHT / 2 - halfRealWorldHeight;
        topEdge = Configurations.WORLD_HEIGHT / 2 + halfRealWorldHeight;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        AnchorComponent anchorComponent = AnchorComponent.getComponent(entity);

        PositionComponent positionComponent = PositionComponent.getComponent(entity);
        SizeComponent sizeComponent = SizeComponent.getComponent(entity);

        switch (anchorComponent.side) {
            case TOP: {
                float x = positionComponent.x;
                float y = topEdge - sizeComponent.height + positionComponent.y;

                EntityBuilder.addComponent(entity, NewPositionComponent.class).init(x, y);
                break;
            }
            case BOTTOM: {
                float x = positionComponent.x;
                float y = bottomEdge;

                EntityBuilder.addComponent(entity, NewPositionComponent.class).init(x, y);
                break;
            }

            case LEFT: {
                float x = leftEdge + positionComponent.x;
                float y = positionComponent.y;

                EntityBuilder.addComponent(entity, NewPositionComponent.class).init(x, y);
                break;
            }

            case RIGHT: {
                float x = rightEdge - sizeComponent.width + positionComponent.x;
                float y = positionComponent.y;

                EntityBuilder.addComponent(entity, NewPositionComponent.class).init(x, y);
                break;
            }
        }
    }
}

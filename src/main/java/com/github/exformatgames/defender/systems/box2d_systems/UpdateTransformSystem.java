package com.github.exformatgames.defender.systems.box2d_systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.*;
import com.github.exformatgames.defender.components.box2d.BodyComponent;
import com.github.exformatgames.defender.components.transform_components.NewPositionComponent;
import com.github.exformatgames.defender.components.transform_components.RotationComponent;
import com.github.exformatgames.defender.components.transform_components.SizeComponent;
import com.github.exformatgames.defender.utils.EntityBuilder;

public class UpdateTransformSystem extends IteratingSystem {

    public static float FRAME_TIME = 0;
    private long startTime = 0;

    public UpdateTransformSystem() {
        super(Family.all(BodyComponent.class).get());
    }

    @Override
    public void startProcessing() {
        startTime = TimeUtils.nanoTime();
    }

    @Override
    public void endProcessing() {
        FRAME_TIME = (TimeUtils.nanoTime() - startTime) / 1000_000f;
    }

    @Override
    protected void processEntity(Entity entity, float dt) {
        BodyComponent bodyComponent = BodyComponent.getComponent(entity);
        SizeComponent sizeComponent = SizeComponent.getComponent(entity);

        Vector2 position = bodyComponent.body.getPosition();
        float angle = bodyComponent.body.getAngle() * MathUtils.radiansToDegrees;

        EntityBuilder.createComponent(entity, NewPositionComponent.class)
                .init(position.x - sizeComponent.halfWidth, position.y - sizeComponent.halfHeight);
        EntityBuilder.createComponent(entity, RotationComponent.class).degres = angle;

        bodyComponent.oldPosition.set(position);
        bodyComponent.oldRotation = angle;
    }
}



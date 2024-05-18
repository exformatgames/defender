package com.github.exformatgames.defender.systems.box2d_systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.RayCastCallback;
import com.badlogic.gdx.physics.box2d.World;
import com.github.exformatgames.defender.components.box2d.RayComponent;

public class RayCastSystem extends IteratingSystem {

    private final RayCastCallback callback;

    private final Vector2 collisionPoint = new Vector2();
    private Fixture collisionFixture;
    private float closestFraction = 8;

    private final World world;

    public RayCastSystem(World world) {
        super(Family.all(RayComponent.class).get());

        this.world = world;

        callback = new RayCastCallback() {
            @Override
            public float reportRayFixture(Fixture fixture, Vector2 point, Vector2 normal, float fraction) {
                if (fraction < RayCastSystem.this.closestFraction) {
                    RayCastSystem.this.closestFraction = fraction;
                    RayCastSystem.this.collisionPoint.set(point);
                    RayCastSystem.this.collisionFixture = fixture;
                }

                return 1;
            }
        };
    }


    @Override
    protected void processEntity(Entity entity, float dt) {
        RayComponent rayComponent = RayComponent.getComponent(entity);

        if (rayComponent.isCast) {

            closestFraction = rayComponent.maxRayLength;

            world.rayCast(callback, rayComponent.fromPoint, rayComponent.toPoint);

            //TODO без проверки и обнуления в конце, collisionPoint будет висеть на старой фикстуре
            if (collisionFixture != null) {
                rayComponent.collisionFixture = collisionFixture;
                rayComponent.collisionPoint.set(collisionPoint);

                rayComponent.length = rayComponent.fromPoint.dst(collisionPoint);
            } else {
                rayComponent.collisionFixture = null;
                rayComponent.collisionPoint.set(rayComponent.toPoint);
                //TODO хз откуда берутся лишние 1.5..
                rayComponent.length = rayComponent.maxRayLength - 1.5f;
            }

            collisionPoint.set(0, 0);
            collisionFixture = null;

            rayComponent.isCast = false;
        }
    }
}

package com.github.exformatgames.defender.systems.box2d_systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.physics.box2d.World;
import com.github.exformatgames.defender.components.box2d.BodyComponent;
import com.github.exformatgames.defender.components.box2d.RemoveBodyComponent;

public class RemoveBodySystem extends IteratingSystem {
    private final World world;

    public RemoveBodySystem(World world) {
        super(Family.all(RemoveBodyComponent.class).get());
        this.world = world;
    }

    @Override
    protected void processEntity(Entity entity, float dt) {
        BodyComponent bodyComponent = BodyComponent.getComponent(entity);

        if (bodyComponent != null) {
            bodyComponent.body.setActive(false);
            bodyComponent.body.setLinearVelocity(0, 0);
            bodyComponent.body.setAngularVelocity(0);

            world.destroyBody(bodyComponent.body);

            entity.remove(BodyComponent.class);
        }
        entity.remove(RemoveBodyComponent.class);
    }
}

package com.github.exformatgames.defender.systems.game_systems.bullet;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.github.exformatgames.defender.components.box2d.BodyComponent;
import com.github.exformatgames.defender.components.box2d.contact_components.BeginContactComponent;
import com.github.exformatgames.defender.components.game_components.BulletComponent;
import com.github.exformatgames.defender.components.game_components.DamageComponent;
import com.github.exformatgames.defender.components.game_components.state.StateComponent;
import com.github.exformatgames.defender.utils.EntityBuilder;

public abstract class BulletContactSystem extends IteratingSystem {

    public BulletContactSystem() {
        super(Family.all(BulletComponent.class, BeginContactComponent.class).get());
    }


    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        BeginContactComponent beginContactComponent = BeginContactComponent.getComponent(entity);
        BulletComponent bulletComponent = BulletComponent.getComponent(entity);
        BodyComponent bodyComponent = BodyComponent.getComponent(entity);

        Entity targetEntity = beginContactComponent.contactEntity;
        EntityBuilder.addComponent(targetEntity, DamageComponent.class).init(bulletComponent.damage, bulletComponent.type);

        StateComponent stateComponent = StateComponent.getComponent(targetEntity);
        if (stateComponent != null) {
            stateComponent.state = StateComponent.State.HIT;
        }

        Vector2 position = bodyComponent.body.getPosition();
        hit(entity, targetEntity, position.x, position.y);
    }

    protected abstract void hit(Entity entity, Entity hitEntity, float x, float y);
}

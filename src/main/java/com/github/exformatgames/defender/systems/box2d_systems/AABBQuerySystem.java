package com.github.exformatgames.defender.systems.box2d_systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.QueryCallback;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.github.exformatgames.defender.components.box2d.AABBAnswerComponent;
import com.github.exformatgames.defender.components.box2d.AABBQueryComponent;
import com.github.exformatgames.defender.utils.EntityBuilder;

public class AABBQuerySystem extends IteratingSystem {

    private final World world;
    private final QueryCallback queryCallback;

    private final Array<Body> bodies = new Array<>();

    public AABBQuerySystem(World world) {
        super(Family.all(AABBQueryComponent.class).get());

        this.world = world;

        queryCallback = new QueryCallback() {
            @Override
            public boolean reportFixture(Fixture fixture) {
                bodies.add(fixture.getBody());
                return true;
            }
        };
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        bodies.clear();

        AABBQueryComponent queryComponent = AABBQueryComponent.getComponent(entity);

        world.QueryAABB(queryCallback, queryComponent.x, queryComponent.y, queryComponent.x2, queryComponent.y2);
        entity.remove(AABBQueryComponent.class);

        EntityBuilder.createComponent(entity, AABBAnswerComponent.class).bodies.addAll(bodies);
    }
}

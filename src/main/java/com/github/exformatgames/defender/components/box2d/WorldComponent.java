package com.github.exformatgames.defender.components.box2d;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.physics.box2d.*;

public class WorldComponent implements Component {

    public World world;
    public int velocityIteration = 4;
    public int positionIteration = 4;

    public void init(World world, int velocityIteration, int positionIteration) {
        this.world = world;
        this.velocityIteration = velocityIteration;
        this.positionIteration = positionIteration;
    }

    public void init(World world) {
        this.world = world;
    }

    private final static ComponentMapper<WorldComponent> mapper = ComponentMapper.getFor(WorldComponent.class);

    public static WorldComponent getComponent(Entity entity) {
        return mapper.get(entity);
    }
}

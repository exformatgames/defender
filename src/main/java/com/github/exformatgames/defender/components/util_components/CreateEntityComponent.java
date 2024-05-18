package com.github.exformatgames.defender.components.util_components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.github.exformatgames.defender.utils.EntityBuilder;

public class CreateEntityComponent implements Component {

    public EntityBuilder entityBuilder = null;
    public int count = 0;
    public boolean infinityCount = false;
    public float interval = 0;
    public float timer = 0;
    public Vector2 position = new Vector2();
    public String json = null;

    public void init(EntityBuilder entityBuilder, int count, float interval, float timer, Vector2 position, String json) {
        this.entityBuilder = entityBuilder;
        this.count = count;
        this.interval = interval;
        this.timer = timer;
        this.position = position;
        this.json = json;
    }

    public void init(EntityBuilder entityBuilder, float interval) {
        this.entityBuilder = entityBuilder;
        this.interval = interval;
        infinityCount = true;
    }

    private static final ComponentMapper<CreateEntityComponent> mapper = ComponentMapper.getFor(CreateEntityComponent.class);

    public static CreateEntityComponent getComponent(Entity entity) {
        return mapper.get(entity);
    }
}

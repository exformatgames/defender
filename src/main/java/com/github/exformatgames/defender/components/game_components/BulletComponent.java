package com.github.exformatgames.defender.components.game_components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;

public class BulletComponent implements Component {

    public float damage = 0;
    public int type = 0;
    public short targetMask = 0;

    public void init(float damage, int type, short targetCategory) {
        this.damage = damage;
        this.type = type;
        this.targetMask = targetCategory;
    }

    public void init(float damage, short targetMask) {
        this.damage = damage;
        this.type = 0;
        this.targetMask = targetMask;
    }

    private final static ComponentMapper<BulletComponent> mapper = ComponentMapper.getFor(BulletComponent.class);

    public static BulletComponent getComponent(Entity entity) {
        return mapper.get(entity);
    }
}

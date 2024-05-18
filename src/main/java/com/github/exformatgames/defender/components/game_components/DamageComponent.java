package com.github.exformatgames.defender.components.game_components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;

public class DamageComponent implements Component {

    public float damage = 0;
    public int type = 0;

    public void init(float damage, int type) {
        this.damage = damage;
        this.type = type;
    }


    private final static ComponentMapper<DamageComponent> mapper = ComponentMapper.getFor(DamageComponent.class);

    public static DamageComponent getComponent(Entity entity) {
        return mapper.get(entity);
    }
}

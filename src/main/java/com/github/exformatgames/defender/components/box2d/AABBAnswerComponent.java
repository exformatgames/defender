package com.github.exformatgames.defender.components.box2d;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;

public class AABBAnswerComponent implements Component {

    public final Array<Body> bodies = new Array<>();

    private final static ComponentMapper<AABBAnswerComponent> mapper = ComponentMapper.getFor(AABBAnswerComponent.class);

    public static AABBAnswerComponent getComponent(Entity entity) {
        return mapper.get(entity);
    }
}

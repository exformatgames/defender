package com.github.exformatgames.defender.entities;

import com.github.exformatgames.defender.components.box2d.WorldComponent;
import com.github.exformatgames.defender.utils.EntityBuilder;

public class Box2DEntityBuilder extends EntityBuilder {

    @Override
    public void create() {
        createComponent(WorldComponent.class).init(world);
    }
}

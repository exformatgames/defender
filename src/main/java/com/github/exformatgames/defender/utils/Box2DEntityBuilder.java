package com.github.exformatgames.defender.utils;

import com.github.exformatgames.defender.components.box2d.WorldComponent;

public class Box2DEntityBuilder extends EntityBuilder {

    @Override
    public void create() {
        createComponent(WorldComponent.class).init(world);
    }
}

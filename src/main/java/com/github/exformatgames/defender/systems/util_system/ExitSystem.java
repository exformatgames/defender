package com.github.exformatgames.defender.systems.util_system;

import com.badlogic.ashley.systems.*;
import com.badlogic.ashley.core.*;
import com.badlogic.gdx.*;
import com.github.exformatgames.defender.components.ExitComponent;

public class ExitSystem extends IteratingSystem {

    public ExitSystem() {
        super(Family.all(ExitComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float dt) {
        System.out.println("exit");

        Gdx.app.exit();
    }
}

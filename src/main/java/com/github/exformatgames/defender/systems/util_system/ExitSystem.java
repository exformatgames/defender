package com.github.exformatgames.defender.systems.util_system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
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

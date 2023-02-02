package com.github.exformatgames.defender.systems.debug;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.github.exformatgames.defender.components.rendering_components.ui.TextRenderComponent;
import com.github.exformatgames.defender.components.util_components.DebugComponent;

public class DebugPrintEngineInfoSystem extends IteratingSystem {

    public DebugPrintEngineInfoSystem() {
        super(Family.one(DebugComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TextRenderComponent textRenderComponent = TextRenderComponent.getComponent(entity);

        int entities = getEngine().getEntities().size();
        int components = 0;
        for (Entity en: getEngine().getEntities()) {
            components += en.getComponents().size();
        }

        textRenderComponent.text = "FPS: " + (int)(1 / deltaTime)
                + " entities: " + entities
                + " components: " + components;
    }
}

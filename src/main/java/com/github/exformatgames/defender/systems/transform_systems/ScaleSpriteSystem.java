package com.github.exformatgames.defender.systems.transform_systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.*;
import com.github.exformatgames.defender.components.rendering_components.SpriteComponent;
import com.github.exformatgames.defender.components.transform_components.ScaleComponent;

public class ScaleSpriteSystem extends IteratingSystem {
    public ScaleSpriteSystem() {
        super(Family.all(ScaleComponent.class, SpriteComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float dt) {
        ScaleComponent scale = ScaleComponent.getComponent(entity);
        SpriteComponent spriteComponent = SpriteComponent.getComponent(entity);

        for (SpriteComponent sprite : spriteComponent.spriteComponentArray) {
            if (scale.isTo) {
                sprite.scaleX = scale.toX;
                sprite.scaleY = scale.toY;
            } else {
                sprite.scaleX += scale.byX;
                sprite.scaleY += scale.byY;
            }

            if (sprite.scaleX < 0 || sprite.scaleY < 0) {
                sprite.scaleX = 0;
                sprite.scaleY = 0;
            }

            sprite.dirty = true;
        }

        entity.remove(ScaleComponent.class);
    }
}

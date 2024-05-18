package com.github.exformatgames.defender.systems.transform_systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
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

        for (int i = 0; i < spriteComponent.spriteComponentArray.size; i++) {
            SpriteComponent sprite = spriteComponent.spriteComponentArray.get(i);
            if (scale.isTo) {
                //float width = sprite.width * scale.toX;
                //float height = sprite.height * scale.toY;
                //sprite.setSize(width, height);
                //sprite.setOriginCenter();
                sprite.scaleX = scale.toX;
                sprite.scaleY = scale.toY;
            } else {
                //float width = sprite.width + sprite.width * scale.byX;
                //float height = sprite.height + sprite.height * scale.byY;
                //sprite.setSize(width, height);
                //sprite.setOriginCenter();

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

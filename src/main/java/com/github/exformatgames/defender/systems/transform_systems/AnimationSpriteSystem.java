package com.github.exformatgames.defender.systems.transform_systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.github.exformatgames.defender.components.rendering_components.SpriteComponent;
import com.github.exformatgames.defender.components.transform_components.AnimationComponent;

public class AnimationSpriteSystem extends IteratingSystem {

    public AnimationSpriteSystem() {
        super(Family.all(AnimationComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float dt) {
        AnimationComponent animationComponent = AnimationComponent.getComponent(entity);
        SpriteComponent spriteComponent = SpriteComponent.getComponent(entity);

        for (int i = 0; i < animationComponent.animationComponentArray.size; i++) {
            AnimationComponent anim = animationComponent.animationComponentArray.get(i);
            SpriteComponent sprite = spriteComponent.spriteComponentArray.get(i);

            anim.timeAnimation += dt;

            if (anim.animation.getPlayMode() == Animation.PlayMode.LOOP || !anim.animation.isAnimationFinished(anim.timeAnimation)) {
                sprite.setUV(anim.animation.getKeyFrame(anim.timeAnimation));
            }
        }
    }
}

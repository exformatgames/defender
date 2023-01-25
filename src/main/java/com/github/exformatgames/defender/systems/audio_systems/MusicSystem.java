package com.github.exformatgames.defender.systems.audio_systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.*;
import com.github.exformatgames.defender.components.audio_components.MusicComponent;

public class MusicSystem extends IteratingSystem {

    public MusicSystem() {
        super(Family.all(MusicComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float dt) {
        MusicComponent component = MusicComponent.getComponent(entity);

        if (component.music != null) {


            if (component.music.getVolume() != component.volume) {
                component.music.setVolume(component.volume);
            }

            if (component.remove) {
                component.remove = false;
                component.pause = false;
                component.stop = false;
                component.play = false;
                component.volume = 0;

                entity.remove(MusicComponent.class);
            }

            if (!component.music.isPlaying() && component.play) {
                component.music.play();
                component.play = false;
            }

            if (component.music.isPlaying() && component.pause) {
                component.music.pause();
                component.pause = false;
            }

            if (component.music.isPlaying() && component.stop) {
                component.music.stop();
                component.stop = false;
            }
        }
    }
}

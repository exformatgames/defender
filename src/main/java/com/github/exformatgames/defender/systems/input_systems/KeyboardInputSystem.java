package com.github.exformatgames.defender.systems.input_systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.github.exformatgames.defender.Configurations;
import com.github.exformatgames.defender.components.input_components.key_events.KeyJustPressedComponent;
import com.github.exformatgames.defender.components.input_components.key_events.KeyPressedComponent;

public class KeyboardInputSystem extends IteratingSystem {

    private final Array<Integer> pressedKeys = new Array<>();
    private final Array<Integer> justPressedKeys = new Array<>();

    public KeyboardInputSystem() {
        super(Family.one(KeyPressedComponent.class, KeyJustPressedComponent.class).get());

        if (Configurations.TARGET_KEYS.isEmpty()) {
            Configurations.TARGET_KEYS.addAll(Configurations.DEFAULT_KEYS);
        }
    }

    @Override
    public void startProcessing() {

        pressedKeys.clear();
        justPressedKeys.clear();

        for (int i = 0; i < Configurations.TARGET_KEYS.size; i++) {
            int key = Configurations.TARGET_KEYS.get(i);
            if (Gdx.input.isKeyPressed(key)) {
                pressedKeys.add(key);
            }
            if (Gdx.input.isKeyJustPressed(key)) {
                justPressedKeys.add(key);
            }
        }
    }

    @Override
    protected void processEntity(Entity entity, float dt) {
        KeyPressedComponent pressedComponent = KeyPressedComponent.getComponent(entity);
        KeyJustPressedComponent justPressedComponent = KeyJustPressedComponent.getComponent(entity);

        if (pressedComponent != null) {
            pressedComponent.keys.clear();
            pressedComponent.keys.addAll(pressedKeys);
        }

        if (justPressedComponent != null) {
            justPressedComponent.keys.clear();
            justPressedComponent.keys.addAll(justPressedKeys);
        }
    }
}

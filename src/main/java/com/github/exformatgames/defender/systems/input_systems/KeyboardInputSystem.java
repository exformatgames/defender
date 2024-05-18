package com.github.exformatgames.defender.systems.input_systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.utils.Array;
import com.github.exformatgames.defender.Configurations;
import com.github.exformatgames.defender.components.input_components.key_events.KeyJustPressedComponent;
import com.github.exformatgames.defender.components.input_components.key_events.KeyPressedComponent;
import com.github.exformatgames.defender.components.input_components.key_events.KeysDownComponent;
import com.github.exformatgames.defender.components.input_components.key_events.KeysUpComponent;

public class KeyboardInputSystem extends IteratingSystem {

    private final Array<Integer> pressedKeys = new Array<>();
    private final Array<Integer> upKeys = new Array<>();
    private final Array<Integer> downKeys = new Array<>();
    private final Array<Integer> justPressedKeys = new Array<>();

    public KeyboardInputSystem(InputMultiplexer inputMultiplexer) {
        super(Family.one(KeyPressedComponent.class, KeyJustPressedComponent.class, KeysUpComponent.class, KeysDownComponent.class).get());

        if (Configurations.TARGET_KEYS.isEmpty()) {
            Configurations.TARGET_KEYS.addAll(Configurations.DEFAULT_KEYS);
        }

        new InputKeyboard(inputMultiplexer);
    }

    @Override
    public void startProcessing() {

        //pressedKeys.clear();
        //justPressedKeys.clear();

        pressedKeys.addAll(downKeys);

        for (int key: upKeys) {
            pressedKeys.removeValue(key, true);
            downKeys.removeValue(key, true);
        }

        /*
        for (int i = 0; i < Configurations.TARGET_KEYS.size; i++) {
            int key = Configurations.TARGET_KEYS.get(i);
            if (Gdx.input.isKeyPressed(key)) {
                //pressedKeys.add(key);
            }
            if (Gdx.input.isKeyJustPressed(key)) {
                //justPressedKeys.add(key);
            }
        }
        */
    }

    @Override
    public void endProcessing() {
        upKeys.clear();
        downKeys.clear();
    }

    @Override
    protected void processEntity(Entity entity, float dt) {
        KeyPressedComponent pressedComponent = KeyPressedComponent.getComponent(entity);
        KeyJustPressedComponent justPressedComponent = KeyJustPressedComponent.getComponent(entity);
        KeysUpComponent keysUpComponent = KeysUpComponent.getComponent(entity);
        KeysDownComponent keysDownComponent = KeysDownComponent.getComponent(entity);

        if (pressedComponent != null) {
            pressedComponent.keys.clear();
            pressedComponent.keys.addAll(pressedKeys);
        }

        if (justPressedComponent != null) {
            justPressedComponent.keys.clear();
            justPressedComponent.keys.addAll(justPressedKeys);
        }

        if (keysUpComponent != null) {
            keysUpComponent.keys.clear();
            keysUpComponent.keys.addAll(upKeys);
        }

        if (keysDownComponent != null) {
            keysDownComponent.keys.clear();
            keysDownComponent.keys.addAll(downKeys);
        }
    }

    private class InputKeyboard extends InputAdapter {

        public InputKeyboard(InputMultiplexer inputMultiplexer) {
            inputMultiplexer.addProcessor(this);
        }
        @Override
        public boolean keyDown(int keycode) {
            //for (int i = 0; i < Configurations.TARGET_KEYS.size; i++) {
            //    int key = Configurations.TARGET_KEYS.get(i);
            //    if (keycode == key) {
            //        downKeys.add(key);
            //    }
            //}
            downKeys.add(keycode);
            return false;
        }

        @Override
        public boolean keyUp(int keycode) {
            //for (int i = 0; i < Configurations.TARGET_KEYS.size; i++) {
            //    int key = Configurations.TARGET_KEYS.get(i);
            //    if (keycode == key) {
            //        upKeys.add(key);
            //    }
            //}
            upKeys.add(keycode);

            return false;
        }
    }
}

package com.github.exformatgames.defender.systems.input_systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.exformatgames.defender.Configurations;
import com.github.exformatgames.defender.components.input_components.MouseComponent;
import com.github.exformatgames.defender.components.input_components.button_event_components.ButtonJustPressedComponent;
import com.github.exformatgames.defender.components.input_components.button_event_components.ButtonPressedComponent;

public class MouseInputSystem extends IteratingSystem {

    private final Array<Integer> pressedButtons = new Array<>();
    private final Array<Integer> justPressedButtons = new Array<>();
    private final Vector2 mousePosition = new Vector2();
    private final Vector2 oldMousePosition = new Vector2();
    private final Viewport viewport;
    public MouseInputSystem(InputMultiplexer inputMultiplexer, Viewport viewport) {
        super(Family.one(MouseComponent.class, ButtonPressedComponent.class, ButtonJustPressedComponent.class).get());

        this.viewport = viewport;

        if (Configurations.TARGET_BUTTONS.isEmpty()) {
            Configurations.TARGET_BUTTONS.addAll(Configurations.DEFAULT_BUTTONS);
        }

        new InputMouse(inputMultiplexer);
    }


    @Override
    public void startProcessing() {
        pressedButtons.clear();
        justPressedButtons.clear();

        for (int i = 0; i < Configurations.TARGET_BUTTONS.size; i++) {
            int key = Configurations.TARGET_BUTTONS.get(i);
            if (Gdx.input.isButtonPressed(key)) {
                pressedButtons.add(key);
            }
            if (Gdx.input.isButtonJustPressed(key)) {
                justPressedButtons.add(key);
            }
        }
    }

    @Override
    protected void processEntity(Entity entity, float dt) {
        MouseComponent mouseComponent = MouseComponent.getComponent(entity);
        ButtonPressedComponent buttonPressedComponent = ButtonPressedComponent.getComponent(entity);
        ButtonJustPressedComponent buttonJustPressedComponent = ButtonJustPressedComponent.getComponent(entity);

        mouseComponent.position.set(mousePosition.x, mousePosition.y);
        mouseComponent.delta.set(mousePosition.x - oldMousePosition.x, mousePosition.y - oldMousePosition.y);
        if (buttonPressedComponent != null) {
            buttonPressedComponent.buttons.clear();
            buttonPressedComponent.buttons.addAll(pressedButtons);
        }

        if (buttonJustPressedComponent != null) {
            buttonJustPressedComponent.buttons.clear();
            buttonJustPressedComponent.buttons.addAll(justPressedButtons);
        }
    }

    private class InputMouse implements InputProcessor {

        public InputMouse(InputMultiplexer inputMultiplexer) {
            inputMultiplexer.addProcessor(this);
        }
        @Override
        public boolean keyDown(int keycode) {
            return false;
        }

        @Override
        public boolean keyUp(int keycode) {
            return false;
        }

        @Override
        public boolean keyTyped(char character) {
            return false;
        }

        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            return false;
        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            return false;
        }

        @Override
        public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
            return false;
        }

        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {
            oldMousePosition.set(mousePosition);
            mousePosition.set(screenX, screenY);
            viewport.unproject(mousePosition);
            return false;
        }

        @Override
        public boolean mouseMoved(int screenX, int screenY) {
            oldMousePosition.set(mousePosition);
            mousePosition.set(screenX, screenY);
            viewport.unproject(mousePosition);

            return false;
        }

        @Override
        public boolean scrolled(float amountX, float amountY) {
            return false;
        }
    }
}



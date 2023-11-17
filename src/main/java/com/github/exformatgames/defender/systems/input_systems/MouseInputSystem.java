package com.github.exformatgames.defender.systems.input_systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.exformatgames.defender.Configurations;
import com.github.exformatgames.defender.components.input_components.MouseComponent;
import com.github.exformatgames.defender.components.input_components.button_event_components.ButtonJustPressedComponent;
import com.github.exformatgames.defender.components.input_components.button_event_components.ButtonPressedComponent;
import com.github.exformatgames.defender.systems.util_system.EventSystem;

public class MouseInputSystem extends EventSystem {

    private final Vector3 mousePosition = new Vector3();

    private final Viewport viewport;
    public MouseInputSystem(InputMultiplexer inputMultiplexer, Viewport viewport) {
        super(Family.one(ButtonPressedComponent.class, ButtonJustPressedComponent.class).get());

        this.viewport = viewport;

        new InputMouse(inputMultiplexer);
    }


    @Override
    public void update() {
        super.update();
    }

    @Override
    protected void processEntity(Entity entity) {
        MouseComponent mouseComponent = MouseComponent.getComponent(entity);

        mouseComponent.position.set(mousePosition.x, mousePosition.y);
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
            return false;
        }

        @Override
        public boolean mouseMoved(int screenX, int screenY) {

            mousePosition.set(screenX, screenY, 0);
            viewport.unproject(mousePosition);
            update();
            return false;
        }

        @Override
        public boolean scrolled(float amountX, float amountY) {
            return false;
        }
    }
}



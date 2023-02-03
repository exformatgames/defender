package com.github.exformatgames.defender.systems.input_systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.github.exformatgames.defender.Configurations;
import com.github.exformatgames.defender.components.input_components.MouseComponent;
import com.github.exformatgames.defender.components.input_components.button_event_components.ButtonJustPressedComponent;
import com.github.exformatgames.defender.components.input_components.button_event_components.ButtonPressedComponent;

public class MouseInputSystem extends IteratingSystem {

    private final Array<Integer> pressedButtons = new Array<>();
    private final Array<Integer> justPressedButtons = new Array<>();

    private final Vector3 mousePosition = new Vector3();

    private final OrthographicCamera camera;

    public MouseInputSystem(OrthographicCamera camera) {
        super(Family.one(ButtonPressedComponent.class, ButtonJustPressedComponent.class).get());

        this.camera = camera;
        if (Configurations.TARGET_BUTTONS.isEmpty()){
            Configurations.TARGET_BUTTONS.addAll(Configurations.DEFAULT_BUTTONS);
        }
    }

    @Override
    public void startProcessing() {
        mousePosition.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(mousePosition);

        pressedButtons.clear();
        justPressedButtons.clear();

        for (int i = 0; i < Configurations.TARGET_BUTTONS.size; i++){
            int button = Configurations.TARGET_BUTTONS.get(i);
            if (Gdx.input.isButtonPressed(button)){
                pressedButtons.add(button);
            }
            if (Gdx.input.isButtonJustPressed(button)){
                justPressedButtons.add(button);
            }
        }
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        ButtonPressedComponent buttonPressedComponent = ButtonPressedComponent.getComponent(entity);
        ButtonJustPressedComponent justPressedComponent = ButtonJustPressedComponent.getComponent(entity);
        MouseComponent mouseComponent = MouseComponent.getComponent(entity);

        mouseComponent.position.set(mousePosition.x, mousePosition.y);

        if (buttonPressedComponent != null) {
            buttonPressedComponent.buttons.clear();
            buttonPressedComponent.buttons.addAll(pressedButtons);
            buttonPressedComponent.mousePosition.set(mousePosition.x, mousePosition.y);
        }

        if (justPressedComponent != null) {
            justPressedComponent.buttons.clear();
            justPressedComponent.buttons.addAll(justPressedButtons);
            justPressedComponent.mousePosition.set(mousePosition.x, mousePosition.y);
        }
    }
}

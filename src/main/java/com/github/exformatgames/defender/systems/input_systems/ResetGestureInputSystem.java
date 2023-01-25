package com.github.exformatgames.defender.systems.input_systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.*;
import com.github.exformatgames.defender.components.input_components.gesture_components.*;

public class ResetGestureInputSystem extends IteratingSystem {
    public ResetGestureInputSystem() {
        super(Family.one(GestureTapComponent.class, GesturePanComponent.class,
                GestureLongPressComponent.class, GestureZoomComponent.class,
                GestureRotateComponent.class, GestureFlingComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float dt) {
        GestureTapComponent tapComponent = GestureTapComponent.getComponent(entity);
        GesturePanComponent panComponent = GesturePanComponent.getComponent(entity);
        GestureLongPressComponent longPressComponent = GestureLongPressComponent.getComponent(entity);
        GestureZoomComponent zoomComponent = GestureZoomComponent.getComponent(entity);
        GestureFlingComponent flingComponent = GestureFlingComponent.getComponent(entity);
        GestureRotateComponent rotateComponent = GestureRotateComponent.getComponent(entity);

        if (tapComponent != null) {
            tapComponent.position.setZero();
            tapComponent.count = 0;
        }


        if (panComponent != null) {
            panComponent.position.setZero();
            panComponent.delta.setZero();
            panComponent.direction.setZero();
            panComponent.stop.setZero();
        }


        if (longPressComponent != null)
            longPressComponent.position.setZero();


        if (zoomComponent != null) {
            zoomComponent.initialDistance = 0;
            zoomComponent.endDistance = 0;
        }


        if (flingComponent != null) {
            flingComponent.velocity.setZero();
            flingComponent.up = false;
            flingComponent.down = false;
            flingComponent.left = false;
            flingComponent.right = false;
            flingComponent.sideFling = GestureFlingComponent.SideFling.NULL;
        }

        if (rotateComponent != null) {
            rotateComponent.rotationPoint.setZero();
            rotateComponent.degres = 0;
        }
    }
}

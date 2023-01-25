package com.github.exformatgames.defender.systems.input_systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.github.exformatgames.defender.Constants;
import com.github.exformatgames.defender.systems.util_system.EventSystem;
import com.github.exformatgames.defender.components.input_components.gesture_components.*;

public class GestureInputSystem extends EventSystem {

    private TouchEvent currentEvent;

    private final Vector3 tap = new Vector3(0, 0, 0);

    private final Vector2 pan = new Vector2(0, 0);
    private final Vector2 panDelta = new Vector2(0, 0);
    private final Vector2 panStop = new Vector2(0, 0);

    private final Vector2 longPress = new Vector2(0, 0);

    private float zoomInitialDistance;
    private float zoomDistance;
    private float zoomDelta;

    private final Vector2 fling = new Vector2(0, 0);

    private final Vector2 rotationPoint = new Vector2(0, 0);
    private float rotateValue = 0;
    private float rotationValue = 0;
    private float rotationOld = 0;

    private final Camera camera;
    private Vector3 screenCoordinates = new Vector3();


    public GestureInputSystem(InputMultiplexer inputMultiplexer, Camera camera) {
        super(Family.one(GestureTapComponent.class, GesturePanComponent.class,
                GestureLongPressComponent.class, GestureZoomComponent.class,
                GestureRotateComponent.class, GestureFlingComponent.class).get());

        new InputGestures(inputMultiplexer);
        this.camera = camera;
    }

    @Override
    public void update() {

        super.update();

        currentEvent = TouchEvent.NULL;
    }

    @Override
    protected void processEntity(Entity entity) {
        switch (currentEvent) {
            case TAP: {
                GestureTapComponent tapComponent = GestureTapComponent.getComponent(entity);
                if (tapComponent != null) {
                    screenCoordinates.set(tap.x, tap.y, 0);
                    screenCoordinates = camera.unproject(screenCoordinates);

                    tapComponent.position.set(screenCoordinates.x, screenCoordinates.y);
                    tapComponent.count = (int) tap.z;
                }

                break;
            }

            case PAN: {
                GesturePanComponent panComponent = GesturePanComponent.getComponent(entity);
                if (panComponent != null) {
                    screenCoordinates.set(pan.x, pan.y, 0);
                    screenCoordinates = camera.unproject(screenCoordinates);
                    panComponent.position.set(screenCoordinates.x, screenCoordinates.y);

                    screenCoordinates.set(panDelta.x, panDelta.y, 0);
                    screenCoordinates = camera.unproject(screenCoordinates);
                    panComponent.delta.set(screenCoordinates.x, screenCoordinates.y);

                    panComponent.direction.set(screenCoordinates.x, screenCoordinates.y).nor();
                }

                break;
            }

            case PAN_STOP: {
                GesturePanComponent panComponent = GesturePanComponent.getComponent(entity);
                if (panComponent != null) {
                    screenCoordinates.set(panStop.x, panStop.y, 0);
                    screenCoordinates = camera.unproject(screenCoordinates);
                    panComponent.stop.set(screenCoordinates.x, screenCoordinates.y);
                }

                break;
            }

            case LONG: {
                GestureLongPressComponent longPressComponent = GestureLongPressComponent.getComponent(entity);
                if (longPressComponent != null) {
                    screenCoordinates.set(longPress.x, longPress.y, 0);
                    screenCoordinates = camera.unproject(screenCoordinates);
                    longPressComponent.position.set(screenCoordinates.x, screenCoordinates.y);
                }

                break;
            }

            case ZOOM: {
                GestureZoomComponent zoomComponent = GestureZoomComponent.getComponent(entity);
                if (zoomComponent != null) {
                    zoomComponent.initialDistance = zoomInitialDistance;
                    zoomComponent.endDistance = zoomDistance;

                    zoomComponent.delta = zoomDelta;
                }

                break;
            }

            case FLING: {
                GestureFlingComponent flingComponent = GestureFlingComponent.getComponent(entity);
                if (flingComponent != null) {
                    flingComponent.velocity.set(fling.x, fling.y);

                    if (fling.y > 0 && fling.y > Math.abs(fling.x)) {
                        flingComponent.up = true;
                        flingComponent.sideFling = GestureFlingComponent.SideFling.UP;
                    }

                    if (fling.y < 0 && Math.abs(fling.y) > Math.abs(fling.x)) {
                        flingComponent.down = true;
                        flingComponent.sideFling = GestureFlingComponent.SideFling.DOWN;
                    }

                    if (fling.x > 0 && fling.x > Math.abs(fling.y)) {
                        flingComponent.right = true;
                        flingComponent.sideFling = GestureFlingComponent.SideFling.RIGHT;
                    }

                    if (fling.x < 0 && Math.abs(fling.x) > Math.abs(fling.y)) {
                        flingComponent.left = true;
                        flingComponent.sideFling = GestureFlingComponent.SideFling.LEFT;
                    }
                }
                break;
            }

            case ROTATE: {
                GestureRotateComponent rotateComponent = GestureRotateComponent.getComponent(entity);
                if (rotateComponent != null) {
                    rotateComponent.rotationPoint.set(rotationPoint.x, rotationPoint.y);
                    rotateComponent.degres = rotationValue;
                    rotateComponent.delta = rotateValue;
                }

                break;
            }
            default:
                break;
        }
    }

    private class InputGestures implements GestureDetector.GestureListener {

        public InputGestures(InputMultiplexer inputMultiplexer) {
            inputMultiplexer.addProcessor(new GestureDetector(this));
        }

        @Override
        public boolean tap(float x, float y, int count, int button) {
            tap.set(x, Gdx.graphics.getHeight() - y, 0);
            tap.z = count;

            currentEvent = TouchEvent.TAP;
            update();

            return false;
        }

        @Override
        public boolean pan(float x, float y, float deltaX, float deltaY) {
            pan.set(x, Gdx.graphics.getHeight() - y);
            panDelta.set(deltaX, deltaY * -1);

            currentEvent = TouchEvent.PAN;

            update();

            return false;
        }

        @Override
        public boolean panStop(float x, float y, int pointer, int button) {
            panStop.set(x, Gdx.graphics.getHeight() - y);
            currentEvent = TouchEvent.PAN_STOP;

            update();

            return false;
        }

        @Override
        public boolean longPress(float x, float y) {
            longPress.set(x, Gdx.graphics.getHeight() - y);

            currentEvent = TouchEvent.LONG;
            update();

            return true;
        }

        @Override
        public boolean zoom(float initialDistance, float distance) {
            initialDistance *= Constants.SCL;
            distance *= Constants.SCL;

            float differenceDistance = zoomDistance / distance;
            if (differenceDistance > 1.02f || differenceDistance < 0.98f) {

                zoomDelta = differenceDistance < 1 ? Constants.ZOOM_BASIS : -Constants.ZOOM_BASIS;

                zoomInitialDistance = initialDistance;
                zoomDistance = distance;

                currentEvent = TouchEvent.ZOOM;

                update();
            }

            return false;
        }

        @Override
        public boolean fling(float velocityX, float velocityY, int button) {
            fling.set(velocityX, velocityY * -1);
            fling.scl(Constants.SCL);
            currentEvent = TouchEvent.FLING;

            update();

            return false;
        }

        @Override
        public boolean pinch(Vector2 initial1, Vector2 initial2, Vector2 end1, Vector2 end2) {

            Intersector.intersectLines(initial1, initial2, end1, end2, rotationPoint);

            initial2.sub(initial1);
            end2.sub(end1);
            float atan2Start = MathUtils.atan2(initial2.y, initial2.x);
            float atan2End = MathUtils.atan2(end2.y, end2.x);
            initial2.add(initial1);
            end2.add(end1);

            float current = (atan2Start - atan2End) * MathUtils.radiansToDegrees;

            rotateValue = (current - rotationOld) * 2;
            rotationValue = current;
            rotationOld = current;
            currentEvent = TouchEvent.ROTATE;

            update();

            return false;
        }

        @Override
        public void pinchStop() {
        }

        @Override
        public boolean touchDown(float x, float y, int pointer, int button) {
            return false;
        }
    }

    private enum TouchEvent {
        TAP,
        PAN,
        PAN_STOP,
        LONG,
        ZOOM,
        FLING,
        ROTATE,
        NULL
    }
}

	

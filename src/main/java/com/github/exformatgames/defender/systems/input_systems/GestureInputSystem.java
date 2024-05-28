package com.github.exformatgames.defender.systems.input_systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.exformatgames.defender.Configurations;
import com.github.exformatgames.defender.components.input_components.DragAndDropComponent;
import com.github.exformatgames.defender.components.input_components.gesture_components.*;
import com.github.exformatgames.defender.systems.util_system.EventSystem;

public class GestureInputSystem extends EventSystem {

    private TouchEvent currentEvent;

    private final Vector3 tap = new Vector3(0, 0, 0);

    private final Vector2 pan = new Vector2(0, 0);
    private final Vector2 panDelta = new Vector2(0, 0);
    private final Vector2 panStop = new Vector2(0, 0);

    private final Vector2 longPress = new Vector2(0, 0);

    private float zoomInitialDistance;
    private float zoomDistance;

    private final Vector2 fling = new Vector2(0, 0);

    private final Vector2 rotationPoint = new Vector2(0, 0);
    private float rotateValue = 0;
    private float rotationValue = 0;
    private float rotationOld = 0;

    private Vector3 screenCoordinates = new Vector3();
    //private final Vector2 touchdown = new Vector2();

    private final Vector2 drag = new Vector2();
    private final Vector2 dragDelta = new Vector2();

    private final Viewport viewport;
    private final Vector2 screenCoords = new Vector2();

    public GestureInputSystem(InputMultiplexer inputMultiplexer, Viewport viewport) {
        super(Family.one(
                GestureTapComponent.class,
                GesturePanComponent.class,
                GestureLongPressComponent.class,
                GestureZoomComponent.class,
                GestureRotateComponent.class,
                GestureFlingComponent.class,
                DragAndDropComponent.class).get());

        new InputGestures(inputMultiplexer);
        this.viewport = viewport;
    }

    @Override
    public void startProcessing() {
        viewport.apply();

        viewport.unproject(drag);
    }

    @Override
    public void endProcessing() {
        currentEvent = TouchEvent.NULL;
    }

    @Override
    public void update() {
        startProcessing();

        super.update();

        endProcessing();
    }

    @Override
    protected void processEntity(Entity entity) {
        switch (currentEvent) {
            case TAP: {
                GestureTapComponent tapComponent = GestureTapComponent.getComponent(entity);
                if (tapComponent != null) {
                    screenCoordinates.set(tap.x, tap.y, 0);
                    screenCoordinates = viewport.getCamera().unproject(screenCoordinates);

                    tapComponent.position.set(screenCoordinates.x, screenCoordinates.y);
                    tapComponent.count = (int) tap.z;
                }

                break;
            }

            case PAN: {
                GesturePanComponent panComponent = GesturePanComponent.getComponent(entity);
                if (panComponent != null) {
                    screenCoords.set(pan.x, pan.y);

                    viewport.unproject(screenCoords);
                    //viewport.unproject(touchdown);

                    GesturePanComponent.position.set(screenCoords.x, screenCoords.y).scl(((OrthographicCamera)viewport.getCamera()).zoom);

                    float asX = Configurations.WORLD_WIDTH / viewport.getScreenWidth();
                    float asY = Configurations.WORLD_HEIGHT / viewport.getScreenHeight();

                    screenCoords.set(panDelta.x * asX, panDelta.y * asY);
                    GesturePanComponent.delta.set(screenCoords.x, screenCoords.y).scl(((OrthographicCamera)viewport.getCamera()).zoom);
                    GesturePanComponent.direction.set(screenCoords.x, screenCoords.y).nor();
                }

                break;
            }

            case PAN_STOP: {
                GesturePanComponent panComponent = GesturePanComponent.getComponent(entity);
                if (panComponent != null) {
                    screenCoords.set(panStop.x, panStop.y);
                    viewport.unproject(screenCoords);
                    GesturePanComponent.stop.set(screenCoords.x, screenCoords.y);

                }

                break;
            }

            case LONG: {
                GestureLongPressComponent longPressComponent = GestureLongPressComponent.getComponent(entity);
                if (longPressComponent != null) {
                    screenCoordinates.set(longPress.x, longPress.y, 0);
                    screenCoordinates = viewport.getCamera().unproject(screenCoordinates);
                    longPressComponent.position.set(screenCoordinates.x, screenCoordinates.y);
                }

                break;
            }

            case ZOOM: {
                GestureZoomComponent zoomComponent = GestureZoomComponent.getComponent(entity);
                if (zoomComponent != null) {
                    zoomComponent.initialDistance = zoomInitialDistance;
                    zoomComponent.endDistance = zoomDistance;
                    zoomComponent.ratio = zoomInitialDistance / zoomDistance;
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

            case DRAG_START:{
                DragAndDropComponent dragAndDropComponent = DragAndDropComponent.getComponent(entity);
                if (dragAndDropComponent != null) {
                    dragAndDropComponent.isDragging = true;
                    dragAndDropComponent.dragStop.setZero();
                    dragAndDropComponent.dragStart.set(drag);
                    dragAndDropComponent.dragPosition.setZero();
                    dragAndDropComponent.dragDelta.setZero();
                }
                break;
            }

            case DRAG: {
                DragAndDropComponent dragAndDropComponent = DragAndDropComponent.getComponent(entity);
                if (dragAndDropComponent != null) {
                    float asX = Configurations.WORLD_WIDTH / viewport.getScreenWidth();
                    float asY = Configurations.WORLD_HEIGHT / viewport.getScreenHeight();

                    dragAndDropComponent.dragPosition.set(drag.x, drag.y).scl(((OrthographicCamera)viewport.getCamera()).zoom);
                    dragAndDropComponent.dragDelta.set(dragDelta.x * asX, dragDelta.y * asY).scl(((OrthographicCamera)viewport.getCamera()).zoom);
                }

                break;
            }

            case DRAG_STOP: {
                DragAndDropComponent dragAndDropComponent = DragAndDropComponent.getComponent(entity);
                if (dragAndDropComponent != null) {
                    dragAndDropComponent.isDragging = false;
                    dragAndDropComponent.dragStop.set(drag);
                    dragAndDropComponent.dragStart.setZero();
                    dragAndDropComponent.dragPosition.setZero();
                    dragAndDropComponent.dragDelta.setZero();
                }
                break;
            }
        }
    }

    private class InputGestures implements GestureDetector.GestureListener, InputDragAndDrop.DragAndDropInterface {

        public InputGestures(InputMultiplexer inputMultiplexer) {
            inputMultiplexer.addProcessor(new MyGestureDetector(this, this));
        }

        @Override
        public boolean tap(float x, float y, int count, int button) {
            tap.set(x, y, 0);
            tap.z = count;

            currentEvent = TouchEvent.TAP;
            update();

            return false;
        }

        @Override
        public boolean pan(float x, float y, float deltaX, float deltaY) {
            pan.set(x, y);
            panDelta.set(deltaX, deltaY * -1);

            currentEvent = TouchEvent.PAN;

            update();

            return false;
        }

        @Override
        public boolean panStop(float x, float y, int pointer, int button) {
            panStop.set(x, y);
            currentEvent = TouchEvent.PAN_STOP;

            update();

            return false;
        }

        @Override
        public boolean longPress(float x, float y) {
            longPress.set(x, y);

            currentEvent = TouchEvent.LONG;
            update();

            return true;
        }

        @Override
        public boolean zoom(float initialDistance, float distance) {
            zoomInitialDistance = initialDistance;
            zoomDistance = distance;

            currentEvent = TouchEvent.ZOOM;

            update();

            return false;
        }

        @Override
        public boolean fling(float velocityX, float velocityY, int button) {
            fling.set(velocityX, velocityY * -1);
            fling.scl(Configurations.SCL);
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
            //touchdown.set(x,y);
            return false;
        }


        @Override
        public void dragStart(float x, float y) {
            currentEvent = TouchEvent.DRAG_START;
            drag.set(x, y);
            update();
        }

        @Override
        public void dragProcess(float x, float y, float deltaX, float deltaY) {
            currentEvent = TouchEvent.DRAG;
            drag.set(x, y);
            dragDelta.set(deltaX, deltaY);
            update();
        }

        @Override
        public void dragStop(float x, float y) {
            currentEvent = TouchEvent.DRAG_STOP;
            drag.set(x, y);
            update();
        }
    }

    private static class InputDragAndDrop extends DragListener{

        private final DragAndDropInterface dragAndDropInterface;

        public InputDragAndDrop(DragAndDropInterface dragAndDropInterface) {
            this.dragAndDropInterface = dragAndDropInterface;
        }

        @Override
        public void dragStart(InputEvent event, float x, float y, int pointer) {
            dragAndDropInterface.dragStart(x, y);
        }

        @Override
        public void drag(InputEvent event, float x, float y, int pointer) {
            dragAndDropInterface.dragProcess(x, y, getDeltaX(), getDeltaY());
        }

        @Override
        public void dragStop(InputEvent event, float x, float y, int pointer) {
            dragAndDropInterface.dragStop(x, y);
        }

        public interface DragAndDropInterface {
            void dragStart(float x, float y);
            void dragProcess(float x, float y, float deltaX, float deltaY);
            void dragStop(float x, float y);
        }
    }

    private static  class MyGestureDetector extends GestureDetector {

        private final InputDragAndDrop dragAndDrop;
        private final InputEvent event = new InputEvent();

        public MyGestureDetector(GestureListener listener, InputDragAndDrop.DragAndDropInterface dragAndDropInterface) {
            super(listener);
            dragAndDrop = new InputDragAndDrop(dragAndDropInterface);
        }

        @Override
        public boolean touchDown(float x, float y, int pointer, int button) {
            boolean re = super.touchDown(x, y, pointer, button);

            dragAndDrop.touchDown(event, x, y, pointer, button);

            return re;
        }

        @Override
        public boolean touchDragged(float x, float y, int pointer) {
            boolean re = super.touchDragged(x, y, pointer);

            dragAndDrop.touchDragged(event, x, y, pointer);

            return re;
        }

        @Override
        public boolean touchUp(float x, float y, int pointer, int button) {
            boolean re = super.touchUp(x, y, pointer, button);

            dragAndDrop.touchUp(event, x, y, pointer, button);

            return re;
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
        DRAG_START,
        DRAG,
        DRAG_STOP,
        NULL
    }
}



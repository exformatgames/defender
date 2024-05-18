package com.github.exformatgames.defender.systems.debug;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.exformatgames.defender.Configurations;
import com.github.exformatgames.defender.components.box2d.BodyComponent;
import com.github.exformatgames.defender.components.box2d.WorldComponent;
import com.github.exformatgames.defender.components.box2d.contact_components.BeginContactComponent;

public class DebugPrintEngineInfoSystem extends IteratingSystem {

    private final SpriteBatch spriteBatch;
    private final OrthographicCamera camera;
    private final Viewport viewport;

    private final BitmapFont bitmapFont;

    private float avgFPS = 0;
    private int avgFPSCounter = 0;

    private float avgFPSprint = 0;


    public DebugPrintEngineInfoSystem(SpriteBatch spriteBatch, Viewport viewport) {
        super(Family.all().get());
        this.spriteBatch = spriteBatch;
        this.camera = (OrthographicCamera) viewport.getCamera();
        this.viewport = viewport;

        bitmapFont = new BitmapFont(Gdx.files.internal("debug-font.fnt"));
        //bitmapFont.getData().setScale(2);
        bitmapFont.setColor(Color.GREEN);
    }

    @Override
    public void update(float deltaTime) {
        viewport.apply(true);

        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();

        int entities = getEngine().getEntities().size();
        int components = 0;
        for (Entity en: getEngine().getEntities()) {
            components += en.getComponents().size();
        }

        int FPS = (int)(1 / deltaTime);
        avgFPS += deltaTime;
        avgFPSCounter++;

        if (avgFPS > 0.5f) {
            avgFPSprint = (int)(1 / (avgFPS / avgFPSCounter));

            avgFPS = 0;
            avgFPSCounter = 0;
        }

        String info = "FPS: " + FPS
                + "\nawg FPS: " + avgFPSprint
                + "\nentities: " + entities
                + "\ncomponents: " + components;

        if (getEngine().getEntitiesFor(Family.one(WorldComponent.class).get()).size() != 0) {
            int bodies = getEngine().getEntitiesFor(Family.one(BodyComponent.class).get()).size();
            int contacts = getEngine().getEntitiesFor(Family.one(BeginContactComponent.class).get()).size();
            info += "\nbodies: " + bodies + "\ncontacts: " + contacts;
        }

        bitmapFont.draw(spriteBatch, info, 0.1f * Configurations.VIEWPORTS_RATIO, Configurations.UI_HEIGHT);
        spriteBatch.end();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {}
}

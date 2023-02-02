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
import com.github.exformatgames.defender.components.rendering_components.ui.TextRenderComponent;
import com.github.exformatgames.defender.components.util_components.DebugComponent;

public class DebugPrintEngineInfoSystem extends IteratingSystem {

    private final SpriteBatch spriteBatch;
    private final OrthographicCamera camera;
    private final Viewport viewport;

    private final BitmapFont bitmapFont;

    public DebugPrintEngineInfoSystem(SpriteBatch spriteBatch, Viewport viewport) {
        super(Family.all().get());
        this.spriteBatch = spriteBatch;
        this.camera = (OrthographicCamera) viewport.getCamera();
        this.viewport = viewport;

        bitmapFont = new BitmapFont(Gdx.files.internal("debug-font.fnt"));
        bitmapFont.getData().setScale(2);
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

        String info = "FPS: " + (int)(1 / deltaTime)
                + " entities: " + entities
                + " components: " + components;

        if (getEngine().getEntitiesFor(Family.one(WorldComponent.class).get()).size() != 0) {
            int bodies = getEngine().getEntitiesFor(Family.one(BodyComponent.class).get()).size();
            int contacts = getEngine().getEntitiesFor(Family.one(BeginContactComponent.class).get()).size();
            info += " bodies: " + bodies + " contacts: " + contacts;
        }

        bitmapFont.draw(spriteBatch, info, 0.1f * Configurations.VIEWPORTS_RATIO, Configurations.UI_HEIGHT);
        spriteBatch.end();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {}
}

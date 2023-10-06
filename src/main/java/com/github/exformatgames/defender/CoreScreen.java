package com.github.exformatgames.defender;

import com.badlogic.gdx.Screen;

public class CoreScreen implements Screen {


    private final Core core;

    public CoreScreen(Core core) {
        this.core = core;
    }


    @Override
    public void show() {
        core.create(false);
    }

    @Override
    public void render(float delta) {
        core.update(delta);
    }

    @Override
    public void resize(int width, int height) {
        core.resize(width, height);
    }

    @Override
    public void pause() {
        core.pause();
    }

    @Override
    public void resume() {
        core.resume();
    }

    @Override
    public void hide() {
        core.hide();
    }

    @Override
    public void dispose() {
        core.dispose();
    }
}

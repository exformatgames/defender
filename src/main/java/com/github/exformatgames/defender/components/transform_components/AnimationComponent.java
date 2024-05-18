package com.github.exformatgames.defender.components.transform_components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.github.exformatgames.defender.Core;

public class AnimationComponent implements Component, Pool.Poolable {

    public Array<AnimationComponent> animationComponentArray = new Array<>();


	public Animation<TextureRegion> animation;
	public float scale = 1;
	public float timeAnimation = 0;

    public String name = "";

    public boolean isFinished = false;

	public void init(float frameDuration, Array<TextureRegion> regions, Animation.PlayMode mode, float scl){
		animation = new Animation<>(frameDuration, regions, mode);

		this.scale = scl;
		timeAnimation = 0;
	}

	public void init(float frameDuration, Animation<TextureRegion> animation, Animation.PlayMode mode, float scl){
		this.animation = animation;
		this.animation.setPlayMode(mode);
		this.animation.setFrameDuration(frameDuration);
		this.scale = scl;
		timeAnimation = 0;
	}

    public void init(Animation<TextureRegion> animation){
        this.animation = animation;
        this.animation.setPlayMode(animation.getPlayMode());
        this.animation.setFrameDuration(animation.getFrameDuration());
        timeAnimation = 0;
    }

    public void init(String name, float frameDuration, Animation.PlayMode playMode) {
        animation = Core.ASSETS.getAnimation(name, frameDuration, playMode);
        this.name = name;
    }

    public void init(String name, int widthFrame, float frameDuration, Animation.PlayMode playMode) {
        TextureRegion region = Core.ASSETS.getRegion(name);
        int frames = region.getRegionWidth() / widthFrame;

        TextureRegion[][] matrix = region.split(widthFrame, region.getRegionHeight());
        Array<TextureRegion> regions = new Array<>(frames);
        for (int i = 0; i < frames; i++) {
            regions.add(matrix[0][i]);
        }

        init(frameDuration, regions, playMode, 1);
        this.name = name;
    }

    @Override
    public void reset() {
        timeAnimation = 0;
        isFinished = false;
        name = "";
    }

    private final static ComponentMapper<AnimationComponent> mapper = ComponentMapper.getFor(AnimationComponent.class);

	public static AnimationComponent getComponent(Entity entity) {
		return mapper.get(entity);
	}
}

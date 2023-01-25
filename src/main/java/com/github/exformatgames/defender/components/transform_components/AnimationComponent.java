package com.github.exformatgames.defender.components.transform_components;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.*;
import com.badlogic.gdx.utils.*;

public class AnimationComponent implements Component {

	public String name = "";
	public Array<AnimationComponent> animationComponentArray = new Array<>();
	public Animation<AtlasRegion> animation;
	public float scale = 1;
	public float timeAnimation = 0;
	
	public void init(String name, float frameDuration, Array<AtlasRegion > regions, Animation.PlayMode mode, float scl){
		animation = new Animation<>(frameDuration, regions, mode);

		this.scale = scl;
		timeAnimation = 0;
		this.name = name;
	}

	public void init(String name, float frameDuration, Animation<AtlasRegion> animation, Animation.PlayMode mode, float scl){
		this.animation = animation;
		this.animation.setPlayMode(mode);
		this.animation.setFrameDuration(frameDuration);
		this.scale = scl;
		timeAnimation = 0;
		this.name = name;
	}
	
	private final static ComponentMapper<AnimationComponent> mapper = ComponentMapper.getFor(AnimationComponent.class);

	public static AnimationComponent getComponent(Entity entity) {
		return mapper.get(entity);
	}
}

package com.github.exformatgames.defender.components.box2d.transform_components;

import com.badlogic.ashley.core.*;

public class AngularImpulseComponent implements Component {

	public float impulse = 0;
	
	private final static ComponentMapper<AngularImpulseComponent> mapper = ComponentMapper.getFor(AngularImpulseComponent.class);

	public static AngularImpulseComponent getComponent(Entity entity) {
		return mapper.get(entity);
	}
}

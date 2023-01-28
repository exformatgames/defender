package com.github.exformatgames.defender.components;
import com.badlogic.ashley.core.*;

public class ExitComponent implements Component {

	private final static ComponentMapper<ExitComponent> mapper = ComponentMapper.getFor(ExitComponent.class);

	public static ExitComponent getComponent(Entity entity) {
		return mapper.get(entity);
	}
}

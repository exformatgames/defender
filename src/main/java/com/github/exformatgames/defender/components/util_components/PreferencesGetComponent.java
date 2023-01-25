package com.github.exformatgames.defender.components.util_components;

import com.badlogic.ashley.core.*;

public class PreferencesGetComponent implements Component {

	public String preferencesName;

	public String key;

	public String stringValue;
	public int intValue;
	public float floatValue;
	public boolean boolValue;

	private final static ComponentMapper<PreferencesGetComponent> mapper = ComponentMapper.getFor(PreferencesGetComponent.class);

	public static PreferencesGetComponent getComponent(Entity entity) {
		return mapper.get(entity);
	}

}

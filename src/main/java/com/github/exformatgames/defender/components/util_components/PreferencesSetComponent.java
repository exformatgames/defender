package com.github.exformatgames.defender.components.util_components;

import com.badlogic.ashley.core.*;

public class PreferencesSetComponent implements Component {
	
	public String preferencesName;
	public PreferencesType type;
	
	public String key;
	
	public String stringValue;
	public int intValue;
	public float floatValue;
	public boolean boolValue;
	
	private final static ComponentMapper<PreferencesSetComponent> mapper = ComponentMapper.getFor(PreferencesSetComponent.class);

	public static PreferencesSetComponent getComponent(Entity entity) {
		return mapper.get(entity);
	}

	public enum PreferencesType{
		STRING,
		BOOL,
		INT,
		FLOAT
	}
}

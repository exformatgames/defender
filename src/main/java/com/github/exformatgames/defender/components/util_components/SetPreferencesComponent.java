package com.github.exformatgames.defender.components.util_components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.github.exformatgames.defender.Configurations;

public class SetPreferencesComponent implements Component {
	
	public String preferencesName;
	public PreferencesType type;
	
	public String key;
	
	public String stringValue;
	public int intValue;
	public float floatValue;
	public boolean boolValue;

	public void init(String preferencesName, PreferencesType type, String key, String stringValue, int intValue, float floatValue, boolean boolValue) {
		this.preferencesName = preferencesName;
		this.type = type;
		this.key = key;
		this.stringValue = stringValue;
		this.intValue = intValue;
		this.floatValue = floatValue;
		this.boolValue = boolValue;
	}

	public void init(String preferencesName, String key, String stringValue) {
		init(preferencesName, PreferencesType.STRING, key, stringValue, 0, 0, false);
	}

	public void init(String preferencesName, String key, int intValue) {
		init(preferencesName, PreferencesType.INT, key, null, intValue, 0, false);
	}

	public void init(String preferencesName, String key, float floatValue) {
		init(preferencesName, PreferencesType.FLOAT, key, null, 0, floatValue, false);
	}

	public void init(String preferencesName, String key, boolean boolValue) {
		init(preferencesName, PreferencesType.BOOL, key, null, 0, 0, boolValue);
	}

	public void init(String key, String stringValue) {
		init(Configurations.PREFERENCES_NAME, PreferencesType.STRING, key, stringValue, 0, 0, false);
	}

	public void init(String key, int intValue) {
		init(Configurations.PREFERENCES_NAME, PreferencesType.INT, key, null, intValue, 0, false);
	}

	public void init(String key, float floatValue) {
		init(Configurations.PREFERENCES_NAME, PreferencesType.FLOAT, key, null, 0, floatValue, false);
	}

	public void init(String key, boolean boolValue) {
		init(Configurations.PREFERENCES_NAME, PreferencesType.BOOL, key, null, 0, 0, boolValue);
	}

	private final static ComponentMapper<SetPreferencesComponent> mapper = ComponentMapper.getFor(SetPreferencesComponent.class);

	public static SetPreferencesComponent getComponent(Entity entity) {
		return mapper.get(entity);
	}

	public enum PreferencesType {
		BOOL,
		FLOAT,
		INT,
		STRING
	}
}

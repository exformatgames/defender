package com.github.exformatgames.defender.components.util_components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.github.exformatgames.defender.Configurations;

public class GetPreferencesComponent implements Component {

    public String preferencesName;
    public SetPreferencesComponent.PreferencesType type;

    public String key;

    public String stringValue;
    public int intValue;
    public float floatValue;
    public boolean boolValue;

    public void init(String preferencesName, SetPreferencesComponent.PreferencesType type, String key, String stringValue, int intValue, float floatValue, boolean boolValue) {
        this.preferencesName = preferencesName;
        this.type = type;
        this.key = key;
        this.stringValue = stringValue;
        this.intValue = intValue;
        this.floatValue = floatValue;
        this.boolValue = boolValue;
    }

    public void init(String preferencesName, String key, String defaultValue) {
        init(preferencesName, SetPreferencesComponent.PreferencesType.STRING, key, defaultValue, 0, 0, false);
    }

    public void init(String preferencesName, String key, int defaultValue) {
        init(preferencesName, SetPreferencesComponent.PreferencesType.INT, key, null, defaultValue, 0, false);
    }

    public void init(String preferencesName, String key, float defaultValue) {
        init(preferencesName, SetPreferencesComponent.PreferencesType.FLOAT, key, null, 0, defaultValue, false);
    }

    public void init(String preferencesName, String key, boolean defaultValue) {
        init(preferencesName, SetPreferencesComponent.PreferencesType.BOOL, key, null, 0, 0, defaultValue);
    }

    public void init(String key, String defaultValue) {
        init(Configurations.PREFERENCES_NAME, SetPreferencesComponent.PreferencesType.STRING, key, defaultValue, 0, 0, false);
    }

    public void init(String key, int defaultValue) {
        init(Configurations.PREFERENCES_NAME, SetPreferencesComponent.PreferencesType.INT, key, null, defaultValue, 0, false);
    }

    public void init(String key, float defaultValue) {
        init(Configurations.PREFERENCES_NAME, SetPreferencesComponent.PreferencesType.FLOAT, key, null, 0, defaultValue, false);
    }

    public void init(String key, boolean defaultValue) {
        init(Configurations.PREFERENCES_NAME, SetPreferencesComponent.PreferencesType.BOOL, key, null, 0, 0, defaultValue);
    }

    private final static ComponentMapper<GetPreferencesComponent> mapper = ComponentMapper.getFor(GetPreferencesComponent.class);

    public static GetPreferencesComponent getComponent(Entity entity) {
        return mapper.get(entity);
    }
}

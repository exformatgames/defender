package com.github.exformatgames.defender.systems.util_system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.github.exformatgames.defender.components.util_components.GetPreferencesComponent;

public class GetPreferencesSystem extends IteratingSystem {

    public GetPreferencesSystem() {
        super(Family.all(GetPreferencesComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        GetPreferencesComponent preferencesComponent = GetPreferencesComponent.getComponent(entity);

        Preferences preferences = Gdx.app.getPreferences(preferencesComponent.preferencesName);

        switch(preferencesComponent.type){
            case STRING : {
                preferencesComponent.stringValue = preferences.getString(preferencesComponent.key, preferencesComponent.stringValue);
                break;
            }

            case INT : {
                preferencesComponent.intValue = preferences.getInteger(preferencesComponent.key, preferencesComponent.intValue);
                break;
            }

            case FLOAT : {
                preferencesComponent.floatValue = preferences.getFloat(preferencesComponent.key, preferencesComponent.floatValue);
                break;
            }

            case BOOL : {
                preferencesComponent.boolValue = preferences.getBoolean(preferencesComponent.key, preferencesComponent.boolValue);
                break;
            }
        }
    }
}

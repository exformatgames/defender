package com.github.exformatgames.defender.systems.util_system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.github.exformatgames.defender.Configurations;
import com.github.exformatgames.defender.components.util_components.SetPreferencesComponent;

public class SetPreferencesSystem extends IteratingSystem {

	public SetPreferencesSystem(){
		super(Family.all(SetPreferencesComponent.class).get());
	}

	@Override
	protected void processEntity(Entity entity, float dt) {
		SetPreferencesComponent setComponent = SetPreferencesComponent.getComponent(entity);

		Preferences prefs = Gdx.app.getPreferences(Configurations.PREFERENCES_NAME);

		switch(setComponent.type){
			case STRING : {
					prefs.putString(setComponent.key, setComponent.stringValue);
					break;
				}

			case INT : {
					prefs.putInteger(setComponent.key, setComponent.intValue);
					break;
				}

			case FLOAT : {
					prefs.putFloat(setComponent.key, setComponent.floatValue);
					break;
				}

			case BOOL : {
					prefs.putBoolean(setComponent.key, setComponent.boolValue);
				break;
			}
		}

		prefs.flush();
	}
}

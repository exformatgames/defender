package com.github.exformatgames.defender.systems.util_system;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.*;
import com.badlogic.gdx.*;
import com.github.exformatgames.defender.components.util_components.PreferencesSetComponent;

public class PreferencesSystem extends IteratingSystem {
	
	public PreferencesSystem(){
		super(Family.all(PreferencesSetComponent.class).get());
	}

	@Override
	protected void processEntity(Entity entity, float dt) {
		PreferencesSetComponent setComponent = PreferencesSetComponent.getComponent(entity);
		
		Preferences prefs = Gdx.app.getPreferences(setComponent.preferencesName);
		
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

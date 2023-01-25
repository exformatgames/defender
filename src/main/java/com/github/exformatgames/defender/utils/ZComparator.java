package com.github.exformatgames.defender.utils;

import com.badlogic.ashley.core.*;
import com.github.exformatgames.defender.components.rendering_components.ZIndexComponent;

import java.util.*;

public class ZComparator implements Comparator<Entity> { 
	
	private final ComponentMapper<ZIndexComponent> zIndexMapper;
	
	public ZComparator(){ 
		zIndexMapper = ComponentMapper.getFor(ZIndexComponent.class); 
	} 
	
	@Override 
	public int compare(Entity entityA, Entity entityB) { 
		return (int) Math.signum(zIndexMapper.get(entityA).zIndex - zIndexMapper.get(entityB).zIndex); 
	}
}


package com.github.exformatgames.defender.components.light_components;

import box2dLight.*;
import com.badlogic.ashley.core.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.*;
import com.github.exformatgames.defender.components.rendering_components.LightRenderComponent;

public class LightComponent implements Component {
    public Light light = null;

    public Vector2 position = new Vector2();
    public Color color = new Color(1, 1, 1, 1);
    public float dir = 0;
    public int rays = 0;
    public float length = 0;

    public LightComponent init(Vector2 position, Color color, float dir, int rays, float length) {
        this.position = position;
        this.color = color;
        this.dir = dir;
        this.rays = rays;
        this.length = length;

        return this;
    }

    public LightComponent init(LightType type, Vector2 position, Color color, float direction, int rays, float distance, float coneDegres) {
        switch (type) {
            case CONE: {
                light = new ConeLight(LightRenderComponent.RAY_HANDLER, rays, color, distance, position.x, position.y, direction, coneDegres);
                break;
            }
            case CHAIN: {
                light = new ChainLight(LightRenderComponent.RAY_HANDLER, rays, color, distance, (int) direction);
                break;
            }
            case POINT: {
                light = new PointLight(LightRenderComponent.RAY_HANDLER, rays, color, distance, position.x, position.y);
                break;
            }
            case DIRECTIONAL: {
                light = new DirectionalLight(LightRenderComponent.RAY_HANDLER, rays, color, direction);
                break;
            }
        }

        return this;
    }

    private final static ComponentMapper<LightComponent> mapper = ComponentMapper.getFor(LightComponent.class);

    public static LightComponent getComponent(Entity entity) {
        return mapper.get(entity);
    }

    public enum LightType {
        DIRECTIONAL,
        POINT,
        CONE,
        CHAIN;
    }
}

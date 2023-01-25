package com.github.exformatgames.defender.components.box2d.contact_components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Contact;

public class EndContactComponent implements Component {

    public Entity contactEntity;
    public Contact contact;

    public EndContactComponent init(Entity contactEntity, Contact contact) {
        this.contactEntity = contactEntity;
        this.contact = contact;

        return this;
    }

    private final static ComponentMapper<EndContactComponent> mapper = ComponentMapper.getFor(EndContactComponent.class);

    public static EndContactComponent getComponent(Entity entity) {
        return mapper.get(entity);
    }
}

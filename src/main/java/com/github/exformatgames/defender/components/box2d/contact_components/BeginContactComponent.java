package com.github.exformatgames.defender.components.box2d.contact_components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;

public class BeginContactComponent implements Component {

    public Entity contactEntity;
    public Body contactBody;
    public Contact contact;
    public short categoryBits;


    public BeginContactComponent init(Entity contactEntity, Contact contact) {
        this.contactEntity = contactEntity;
        this.contact = contact;

        return this;
    }

    public BeginContactComponent init(Entity contactEntity, Body contactBody, Contact contact) {
        this.contactEntity = contactEntity;
        this.contact = contact;
        this.contactBody = contactBody;
        return this;
    }

    public BeginContactComponent init(Entity contactEntity, Body contactBody, Contact contact, short categoryBits) {
        this.contactEntity = contactEntity;
        this.contactBody = contactBody;
        this.contact = contact;
        this.categoryBits = categoryBits;

        return this;
    }

    private final static ComponentMapper<BeginContactComponent> mapper = ComponentMapper.getFor(BeginContactComponent.class);

    public static BeginContactComponent getComponent(Entity entity) {
        return mapper.get(entity);
    }
}

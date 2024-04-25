package com.pirate.arena.app.dynamoDB;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.xspec.S;
import com.pirate.arena.app.models.Skill;


import java.util.List;
import java.util.Optional;

public interface IServiceQueries {
    Optional<List<Item>> findUserSkills(String username);

    List<Skill> getAllSkills();
    Optional<Item> getSkillById(String id);

    void putItem(String table, Item item);
}

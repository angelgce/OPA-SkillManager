package com.pirate.arena.app.services;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.google.gson.Gson;
import com.pirate.arena.app.dynamoDB.ServiceQueries;
import com.pirate.arena.app.exceptions.BadRequestException;
import com.pirate.arena.app.models.Skill;
import com.pirate.arena.app.models.SkillSet;
import com.pirate.arena.app.request.RequestGetSkill;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ServiceSkill extends ServiceValidateRequest implements IServiceSkill {

    private final ServiceQueries serviceQueries;

    @Override
    public String createSkill(Skill skill) {
        validateInputs(Optional.ofNullable(skill));
        if (serviceQueries.getSkillById(skill.getId()).isPresent())
            throw new BadRequestException("[Create Skill] The skill ID ["
                    .concat(skill.getId()).concat("] is already in use."));
        Item item = new Item()
                .withPrimaryKey("id", skill.getId())
                .withString("name", skill.getName())
                .withString("description", skill.getDescription())
                .withString("image", skill.getImage())
                .withList("charactersLinked", skill.getCharactersLinked())
                .withList("classes", skill.getClasses())
                .withString("cooldown", skill.getCooldown())
                .withList("skillSet", mapSetSkills(skill.getSkillSet()))
                .withBoolean("isUnlocked", false);
        serviceQueries.putItem("skills", item);
        return "success";
    }

    @Override
    public List<Map> mapSetSkills(List<SkillSet> skillSet) {
        List<Map> sets = new ArrayList<>();
        skillSet.forEach(item -> {
            Map skillItem = new HashMap();
            skillItem.put("effect", item.getEffect());
            skillItem.put("value", item.getValue());
            skillItem.put("duration", item.getDuration());
            sets.add(skillItem);
        });
        return sets;
    }

    @Override
    public Skill findSkillById(RequestGetSkill requestGetSkill) {
        validateInputs(Optional.ofNullable(requestGetSkill));
        Optional<Item> item = serviceQueries.getSkillById(requestGetSkill.id());
        if (item.isEmpty())
            throw new BadRequestException("[Find Skill By Id] The skill ID ["
                    .concat(requestGetSkill.id()).concat("] does not exist."));
        Gson gson = new Gson();
        return gson.fromJson(item.get().toJSON(), Skill.class);
    }

    @Override
    public List<Skill> findUserSkills(RequestGetSkill requestGetSkill) {
        validateInputs(Optional.ofNullable(requestGetSkill));
        return serviceQueries.getAllSkills().stream()
                .filter(filter -> filter
                        .getCharactersLinked()
                        .stream()
                        .anyMatch(match -> match.equals(requestGetSkill.id())))
                .collect(Collectors.toList());
    }

    public String convertToJson(Optional<?> object) {
        Gson gson = new Gson();
        return gson.toJson(object.get());
    }
}

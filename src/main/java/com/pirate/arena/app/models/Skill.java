package com.pirate.arena.app.models;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.Builder;

import java.util.List;

@Builder
@DynamoDBTable(tableName = "skills")
public class Skill {
    @DynamoDBHashKey
    private String id;
    @DynamoDBAttribute
    private String name;
    @DynamoDBAttribute
    private String description;
    @DynamoDBAttribute
    private String image;
    @DynamoDBAttribute
    private List<String> charactersLinked;
    @DynamoDBAttribute
    private List<SkillSet> skillSet;
    @DynamoDBAttribute
    private List<String> classes;
    @DynamoDBAttribute
    private String cooldown;

    @DynamoDBAttribute
    private boolean isLocked;

    public Skill() {
    }

    public Skill(String id, String name, String description, String image, List<String> charactersLinked, List<SkillSet> skillSet, List<String> classes, String cooldown, boolean isLocked) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.charactersLinked = charactersLinked;
        this.skillSet = skillSet;
        this.classes = classes;
        this.cooldown = cooldown;
        this.isLocked = isLocked;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getCharactersLinked() {
        return charactersLinked;
    }

    public void setCharactersLinked(List<String> charactersLinked) {
        this.charactersLinked = charactersLinked;
    }

    public List<SkillSet> getSkillSet() {
        return skillSet;
    }

    public void setSkillSet(List<SkillSet> skillSet) {
        this.skillSet = skillSet;
    }

    public List<String> getClasses() {
        return classes;
    }

    public void setClasses(List<String> classes) {
        this.classes = classes;
    }

    public String getCooldown() {
        return cooldown;
    }

    public void setCooldown(String cooldown) {
        this.cooldown = cooldown;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }
}

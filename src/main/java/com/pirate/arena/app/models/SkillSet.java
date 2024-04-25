package com.pirate.arena.app.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@DynamoDBDocument
public class SkillSet {
    @DynamoDBAttribute
    private String effect;
    @DynamoDBAttribute
    private int value;
    @DynamoDBAttribute
    private int duration;

    public SkillSet() {
    }

    public SkillSet(String effect, int value, int duration) {
        this.effect = effect;
        this.value = value;
        this.duration = duration;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}

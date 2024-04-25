package com.pirate.arena.app.dynamoDB;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.google.gson.Gson;
import com.pirate.arena.app.exceptions.BadRequestException;
import com.pirate.arena.app.models.Skill;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class ServiceQueries implements IServiceQueries {

    private final ServiceDynamoDB serviceDynamoDB;
    private final DynamoDBMapperConfig mapperConfig = new DynamoDBMapperConfig.Builder()
            .withTableNameOverride(DynamoDBMapperConfig.TableNameOverride.withTableNameReplacement("skills")).build();

    @Override
    public Optional<List<Item>> findUserSkills(String character) {
        return Optional.ofNullable(serviceDynamoDB.getItemByIndex("skills", "charactersLinked", character, "charactersLinked-index"));
    }

    @Override
    public List<Skill> getAllSkills() {
        DynamoDBMapper mapper = new DynamoDBMapper(serviceDynamoDB.getClient(), mapperConfig);
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        return mapper.scan(Skill.class, scanExpression);
    }

    //
    @Override
    public Optional<Item> getSkillById(String id) {
//        Optional<Item> json = Optional.ofNullable(serviceDynamoDB.getItemByKey("skills", "id", id));
//        if (json.isEmpty())
//            throw new BadRequestException("[Create Skill] The skill ID [".concat(id).concat("] is already in use."));
//        Gson gson = new Gson();
//        return gson.fromJson(json.get().toJSON(), Skill.class);
        return Optional.ofNullable(serviceDynamoDB.getItemByKey("skills", "id", id));
    }


    //roster
    @Override
    public void putItem(String table, Item item) {
        serviceDynamoDB.putItem(table, item);
    }


}

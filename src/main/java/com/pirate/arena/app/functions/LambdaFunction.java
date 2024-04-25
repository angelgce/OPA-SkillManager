package com.pirate.arena.app.functions;

import com.pirate.arena.app.models.Skill;
import com.pirate.arena.app.request.RequestGetSkill;
import com.pirate.arena.app.services.ServiceSkill;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.function.Function;

@Configuration
@RequiredArgsConstructor
public class LambdaFunction {


    //CREATE SKILL
    //EDIT SKILL
    //GET SKILL
    //GET LIST SKILLS

    private final ServiceSkill serviceSkill;

    //
    @Bean
    public Function<Skill, ResponseEntity<String>> createSkill() {
        return value -> ResponseEntity.ok().body(serviceSkill.createSkill(value));
    }

    @Bean
    public Function<RequestGetSkill, ResponseEntity<Skill>> getSkillById() {
        return value -> ResponseEntity.ok()
                .body(serviceSkill.findSkillById(value));
    }

    @Bean
    public Function<RequestGetSkill, ResponseEntity<List<Skill>>> getSkillByCharacter() {
        return value -> ResponseEntity.ok()
                .body(serviceSkill.findUserSkills(value));
    }
}

package com.pirate.arena.app.services;

import com.pirate.arena.app.models.Skill;
import com.pirate.arena.app.models.SkillSet;
import com.pirate.arena.app.request.RequestGetSkill;

import java.util.List;
import java.util.Map;

public interface IServiceSkill {

    String createSkill(Skill skill);

    Skill findSkillById(RequestGetSkill requestGetSkill);

    List<Skill> findUserSkills(RequestGetSkill requestGetSkill);
    List<Map> mapSetSkills(List<SkillSet> skillSet);

}

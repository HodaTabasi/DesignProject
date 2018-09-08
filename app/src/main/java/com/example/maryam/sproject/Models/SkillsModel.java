package com.example.maryam.sproject.Models;

public class SkillsModel {

    private String skill;
    private String experienceYears;

    public SkillsModel() {
    }

    public SkillsModel(String skill, String experienceYears) {
        this.skill = skill;
        this.experienceYears = experienceYears;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(String experienceYears) {
        this.experienceYears = experienceYears;
    }
}

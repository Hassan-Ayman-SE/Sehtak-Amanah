package com.hassan.sehtakamanah.model;

public class Section {
    String name;
    int img;
    String effects;
    String causesM;
    String treatments;
    String information, diseasesAfflicted, causesB, avoidDiseasesMethods;
    boolean flag;

    //one adapter to show Mental and Body
    //this constructor use it for Mental section
    public Section(String name, int img, String effects, String causesM, String treatments, boolean flag) {
        this.name = name;
        this.img = img;
        this.effects = effects;
        this.causesM = causesM;
        this.treatments = treatments;
        this.flag = flag;
    }

    //this constructor use it for Body section
    public Section(String name, int img, String information, String diseasesAfflicted, String causesB, String avoidDiseasesMethods, boolean flag) {
        this.name = name;
        this.img = img;
        this.information = information;
        this.diseasesAfflicted = diseasesAfflicted;
        this.causesB = causesB;
        this.avoidDiseasesMethods = avoidDiseasesMethods;
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getEffects() {
        return effects;
    }

    public void setEffects(String effects) {
        this.effects = effects;
    }


    public String getTreatments() {
        return treatments;
    }

    public void setTreatments(String treatments) {
        this.treatments = treatments;
    }

    public String getCausesM() {
        return causesM;
    }

    public void setCausesM(String causesM) {
        this.causesM = causesM;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getDiseasesAfflicted() {
        return diseasesAfflicted;
    }

    public void setDiseasesAfflicted(String diseasesAfflicted) {
        this.diseasesAfflicted = diseasesAfflicted;
    }

    public String getCausesB() {
        return causesB;
    }

    public void setCausesB(String causesB) {
        this.causesB = causesB;
    }

    public String getAvoidDiseasesMethods() {
        return avoidDiseasesMethods;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void setAvoidDiseasesMethods(String avoidDiseasesMethods) {
        this.avoidDiseasesMethods = avoidDiseasesMethods;


    }
}

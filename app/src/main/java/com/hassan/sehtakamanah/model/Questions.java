package com.hassan.sehtakamanah.model;

import com.google.gson.annotations.SerializedName;

public class Questions {

    @SerializedName("question_app")
    private String question;


    public Questions(String question){

        this.question = question;


    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }


}


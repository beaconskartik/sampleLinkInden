package com.example.linkedinproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Question {

    @SerializedName("question")
    @Expose
    public String question;

    @SerializedName("type")
    @Expose
    public String type;
}

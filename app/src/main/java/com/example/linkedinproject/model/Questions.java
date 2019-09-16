package com.example.linkedinproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Questions {

    @SerializedName("questions")
    @Expose
    public List<Question> questions;
}

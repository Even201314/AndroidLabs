package com.even.androidlabs.test.model;

/**
 * Test Model
 * Created by Even on 17/1/22.
 */

public class TestModel {
    private ModelType modelType;
    private String description;

    public TestModel(ModelType modelType, String description) {
        this.modelType = modelType;
        this.description = description;
    }

    public ModelType getModelType() {
        return modelType;
    }

    public void setModelType(ModelType modelType) {
        this.modelType = modelType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

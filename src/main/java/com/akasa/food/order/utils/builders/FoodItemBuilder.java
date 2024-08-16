package com.akasa.food.order.utils.builders;

import com.akasa.food.order.models.FoodItem;

public class FoodItemBuilder {
    private String name;
    private Float price;
    private Integer diet;
    private Integer category;
    private String description;
    private float ratings;
    private String img;
    private boolean isFeatured;

    public FoodItemBuilder name(String name) {
        this.name = name;
        return this;
    }

    public FoodItemBuilder price(Float price) {
        this.price = price;
        return this;
    }

    public FoodItemBuilder diet(Integer diet) {
        this.diet = diet;
        return this;
    }

    public FoodItemBuilder category(Integer category) {
        this.category = category;
        return this;
    }

    public FoodItemBuilder description(String description) {
        this.description = description;
        return this;
    }

    public FoodItemBuilder ratings(float ratings) {
        this.ratings = ratings;
        return this;
    }

    public FoodItemBuilder img(String img) {
        this.img = img;
        return this;
    }

    public FoodItemBuilder isFeatured(boolean value) {
        this.isFeatured = value;
        return this;
    }

    public static FoodItemBuilder builder(){
        return new FoodItemBuilder();
    }

    public FoodItem build() {
        FoodItem foodItem = new FoodItem();
        foodItem.setName(name);
        foodItem.setPrice(price);
        foodItem.setDiet(diet);
        foodItem.setCategory(category);
        foodItem.setDescription(description);
        foodItem.setRatings(ratings);
        foodItem.setImg(img);
        foodItem.setFeatured(isFeatured);
        return foodItem;
    }
}


package com.course.mobiletest;

public class Diet_Info {
    private int id;
    private String foodname;
    private String amount;
    private int calorie;
    private String review;
    private String time;
    private String date;
    private String imageID;
    private String imageURI;
    private String address;


    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }

    public String getImageID() {
        return imageID;
    }

    public void setImageID(String imageID) {
        this.imageID = imageID;
    }

    public String getImageURI() {
        return imageURI;
    }

    public void setImageURI(String imageURI) {
        this.imageURI = imageURI;
    }

    public Diet_Info(String foodname, String amount, int calorie, String review, String time, String date, String imageid, String imageuri, String address){
        this.id = id;
        this.foodname=foodname;
        this.amount=amount;
        this.calorie=calorie;
        this.date=date;
        this.review=review;
        this.time=time;
        this.imageID=imageid;
        this.imageURI=imageuri;
        this.address=address;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getFoodname() {
        return foodname;
    }
    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public String getAmount() {
        return amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }

    public int getCalorie() {
        return calorie;
    }
    public void setCalorie(Integer calorie) {
        this.calorie = calorie;
    }

    public String getReview() {
        return review;
    }
    public void setReview(String review) {
        this.review = review;
    }

    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }



    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}

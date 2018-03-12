package com.example.abhi.zealicon.model;

/**
 * Created by abhi on 16/2/18.
 */

/**
 * THis model is used for all Developers Team and Sponsors
 */
public class Developer {

  private String name;
  private String imgurl;
  private String position;
  /*********This field is contact no for team and github url for developer************/
  private String mobNum;

  //Constructor for Developer and Team members
  public Developer(String name, String imgurl, String position, String mobNum) {
    this.name = name;
    this.imgurl = imgurl;
    this.position = position;
    this.mobNum = mobNum;
  }

  //Constructor for sponsors
  public Developer(String sponsorName,String imgurl,String category){
    this.name = sponsorName;
    this.imgurl = imgurl;
    this.position = category;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getImgurl() {
    return imgurl;
  }

  public void setImgurl(String imgurl) {
    this.imgurl = imgurl;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public String getMobNum() {
    return mobNum;
  }

  public void setMobNum(String mobNum) {
    this.mobNum = mobNum;
  }



}

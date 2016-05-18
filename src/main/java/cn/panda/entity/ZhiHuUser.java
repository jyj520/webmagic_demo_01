package cn.panda.entity;

/**
 * Created by Administrator on 2016/5/10 0010.
 */
public class ZhiHuUser {

    Long st;
    Integer followees;
    Integer followers;
    String name;
    String bio;
    String avatar;
    String weibo;
    Integer agree;
    Integer thanks;
    String nameOnLink;

    public Long getSt() {
        return st;
    }

    public void setSt(Long st) {
        this.st = st;
    }

    public Integer getFollowees() {
        return followees;
    }

    public void setFollowees(Integer followees) {
        this.followees = followees;
    }

    public Integer getFollowers() {
        return followers;
    }

    public void setFollowers(Integer followers) {
        this.followers = followers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getWeibo() {
        return weibo;
    }

    public void setWeibo(String weibo) {
        this.weibo = weibo;
    }

    public Integer getAgree() {
        return agree;
    }

    public void setAgree(Integer agree) {
        this.agree = agree;
    }

    public Integer getThanks() {
        return thanks;
    }

    public void setThanks(Integer thanks) {
        this.thanks = thanks;
    }

    public String getNameOnLink() {
        return nameOnLink;
    }

    public void setNameOnLink(String nameOnLink) {
        this.nameOnLink = nameOnLink;
    }
}

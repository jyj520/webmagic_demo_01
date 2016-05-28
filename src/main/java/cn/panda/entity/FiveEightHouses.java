package cn.panda.entity;

import java.util.Date;

/**
 * Created by Administrator on 2016/5/27 0027.
 */

/**
 * 58房源
 */
public class FiveEightHouses {

    Long id;
    String originalLink;    //原始链接
    String title;       //标题
    String publishDate; //发布时间
    String price;       //售价
    String houseType;   //户型
    String quyu;    //区域，如泰山区，岱岳区
    String jiedao;     //街道
    String villageName;   //小区名
    String houseAdress; //房屋地址
    String contactName; //联系人
    String phone;       //手机号
    String decType;     //装修类型
    String houseCategory;   //住宅类别
    String keepYear;      // 产权
    String floor;       //楼层
    String buildYear;   //建筑年代
    String orientation; //朝向
    String description; //描述
    String imgUlr;      //房屋照片
    String buildStructure; //建筑结构
    String houseSellingType; //房屋类型
    Date addDate;     //添加日期
    Integer isDelete;    //是否被删除
    Integer isUsed;      //是否被用过


    //toString

    @Override
    public String toString() {
        return "FiveEightHouses{" +
                "id=" + id +
                ", originalLink='" + originalLink + '\'' +
                ", title='" + title + '\'' +
                ", publishDate='" + publishDate + '\'' +
                ", price='" + price + '\'' +
                ", houseType='" + houseType + '\'' +
                ", quyu='" + quyu + '\'' +
                ", jiedao='" + jiedao + '\'' +
                ", villageName='" + villageName + '\'' +
                ", houseAdress='" + houseAdress + '\'' +
                ", contactName='" + contactName + '\'' +
                ", phone='" + phone + '\'' +
                ", decType='" + decType + '\'' +
                ", houseCategory='" + houseCategory + '\'' +
                ", keepYear='" + keepYear + '\'' +
                ", floor='" + floor + '\'' +
                ", buildYear='" + buildYear + '\'' +
                ", orientation='" + orientation + '\'' +
                ", description='" + description + '\'' +
                ", imgUlr='" + imgUlr + '\'' +
                ", buildStructure='" + buildStructure + '\'' +
                ", houseSellingType='" + houseSellingType + '\'' +
                ", addDate=" + addDate +
                ", isDelete=" + isDelete +
                ", isUsed=" + isUsed +
                '}';
    }


    //toString


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginalLink() {
        return originalLink;
    }

    public void setOriginalLink(String originalLink) {
        this.originalLink = originalLink;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public String getQuyu() {
        return quyu;
    }

    public void setQuyu(String quyu) {
        this.quyu = quyu;
    }

    public String getJiedao() {
        return jiedao;
    }

    public void setJiedao(String jiedao) {
        this.jiedao = jiedao;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public String getHouseAdress() {
        return houseAdress;
    }

    public void setHouseAdress(String houseAdress) {
        this.houseAdress = houseAdress;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDecType() {
        return decType;
    }

    public void setDecType(String decType) {
        this.decType = decType;
    }

    public String getHouseCategory() {
        return houseCategory;
    }

    public void setHouseCategory(String houseCategory) {
        this.houseCategory = houseCategory;
    }

    public String getKeepYear() {
        return keepYear;
    }

    public void setKeepYear(String keepYear) {
        this.keepYear = keepYear;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getBuildYear() {
        return buildYear;
    }

    public void setBuildYear(String buildYear) {
        this.buildYear = buildYear;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUlr() {
        return imgUlr;
    }

    public void setImgUlr(String imgUlr) {
        this.imgUlr = imgUlr;
    }

    public String getBuildStructure() {
        return buildStructure;
    }

    public void setBuildStructure(String buildStructure) {
        this.buildStructure = buildStructure;
    }

    public String getHouseSellingType() {
        return houseSellingType;
    }

    public void setHouseSellingType(String houseSellingType) {
        this.houseSellingType = houseSellingType;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(Integer isUsed) {
        this.isUsed = isUsed;
    }
}

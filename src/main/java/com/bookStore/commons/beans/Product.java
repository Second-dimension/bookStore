package com.bookStore.commons.beans;

/**
 * company: www.abc.com
 * Author: Mac-book
 * Create Data: 2019/5/21
 */
public class Product {

    private String id;
    private String name;
    private double price;
    private String category;
    private int pnum;
    private String imgurl;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPnum() {
        return pnum;
    }

    public void setPnum(int pnum) {
        this.pnum = pnum;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        //如果两个对象是同一个对象，返回true
        if (this == obj)
            return true;
        //测试（equals后的）对象如果为空，返回false
        if (obj == null)
            return false;
        //两个对象所属的类是否相同，不相同返回false
        if (getClass() != obj.getClass())
            return false;
        //obj是Product类型，就强制类型转换
        Product other = (Product) obj;
        //如果equals前的product的主键id为null
        if (id == null) {
            //equals后的对象主键不为null，返回false
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))//如果两个对象的主键不相等，返回false
            return false;
        //两个对象是同一类型的对象并且主键相等，返回true
        return true;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", pnum=" + pnum +
                ", imgurl='" + imgurl + '\'' +
                ", descrinption='" + description + '\'' +
                '}';


    }
}

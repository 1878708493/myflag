package com.example.sdu.myflag.bean;

/**
 * 字母实体
 */
public class FriendBean implements Comparable<FriendBean> {

    private int id;
    private String name;//名字
    private String remark;
    private String firstLetter;//名字首字母

    public FriendBean() {
        super();
    }

    public FriendBean(int id, String name, String firstLetter, String remark) {
        super();
        this.id = id;
        this.name = name;
        this.firstLetter = firstLetter;
        this.remark = remark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public int compareTo(FriendBean another) {
        if (this.getFirstLetter().equals("@")
                || another.getFirstLetter().equals("#")) {
            return -1;
        } else if (this.getFirstLetter().equals("#")
                || another.getFirstLetter().equals("@")) {
            return 1;
        } else {
            return this.getFirstLetter().compareTo(another.getFirstLetter());
        }
    }

}

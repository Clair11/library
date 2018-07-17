package com.example.demo.pojo;

import java.util.Date;

public class Book {
    private Integer bookId;

    private String bookName;

    private String author;

    private Double price;

    private Date createTime;

    private Boolean isBorrow;

    private Boolean isSell;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName == null ? null : bookName.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean getIsBorrow() {
        return isBorrow;
    }

    public void setIsBorrow(Boolean isBorrow) {
        this.isBorrow = isBorrow;
    }

    public Boolean getIsSell() {
        return isSell;
    }

    public void setIsSell(Boolean isSell) {
        this.isSell = isSell;
    }
}
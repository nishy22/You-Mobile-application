package com.example.you.Adapter;

public class recycleview_list {
    private Integer image;
    private String Text;

    public recycleview_list(Integer image, String text) {
        this.image = image;
        Text = text;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }
}

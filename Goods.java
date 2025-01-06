package com.example.myshop;

public class Goods {
    private  int shopId;
    private String shopName;
    private String shopPrice;
    private String shopFenLei;
    private int shopInCart;
    private int pic;


    public Goods(){
        this.pic=R.mipmap.search;
    }

    public Goods(String shopName,String shopPrice,String shopFenLei,int shopInCart){
        this.shopName=shopName;
        this.shopPrice=shopPrice;
        this.shopFenLei=shopFenLei;
        this.shopInCart=shopInCart;
        switch (shopName){
            case "笔记本":
                this.pic=R.mipmap.notebook;
                break;
            case "羽绒服":
                this.pic=R.mipmap.yurongfu;
                break;
            case "计算器":
                this.pic=R.mipmap.jisuanqi;
                break;
            case "拖鞋":
                this.pic=R.mipmap.tuoxie;
                break;
            case "竹蜻蜓":
                this.pic=R.mipmap.zhuqinting;
                break;
            case "橡皮":
                this.pic=R.mipmap.xiangpi;
                break;
            case "圆珠笔":
                this.pic=R.mipmap.yuanzhubi;
                break;
            case "长袜":
                this.pic=R.mipmap.changwa;
                break;
            case "小象公仔":
                this.pic=R.mipmap.xiaoxianggongzai;
                break;
        }

    }



    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(String shopPrice) {
        this.shopPrice = shopPrice;
    }

    public String getShopFenLei() {
        return shopFenLei;
    }

    public void setShopFenLei(String shopFenLei) {
        this.shopFenLei = shopFenLei;
    }

    public int getShopInCart() {
        return shopInCart;
    }

    public void setShopInCart(int shopInCart) {
        this.shopInCart = shopInCart;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }
}

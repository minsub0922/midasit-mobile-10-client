package com.example.minseop.midasit.model;

public class Order {

    public static final String ORDER_STATUS_SHOPPINGCART = "SHOPPINGCART";
    public static final String ORDER_STATUS_RESERVATION = "RESERVATION";
    public static final String ORDER_STATUS_COMPLETED = "COMPLETED";

    private int userId;
    private int menuId;
    private String status;
    private int count;
    private int ice;
    private int syrup;
    private int whipping;
    private String name;
    private int price;
    private String image;
    private String username;

    public Order(int userId, int menuId, String status, int count, boolean ice, boolean syrup, boolean whipping) {
        this.userId = userId;
        this.menuId = menuId;
        this.status = status;
        this.count = count;
        this.ice = ice ? 1 : 0;
        this.syrup = syrup ? 1 : 0;
        this.whipping = whipping ? 1 : 0;
    }

    public int getUserId() {
        return userId;
    }

    public int getMenuId() {
        return menuId;
    }

    public String getStatus() {
        return status;
    }

    public int getCount() {
        return count;
    }

    public boolean isIce() {
        return ice != 0;
    }

    public boolean isSyrup() {
        return syrup != 0;
    }

    public boolean isWhipping() {
        return whipping != 0;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("userId=").append(userId);
        sb.append(", menuId=").append(menuId);
        sb.append(", status='").append(status).append('\'');
        sb.append(", count=").append(count);
        sb.append(", ice=").append(ice);
        sb.append(", syrup=").append(syrup);
        sb.append(", whipping=").append(whipping);
        sb.append(", name='").append(name).append('\'');
        sb.append(", price=").append(price);
        sb.append(", image='").append(image).append('\'');
        sb.append(", username='").append(username).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

package com.pn.management.model;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Min;
import org.jetbrains.annotations.NotNull;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


import java.util.Date;

@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;
    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9]+$")
    private String ticker;



    @Min(value=1)
    private int quantity;

    @Min(value=1)
    private double price;

    private Date orderTime;

    @NotNull
    @Pattern(regexp = "^[B|S]+$")
    private String direction;


    private String status;

    public Order() {
    }

    public Order(String ticker, int quantity, double price, String direction) {
        this.ticker = ticker;
        this.quantity = quantity;
        this.price = price;
        this.direction = direction;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", ticker='" + ticker + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", orderTime=" + orderTime +
                ", direction=" + direction +
                ", status='" + status + '\'' +
                '}';
    }
}

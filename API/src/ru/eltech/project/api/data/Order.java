package ru.eltech.project.api.data;

import ru.eltech.project.api.data.enums.DeliveryType;
import ru.eltech.project.api.data.enums.PaymentType;
import ru.eltech.project.api.data.enums.Status;

import java.util.Date;

public class Order {
    private String id;
    private String clientId;
    private Status status;
    private DeliveryType deliveryType;
    private double summaryPrice;
    private Date deliveryDate;
    private Date paymentDate;
    private PaymentType paymentType;

    public Order(String id, String clientId, Status status, DeliveryType deliveryType, float summaryPrice, Date deliveryDate, Date paymentDate, PaymentType paymentType) {
        this.id = id;
        this.clientId = clientId;
        this.status = status;
        this.deliveryType = deliveryType;
        this.summaryPrice = summaryPrice;
        this.deliveryDate = deliveryDate;
        this.paymentDate = paymentDate;
        this.paymentType = paymentType;
    }

    public Order() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }

    public double getSummaryPrice() {
        return summaryPrice;
    }

    public void setSummaryPrice(double summaryPrice) {
        this.summaryPrice = summaryPrice;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }
}

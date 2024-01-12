package com.example.CloneGamestop.DTO;

import com.example.CloneGamestop.Model.Order;

public class OrderDTO {

    private Long idOrder;
    private boolean allActionsCompleted;
    private String statusOrder;

    public OrderDTO() {

    }

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public boolean getallActionsCompleted() {
        return allActionsCompleted;
    }

    public void setallActionsCompleted(boolean allActionsCompleted) {
        this.allActionsCompleted = allActionsCompleted;
    }

    public String getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(String statusOrder) {
        this.statusOrder = statusOrder;
    }

    public static OrderDTO fromOrder(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setIdOrder(orderDTO.idOrder);
        orderDTO.setallActionsCompleted(orderDTO.allActionsCompleted);
        orderDTO.setStatusOrder(orderDTO.statusOrder);
        return orderDTO;
    }

}

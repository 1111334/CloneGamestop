package com.example.CloneGamestop.DTO;

import com.example.CloneGamestop.Model.Order;
import lombok.Data;

@Data
public class OrderDTO {

    private Long idOrder;
    private boolean allActionsCompleted;
    private String statusOrder;

    public OrderDTO() {

    }

    public static OrderDTO fromOrder(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setIdOrder(orderDTO.idOrder);
        orderDTO.setallActionsCompleted(orderDTO.allActionsCompleted);
        orderDTO.setStatusOrder(orderDTO.statusOrder);
        return orderDTO;
    }

    private void setallActionsCompleted(boolean allActionsCompleted) {
    }

}

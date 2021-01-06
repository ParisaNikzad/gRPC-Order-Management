package com.pn.management.service;

import com.grpc.Order;
import com.grpc.orderManagementServiceGrpc.orderManagementServiceImplBase;

import com.pn.management.repository.OrderRepo;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@GRpcService
public class OrderService extends orderManagementServiceImplBase {

    @Autowired
    OrderRepo orderRepo;

    @Override
    public void createOrder(Order.OrderRequest request, StreamObserver<Order.OrderResponse> responseObserver) {
        com.pn.management.model.Order order = new com.pn.management.model.Order(request.getTicker(), request.getQuantity(), request.getPrice(), request.getDirection());
        String msg;
        order.setOrderTime(new Date());
        order.setStatus("RECEIVED");
        order = orderRepo.save(order);

        if(null !=order && null != order.getOrderId()){
            msg = "Order is saved successfully with Order Id: " + order.getOrderId() + " at " + order.getOrderTime();
        }
        else {
            msg = "Order is not saved!";
        }

        Order.OrderResponse.Builder response = Order.OrderResponse.newBuilder().setMsg(msg);
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

    @Override
    public void searchOrderById(Order.OrderRequestId request, StreamObserver<Order.OrderRequestInfo> responseObserver) {

        com.pn.management.model.Order order = new com.pn.management.model.Order();
        order.setOrderId(request.getOrderId());
        com.pn.management.model.Order orderResponse =orderRepo.findById(order.getOrderId()).get();
        Order.OrderRequestInfo.Builder orderRequestInfo = Order.OrderRequestInfo.newBuilder().setOrderId(orderResponse.getOrderId()).setTicker(orderResponse.getTicker()).setQuantity(orderResponse.getQuantity()).setPrice(orderResponse.getPrice()).setDirection(orderResponse.getDirection()).setOrderTime(orderResponse.getOrderTime().toString()).setStatus(orderResponse.getStatus());
        responseObserver.onNext(orderRequestInfo.build());
        responseObserver.onCompleted();
    }

}

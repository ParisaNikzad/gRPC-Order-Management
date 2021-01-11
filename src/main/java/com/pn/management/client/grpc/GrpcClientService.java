package com.pn.management.client.grpc;

import com.grpc.Order;
import com.grpc.orderManagementServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.LoggerFactory;

public class GrpcClientService {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(GrpcClient.class);
    private static final ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 6565).usePlaintext().build();
    private static final orderManagementServiceGrpc.orderManagementServiceBlockingStub orderManagementServiceStub = orderManagementServiceGrpc.newBlockingStub(channel);

    public static Order.OrderRequest getOrderRequest() {
        return Order.OrderRequest.newBuilder().setTicker("Axr0300").setQuantity(5).setPrice(1.25).setDirection("B").build();
    }

    public static void addOrder(Order.OrderRequest orderRequest){
        Order.OrderResponse orderResponse = orderManagementServiceStub.createOrder(orderRequest);
        LOGGER.info(orderResponse.toString());

    }

    public static void getOrderById(int orderId){
        Order.OrderRequestId orderRequestId = Order.OrderRequestId.newBuilder().setOrderId(1).build();
        Order.OrderRequestInfo orderResponseInfo = orderManagementServiceStub.searchOrderById(orderRequestId);
        LOGGER.info(orderResponseInfo.toString());
    }
}

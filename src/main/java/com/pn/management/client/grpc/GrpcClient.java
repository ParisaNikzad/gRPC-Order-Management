package com.pn.management.client.grpc;

import org.springframework.beans.factory.annotation.Autowired;


public class GrpcClient {

    @Autowired
    static GrpcClientService grpcClientService;

    public static void main(String[] args) {

        grpcClientService.addOrder(grpcClientService.getOrderRequest());
        grpcClientService.getOrderById(1);

    }



}

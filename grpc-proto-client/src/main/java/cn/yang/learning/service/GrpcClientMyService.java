package cn.yang.learning.service;

import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import net.devh.boot.grpc.examples.lib.HelloWorldProto;
import net.devh.boot.grpc.examples.lib.MyServiceGrpc;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GrpcClientMyService {

    @GrpcClient("cloud-grpc-server")
    private MyServiceGrpc.MyServiceBlockingStub myServiceBlockingStub;

    public String sendMessage(final String name) {
        try {
            final HelloWorldProto.HelloReply response = this.myServiceBlockingStub.sayHello(HelloWorldProto.HelloRequest.newBuilder().setName(name).build());
            return response.getMessage();
        } catch (Exception e) {
            log.error("Request failed", e);
            return "FAILED with " + e.getMessage();
        }
    }

}

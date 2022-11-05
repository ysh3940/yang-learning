package cn.yang.learning.service;

import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.examples.lib.HelloWorldProto;
import net.devh.boot.grpc.examples.lib.MyServiceGrpc;
import net.devh.boot.grpc.server.service.GrpcService;

@Slf4j
@GrpcService
public class GrpcServerMyService extends MyServiceGrpc.MyServiceImplBase {

    @Override
    public void sayHello(HelloWorldProto.HelloRequest req, StreamObserver<HelloWorldProto.HelloReply> responseObserver) {
        log.error("GrpcServerMyService.req = {}", req.getName());

        HelloWorldProto.HelloReply reply = HelloWorldProto.HelloReply.newBuilder().setMessage("Hello ==> " + req.getName()).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

}

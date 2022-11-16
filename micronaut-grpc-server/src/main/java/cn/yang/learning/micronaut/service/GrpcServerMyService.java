//package cn.yang.learning.micronaut.service;
//
//import io.grpc.stub.StreamObserver;
//import jakarta.inject.Singleton;
//import net.devh.boot.grpc.examples.lib.HelloReply;
//import net.devh.boot.grpc.examples.lib.HelloRequest;
//import net.devh.boot.grpc.examples.lib.MyServiceGrpc;
//
//
//@Singleton
//public class GrpcServerMyService extends MyServiceGrpc.MyServiceImplBase {
//
//
//    @Override
//    public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
//        System.out.println("GrpcServerMyService.req = " + req.getName());
//
//        HelloReply reply = HelloReply.newBuilder().setMessage("Hello ==> " + req.getName()).build();
//        responseObserver.onNext(reply);
//        responseObserver.onCompleted();
//    }
//
//}

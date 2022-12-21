package ru.test;

import io.grpc.stub.StreamObserver;
import ru.test.GreeterGrpc;
import ru.test.Greet;

public class GreeterService extends GreeterGrpc.GreeterImplBase {
    @Override
    public void sayHello(Greet.HelloRequest request, StreamObserver<Greet.HelloReply> responseObserver) {
        String name = request.getName();
        String message = "Hello, " + name + "!";
        System.out.println("New client request with name: " + name);
        Greet.HelloReply reply = Greet.HelloReply.newBuilder().setMessage(message).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}

package ru.test;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import ru.test.GreeterGrpc;
import ru.test.Greet;

public class HelloClient {
    public static void main(String[] args) {
        String name = "World";
        if (args.length > 0) {
            name = args[0];
        }
        new HelloClient().run(name);
    }

    private void run(String name) {
        int port = 50051;
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("127.0.0.1", port)
                .usePlaintext()
                .build();
        GreeterGrpc.GreeterBlockingStub stub = GreeterGrpc.newBlockingStub(channel);
        Greet.HelloRequest request = Greet.HelloRequest.newBuilder().setName(name).build();
        try {
        Greet.HelloReply reply = stub.sayHello(request);
        System.out.println("Server response: " + reply.getMessage());
        } catch (StatusRuntimeException e) {
            e.printStackTrace();
        }
    }
}

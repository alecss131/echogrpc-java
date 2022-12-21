package ru.test;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;

public class HelloServer {
    public static void main(String[] args) {
        try {
            new HelloServer().run();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void run() throws IOException, InterruptedException {
        int port = 50051;
        Server server = ServerBuilder.forPort(port)
                .addService(new GreeterService())
                .build()
                .start();
        System.out.println("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("Shutting down gRPC server since JVM is shutting down");
            if (server != null) {
                server.shutdown();
            }
            System.err.println("Server shut down");
        }));
        server.awaitTermination();
    }

}
package Business;

import io.grpc.stub.StreamObserver;
import io.kubemq.sdk.basic.ServerAddressNotSuppliedException;
import io.kubemq.sdk.event.EventReceive;
import io.kubemq.sdk.event.Subscriber;
import io.kubemq.sdk.subscription.EventsStoreType;
import io.kubemq.sdk.subscription.SubscribeRequest;
import io.kubemq.sdk.subscription.SubscribeType;
import io.kubemq.sdk.tools.Converter;

import java.util.logging.Level;
import java.util.logging.Logger;

import Persistence.Workout_CRUD;
import Helper.Workout;

import javax.net.ssl.SSLException;

public class Messaging {

    // Method for receiving events from the KubeMQ channel for SearchWorkout
    public static void Receiving_Events_Store(String channelName) throws ServerAddressNotSuppliedException, SSLException {
        String clientID = "search-workout-subscriber";
        String kubeMQAddress = System.getenv("kubeMQAddress"); // Use your KubeMQ server address
        Subscriber subscriber = new Subscriber(kubeMQAddress);

        // Subscribe request setup for receiving events
        SubscribeRequest subscribeRequest = new SubscribeRequest();
        subscribeRequest.setChannel(channelName);
        subscribeRequest.setClientID(clientID);
        subscribeRequest.setSubscribeType(SubscribeType.EventsStore);
        subscribeRequest.setEventsStoreType(EventsStoreType.StartNewOnly);
        subscribeRequest.setEventsStoreTypeValue(1); // Correct usage for event stream type

        // Stream observer to listen for incoming events
        StreamObserver<EventReceive> streamObserver = new StreamObserver<EventReceive>() {
            @Override
            public void onNext(EventReceive value) {
                try {
                    String body = (String) Converter.FromByteArray(value.getBody());  // Convert event body to string
                    System.out.println("📥 Received Event: " + body);

                    // Split the message body by delimiter ":"
                    String[] parts = body.split(":");

                    if (parts.length >= 4 && parts[0].equals("WORKOUT_ADDED")) {
                        // Extracting workout details from the event message
                        String workoutDate = parts[1];
                        String duration = parts[2];
                        String userID = parts[3];
                        // Creating a new workout object and saving it to the database (the ID is assigned on insert)
                        Workout workout = new Workout(null, workoutDate, duration, userID);
                        Workout_CRUD.insertWorkout(workout);

                        System.out.println("✅ Workout saved to Search DB: " + workoutDate);
                    }

                } catch (Exception ex) {
                    Logger.getLogger(Messaging.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void onError(Throwable t) {
                System.out.printf("❌ onError: %s%n", t.getMessage());  // Handle any errors during event processing
            }

            @Override
            public void onCompleted() {
                System.out.println("🛑 Event stream completed.");  // Event stream completion message
            }
        };

        // Subscribe to events from KubeMQ channel
        subscriber.SubscribeToEvents(subscribeRequest, streamObserver);
    }
}

package Business;

import io.kubemq.sdk.event.Channel;
import io.kubemq.sdk.event.Event;
import io.kubemq.sdk.tools.Converter;
import io.kubemq.sdk.basic.ServerAddressNotSuppliedException;

import javax.net.ssl.SSLException;
import java.io.IOException;

public class Messaging {

    public static void sendMessage(String message) throws IOException {
        String channelName = "add_workout_channel";
        String clientID = "add-workout-publisher";
        String kubeMQAddress = System.getProperty("kubeMQAddress", "localhost:50000");

        System.out.println("📤 Sending message to KubeMQ: " + message);
        System.out.println("🔌 Using address: " + kubeMQAddress);

        Channel channel = new Channel(channelName, clientID, false, kubeMQAddress);
        channel.setStore(true); // make the event persistent

        Event event = new Event();
        event.setBody(Converter.ToByteArray(message));
        event.setEventId("event-add-workout");

        try {
            channel.SendEvent(event);
            System.out.println("✅ Message sent.");
        } catch (SSLException | ServerAddressNotSuppliedException e) {
            System.out.println("❌ Messaging error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

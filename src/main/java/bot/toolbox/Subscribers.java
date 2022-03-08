package bot.toolbox;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;

import java.util.ArrayList;
import java.util.HashMap;
import bot.exceptions.*;
import bot.events.*;

public class Subscribers {
    public static HashMap<Message, ArrayList<User>> subscribers = new HashMap<Message,ArrayList<User>>();
    public static HashMap<Message, Integer> capacities = new HashMap<Message, Integer>();
    public static void notifySubscribers(){

    }
    public static void addSubscriber(Message message, User user) throws AlreadySubscribedException{
        if(subscribers.get(message) == null){
            subscribers.put(message, new ArrayList<User>(1));
            subscribers.get(message).add(user);
            setCapacity(message,4);
            printSubscribers(message);
        }
        else {
            if (subscribers.get(message).contains(user)) {
                throw new AlreadySubscribedException();
            } else {
                subscribers.get(message).add(user);
                setCapacity(message,4);
                printSubscribers(message);
            }
        }
    }
    public static void removeSubscriber(Message message, User user){
        subscribers.get(message).remove(user);
    }
    public static void setCapacity(Message message, Integer capacity){
        capacities.put(message, capacity);
    }
    public static void printSubscribers(Message message){
        System.out.print("List : ");
        System.out.println(subscribers.get(message).toString());
    }
    public static int getCapacity(Message message){
        return capacities.get(message);
    }

}

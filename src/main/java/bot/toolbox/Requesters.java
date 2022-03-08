package bot.toolbox;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;

import java.util.ArrayList;
import java.util.HashMap;

public class Requesters {
    public static HashMap<User, ArrayList<Message>> requesters = new HashMap<User, ArrayList<Message>>();
    public static void addSession(Message session, User requester){
        if(requesters.get(requester) == null){
            requesters.put(requester, new ArrayList<>(1));
            requesters.get(requester).add(session);
        }
        else{
            if(!(requesters.get(requester).contains(session))){
                requesters.get(requester).add(session);
            }
        }
    }
}

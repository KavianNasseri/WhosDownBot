package bot;

import bot.events.Events;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import org.json.JSONObject;

import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws LoginException, IOException {
        BufferedReader br = new BufferedReader(new FileReader("D:\\New Bot\\config.json"));
        String line;
        StringBuilder sbObj = new StringBuilder();
        while((line = br.readLine()) != null){
            sbObj.append(line);
        }
        JSONObject obj = new JSONObject(sbObj.toString());
        JDA bot = JDABuilder.createDefault(obj.getString("token")).setActivity(Activity.playing("With my balls")).build();
        bot.addEventListener(new Events());
    }
}

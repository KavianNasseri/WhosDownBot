package bot;

import bot.events.Events;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class Main {
    public static void main(String[] args) throws LoginException {
        JDA bot = JDABuilder.createDefault("OTQ5MTI3MjUwMzM3NDY4NDQ2.YiF1og.whY9dnCFHWkzbeUi-st-famL29s").setActivity(Activity.playing("With my balls")).build();
        bot.addEventListener(new Events());
    }
}

package bot.events;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import bot.toolbox.*;

import static bot.toolbox.Constants.PREFIX;

public class Events extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        // Ignore if itself
        if (event.getAuthor().isBot())
            return;

        Message message = event.getMessage();
        String content = message.getContentRaw();

        // Session Command
        if (content.equals(PREFIX + "session"))
        {
            MessageChannel channel = event.getChannel();
            Functions.sessionRequest(channel);
        }
    }
    public void onButtonInteraction(ButtonInteractionEvent event){
        if(event.getComponentId().equals("Gaming")){
            event.deferEdit().queue();
            Functions.chooseGame(event.getChannel());
            Functions.disableButtons(event.getMessage(),0);
        }
    }
}

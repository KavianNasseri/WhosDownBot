package bot.events;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import bot.toolbox.*;
import net.dv8tion.jda.internal.requests.FunctionalCallback;

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
            Functions.sessionRequest(event);
        }
    }
    public void onButtonInteraction(ButtonInteractionEvent event){
        if(event.getComponentId().equals("Gaming")){
            if(event.getUser().getName().equals(Functions.requester)) {
                Functions.disableButtons(event);
                Functions.chooseGame(event.getChannel());
            }
            else{
                event.reply("That is someone else's session request").setEphemeral(true).queue();
            }
        }
    }
}
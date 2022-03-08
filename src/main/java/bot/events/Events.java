package bot.events;

import bot.exceptions.AlreadySubscribedException;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.SelectMenuInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import bot.toolbox.*;
import bot.exceptions.*;
import net.dv8tion.jda.api.interactions.components.selections.SelectMenu;
import net.dv8tion.jda.internal.requests.FunctionalCallback;

import java.util.function.Function;

import static bot.toolbox.Constants.PREFIX;

public class Events extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        // Ignore if itself
        if (event.getAuthor().isBot()) {
            if(Functions.isCreatingSession){
                try{
                    Subscribers.addSubscriber(event.getMessage(), Functions.requester);
                    Functions.isCreatingSession = false;
                }
                catch (AlreadySubscribedException e){
                }
            }
            return;
        }

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
            if(event.getUser().equals(Functions.requester)) {
                Functions.disableButtons(event);
                Functions.chooseGame(event.getChannel());
            }
            else{
                event.reply("That is someone else's session request").setEphemeral(true).queue();
            }
        }
        else if(event.getComponentId().equals("Down")){
            try {
                Subscribers.addSubscriber(event.getMessage(), event.getUser());
                event.reply("You are now added to the list of subscribers and will be notified to join the session").setEphemeral(true).queue();
                Functions.editEmbed(event.getMessage(),Subscribers.subscribers.get(event.getMessage()).size() + "/" + Subscribers.getCapacity(event.getMessage()));
            }
            catch(AlreadySubscribedException e){
                Subscribers.removeSubscriber(event.getMessage(),event.getUser());
                event.reply("You were removed from the list of subscribers").setEphemeral(true).queue();
                Functions.editEmbed(event.getMessage(), Subscribers.subscribers.get(event.getMessage()).size() + "/" + Subscribers.getCapacity(event.getMessage()));
            }
        }
    }
    public void onSelectMenuInteraction​(SelectMenuInteractionEvent event){
        if(event.getUser().equals(Functions.requester)) {
            event.deferEdit().queue();
            String selection = event.getValues().get(0);
            switch(selection){
                case "Warzone":
                    Functions.makeSessionInvite(event.getChannel(),event.getUser(), "Call of Duty: Warzone", "13:05");
                    break;
                case "Rainbow":
                    Functions.makeSessionInvite(event.getChannel(),event.getUser(),"Rainbow Six Siege", "13:05");
                    break;
                case "Apex":
                    Functions.makeSessionInvite(event.getChannel(),event.getUser(),"Apex Legends","13:05");
                    break;
                case "GTA":
                    Functions.makeSessionInvite(event.getChannel(),event.getUser(),"GTA V", "13:05");
                    break;
                default:
                    break;
            }
        }
        else{
            event.reply("That is someone else's session request").setEphemeral(true).queue();
        }

    }
}
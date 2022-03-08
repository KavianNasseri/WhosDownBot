package bot.toolbox;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.selections.SelectMenu;
import bot.exceptions.*;

import javax.swing.*;
import java.awt.*;

public class Functions {
    public static User requester = null;
    public static boolean isCreatingSession = false;
    public static void sessionRequest(MessageReceivedEvent event){
        requester = event.getAuthor();
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Constants.EMBED_COLOR);
        embed.setTitle("What type of session are you planning?");
        event.getChannel().sendMessageEmbeds(embed.build()).setActionRow(Button.secondary(
                        "Gaming", Emoji.fromMarkdown(Constants.VIDEO_GAME)), Button.secondary("Studying", Emoji.fromMarkdown(Constants.BOOK)),
                Button.secondary("Movie", Emoji.fromMarkdown(Constants.TV)),Button.primary("Custom","Custom")).queue();
    }

    public static void makeSessionInvite(MessageChannel channel, User inviter, String title, String time) {
        isCreatingSession = true;
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(new Color(43, 155, 240));
        embed.setTitle(title);
        embed.setDescription("Hop on guys!");
        embed.addField("Join at:", time, true).addField("Number of Subscribers:", "1/4", true);
        embed.setAuthor(inviter.getName(), null ,inviter.getAvatarUrl());
        if(title.equals("Apex Legends")){
            embed.setImage("https://i.ibb.co/F8JpJD9/APEX.jpg");
        }
        else if(title.equals("GTA V")){
            embed.setImage("https://i.ibb.co/PzfCryc/GTAV.jpg");
        }
        channel.sendMessageEmbeds(embed.build()).setActionRow( Button.secondary("Down",Emoji.fromMarkdown("⬇️"))).queue();
    }

    public static void chooseGame(MessageChannel channel){
        EmbedBuilder embed = new EmbedBuilder();
        SelectMenu menu = SelectMenu.create("menu:class")
                .setPlaceholder("Call of Duty: Warzone") // shows the placeholder indicating what this menu is for
                .addOption("Call of Duty: Warzone", "Warzone")
                .addOption("Rainbow Six Siege", "Rainbow")
                .addOption("Apex Legends", "Apex")
                .addOption("GTA V", "GTA")
                .build();
        embed.setColor(new Color(43, 155, 240));
        embed.setTitle("Choose one of the games below or type your own:");
        channel.sendMessageEmbeds(embed.build()).setActionRow(menu).queue();
    }
    public static void editEmbed(Message message, String newText){
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(new Color(43, 155, 240));
        embed.setTitle(message.getEmbeds().get(0).getTitle());
        embed.setDescription("Hop on guys!");
        embed.addField("Join at:", message.getEmbeds().get(0).getFields().get(0).getValue(), true).addField("Number of Subscribers:", newText, true);
        embed.setAuthor(Subscribers.subscribers.get(message).get(0).getName(), null ,Subscribers.subscribers.get(message).get(0).getAvatarUrl());
        embed.setImage(message.getEmbeds().get(0).getImage().getUrl());
        message.editMessageEmbeds(embed.build()).queue();
    }

    public static void disableButtons(ButtonInteractionEvent event){
        event.deferEdit().setActionRow(Button.secondary(
                        "Gaming", Emoji.fromMarkdown(Constants.VIDEO_GAME)).asDisabled(), Button.secondary("Studying", Emoji.fromMarkdown(Constants.BOOK)).asDisabled(),
                Button.secondary("Movie", Emoji.fromMarkdown(Constants.TV)).asDisabled(),Button.primary("Custom","Custom").asDisabled()).queue();
    }
}
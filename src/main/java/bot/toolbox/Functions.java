package bot.toolbox;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.selections.SelectMenu;

import javax.swing.*;
import java.awt.*;

public class Functions {
    public static String requester = "";
    public static void sessionRequest(MessageReceivedEvent event){
        requester = event.getAuthor().getName();
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Constants.EMBED_COLOR);
        embed.setTitle("What type of session are you planning?");
        event.getChannel().sendMessageEmbeds(embed.build()).setActionRow(Button.secondary(
                        "Gaming", Emoji.fromMarkdown(Constants.VIDEO_GAME)), Button.secondary("Studying", Emoji.fromMarkdown(Constants.BOOK)),
                Button.secondary("Movie", Emoji.fromMarkdown(Constants.TV)),Button.primary("Custom","Custom")).queue();
    }
    public static void sessionInvite(MessageChannel channel, User inviter, String game) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(new Color(43, 155, 240));
        embed.setTitle("Gaming Session");
        embed.setAuthor(inviter.getName(), null ,inviter.getAvatarUrl());
        channel.sendMessageEmbeds(embed.build()).queue();
    }
    public static void chooseGame(MessageChannel channel){
        EmbedBuilder embed = new EmbedBuilder();
        SelectMenu menu = SelectMenu.create("menu:class")
                .setPlaceholder("Call of Duty: Warzone") // shows the placeholder indicating what this menu is for
                .addOption("Call of Duty: Warzone", "Warzone")
                .addOption("Rainbow Six Siege", "Rainbow")
                .addOption("Apex Legends", "Apex")
                .build();
        embed.setColor(new Color(43, 155, 240));
        embed.setTitle("Choose one of the games below or type your own:");
        channel.sendMessageEmbeds(embed.build()).setActionRow(menu).queue();
    }
    public static void disableButtons(ButtonInteractionEvent event){
        event.deferEdit().setActionRow(Button.secondary(
                        "Gaming", Emoji.fromMarkdown(Constants.VIDEO_GAME)).asDisabled(), Button.secondary("Studying", Emoji.fromMarkdown(Constants.BOOK)).asDisabled(),
                Button.secondary("Movie", Emoji.fromMarkdown(Constants.TV)).asDisabled(),Button.primary("Custom","Custom").asDisabled()).queue();
    }
}
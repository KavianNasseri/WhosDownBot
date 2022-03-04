package bot.toolbox;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.awt.*;

public class Functions {
    public static void sessionRequest(MessageChannel channel){
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Constants.EMBED_COLOR);
        embed.setTitle("What type of session are you planning?");
        channel.sendMessageEmbeds(embed.build()).setActionRow(Button.secondary(
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
}

package info.itsthesky.disky.elements.events.react;

import info.itsthesky.disky.api.emojis.Emote;
import info.itsthesky.disky.api.events.DiSkyEvent;
import info.itsthesky.disky.api.events.SimpleDiSkyEvent;
import info.itsthesky.disky.core.SkriptUtils;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.GenericMessageEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;

public class ReactionAddEvent extends DiSkyEvent<MessageReactionAddEvent> {

	static {
		register("Reaction Add", ReactionAddEvent.class, ReactionAddEvent.BukkitReactionAddEvent.class,
				"(reaction|emote)[s] add[ed]")
				.description("Fired when a message, that can be seen by the bot, receive a reaction.",
						"This will be fired, by default, both guild & private messages, use the 'event is from guild' condition to avoid confusion.");

		SkriptUtils.registerBotValue(ReactionAddEvent.BukkitReactionAddEvent.class);

		SkriptUtils.registerRestValue("message",
				ReactionAddEvent.BukkitReactionAddEvent.class,
				event -> event.getJDAEvent().retrieveMessage());

		SkriptUtils.registerValue(ReactionAddEvent.BukkitReactionAddEvent.class, Guild.class,
				event -> event.getJDAEvent().getGuild());
		SkriptUtils.registerValue(ReactionAddEvent.BukkitReactionAddEvent.class, Member.class,
				event -> event.getJDAEvent().getMember());
		SkriptUtils.registerValue(ReactionAddEvent.BukkitReactionAddEvent.class, User.class,
				event -> event.getJDAEvent().getUser());
		SkriptUtils.registerValue(ReactionAddEvent.BukkitReactionAddEvent.class, MessageChannel.class,
				event -> event.getJDAEvent().getChannel());
		SkriptUtils.registerValue(ReactionAddEvent.BukkitReactionAddEvent.class, Emote.class,
				event -> Emote.fromReaction(event.getJDAEvent().getReactionEmote()));

		SkriptUtils.registerValue(ReactionAddEvent.BukkitReactionAddEvent.class, GuildChannel.class,
				event -> event.getJDAEvent().isFromGuild() ? event.getJDAEvent().getGuildChannel() : null);
		SkriptUtils.registerValue(ReactionAddEvent.BukkitReactionAddEvent.class, TextChannel.class,
				event -> event.getJDAEvent().isFromGuild() ? event.getJDAEvent().getTextChannel() : null);
		SkriptUtils.registerValue(ReactionAddEvent.BukkitReactionAddEvent.class, NewsChannel.class,
				event -> event.getJDAEvent().isFromGuild() ? event.getJDAEvent().getNewsChannel() : null);
		SkriptUtils.registerValue(ReactionAddEvent.BukkitReactionAddEvent.class, ThreadChannel.class,
				event -> event.getJDAEvent().isFromGuild() ? event.getJDAEvent().getThreadChannel() : null);

		SkriptUtils.registerValue(ReactionAddEvent.BukkitReactionAddEvent.class, PrivateChannel.class,
				event -> !event.getJDAEvent().isFromGuild() ? event.getJDAEvent().getPrivateChannel() : null);
	}

	public static class BukkitReactionAddEvent extends SimpleDiSkyEvent<MessageReactionAddEvent> implements info.itsthesky.disky.api.events.specific.MessageEvent {
		public BukkitReactionAddEvent(ReactionAddEvent event) {}

		@Override
		public GenericMessageEvent getMessageEvent() {
			return getJDAEvent();
		}
	}
	
}

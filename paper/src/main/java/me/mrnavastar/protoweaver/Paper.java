package me.mrnavastar.protoweaver;

import io.netty.channel.Channel;
import io.netty.channel.epoll.EpollSocketChannel;
import me.mrnavastar.protoweaver.api.ProtoWeaver;
import me.mrnavastar.protoweaver.api.protocol.protomessage.ProtoMessage;
import me.mrnavastar.protoweaver.api.protocol.velocity.VelocityAuth;
import me.mrnavastar.protoweaver.core.util.ProtoLogger;
import me.mrnavastar.protoweaver.server.netty.ProtoDeterminer;
import me.mrnavastar.protoweaver.server.netty.ProtoInitializer;
import me.mrnavastar.r.R;
import net.kyori.adventure.key.Key;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

public class Paper extends JavaPlugin implements ProtoLogger.IProtoLogger {

    private final Logger logger = getLogger();

    @Override
    public void onEnable() {
        ProtoLogger.setLogger(this);
        ProtoInitializer.initialize(getDataFolder().getAbsolutePath() + "/keys", () -> {
            try {
                R.of(Class.forName("io.papermc.paper.network.ChannelInitializeListenerHolder"))
                        .call("addListener",
                                R.TypeBinding.of(Key.class, new NamespacedKey("protoweaver", "internal")),
                                R.of(this).implement("io.papermc.paper.network.ChannelInitializeListener")
                        );

                VelocityAuth.setSecret(R.of(R.of(Class.forName("io.papermc.paper.configuration.GlobalConfiguration"))
                                .call("get", Object.class))
                        .of("proxies")
                        .of("velocity")
                        .get("secret", String.class).getBytes(StandardCharsets.UTF_8)
                );
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        ProtoWeaver.load(ProtoMessage.getProtocol());
        ProtoMessage.MESSAGE_RECEIVED.register((h, a, b) -> {
            System.out.println(a);
        });
    }

    @SuppressWarnings("unused")
    public void afterInitChannel(Channel channel) {
        ProtoDeterminer.registerToPipeline(channel);
    }

    @SuppressWarnings("unused")
    public void afterInitChannel(EpollSocketChannel channel) {
        ProtoDeterminer.registerToPipeline(channel);
    }

    @Override
    public void info(String message) {
        logger.info(message);
    }

    @Override
    public void warn(String message) {
        logger.warning(message);
    }

    @Override
    public void err(String message) {
        logger.severe(message);
    }
}
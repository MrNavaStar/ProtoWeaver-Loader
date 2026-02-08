package me.mrnavastar.protoweaver.loader;

import me.mrnavastar.protoweaver.api.protocol.velocity.VelocityAuth;
import me.mrnavastar.protoweaver.core.util.ProtoLogger;
import me.mrnavastar.protoweaver.server.netty.ProtoInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import one.oktw.FabricProxyLite;

import java.nio.charset.StandardCharsets;

public class Fabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        ProtoLogger.setLogger(new Logger());
        ProtoInitializer.initialize(FabricLoader.getInstance().getConfigDir() + "/protoweaver/keys", () ->
                FabricLoader.getInstance().getModContainer("fabricproxy-lite").ifPresent(modContainer -> {
                    // FabricProxyLites config is initialized as a mixin plugin, so it's guaranteed to be loaded before protoweaver
                    VelocityAuth.setSecret(FabricProxyLite.config.getSecret().getBytes(StandardCharsets.UTF_8));
        }));
    }
}

package me.mrnavastar.protoweaver.loader;


import me.mrnavastar.protoweaver.api.protocol.velocity.VelocityAuth;
import me.mrnavastar.protoweaver.core.util.ProtoConstants;
import me.mrnavastar.protoweaver.core.util.ProtoLogger;
import me.mrnavastar.protoweaver.server.netty.ProtoInitializer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLConfig;
import org.adde0109.pcf.PCF;

import java.nio.charset.StandardCharsets;

@Mod(ProtoConstants.PROTOWEAVER_ID)
public class NeoForge {

    public NeoForge() {
        ProtoLogger.setLogger(new Logger());
        ProtoInitializer.initialize(FMLConfig.defaultConfigPath() + "/protoweaver/keys", () -> {
            try {
                Class.forName("org.adde0109.pcf.PFC");
                VelocityAuth.setSecret(PCF.instance().forwarding().secret().getBytes(StandardCharsets.UTF_8));
            } catch (Exception ignore) {}
        });
    }
}
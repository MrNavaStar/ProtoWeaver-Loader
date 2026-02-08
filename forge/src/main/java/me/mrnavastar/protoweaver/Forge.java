package me.mrnavastar.protoweaver;

import me.mrnavastar.protoweaver.api.protocol.velocity.VelocityAuth;
import me.mrnavastar.protoweaver.core.util.ProtoConstants;
import me.mrnavastar.protoweaver.core.util.ProtoLogger;
import me.mrnavastar.protoweaver.server.netty.ProtoInitializer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLConfig;
import org.adde0109.pcf.PCF;

import java.nio.charset.StandardCharsets;

@Mod(ProtoConstants.PROTOWEAVER_ID)
public class Forge {

    public Forge() {
        ProtoLogger.setLogger(new Logger());
        ProtoInitializer.initialize(FMLConfig.defaultConfigPath() + "/protoweaver/keys", () -> {
            try {
                Class.forName("org.adde0109.pcf.PFC");
                VelocityAuth.setSecret(PCF.instance().forwarding().secret().getBytes(StandardCharsets.UTF_8));
            } catch (Exception ignore) {}
        });
    }
}
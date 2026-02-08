package me.mrnavastar.protoweaver.loader;

import me.mrnavastar.protoweaver.core.util.ProtoConstants;
import me.mrnavastar.protoweaver.core.util.ProtoLogger;
import org.slf4j.LoggerFactory;

public class Logger implements ProtoLogger.IProtoLogger {

	public static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ProtoConstants.PROTOWEAVER_ID);

    @Override
    public void info(String message) {
        LOG.info(message);
    }

    @Override
    public void warn(String message) {
        LOG.warn(message);
    }

    @Override
    public void err(String message) {
        LOG.error(message);
    }
}
package dev.katkak.namelessmod;

import dev.katkak.namelessmod.item.NamelessModItems;
import dev.katkak.namelessmod.platform.Services;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NamelessModCommon {
    public static final String MOD_NAME = "Nameless Mod";
    public static final String MOD_ID = "namelessmod";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    public static void init() {
        LOGGER.info("Hello from Common init on {}! we are currently in a {} environment!", Services.PLATFORM.getPlatformName(), Services.PLATFORM.getEnvironmentName());

        NamelessModItems.register();
    }

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}

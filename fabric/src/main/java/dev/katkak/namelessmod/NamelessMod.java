package dev.katkak.namelessmod;

import net.fabricmc.api.ModInitializer;

public class NamelessMod implements ModInitializer {
    @Override
    public void onInitialize() {
        NamelessModCommon.LOGGER.info("Hello Fabric world!");

        NamelessModCommon.init();
    }
}

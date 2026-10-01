package dev.katkak.namelessmod;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(NamelessModCommon.MOD_ID)
public class NamelessMod {
    public NamelessMod(IEventBus eventBus) {
        NamelessModCommon.LOGGER.info("Hello NeoForge world!");

        NamelessModCommon.init();
    }
}

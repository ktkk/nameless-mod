package dev.katkak.namelessmod;

import dev.katkak.namelessmod.datagen.NamelessModModelProvider;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = NamelessModCommon.MOD_ID)
public class NamelessModDataGen {
    @SubscribeEvent
    public static void gatherClientData(GatherDataEvent.Client event) {
        final var generator = event.getGenerator();

        final var packOutput = generator.getPackOutput();
        final var lookupProvider = event.getWorldLookupProvider();

        generator.addProvider(true, new NamelessModModelProvider(packOutput));
    }
}

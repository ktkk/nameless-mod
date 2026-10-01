package dev.katkak.namelessmod.item;

import dev.katkak.namelessmod.NamelessModCommon;
import dev.katkak.namelessmod.registry.RegistryObject;
import dev.katkak.namelessmod.registry.RegistryProvider;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;

import java.util.function.Function;

public class NamelessModItems {
    private static final RegistryProvider<Item> ITEMS = RegistryProvider.get(Registries.ITEM, NamelessModCommon.MOD_ID);

    public static void register() {
    }

    public static final RegistryObject<Item> SUSPICIOUS_SUBSTANCE = register("suspicious_substance", Item::new);

    private static RegistryObject<Item> register(String path, Function<Item.Properties, Item> itemFactory) {
        final var properties = new Item.Properties();
        return ITEMS.register(path, (resourceKey) -> itemFactory.apply(properties.setId(resourceKey)));
    }
}

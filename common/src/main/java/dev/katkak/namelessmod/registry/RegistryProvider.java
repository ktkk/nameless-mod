package dev.katkak.namelessmod.registry;

import dev.katkak.namelessmod.platform.Services;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

import java.util.function.Function;

public interface RegistryProvider<T> {
    static <T> RegistryProvider<T> get(ResourceKey<? extends Registry<T>> resourceKey, String modId) {
        return Services.REGISTRY_FACTORY.create(resourceKey, modId);
    }

    <I extends T> RegistryObject<I> register(String path, Function<ResourceKey<I>, ? extends I> function);

    String modId();
}

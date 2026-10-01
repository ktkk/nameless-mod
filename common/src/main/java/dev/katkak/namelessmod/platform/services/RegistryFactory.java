package dev.katkak.namelessmod.platform.services;

import dev.katkak.namelessmod.registry.RegistryProvider;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public interface RegistryFactory {
    <T> RegistryProvider<T> create(ResourceKey<? extends Registry<T>> resourceKey, String modId);
}

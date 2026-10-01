package dev.katkak.namelessmod.platform;

import com.google.auto.service.AutoService;
import dev.katkak.namelessmod.NamelessModCommon;
import dev.katkak.namelessmod.platform.services.RegistryFactory;
import dev.katkak.namelessmod.registry.RegistryObject;
import dev.katkak.namelessmod.registry.RegistryProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;

import java.util.function.Function;

@AutoService(RegistryFactory.class)
public class FabricRegistryFactory implements RegistryFactory {
    @Override
    public <T> RegistryProvider<T> create(ResourceKey<? extends Registry<T>> resourceKey, String modId) {
        return new Provider<>(resourceKey, modId);
    }

    private record Provider<T>(Registry<T> registry, String modId) implements RegistryProvider<T> {
        @SuppressWarnings("unchecked")
        private Provider(ResourceKey<? extends Registry<T>> resourceKey, String modId) {
            this((Registry<T>) BuiltInRegistries.REGISTRY.getValue(resourceKey.identifier()), modId);
        }

        @SuppressWarnings("unchecked")
        @Override
        public <I extends T> RegistryObject<I> register(String path, Function<ResourceKey<I>, ? extends I> function) {
            final var id = NamelessModCommon.id(path);
            final var resourceKey = ResourceKey.create((ResourceKey<? extends Registry<I>>) this.registry.key(), id);
            final var result = Registry.register(this.registry, id, function.apply(resourceKey));
            return new RegistryObject<>() {
                @Override
                public ResourceKey<I> getResourceKey() {
                    return resourceKey;
                }

                @Override
                public Identifier getId() {
                    return id;
                }

                @Override
                public I get() {
                    return result;
                }

                @Override
                public Holder<I> asHolder() {
                    return (Holder<I>) registry.getOrThrow((ResourceKey<T>) resourceKey);
                }
            };
        }
    }
}

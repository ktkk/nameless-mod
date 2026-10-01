package dev.katkak.namelessmod.platform;

import com.google.auto.service.AutoService;
import dev.katkak.namelessmod.platform.services.RegistryFactory;
import dev.katkak.namelessmod.registry.RegistryObject;
import dev.katkak.namelessmod.registry.RegistryProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.neoforged.fml.ModList;
import net.neoforged.fml.javafmlmod.FMLModContainer;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Function;

@AutoService(RegistryFactory.class)
public class NeoforgeRegistryFactory implements RegistryFactory {
    @Override
    public <T> RegistryProvider<T> create(ResourceKey<? extends Registry<T>> resourceKey, String modId) {
        final var container = ModList.get().getModContainerById(modId).orElseThrow(() -> new NoSuchElementException("Cannot find mod container for '%s'".formatted(modId)));
        if (!(container instanceof FMLModContainer fmlModContainer)) {
            throw new ClassCastException("The container for '%s' is not an FML mod container".formatted(modId));
        }
        final var registry = DeferredRegister.create(resourceKey, modId);
        registry.register(Objects.requireNonNull(fmlModContainer.getEventBus()));
        return new Provider<>(registry, modId);
    }

    private record Provider<T>(DeferredRegister<T> registry, String modId) implements RegistryProvider<T> {
        @SuppressWarnings("unchecked")
        @Override
        public <I extends T> RegistryObject<I> register(String path, Function<ResourceKey<I>, ? extends I> function) {
            final var result = this.registry.register(path, (id) -> {
                final var resourceKey = ResourceKey.create((ResourceKey<? extends Registry<I>>) this.registry.getRegistryKey(), id);
                return function.apply(resourceKey);
            });
            return new RegistryObject<>() {
                @SuppressWarnings("unchecked")
                @Override
                public ResourceKey<I> getResourceKey() {
                    return (ResourceKey<I>) result.getKey();
                }

                @Override
                public Identifier getId() {
                    return result.getId();
                }

                @Override
                public I get() {
                    return result.get();
                }

                @SuppressWarnings("unchecked")
                @Override
                public Holder<I> asHolder() {
                    return (Holder<I>) result;
                }
            };
        }
    }
}

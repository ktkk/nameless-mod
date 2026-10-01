package dev.katkak.namelessmod.platform;

import dev.katkak.namelessmod.NamelessModCommon;
import dev.katkak.namelessmod.platform.services.PlatformHelper;
import dev.katkak.namelessmod.platform.services.RegistryFactory;

import java.util.ServiceLoader;

public class Services {
    public static final PlatformHelper PLATFORM = load(PlatformHelper.class);
    public static final RegistryFactory REGISTRY_FACTORY = load(RegistryFactory.class);

    public static <T> T load(Class<T> clazz) {
        final T loadedService = ServiceLoader.load(clazz, Services.class.getClassLoader())
            .findFirst()
            .orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
        NamelessModCommon.LOGGER.debug("Loaded {} for service {}", loadedService, clazz);
        return loadedService;
    }
}

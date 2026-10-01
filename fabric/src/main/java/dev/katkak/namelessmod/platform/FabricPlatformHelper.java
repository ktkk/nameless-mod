package dev.katkak.namelessmod.platform;

import net.fabricmc.loader.api.FabricLoader;
import com.google.auto.service.AutoService;
import dev.katkak.namelessmod.platform.services.PlatformHelper;

@AutoService(PlatformHelper.class)
public class FabricPlatformHelper implements PlatformHelper {
    @Override
    public String getPlatformName() {
        return "Fabric";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }
}

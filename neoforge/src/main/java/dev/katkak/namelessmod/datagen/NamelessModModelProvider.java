package dev.katkak.namelessmod.datagen;

import dev.katkak.namelessmod.NamelessModCommon;
import dev.katkak.namelessmod.item.NamelessModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;

public class NamelessModModelProvider extends ModelProvider {
    public NamelessModModelProvider(PackOutput output) {
        super(output, NamelessModCommon.MOD_ID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        itemModels.generateFlatItem(NamelessModItems.SUSPICIOUS_SUBSTANCE.get(), ModelTemplates.FLAT_ITEM);
    }
}

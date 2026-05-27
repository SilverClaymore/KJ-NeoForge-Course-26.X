package net.silverclaymore.mccourse.datagen;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;
import net.silverclaymore.mccourse.MCCourse;
import net.silverclaymore.mccourse.block.ModBlocks;
import net.silverclaymore.mccourse.item.ModItems;
import org.jspecify.annotations.NonNull;

public class ModModelProvider extends ModelProvider {
    public ModModelProvider(PackOutput output) {
        super(output, MCCourse.MOD_ID);
    }

    @Override
    protected void registerModels(@NonNull BlockModelGenerators blockModels, ItemModelGenerators itemModels) {

        /* ITEMS */
        itemModels.generateFlatItem(ModItems.ZIRCON.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.RAW_ZIRCON.get(), ModelTemplates.FLAT_ITEM);

        /* BLOCKS */
        blockModels.createTrivialCube(ModBlocks.ZIRCON_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.RAW_ZIRCON_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.ZIRCON_ORE.get());
        blockModels.createTrivialCube(ModBlocks.ZIRCON_DEEPSLATE_ORE.get());
        blockModels.createTrivialCube(ModBlocks.ZIRCON_END_ORE.get());
        blockModels.createTrivialCube(ModBlocks.ZIRCON_NETHER_ORE.get());
    }
}

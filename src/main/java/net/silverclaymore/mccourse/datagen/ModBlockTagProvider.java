package net.silverclaymore.mccourse.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.WallBlock;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.silverclaymore.mccourse.MCCourse;
import net.silverclaymore.mccourse.block.ModBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, MCCourse.MOD_ID);
    }

    protected void addCommonMineableTags() {
        BLOCK_GROUPS.forEach((tag, blocks) ->
                blocks.forEach(block -> {
                    addCommonTag(tag, block);

                    /* // section not implemented yet
                    if (block.get() instanceof FenceBlock)
                        this.tag(BlockTags.FENCES).add(block.get());

                    if (block.get() instanceof FenceGateBlock)
                        this.tag(BlockTags.FENCE_GATES).add(block.get());

                    if (block.get() instanceof WallBlock)
                        this.tag(BlockTags.WALLS).add(block.get());
                     */
                })
        );
    }

    protected void addCommonTag(TagKey<Block> mineableWith, DeferredBlock<Block> block){
        if (block != null) this.tag(mineableWith).add(block.get());
    }

    private static final Map<TagKey<Block>, List<DeferredBlock<Block>>> BLOCK_GROUPS = Map.of(
            BlockTags.MINEABLE_WITH_PICKAXE, List.of(
                    // Zircon
                    ModBlocks.ZIRCON_BLOCK, ModBlocks.RAW_ZIRCON_BLOCK, ModBlocks.ZIRCON_ORE, ModBlocks.ZIRCON_DEEPSLATE_ORE, ModBlocks.ZIRCON_END_ORE, ModBlocks.ZIRCON_NETHER_ORE
            )
            /*, // Wood section not ready yet. i keep the minimum commented for the future
            BlockTags.MINEABLE_WITH_AXE, List.of(
                    // Ebony Wood
                    ModBlocks.EBONY_LOG, ModBlocks.EBONY_PLANKS, ModBlocks.EBONY_WOOD, ModBlocks.STRIPPED_EBONY_LOG, ModBlocks.STRIPPED_EBONY_WOOD
            )*/
    );

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        addCommonMineableTags();

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.ZIRCON_DEEPSLATE_ORE.get());

        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.ZIRCON_END_ORE.get());
    }
}
package net.silverclaymore.mccourse.datagen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.silverclaymore.mccourse.block.ModBlocks;
import net.silverclaymore.mccourse.item.ModItems;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    public ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    private static final Map<String, DeferredBlock<?>[]> DROPSELF_BLOCK_GROUPS = Map.of(
            "zircon", new DeferredBlock<?>[]{
                    ModBlocks.ZIRCON_BLOCK, ModBlocks.RAW_ZIRCON_BLOCK
            }
    );

    private static final Map<DeferredItem<Item>, DeferredBlock<?>[]> ORE_BLOCKS = Map.of(
            ModItems.RAW_ZIRCON, new DeferredBlock<?>[]{ModBlocks.ZIRCON_ORE, ModBlocks.ZIRCON_DEEPSLATE_ORE, ModBlocks.ZIRCON_END_ORE, ModBlocks.ZIRCON_NETHER_ORE}
    );

    private static final Map<Integer, int[]> ORE_DROP_RANGES = Map.of(
            0, new int[]{1, 1},  // Standard ore drop
            1, new int[]{2, 5},  // Deepslate
            2, new int[]{4, 7},  // End
            3, new int[]{5, 9}   // Nether
    );

    protected void addOreDrops() {
        ORE_BLOCKS.forEach((item, ores) -> {
            for (int i = 0; i < ores.length; i++) {
                if (ores[i] != null) {
                    final DeferredBlock<?> oreBlock = ores[i];
                    final boolean isPrimaryOre = (i == 0); // First element is the standard ore

                    int[] dropValues = ORE_DROP_RANGES.getOrDefault(i, new int[]{1, 1});
                    final int minDropAmount = dropValues[0];
                    final int maxDropAmount = dropValues[1];

                    this.add(oreBlock.get(), block ->
                            isPrimaryOre
                                    ? createOreDrop(oreBlock.get(), item.get())
                                    : createMultipleOreDrops(oreBlock.get(), item.get(), minDropAmount, maxDropAmount)
                    );
                }
            }
        });
    }

    protected void commonDropSelf() {
        DROPSELF_BLOCK_GROUPS.forEach((groupName, blocks) -> {
            for (DeferredBlock<?> block : blocks) {
                this.dropSelf(block.get()); // Standard drops-self function
            }
        });
    }

    protected LootTable.Builder createMultipleOreDrops(Block block, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(block, this.applyExplosionDecay(
                block, LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                        .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))));
    }

    @Override
    protected void generate() {
        addOreDrops(); // Ores defined in ORE_BLOCKS
        commonDropSelf();
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
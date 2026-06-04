package net.silverclaymore.mccourse.block;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.silverclaymore.mccourse.MCCourse;
import net.silverclaymore.mccourse.block.custom.MagicBlock;
import net.silverclaymore.mccourse.item.ModItems;


import java.util.function.Function;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MCCourse.MOD_ID);

    public static final DeferredBlock<Block> ZIRCON_BLOCK = registerBlock("zircon_block",
            properties -> new Block(properties.strength(4.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)));

    public static final DeferredBlock<Block> RAW_ZIRCON_BLOCK = registerBlock("raw_zircon_block",
            properties -> new Block(properties.strength(3.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)));

    public static final DeferredBlock<Block> ZIRCON_ORE = registerBlock("zircon_ore",
            properties -> new DropExperienceBlock(UniformInt.of(2,4),
                    properties.strength(3.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ZIRCON_DEEPSLATE_ORE = registerBlock("zircon_deepslate_ore",
            properties -> new DropExperienceBlock(UniformInt.of(4,6),
                    properties.strength(3.0F)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ZIRCON_END_ORE = registerBlock("zircon_end_ore",
            properties -> new DropExperienceBlock(UniformInt.of(2,4),
                    properties.strength(3.0F)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ZIRCON_NETHER_ORE = registerBlock("zircon_nether_ore",
            properties -> new DropExperienceBlock(UniformInt.of(2,4),
                    properties.strength(3.0F)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> MAGIC_BLOCK = registerBlock("magic_block",
            properties -> new MagicBlock(
                    properties.strength(2.0F)
                            .noLootTable()
                            .sound(SoundType.AMETHYST)));


    private static <T extends Block>DeferredBlock<T> registerBlock(String name, Function<BlockBehaviour.Properties, T> function) {
        DeferredBlock<T> toReturn = BLOCKS.registerBlock(name, function);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.registerItem(name, properties ->  new BlockItem(block.get(), properties.useBlockDescriptionPrefix()));
    }

    public static void register (IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
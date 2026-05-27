package net.silverclaymore.mccourse.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.silverclaymore.mccourse.MCCourse;
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
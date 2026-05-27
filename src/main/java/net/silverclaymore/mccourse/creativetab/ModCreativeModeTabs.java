package net.silverclaymore.mccourse.creativetab;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.silverclaymore.mccourse.MCCourse;
import net.silverclaymore.mccourse.block.ModBlocks;
import net.silverclaymore.mccourse.item.ModItems;

import java.util.Map;

public class ModCreativeModeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MCCourse.MOD_ID);

    private static void addElements(CreativeModeTab.Output pOutput, DeferredItem<?>[] items) {
        for (DeferredItem<?> item : items) {
            pOutput.accept(item);
        }
    }

    private static void addElements(CreativeModeTab.Output pOutput, DeferredBlock<?>[] blocks) {
        for (DeferredBlock<?> block : blocks) {
            pOutput.accept(block);
        }
    }

    private static final Map<String, DeferredItem<?>[]> ITEM_GROUPS = Map.of(
            "zircon", new DeferredItem<?>[]{ //From Neoforge mccourse
                    ModItems.ZIRCON, ModItems.RAW_ZIRCON
            }
    );

    private static final Map<String, DeferredBlock<?>[]> BLOCK_GROUPS = Map.of(
            "zircon", new DeferredBlock<?>[]{
                    ModBlocks.ZIRCON_BLOCK, ModBlocks.RAW_ZIRCON_BLOCK
            }
    );


    public static void createCreativeTab(String tabKeyStart, DeferredItem<?> item ) {
        CREATIVE_MODE_TABS.register(tabKeyStart + "_items_tab", () -> CreativeModeTab.builder()
                .title(Component.translatable("creativetab.mccourse." + tabKeyStart + "_items"))
                .icon(() -> new ItemStack(item.get()))
                .displayItems((parameters, output) -> addElements(output, ITEM_GROUPS.get(tabKeyStart)))
                //.withTabsBefore(ResourceLocation.fromNamespaceAndPath(MCCourseMod.MOD_ID, tabKeyStart + "_items_tab"))
                .build());
    }

    public static void createCreativeTab(String tabKeyStart, DeferredBlock<?> block ) {
        CREATIVE_MODE_TABS.register(tabKeyStart + "_blocks_tab", () -> CreativeModeTab.builder()
                .title(Component.translatable("creativetab.mccourse." + tabKeyStart + "_blocks"))
                .icon(() -> new ItemStack(block.get()))
                .displayItems((parameters, output) -> addElements(output, BLOCK_GROUPS.get(tabKeyStart)))
                //.withTabsBefore(ResourceLocation.fromNamespaceAndPath(MCCourse.MOD_ID, tabKeyStart + "_items_tab"))
                .build());
    }


    public static void register(IEventBus eventBus){
        // Register item-based creative tabs
        createCreativeTab("zircon", ModItems.ZIRCON);
//        createCreativeTab("black_opal", ModItems.BLACK_OPAL);
//        createCreativeTab("bismuth", ModItems.BISMUTH);
//        createCreativeTab("alexandrite", ModItems.ALEXANDRITE);
//        createCreativeTab("pink_garnet", ModItems.PINK_GARNET);

        // Register block-based creative tabs
        createCreativeTab("zircon", ModBlocks.ZIRCON_BLOCK);
//        createCreativeTab("bismuth", ModBlocks.BISMUTH_BLOCK);
//        createCreativeTab("black_opal", ModBlocks.BLACK_OPAL_BLOCK);
//        createCreativeTab("pink_garnet", ModBlocks.PINK_GARNET_BLOCK);
//        createCreativeTab("alexandrite", ModBlocks.ALEXANDRITE_BLOCK);

        CREATIVE_MODE_TABS.register(eventBus);
    }
}

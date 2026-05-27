package net.silverclaymore.mccourse;

import net.silverclaymore.mccourse.block.ModBlocks;
import net.silverclaymore.mccourse.creativetab.ModCreativeModeTabs;
import net.silverclaymore.mccourse.item.ModItems;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(MCCourse.MOD_ID)
public class MCCourse {
    public static final String MOD_ID = "mccourse";
    public static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "mccourse" namespace
    //public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MOD_ID);
    // Create a Deferred Register to hold Items which will all be registered under the "mccourse" namespace
    //public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MOD_ID);
    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "mccourse" namespace
    //public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);

    // Creates a new Block with the id "mccourse:example_block", combining the namespace and path
    //public static final DeferredBlock<Block> EXAMPLE_BLOCK = BLOCKS.registerSimpleBlock("example_block", p -> p.mapColor(MapColor.STONE));
    // Creates a new BlockItem with the id "mccourse:example_block", combining the namespace and path
    //public static final DeferredItem<BlockItem> EXAMPLE_BLOCK_ITEM = ITEMS.registerSimpleBlockItem("example_block", EXAMPLE_BLOCK);


    // Creates a creative tab with the id "mccourse:example_tab" for the example item, that is placed after the combat tab
    /*public static final DeferredHolder<CreativeModeTab, CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("example_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.mccourse")) //The language key for the title of your CreativeModeTab
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> EXAMPLE_ITEM.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(EXAMPLE_ITEM.get()); // Add the example item to the tab. For your own tabs, this method is preferred over the event
            }).build());
    */
    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public MCCourse(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);


        // Register the Deferred Register to the mod event bus so blocks get registered
        //BLOCKS.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModCreativeModeTabs.register(modEventBus);


        NeoForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
//        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
//            event.accept(EXAMPLE_BLOCK_ITEM);
//        }
        if(event.getTabKey()== CreativeModeTabs.INGREDIENTS){
            event.accept(ModItems.ZIRCON);
            event.accept(ModItems.RAW_ZIRCON);
            //event.accept(ModItems.BLACK_OPAL);
            //event.accept(ModItems.RAW_BLACK_OPAL);
            //event.accept(ModItems.BISMUTH);
            //event.accept(ModItems.RAW_BISMUTH);
            //event.accept(ModItems.ALEXANDRITE);
            //event.accept(ModItems.RAW_ALEXANDRITE);
        }

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        //LOGGER.info("HELLO from server starting");
    }
}
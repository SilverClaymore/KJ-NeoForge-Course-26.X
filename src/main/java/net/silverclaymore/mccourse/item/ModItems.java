package net.silverclaymore.mccourse.item;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.silverclaymore.mccourse.MCCourse;
import net.silverclaymore.mccourse.item.custom.ChiselItem;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MCCourse.MOD_ID);

    public static final DeferredItem<Item> ZIRCON = ITEMS.registerSimpleItem("zircon",
            properties -> properties);

    public static final DeferredItem<Item> RAW_ZIRCON = ITEMS.registerSimpleItem("raw_zircon",
            properties -> properties);

    public static final DeferredItem<Item> CHISEL = ITEMS.registerItem("chisel",
            properties -> new ChiselItem(properties.durability(32)));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
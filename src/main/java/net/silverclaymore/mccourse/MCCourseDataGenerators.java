package net.silverclaymore.mccourse;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.silverclaymore.mccourse.datagen.ModModelProvider;

@EventBusSubscriber(modid = MCCourse.MOD_ID) //, bus = EventBusSubscriber.Bus.MOD

public class MCCourseDataGenerators {
    @SubscribeEvent
    public static void gatherClientData(GatherDataEvent.Client event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();


        generator.addProvider(true, new ModModelProvider(packOutput));
    }
}
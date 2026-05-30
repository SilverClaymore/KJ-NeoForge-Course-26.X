package net.silverclaymore.mccourse.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.silverclaymore.mccourse.MCCourse;
import net.silverclaymore.mccourse.block.ModBlocks;
import net.silverclaymore.mccourse.item.ModItems;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }


    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries) {
            super(packOutput, registries);
        }

        @Override
        protected @NonNull RecipeProvider createRecipeProvider(HolderLookup.@NonNull Provider registries, @NonNull RecipeOutput output) {
            return new ModRecipeProvider(registries, output);
        }

        @Override
        public @NonNull String getName() {
            return "MCCourse Recipes";
        }
    }

    protected void oreSmeltAndBlast(List<ItemLike> smeltables, float experienceSmelt, float experienceBlast, int cookingTimeSmelt, int cookingTimeBlast, String group){
        oreSmelting(smeltables, RecipeCategory.MISC, CookingBookCategory.BLOCKS, ModItems.ZIRCON.get(), experienceSmelt, cookingTimeSmelt, group );
        oreBlasting(smeltables, RecipeCategory.MISC, CookingBookCategory.BLOCKS,ModItems.ZIRCON.get(), experienceBlast, cookingTimeBlast, group );
    }


    @Override
    protected void buildRecipes() {

        List<ItemLike> SMELTABLES_ZIRCON = List.of(ModItems.RAW_ZIRCON, ModBlocks.ZIRCON_ORE,
                ModBlocks.ZIRCON_DEEPSLATE_ORE, ModBlocks.ZIRCON_END_ORE, ModBlocks.ZIRCON_NETHER_ORE);
        oreSmeltAndBlast(SMELTABLES_ZIRCON,0.25f, 0.25f, 200, 100, "zircon");

        recipeBlockFromItem9(ModItems.ZIRCON, ModBlocks.ZIRCON_BLOCK);
        recipeItemsFromBlock(ModBlocks.ZIRCON_BLOCK, ModItems.ZIRCON, 9);


    }

    protected void recipeBlockFromItem9(DeferredItem<Item> source, DeferredBlock<Block> result)
    {
        shaped(RecipeCategory.BUILDING_BLOCKS, result.get())
                .pattern("XXX")
                .pattern("XXX")
                .pattern("XXX")
                .define('X', source.get())
                .unlockedBy(getHasName(result.get()), has(result.get()))
                .save(output);
    }
    protected void recipeItemsFromBlock(DeferredBlock<Block> source, DeferredItem<Item> result, int resultCount)
    {
        shapeless(RecipeCategory.MISC, result.get(), resultCount)
                .requires(source.get())
                .unlockedBy(getHasName(source.get()), has(source.get()))
                //.save(output);
                .save(output, MCCourse.MOD_ID + ":" + getItemName(result.get()) + "_from_" + getItemName(source.get()));
                //.save(output, MCCourse.MOD_ID + ":" + result.getId().getPath() + "_from_" + source.getId().getPath());
    }


    @Override
    protected <T extends AbstractCookingRecipe> void oreCooking(
            AbstractCookingRecipe.Factory<T> factory,
            List<ItemLike> smeltables,
            RecipeCategory craftingCategory,
            CookingBookCategory cookingCategory,
            ItemLike result,
            float experience,
            int cookingTime,
            String group,
            String fromDesc
    ) {
        for (ItemLike item : smeltables) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(item), craftingCategory, cookingCategory, result, experience, cookingTime, factory)
                    .group(group)
                    .unlockedBy(getHasName(item), this.has(item))
                    .save(this.output, MCCourse.MOD_ID + ":" + getItemName(result) + fromDesc + "_" + getItemName(item));
        }
    }
}
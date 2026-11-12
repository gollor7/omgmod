package net.cramnoy.omgmod.datagen;

import net.cramnoy.omgmod.block.ModBlocks;
import net.cramnoy.omgmod.items.ModItems;
import net.cramnoy.omgmod.omgmod;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.SmokingRecipe;
import net.minecraft.world.level.ItemLike;

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
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput outptut) {
            return new ModRecipeProvider(registries, outptut);
        }

        @Override
        public String getName() {
            return "OmgMod Recipes";
        }
    }

    @Override
    protected void buildRecipes() {

        shaped(RecipeCategory.MISC, ModBlocks.SILVER_BLOCK.get())
                .pattern("III")
                .pattern("III")
                .pattern("III")
                .define('I', ModItems.SILVER_INGOT.get())
                .unlockedBy(getHasName(ModItems.SILVER_INGOT.get()), has(ModItems.SILVER_INGOT))
                .group("silver")
                .save(output);

        shaped(RecipeCategory.MISC, ModBlocks.RAW_SILVER_BLOCK.get())
                .pattern("RRR")
                .pattern("RRR")
                .pattern("RRR")
                .define('R', ModItems.RAW_SILVER.get())
                .unlockedBy(getHasName(ModItems.RAW_SILVER.get()), has(ModItems.RAW_SILVER))
                .group("silver")
                .save(output);

        shaped(RecipeCategory.MISC, ModItems.SILVER_INGOT.get())
                .pattern("NNN")
                .pattern("NNN")
                .pattern("NNN")
                .define('N', ModItems.SILVER_NUGGET.get())
                .unlockedBy(getHasName(ModItems.SILVER_NUGGET.get()), has(ModItems.SILVER_NUGGET))
                .group("silver")
                .save(output, omgmod.MOD_ID + ":silver_ingot_from_silver_nugget");

        shaped(RecipeCategory.FOOD, ModItems.ONION_PIE.get())
                .pattern("OOO")
                .pattern("WMW")
                .pattern("ESE")
                .define('O', ModItems.ONION.get())
                .define('W', Items.WHEAT)
                .define('M', Items.MILK_BUCKET)
                .define('E', Items.EGG)
                .define('S', Items.SUGAR)
                .unlockedBy(getHasName(ModItems.ONION.get()), has(ModItems.ONION))
                .group("onion")
                .save(output);

        shaped(RecipeCategory.FOOD, ModBlocks.CARROT_CAKE)
                .pattern("SCS")
                .pattern("RCR")
                .pattern("MCS")
                .define('S', Items.SUGAR)
                .define('C', ModItems.CRUST.get())
                .define('M', Items.MILK_BUCKET)
                .define('R', Items.CARROT)
                .unlockedBy(getHasName(Items.CARROT), has(Items.CARROT))
                .group("carrot")
                .save(output);

        shaped(RecipeCategory.FOOD, ModItems.UNBAKED_CRUST.get(), 3)
                .pattern("SWS")
                .pattern("WMW")
                .pattern("EWE")
                .define('S', Items.SUGAR)
                .define('W', Items.WHEAT)
                .define('M', Items.MILK_BUCKET)
                .define('E', Items.EGG)
                .unlockedBy(getHasName(Items.WHEAT), has(Items.WHEAT))
                .group("wheat")
                .save(output);

        shapeless(RecipeCategory.MISC, ModItems.SILVER_INGOT.get(), 9)
                .requires(ModBlocks.SILVER_BLOCK)
                .unlockedBy(getHasName(ModBlocks.SILVER_BLOCK.get()), has(ModBlocks.SILVER_BLOCK))
                .group("silver")
                .save(output, omgmod.MOD_ID + ":silver_ingot_from_silver_block");

        shapeless(RecipeCategory.MISC, ModItems.SILVER_NUGGET.get(), 9)
                .requires(ModItems.SILVER_INGOT)
                .unlockedBy(getHasName(ModItems.SILVER_INGOT.get()), has(ModItems.SILVER_INGOT))
                .group("silver")
                .save(output);

        shapeless(RecipeCategory.MISC, ModItems.RAW_SILVER.get(), 9)
                .requires(ModBlocks.RAW_SILVER_BLOCK)
                .unlockedBy(getHasName(ModItems.RAW_SILVER.get()), has(ModItems.RAW_SILVER))
                .group("silver")
                .save(output);

        List<ItemLike> SILVER_SMELTABLES = List.of(ModItems.RAW_SILVER,
                ModBlocks.SILVER_ORE, ModBlocks.DEEPSLATE_SILVER_ORE, ModBlocks.END_SILVER_ORE, ModBlocks.NETHER_SILVER_ORE);



        oreSmelting(SILVER_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, ModItems.SILVER_INGOT.get(), 0.25f, 200, "silver");
        oreBlasting(SILVER_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, ModItems.SILVER_INGOT.get(), 0.25f, 100, "silver");
        oreSmelting(List.of(ModItems.UNBAKED_CRUST), RecipeCategory.FOOD, CookingBookCategory.FOOD,
                ModItems.CRUST.get(), 0.1f, 200, "crust");

        oreCooking(SmokingRecipe::new, List.of(ModItems.UNBAKED_CRUST), RecipeCategory.FOOD, CookingBookCategory.FOOD,
                ModItems.CRUST.get(), 0.1f, 100, "crust", "_from_smoking");

    }

    @Override
    protected <T extends AbstractCookingRecipe> void oreCooking(AbstractCookingRecipe.Factory<T> factory, List<ItemLike> smeltables,
                                                                RecipeCategory craftingCategory, CookingBookCategory cookingCategory, ItemLike result,
                                                                float experience, int cookingTime, String group, String fromDesc) {
        for(ItemLike itemlike : smeltables) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), craftingCategory, cookingCategory, result, experience, cookingTime, factory).group(group).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(output, omgmod.MOD_ID + ":" + getItemName(result) + fromDesc + "_" + getItemName(itemlike));
        }
    }
}

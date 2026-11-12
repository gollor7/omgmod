package net.cramnoy.omgmod.creativemodtab;

import net.cramnoy.omgmod.block.ModBlocks;
import net.cramnoy.omgmod.items.ModItems;
import net.cramnoy.omgmod.omgmod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, omgmod.MOD_ID);

    public static final Supplier<CreativeModeTab> SILVER_ITEMS_TAB = CREATIVE_MODE_TABS.register("silver_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.SILVER_INGOT.get()))
                    .title(Component.translatable("creativetab.omgmod.silver_items"))
                    .withTabsBefore(CreativeModeTabs.INGREDIENTS)
                    .withTabsAfter(Identifier.fromNamespaceAndPath(omgmod.MOD_ID, "silver_blocks_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.SILVER_INGOT);
                        output.accept(ModItems.RAW_SILVER);
                        output.accept(ModItems.SILVER_NUGGET);

                        output.accept(ModItems.METAL_DETECTOR);


                    }).build());


    public static final Supplier<CreativeModeTab> SILVER_BLOCKS_TAB = CREATIVE_MODE_TABS.register("silver_blocks_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.SILVER_BLOCK.get()))
                    .title(Component.translatable("creativetab.omgmod.silver_blocks"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.SILVER_BLOCK);
                        output.accept(ModBlocks.RAW_SILVER_BLOCK);
                        output.accept(ModBlocks.SILVER_ORE);
                        output.accept(ModBlocks.DEEPSLATE_SILVER_ORE);
                        output.accept(ModBlocks.NETHER_SILVER_ORE);
                        output.accept(ModBlocks.END_SILVER_ORE);
                        output.accept(ModBlocks.MAGIC_BLOCK);


                    }).build());

    public static final Supplier<CreativeModeTab> CUSTOM_FOOD = CREATIVE_MODE_TABS.register("custom_food_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.ONION.get()))
                    .title(Component.translatable("creativetab.omgmod.custom_food"))
                    .withTabsAfter(CreativeModeTabs.FOOD_AND_DRINKS)
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.ONION);
                        output.accept(ModItems.ONION_PIE);
                        output.accept(ModBlocks.CARROT_CAKE);
                        output.accept(ModItems.CRUST);
                        output.accept(ModItems.UNBAKED_CRUST);


                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}

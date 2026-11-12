package net.cramnoy.omgmod.items;

import net.cramnoy.omgmod.food.ModFoods;
import net.cramnoy.omgmod.items.custom.MetalDetectorItem;
import net.cramnoy.omgmod.omgmod;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Consumer;

import static net.cramnoy.omgmod.block.ModBlocks.CARROT_CAKE;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(omgmod.MOD_ID);


    public static final DeferredItem<Item> SILVER_INGOT = ITEMS.registerSimpleItem("silver_ingot");
    public static final DeferredItem<Item> RAW_SILVER = ITEMS.registerSimpleItem("raw_silver");
    public static final DeferredItem<Item> SILVER_NUGGET = ITEMS.registerSimpleItem("silver_nugget");
    public static final DeferredItem<Item> UNBAKED_CRUST = ITEMS.registerSimpleItem("unbaked_crust");

    public static final DeferredItem<Item> METAL_DETECTOR = ITEMS.registerItem("metal_detector",
            properties -> new MetalDetectorItem(properties.durability(64)));

    public static final DeferredItem<Item> ONION = ITEMS.registerItem("onion",
            properties -> new Item(properties.food(ModFoods.ONION, ModFoods.ONION_CONSUMABLE)){
                @Override
                public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
                    if(Minecraft.getInstance().hasShiftDown()) {
                        for(int i=1; i<=5; i++) {
                            builder.accept(Component.translatable("tooltip.omgmod.onion.shift_down_" + i));
                        }
                    } else {
                        builder.accept(Component.translatable("tooltip.omgmod.onion"));
                    }
                    super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
                }
            });

    public static final DeferredItem<Item> ONION_PIE = ITEMS.registerItem("onion_pie",
            properties -> new Item(properties.food(ModFoods.ONION_PIE, ModFoods.ONION_PIE_CONSUMABLE)){
                @Override
                public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
                    if(Minecraft.getInstance().hasShiftDown()) {
                        for(int i=1; i<=4; i++) {
                            builder.accept(Component.translatable("tooltip.omgmod.onion_pie.shift_down_" + i));
                        }
                    } else {
                        builder.accept(Component.translatable("tooltip.omgmod.onion_pie"));
                    }
                    super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
                }
            });

    public static final DeferredItem<Item> CRUST = ITEMS.registerItem("crust",
            properties -> new Item(properties.food(ModFoods.CRUST, ModFoods.CRUST_CONSUMABLE)){
                @Override
                public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
                    if(Minecraft.getInstance().hasShiftDown()) {
                        for(int i=1; i<=2; i++) {
                            builder.accept(Component.translatable("tooltip.omgmod.crust.shift_down_" + i));
                        }
                    } else {
                        builder.accept(Component.translatable("tooltip.omgmod.crust"));
                    }
                    super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
                }
            });

    public static final DeferredItem<BlockItem> CARROT_CAKE_ITEM = ModItems.ITEMS.registerItem("carrot_cake",
            properties -> new BlockItem(CARROT_CAKE.get(), properties.useBlockDescriptionPrefix().stacksTo(1)){
                @Override
                public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
                    if(Minecraft.getInstance().hasShiftDown()) {
                        for(int i=1; i<=5; i++) {
                            builder.accept(Component.translatable("tooltip.omgmod.carrot_cake.shift_down_" + i));
                        }
                    } else {
                        builder.accept(Component.translatable("tooltip.omgmod.carrot_cake"));
                    }
                    super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
                }
            });


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}

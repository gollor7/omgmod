package net.cramnoy.omgmod.food;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

public class ModFoods {
    public static final FoodProperties ONION = new FoodProperties.Builder().nutrition(1).saturationModifier(0.2f).build();

    public static final Consumable ONION_CONSUMABLE = Consumables.defaultFood()
            .consumeSeconds(2.5f)
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.NAUSEA, 400), 0.10f))
            .build();

    public static final FoodProperties ONION_PIE = new FoodProperties.Builder().nutrition(10).saturationModifier(0.6f).build();

    public static final Consumable ONION_PIE_CONSUMABLE = Consumables.defaultFood()
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 2000)))
            .build();

    public static final FoodProperties CRUST = new FoodProperties.Builder().nutrition(2).saturationModifier(0.3f).build();

    public static final Consumable CRUST_CONSUMABLE = Consumables.defaultFood()
            .build();
}


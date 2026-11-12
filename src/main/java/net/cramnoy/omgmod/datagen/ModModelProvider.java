package net.cramnoy.omgmod.datagen;

import net.cramnoy.omgmod.block.ModBlocks;
import net.cramnoy.omgmod.items.ModItems;
import net.cramnoy.omgmod.omgmod;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;

public class ModModelProvider extends ModelProvider {
    public ModModelProvider(PackOutput output) {
        super(output, omgmod.MOD_ID);
    }
    private static final ModelTemplate CAKE = ModelTemplates.create("cake",
            TextureSlot.PARTICLE, TextureSlot.BOTTOM, TextureSlot.TOP, TextureSlot.SIDE);

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        itemModels.generateFlatItem(ModItems.SILVER_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.RAW_SILVER.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.SILVER_NUGGET.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.ONION.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.ONION_PIE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.CRUST.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.UNBAKED_CRUST.get(), ModelTemplates.FLAT_ITEM);

        itemModels.generateFlatItem(ModItems.METAL_DETECTOR.get(), ModelTemplates.FLAT_ITEM);

        itemModels.generateFlatItem(ModBlocks.CARROT_CAKE.asItem(), ModelTemplates.FLAT_ITEM);
        createCarrotCake(blockModels);


        /* BLOCKS */
        blockModels.createTrivialCube(ModBlocks.SILVER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.RAW_SILVER_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.SILVER_ORE.get());
        blockModels.createTrivialCube(ModBlocks.DEEPSLATE_SILVER_ORE.get());
        blockModels.createTrivialCube(ModBlocks.NETHER_SILVER_ORE.get());
        blockModels.createTrivialCube(ModBlocks.END_SILVER_ORE.get());
        blockModels.createTrivialCube(ModBlocks.MAGIC_BLOCK.get());

    }

    private static void createCarrotCake(BlockModelGenerators blockModels) {
        Block cake = ModBlocks.CARROT_CAKE.get();
        TextureMapping t = new TextureMapping()
                .put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(cake, "_side"))
                .put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(cake, "_bottom"))
                .put(TextureSlot.TOP, TextureMapping.getBlockTexture(cake, "_top"))
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(cake, "_side"))
                .put(TextureSlot.INSIDE, TextureMapping.getBlockTexture(cake, "_inner"));

        blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(cake).with(
                PropertyDispatch.initial(BlockStateProperties.BITES)
                        .select(0, BlockModelGenerators.plainVariant(CAKE.create(cake, t, blockModels.modelOutput)))
                        .select(1, slice(1, cake, t, blockModels))
                        .select(2, slice(2, cake, t, blockModels))
                        .select(3, slice(3, cake, t, blockModels))
                        .select(4, slice(4, cake, t, blockModels))
                        .select(5, slice(5, cake, t, blockModels))
                        .select(6, slice(6, cake, t, blockModels))));
    }

    private static MultiVariant slice(int i, Block cake, TextureMapping t, BlockModelGenerators blockModels) {
        return BlockModelGenerators.plainVariant(
                ModelTemplates.create("cake_slice" + i,
                                TextureSlot.PARTICLE, TextureSlot.BOTTOM, TextureSlot.TOP, TextureSlot.SIDE, TextureSlot.INSIDE)
                        .createWithSuffix(cake, "_slice" + i, t, blockModels.modelOutput));
    }
}

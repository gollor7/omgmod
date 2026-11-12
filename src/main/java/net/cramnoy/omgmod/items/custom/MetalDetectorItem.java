package net.cramnoy.omgmod.items.custom;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Consumer;

public class MetalDetectorItem extends Item {
    public MetalDetectorItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos positionClicked = context.getClickedPos();
        Player player = context.getPlayer();

        if(!level.isClientSide()) {
            boolean foundBlock = false;

            for(int i = 0; i <= positionClicked.getY() + 64; i++) {
                BlockState blockstate = level.getBlockState(positionClicked.below(i));

                if(isValuableBlock(blockstate)) {
                    outputValuebleCoordinates(positionClicked.below(i), player, blockstate.getBlock());
                    foundBlock = true;

                    context.getItemInHand().hurtAndBreak(1, player, context.getHand());

                    level.playSound(null, positionClicked, SoundEvents.AMETHYST_BLOCK_CHIME,
                            SoundSource.BLOCKS, 1.5f, 1f);

                    spawnFoundParticle(level, positionClicked, blockstate);

                    break;


                }
            }

            if(!foundBlock) {
                outputNoValuableFound(player);
            }
        }


        return InteractionResult.SUCCESS;
    }

    private void spawnFoundParticle(Level level, BlockPos positionClicked, BlockState blockstate) {
        for(int i = 0; i < 20; i++) {
            ServerLevel serverLevel = (ServerLevel) level;

            serverLevel.sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, blockstate),
                    positionClicked.getX() + 0.5d, positionClicked.getY() + 1, positionClicked.getZ() + 0.5d, 1,
                    Math.cos(i * 10) * 0.15d, 0.15d, Math.sin(i * 18) * 0.15d, 0.1);
        }
    }

    private void outputNoValuableFound(Player player) {
        player.sendSystemMessage(Component.translatable("item.omgmod.metal_detector.no_valuebles"));
    }

    private void outputValuebleCoordinates(BlockPos position, Player player, Block block) {
        player.sendSystemMessage(Component.literal("Valuables Found ")
                .append(block.getName())
                .append(Component.literal(" at (" + position.getX() + ", " + position.getY() + ", "
                + position.getZ()+ ")")));
    }

    private boolean isValuableBlock(BlockState blockstate) {
        return blockstate.is(Blocks.IRON_ORE) || blockstate.is(Blocks.DEEPSLATE_IRON_ORE) ||
                blockstate.is(Blocks.DIAMOND_ORE) || blockstate.is(Blocks.DEEPSLATE_DIAMOND_ORE);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        if(Minecraft.getInstance().hasShiftDown()) {
            builder.accept(Component.translatable("tooltip.omgmod.metal_detector.shift_down"));
        } else {
            builder.accept(Component.translatable("tooltip.omgmod.metal_detector"));
        }
        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
    }
}

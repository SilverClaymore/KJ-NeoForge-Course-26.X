package net.silverclaymore.mccourse.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.silverclaymore.mccourse.item.ModItems;

public class MagicBlock extends Block {
    public MagicBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        level.playSound(player,pos, SoundEvents.AMETHYST_BLOCK_RESONATE, SoundSource.BLOCKS,2f,1f);
        return InteractionResult.SUCCESS;
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState onState, Entity entity) {
        // Player steps on --> Status Effect
        if (entity instanceof Player player && !player.hasEffect(MobEffects.SPEED)) {
            player.addEffect(new MobEffectInstance(MobEffects.SPEED, 400));
        }

        // Item thrown on --> Turns into Diamond
        if (entity instanceof ItemEntity itemEntity) {

            if (isValidItem(itemEntity.getItem())) {
                itemEntity.setItem(new ItemStack(Items.DIAMOND, itemEntity.getItem().getCount()));
                level.playSound(null, pos, SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.BLOCKS, 1f, 1f);

            }
        }
    }

    private boolean isValidItem(ItemStack item) {
        return item.is (ModItems.ZIRCON) || item.is(Items.RAW_IRON);
    }
}

package org.ajls.weirdgadgets.advanced;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public abstract class RandomizableContainerBlockEntityAdvanced extends RandomizableContainerBlockEntity {
    protected RandomizableContainerBlockEntityAdvanced(BlockEntityType<?> p_155629_, BlockPos p_155630_, BlockState p_155631_) {
        super(p_155629_, p_155630_, p_155631_);
    }
    public ResourceLocation getLootTable() {
        return super.lootTable;
    }
}

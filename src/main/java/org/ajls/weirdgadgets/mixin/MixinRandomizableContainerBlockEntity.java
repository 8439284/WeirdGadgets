package org.ajls.weirdgadgets.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootTable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;

//@Mixin(RandomizableContainerBlockEntity.class)
//public abstract class MixinRandomizableContainerBlockEntity extends BaseContainerBlockEntity {
//    @Shadow protected ResourceKey<LootTable> lootTable;
//
//    @Accessor("lootTable")
//    public abstract ResourceLocation getLootTable();
//
//    protected MixinRandomizableContainerBlockEntity(BlockEntityType<?> p_155076_, BlockPos p_155077_, BlockState p_155078_) {
//        super(p_155076_, p_155077_, p_155078_);
//    }
//
////    ResourceKey<LootTable> getLootTable();
//}

@Mixin(RandomizableContainerBlockEntity.class)
public interface MixinRandomizableContainerBlockEntity {

    @Accessor("lootTable")
    public abstract ResourceLocation getLootTable();

//    ResourceKey<LootTable> getLootTable();
}

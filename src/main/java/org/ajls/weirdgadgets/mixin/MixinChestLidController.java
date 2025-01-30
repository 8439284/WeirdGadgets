package org.ajls.weirdgadgets.mixin;

import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.entity.ChestLidController;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ChestLidController.class)
public interface MixinChestLidController {
    @Accessor
    float getOpenness();

    @Accessor("openness")
    public void setOpenness(float openness);

}

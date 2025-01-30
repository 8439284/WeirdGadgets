package org.ajls.weirdgadgets.utils;

import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.entity.ChestLidController;
import org.ajls.weirdgadgets.mixin.MixinChestBlockEntity;
import org.ajls.weirdgadgets.mixin.MixinChestLidController;

public class ChestBlockEntityUtils {
    public static float setOpenness(ChestBlockEntity chestBlockEntity, float openness) {
        MixinChestLidController mixinChestLidController = (MixinChestLidController) getChestLidController(chestBlockEntity);
        mixinChestLidController.setOpenness(openness);
        return mixinChestLidController.getOpenness();
    }
    public static ChestLidController getChestLidController(ChestBlockEntity chestBlockEntity) {
        return ((MixinChestBlockEntity) chestBlockEntity).getChestLidController();
    }
}

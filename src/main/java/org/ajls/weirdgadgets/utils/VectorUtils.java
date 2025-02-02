package org.ajls.weirdgadgets.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;

public class VectorUtils {
    public static Vec3 toVec3(BlockPos blockPos) {
        return new Vec3(blockPos.getX(), blockPos.getY(), blockPos.getZ());
    }
}

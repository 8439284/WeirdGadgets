package org.ajls.weirdgadgets.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.core.QuartPos;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.ajls.weirdgadgets.utils.VectorUtils;
import org.joml.Quaternionf;

import java.util.HashMap;

public class ContainerRenderer  {  //implements BlockEntityRenderer<BaseContainerBlockEntity>
//    private final BlockEntityRendererProvider.Context context;
    private ItemStack renderStack;
    public ContainerRenderer() {

    }
//    public ContainerRenderer(BlockEntityRendererProvider.Context context) {
//        this.context = context;
//    }  //BlockEntityRenderDispatcher dispatcher
    public static HashMap<BaseContainerBlockEntity, HashMap<ItemStack, Integer>> container_displayItems = new HashMap<>();
//    @Override  //nonstatic
    public static void render(BaseContainerBlockEntity blockEntity, PoseStack poseStack) {  //BaseContainerBlockEntity blockEntity, float PartialTick, PoseStack poseStack, MultiBufferSource multiBufferSource, int packedLight, int packedOverlay , MultiBufferSource multiBufferSource
        MultiBufferSource multiBufferSource = Minecraft.getInstance().renderBuffers().bufferSource();
//        renderFloatingBlock(blockEntity, poseStack, multiBufferSource, packedLight);
//        renderFloatingText(blockEntity, poseStack, multiBufferSource, packedLight);
        HashMap<ItemStack, Integer> itemStack_Count = container_displayItems.get(blockEntity);
        if (itemStack_Count == null) {return;}
        boolean hasAny = false;
        for (ItemStack itemStack : itemStack_Count.keySet()) {
            int count = itemStack_Count.get(itemStack);
            if (count > 0) { hasAny = true; break; }
        }
        if (!hasAny) {return;}
//        if (itemStackCount == null) {return;}
//        ItemStack itemStack = itemStackCount.getItemStack();
//        if (itemStack.isEmpty()) {return;}
//        int count = itemStackCount.getCount();
//        if (count <= 0) {return;}
//        if (renderStack.isEmpty()) {return;}
        Level level = blockEntity.getLevel();
        if (level == null) {return;}
        int index = 0;
        for (ItemStack itemStack : itemStack_Count.keySet()) {
            int count = itemStack_Count.get(itemStack);
            BlockPos blockPosAbove = blockEntity.getBlockPos().above();
            for (int i = 0; i < index; i++) {
                blockPosAbove = blockPosAbove.above();
            }
            int packedLight2 = LightTexture.pack(level.getBrightness(LightLayer.BLOCK, blockPosAbove), level.getBrightness(LightLayer.SKY, blockPosAbove));

            poseStack.pushPose();
            BlockPos blockPos = blockEntity.getBlockPos() ;
            Vec3 blockPosVec3 = VectorUtils.toVec3(blockPosAbove);
            blockPosVec3 = blockPosVec3.subtract( (Minecraft.getInstance().player.position()));
//            poseStack.translate(0.5, 1.3, 0.5);
            poseStack.translate(blockPosVec3.x, blockPosVec3.y, blockPosVec3.z);
            Minecraft.getInstance().getItemRenderer().renderStatic(  //context
                    itemStack,
                    ItemDisplayContext.FIXED,
                    packedLight2,
                    OverlayTexture.NO_OVERLAY,
                    poseStack,
                    multiBufferSource,
                    level,
                    0
            );

//            Font font = Minecraft.getInstance().font;  //context.getFont()
////            poseStack.translate();
//            font.drawInBatch(
//                    count + "",
//                    0,
//                    0,
//                    0xECECEC,
//                    false,
//                    poseStack.last().pose(),
//                    multiBufferSource,
//                    Font.DisplayMode.NORMAL,
//                    0,
//                    packedLight2
//            );
            poseStack.popPose();
            index++;
        }

    }


    private void renderFloatingText(BlockEntity chest, PoseStack poseStack, MultiBufferSource bufferSource, int light) {
        String text = "Floating Text!";
        double x = chest.getBlockPos().getX() + 0.5;
        double y = chest.getBlockPos().getY() + 1.5; // Adjust height
        double z = chest.getBlockPos().getZ() + 0.5;

        poseStack.pushPose();
        poseStack.translate(x, y, z);
        poseStack.mulPose(Axis.YP.rotationDegrees( - Minecraft.getInstance().player.getYRot()));  //180 - yaw
        poseStack.scale(0.01F, 0.01F, 0.01F); // Scale down for small text

        Font font = Minecraft.getInstance().font;
        int width = font.width(text);
        font.drawInBatch(text, -width / 2.0F, 0, 0xFFFFFF, false, poseStack.last().pose(), bufferSource, Font.DisplayMode.NORMAL, 0, light);

        poseStack.popPose();
    }

    private void renderFloatingItem(BlockEntity chest, PoseStack poseStack, MultiBufferSource bufferSource, int light) {
        ItemStack stack = new ItemStack(net.minecraft.world.item.Items.DIAMOND); // Example ItemStack

        double x = chest.getBlockPos().getX() + 0.5;
        double y = chest.getBlockPos().getY() + 1.0; // Adjust height
        double z = chest.getBlockPos().getZ() + 0.5;

        poseStack.pushPose();
        poseStack.translate(x, y, z);
        poseStack.scale(0.5F, 0.5F, 0.5F); // Adjust scale if needed
        Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemDisplayContext.FIXED, light, OverlayTexture.NO_OVERLAY, poseStack, bufferSource, chest.getLevel(), 0);
        poseStack.popPose();
    }

    private void renderFloatingBlock(BlockEntity chest, PoseStack poseStack, MultiBufferSource bufferSource, int light) {
        Minecraft mc = Minecraft.getInstance();
        BlockRenderDispatcher dispatcher = mc.getBlockRenderer();

        double x = chest.getBlockPos().getX() + 0.5;
        double y = chest.getBlockPos().getY() + 1.0; // Adjust height
        double z = chest.getBlockPos().getZ() + 0.5;

        poseStack.pushPose();
        poseStack.translate(x, y, z);
        poseStack.scale(0.75F, 0.75F, 0.75F); // Adjust scale if needed

        // Render a diamond block as an example
        BlockState blockState = Blocks.DIAMOND_BLOCK.defaultBlockState();
        dispatcher.renderSingleBlock(blockState, poseStack, bufferSource, light, 0);

        poseStack.popPose();
    }
}

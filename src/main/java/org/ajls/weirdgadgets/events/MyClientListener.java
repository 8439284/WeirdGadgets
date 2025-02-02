//package org.ajls.weirdgadgets.events;
//
//import com.mojang.blaze3d.vertex.PoseStack;
//import net.minecraft.client.Camera;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.gui.Font;
//import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
//import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
//import net.minecraft.core.BlockPos;
//import net.minecraft.world.entity.Entity;
//import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
//import net.minecraft.world.level.block.entity.BlockEntity;
//import net.minecraft.world.level.block.entity.BlockEntityType;
//import net.minecraft.world.level.block.entity.ChestBlockEntity;
//import net.minecraft.world.phys.Vec3;
//import net.minecraftforge.api.distmarker.Dist;
//import net.minecraftforge.client.event.EntityRenderersEvent;
//import net.minecraftforge.client.event.RenderLevelStageEvent;
//import net.minecraftforge.event.TickEvent;
//import net.minecraftforge.eventbus.api.SubscribeEvent;
//import net.minecraftforge.fml.common.Mod;
//import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
//import org.ajls.weirdgadgets.WeirdGadgets;
//import org.ajls.weirdgadgets.commands.SearchItemCommand;
//import org.ajls.weirdgadgets.renderer.ContainerRenderer;
//import org.joml.Matrix4f;
//
//@Mod.EventBusSubscriber(modid = WeirdGadgets.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)  //Bus.MOD
//public class MyClientListener {
//    @SubscribeEvent
//    public static void onClientSetup(final FMLClientSetupEvent event) {
////        BlockEntityRenderers.register(BlockEntityType.CHEST, ContainerRenderer::new);
//    }
//
////    @SubscribeEvent
////    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
////        event.registerBlockEntityRenderer(BlockEntityType.CHEST, ContainerRenderer::new);
////    }
//
//    @SubscribeEvent
//    public static void onRenderLevelStage(RenderLevelStageEvent event) {
//        Minecraft mc = Minecraft.getInstance();
//        PoseStack poseStack = event.getPoseStack();
//        Camera camera = mc.gameRenderer.getMainCamera();
//        Vec3 cameraPos = camera.getPosition();
//
//        if (mc.level == null) return;
//        ContainerRenderer containerRenderer = new ContainerRenderer();
//        for (BaseContainerBlockEntity baseContainerBlockEntity: ContainerRenderer.container_displayItems.keySet()) {
////            ContainerRenderer.render(baseContainerBlockEntity, poseStack);
//            //renderContainerText(baseContainerBlockEntity, poseStack, "test");  //ContainerRenderer.container_displayItems.get(baseContainerBlockEntity).get()
//        }
//
////        for (BlockEntity blockEntity : mc.level.blockEntityList) {
////            if (blockEntity instanceof ChestBlockEntity chest) {
////                BlockPos pos = chest.getBlockPos();
////                double x = pos.getX() + 0.5 - cameraPos.x;
////                double y = pos.getY() + 1.2 - cameraPos.y;
////                double z = pos.getZ() + 0.5 - cameraPos.z;
////
////                String text = "Custom Chest Label"; // Change this to any text you want
////                renderFloatingText(mc.font, poseStack, text, x, y, z);
////            }
////        }
//    }
//
//    private static void renderContainerText(BaseContainerBlockEntity baseContainerBlockEntity, PoseStack poseStack,String text) {
//        BlockPos blockPos = baseContainerBlockEntity.getBlockPos();
//        double x = blockPos.getX();
//        double y = blockPos.getY();
//        double z = blockPos.getZ();
//        Font font = Minecraft.getInstance().font;
//        poseStack.pushPose();
//        poseStack.translate(x, y, z);
//        poseStack.mulPose(Minecraft.getInstance().gameRenderer.getMainCamera().rotation());
//
//        float scale = 0.03F;
//        poseStack.scale(-scale, -scale, scale);
//
//        Matrix4f matrix = poseStack.last().pose();
//        Font.DisplayMode displayMode = Font.DisplayMode.NORMAL;
//        int color = 0xFFFFFF;
//
//        font.drawInBatch(text, -font.width(text) / 2f, 0, color, false, matrix, Minecraft.getInstance().renderBuffers().bufferSource(), displayMode, 0, 15728880);
//
//        poseStack.popPose();
//    }
//
//    private static void renderFloatingText(Font font, PoseStack poseStack, String text, double x, double y, double z) {
//        poseStack.pushPose();
//        poseStack.translate(x, y, z);
//        poseStack.mulPose(Minecraft.getInstance().gameRenderer.getMainCamera().rotation());
//
//        float scale = 0.03F;
//        poseStack.scale(-scale, -scale, scale);
//
//        Matrix4f matrix = poseStack.last().pose();
//        Font.DisplayMode displayMode = Font.DisplayMode.NORMAL;
//        int color = 0xFFFFFF;
//
//        font.drawInBatch(text, -font.width(text) / 2f, 0, color, false, matrix, Minecraft.getInstance().renderBuffers().bufferSource(), displayMode, 0, 15728880);
//
//        poseStack.popPose();
//    }
//
////    @SubscribeEvent
////    public void renderTick(TickEvent.RenderTickEvent event) {
////        if (event.phase == TickEvent.Phase.START || renderManager.getFontRenderer() == null) {
////            return;
////        }
////        Minecraft mc = Minecraft.getInstance();
////        Entity viewEntity = mc.getRenderViewEntity();
////        EntityRenderer.drawNameplate(renderManager.getFontRenderer(), "text", 0f, 64f, 0f, 0, viewEntity.rotationYaw, viewEntity.rotationPitch, renderManager.options.thirdPersonView == 2, viewEntity.isSneaking());
////
////    }
//}

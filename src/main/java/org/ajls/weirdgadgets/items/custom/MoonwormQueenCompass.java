package org.ajls.weirdgadgets.items.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Position;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootTable;

import javax.annotation.Nonnull;

public class MoonwormQueenCompass extends Item {
    private static final int SEARCH_RADIUS = 50; // Radius around the player to search for chests

    public MoonwormQueenCompass(Item.Properties properties) {
        super(properties);
    }


    @Nonnull
    public InteractionResultHolder<ItemStack> use(Level level, Player player, @Nonnull InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!level.isClientSide()) {
            boolean foundDiamonds = getMoonWormQueen(player);
            if (foundDiamonds) {
                stack.shrink(1);
            }
        }
        return InteractionResultHolder.success(stack);
    }


    public static boolean getMoonWormQueen(Player player) {
        Level level = player.level();
        BlockPos playerPos = player.blockPosition();
        boolean foundDiamonds = false;
        Item moonworm_queen = BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath("twilightforest", "moonworm_queen"));
        for (int x = playerPos.getX() - SEARCH_RADIUS; x <= playerPos.getX() + SEARCH_RADIUS; x++) {
            for (int z = playerPos.getZ() - SEARCH_RADIUS; z <= playerPos.getZ() + SEARCH_RADIUS; z++) {
                for (int y = level.getMinBuildHeight(); y <=level.getMaxBuildHeight(); y++) {
                    BlockPos searchPos = new BlockPos(x, y, z);
                    BlockEntity blockEntity = level.getBlockEntity(searchPos);
                    if (blockEntity instanceof ChestBlockEntity) {
                        ChestBlockEntity chest = (ChestBlockEntity) blockEntity;

                        CompoundTag nbtData = chest.saveWithFullMetadata();
                        BlockState blockState = chest.getBlockState();
                        ChestBlockEntity test = new ChestBlockEntity(chest.getBlockPos(), chest.getBlockState());

                        test.load(nbtData);
                        for (int i = 0; i < chest.getContainerSize(); i++) {
                            ItemStack itemStack = chest.getItem(i);
                            Item item = itemStack.getItem();
                            if (item.equals(moonworm_queen) || item.equals(net.minecraft.world.item.Items.DIAMOND) || item.equals(net.minecraft.world.item.Items.IRON_INGOT) || item.equals(Items.GOLD_INGOT)) {  //net.minecraft.world.item.Items.DIAMOND  moonworm_queen  //itemStack.getItem().equals(Items.IRON_INGOT) || itemStack.getItem().equals(Items.GOLD_INGOT)
                                foundDiamonds = true;
                                player.sendSystemMessage(Component.literal(
                                        "Chest with " + itemStack.getHoverName().getString() + " found at: " + x + ", " + y + ", " + z
                                ));
                                break;
                            }
                        }
                        level.setBlockEntity(test);
                        if (foundDiamonds) {
                            return true;
                        }
                    }
                }
            }
        }
        if (!foundDiamonds) {
            player.sendSystemMessage(Component.literal("No chests with Moonworm Queen found nearby."));
        }
        return foundDiamonds;
    }

}

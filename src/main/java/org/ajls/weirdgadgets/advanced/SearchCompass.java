package org.ajls.weirdgadgets.advanced;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nonnull;

public class SearchCompass extends Item {
    private int SEARCH_RADIUS = 50; // Radius around the player to search for chests
    String namespace;
    String path;

    public SearchCompass(Properties properties) {
        super(properties);
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }
    public void setPath(String path) {
        this.path = path;
    }


    @Nonnull
    public InteractionResultHolder<ItemStack> use(Level level, Player player, @Nonnull InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!level.isClientSide()) {
            boolean foundDiamonds = searchItem(player);
            if (foundDiamonds) {
                stack.shrink(1);
            }
        }
        return InteractionResultHolder.success(stack);
    }


    public boolean searchItem(Player player) {
        Level level = player.level();
        BlockPos playerPos = player.blockPosition();
        boolean found = false;
        Item searchingItem = BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(namespace, path));
        for (int x = playerPos.getX() - SEARCH_RADIUS; x <= playerPos.getX() + SEARCH_RADIUS; x++) {
            for (int z = playerPos.getZ() - SEARCH_RADIUS; z <= playerPos.getZ() + SEARCH_RADIUS; z++) {
                for (int y = level.getMinBuildHeight(); y <=level.getMaxBuildHeight(); y++) {
                    BlockPos searchPos = new BlockPos(x, y, z);
                    BlockEntity blockEntity = level.getBlockEntity(searchPos);
                    if (blockEntity instanceof ChestBlockEntity) {
                        ChestBlockEntity chest = (ChestBlockEntity) blockEntity;

                        CompoundTag nbtData = chest.saveWithFullMetadata();
                        BlockState blockState = chest.getBlockState();
                        ChestBlockEntity newChest = new ChestBlockEntity(chest.getBlockPos(), chest.getBlockState());

                        newChest.load(nbtData);
                        for (int i = 0; i < chest.getContainerSize(); i++) {
                            ItemStack itemStack = chest.getItem(i);
                            Item item = itemStack.getItem();
                            if (item.equals(searchingItem)) {  //net.minecraft.world.item.Items.DIAMOND  searchingItem  //itemStack.getItem().equals(Items.IRON_INGOT) || itemStack.getItem().equals(Items.GOLD_INGOT)
                                // || item.equals(net.minecraft.world.item.Items.DIAMOND) || item.equals(net.minecraft.world.item.Items.IRON_INGOT) || item.equals(Items.GOLD_INGOT)
                                found = true;
                                player.sendSystemMessage(Component.literal(
                                        "Chest with " + itemStack.getHoverName().getString() + " found at: " + x + ", " + y + ", " + z
                                ));
                                break;
                            }
                        }
                        level.setBlockEntity(newChest);
                        if (found) {
                            return true;
                        }
                    }
                }
            }
        }
        if (!found) {
            player.sendSystemMessage(Component.literal("No chests with " + path + " found nearby."));
        }
        return found;
    }

}

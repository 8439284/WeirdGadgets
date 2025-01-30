package org.ajls.weirdgadgets.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentContents;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import org.ajls.weirdgadgets.mixin.MixinRandomizableContainerBlockEntity;
import org.ajls.weirdgadgets.renderer.ContainerRenderer;
import org.ajls.weirdgadgets.utils.ChestBlockEntityUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class SearchItemCommand {
    private static final int SEARCH_RADIUS = 50;
    public static HashSet<ChestBlockEntity> foundedChests = new HashSet<>();
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("search")  //neoforge_check_name
                        .then(Commands.argument("name", StringArgumentType.greedyString())  //StringArgumentType.string()
                                .executes(context -> {
                                    // Get the player executing the command
                                    CommandSourceStack source = context.getSource();
                                    if (!(source.getEntity() instanceof Player)) {
                                        Component component = new Component() {
                                            @Override
                                            public Style getStyle() {
                                                return null;
                                            }

                                            @Override
                                            public ComponentContents getContents() {
                                                return null;
                                            }

                                            @Override
                                            public List<Component> getSiblings() {
                                                return List.of();
                                            }

                                            @Override
                                            public FormattedCharSequence getVisualOrderText() {
                                                return null;
                                            }
                                        };
                                        source.sendFailure(component);
//                                        source.sendFailure(new TextComponent("This command can only be run by a player."));
                                        return 0;
                                    }
                                    Player player = (Player) source.getEntity();
                                    Level level = player.level();
                                    BlockPos playerPos = player.blockPosition();
                                    String specifiedName = StringArgumentType.getString(context, "name");
                                    boolean foundDiamonds = false;
                                    ContainerRenderer.container_displayItems.clear();
                                    for (ChestBlockEntity chest : foundedChests) {
                                        ChestBlockEntityUtils.setOpenness(chest, 0);
                                        chest.chestLidController.openness = 0;
                                    }
                                    for (int x = playerPos.getX() - SEARCH_RADIUS; x <= playerPos.getX() + SEARCH_RADIUS; x++) {
                                        for (int z = playerPos.getZ() - SEARCH_RADIUS; z <= playerPos.getZ() + SEARCH_RADIUS; z++) {
                                            for (int y = playerPos.getY() - SEARCH_RADIUS; y <= playerPos.getY() + SEARCH_RADIUS; y++) {
                                                BlockPos searchPos = new BlockPos(x, y, z);
                                                BlockEntity blockEntity = level.getBlockEntity(searchPos);
                                                boolean foundInThisChest = false;
                                                int matchCount = 0;
                                                if (blockEntity instanceof BaseContainerBlockEntity container) {
                                                    if (blockEntity instanceof RandomizableContainerBlockEntity chest) {  //RandomizableContainerBlockEntity
                                                        ResourceLocation lootTable = ((MixinRandomizableContainerBlockEntity) chest).getLootTable();
                                                        if (lootTable != null) {continue;}
                                                    }
                                                    HashMap<String, Integer> itemStackName_amount = new HashMap<>();
                                                    HashMap<ItemStack, Integer> itemStack_amount = new HashMap<>();
                                                    for (int i = 0; i < container.getContainerSize(); i++) {
                                                        ItemStack itemStack = container.getItem(i);
                                                        String itemName = itemStack.getHoverName().getString();
                                                        if (itemName.toLowerCase().contains(specifiedName.toLowerCase())) {
                                                            foundDiamonds = true;
                                                            foundInThisChest = true;
                                                            if (!itemStackName_amount.containsKey(itemName)) {
                                                                itemStackName_amount.put(itemName, itemStack.getCount());
                                                                itemStack_amount.put(itemStack, itemStack.getCount());
                                                                matchCount++;
                                                            }
                                                            else {
                                                                itemStackName_amount.put(itemName, itemStackName_amount.get(itemName) + itemStack.getCount());
                                                            }
                                                        }
                                                    }
                                                    if (foundInThisChest) {
                                                        if (blockEntity instanceof ChestBlockEntity chest) {
                                                            ChestBlockEntityUtils.setOpenness(chest, 1);
//                                                            level.setBlockEntity(chest);
                                                            chest.chestLidController.openness = 1;

                                                            foundedChests.add(chest);
                                                        }
                                                        player.sendSystemMessage(Component.literal(
                                                                matchCount + " matches found at: " + x + ", " + y + ", " + z
                                                        ));
                                                        for (String name : itemStackName_amount.keySet()) {
                                                            player.sendSystemMessage(Component.literal(
                                                                    name + ": " + itemStackName_amount.get(name)
                                                            ));
                                                        }
                                                        ContainerRenderer.container_displayItems.put(container, itemStack_amount);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    if (!foundDiamonds) {
                                        player.sendSystemMessage(Component.literal(
                                                "Nothing found"
                                        ));
                                    }


//                                    // Get the item in the player's main hand
//                                    ItemStack itemInHand = player.getMainHandItem();
//
//                                    // Get the argument passed (the item name to check)
//                                    String specifiedName = StringArgumentType.getString(context, "name");
//
//                                    // Check if the item name contains the specified name
//                                    if (itemInHand.getHoverName().getString().contains(specifiedName)) {
////                                        player.sendMessage(new TextComponent("Yes, you are holding an item with the name: " + specifiedName));
//                                        PlayerUtils.sendMessage(player, "Yes, you are holding an item with the name: " + specifiedName);
////                                        PlayerChatMessage chatMessage = PlayerChatMessage.unsigned(player.getUUID(), "Yes, you are holding an item with the name: " + specifiedName);
////                                        player.createCommandSourceStack().sendChatMessage(new OutgoingChatMessage.Player(chatMessage), false, ChatType.bind(ChatType.CHAT, player));
//                                    } else {
//                                        PlayerUtils.sendMessage(player, "No, the item you are holding does not contain the name: " + specifiedName);
////                                        player.sendMessage(new TextComponent("No, the item you are holding does not contain the name: " + specifiedName));
//                                    }

                                    return 1; // Command executed successfully
                                })
                        )
        );
    }
}

package org.ajls.weirdgadgets.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentContents;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.storage.loot.LootTable;
import org.ajls.weirdgadgets.advanced.RandomizableContainerBlockEntityAdvanced;
import org.ajls.weirdgadgets.mixin.MixinRandomizableContainerBlockEntity;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

public class SearchItemCommand {
    private static final int SEARCH_RADIUS = 50;
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
                                    for (int x = playerPos.getX() - SEARCH_RADIUS; x <= playerPos.getX() + SEARCH_RADIUS; x++) {
                                        for (int z = playerPos.getZ() - SEARCH_RADIUS; z <= playerPos.getZ() + SEARCH_RADIUS; z++) {
                                            for (int y = playerPos.getY() - SEARCH_RADIUS; y <= playerPos.getY() + SEARCH_RADIUS; y++) {
                                                BlockPos searchPos = new BlockPos(x, y, z);
                                                BlockEntity blockEntity = level.getBlockEntity(searchPos);
//                                                if (blockEntity instanceof BaseContainerBlockEntity) {
//                                                    BaseContainerBlockEntity container = (BaseContainerBlockEntity) blockEntity;
//                                                    if (container.)
//                                                }
                                                boolean foundInThisChest = false;
                                                boolean canRandomize = false;
                                                int matchCount = 0;
                                                if (blockEntity instanceof BaseContainerBlockEntity container) {
//                                                    RandomizableContainerBlockEntity test = null;
                                                    if (blockEntity instanceof RandomizableContainerBlockEntity chest) {  //RandomizableContainerBlockEntity
                                                        ResourceLocation lootTable = ((MixinRandomizableContainerBlockEntity) chest).getLootTable();
                                                        if (lootTable != null) {continue;}

                                                        CompoundTag nbtData = chest.saveWithFullMetadata();
//                                                        RandomizableContainerBlockEntityAdvanced chestAdvanced = (RandomizableContainerBlockEntityAdvanced) chest;
//                                                        if (chestAdvanced.getLootTable() != null)  continue;

//                                                        test = new chest.cl(chest.getType(), chest.getBlockPos(), chest.getBlockState()) {
//                                                            @Override
//                                                            protected NonNullList<ItemStack> getItems() {
//                                                                return null;
//                                                            }
//
//                                                            @Override
//                                                            protected void setItems(NonNullList<ItemStack> p_59625_) {
//
//                                                            }
//
//                                                            @Override
//                                                            protected Component getDefaultName() {
//                                                                return null;
//                                                            }
//
//                                                            @Override
//                                                            protected AbstractContainerMenu createMenu(int p_58627_, Inventory p_58628_) {
//                                                                return null;
//                                                            }
//                                                        };
//                                                        test.load(nbtData);
                                                        canRandomize = true;
                                                        Field field = null;
                                                        ResourceKey<LootTable> lootTableKey = null;
                                                        boolean checked = false;
//                                                        Field[] fields = RandomizableContainerBlockEntity.class.getDeclaredFields();
//                                                        for (Field f : fields) {
//                                                            player.sendSystemMessage(Component.literal(
//                                                                    f.getDeclaringClass().getName()
//                                                            ));
//                                                            player.sendSystemMessage(Component.literal(
//                                                                    f.getDeclaringClass().toString()
//                                                            ));
//                                                            if (f.getDeclaringClass() == ResourceKey.class) {
//                                                                f.setAccessible(true);
//                                                                try {
//                                                                    lootTableKey = (ResourceKey<LootTable>) field.get(chest);
//                                                                    checked = true;
//                                                                } catch (IllegalAccessException e) {
//                                                                    throw new RuntimeException(e);
//                                                                }
//                                                            }
//                                                        }
                                                    }


//                                                        try {

//                                                            field = RandomizableContainerBlockEntity.class.getDeclaredField("lootTable");

//                                                        field.setAccessible(true); // Force to access the field
// Set value
//                                                        field.set(chest, 0);  //-114514  //32700
// Get value


//                                                        try {
//                                                            lootTableKey = (ResourceKey<LootTable>) field.get(chest);
//                                                        } catch (IllegalAccessException e) {
//                                                            throw new RuntimeException(e);
//                                                        }
                                                        //                                                        ResourceKey<LootTable> lootTableKey = chest.unpackLootTable();
//                                                        if (lootTableKey != null || !checked) continue;
//                                                    }
                                                    HashMap<String, Integer> itemStack_amount = new HashMap<>();
                                                    for (int i = 0; i < container.getContainerSize(); i++) {
                                                        ItemStack itemStack = container.getItem(i);
                                                        String itemName = itemStack.getHoverName().getString();
                                                        if (itemName.toLowerCase().contains(specifiedName.toLowerCase())) {
                                                            foundDiamonds = true;
                                                            foundInThisChest = true;
                                                            if (!itemStack_amount.containsKey(itemName)) {
                                                                itemStack_amount.put(itemName, itemStack.getCount());
                                                                matchCount++;
                                                            }
                                                            else {
                                                                itemStack_amount.put(itemName, itemStack_amount.get(itemName) + itemStack.getCount());
                                                            }
                                                        }
                                                    }
                                                    if (canRandomize) {
//                                                        level.setBlockEntity(test);
                                                    }
                                                    if (foundInThisChest) {
                                                        player.sendSystemMessage(Component.literal(
                                                                matchCount + " matches found at: " + x + ", " + y + ", " + z
                                                        ));
                                                        for (String name : itemStack_amount.keySet()) {
                                                            player.sendSystemMessage(Component.literal(
                                                                    name + ": " + itemStack_amount.get(name)
                                                            ));
                                                        }
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

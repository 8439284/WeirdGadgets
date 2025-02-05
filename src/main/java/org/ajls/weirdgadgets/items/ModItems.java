package org.ajls.weirdgadgets.items;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.ajls.weirdgadgets.WeirdGadgets;
import org.ajls.weirdgadgets.items.custom.searchitem.MazebreakerPickaxeCompass;
import org.ajls.weirdgadgets.items.custom.searchitem.MoonwormQueenCompassNew;
import org.ajls.weirdgadgets.items.custom.searchitem.PeacockFeatherFanCompass;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,WeirdGadgets.MODID);

//    public static final RegistryObject<Item> BISMUTH = ITEMS.register("bismuth",
//            () -> new Item(new Item.Properties()));
////    public static final DeferredItem<Item> RAW_BISMUTH = ITEMS.register("raw_bismuth",
////            () -> new Item(new Item.Properties()));
//    public static final RegistryObject<Item> ADRENALINE = ITEMS.register("adrenaline",
//            () -> new Adrenaline(new Item.Properties().durability(1).food(Adrenaline.ADRENALINE)));

    public static final RegistryObject<Item> MOONWORM_QUEEN_COMPASS = ITEMS.register("moonworm_queen_compass",
            () -> new MoonwormQueenCompassNew(new Item.Properties()));
    public static final RegistryObject<Item> PEACOCK_FEATHER_FAN_COMPASS = ITEMS.register("peacock_feather_fan_compass",
            () -> new PeacockFeatherFanCompass(new Item.Properties()));
    public static final RegistryObject<Item> MAZEBREAKER_PICKAXE_COMPASS = ITEMS.register("mazebreaker_pickaxe_compass",
            () -> new MazebreakerPickaxeCompass(new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}

package org.ajls.weirdgadgets.events;


import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.ajls.weirdgadgets.WeirdGadgets;
import org.ajls.weirdgadgets.commands.SearchItemCommand;

public class MyListener {


    @SubscribeEvent
    public void registerCommands(RegisterCommandsEvent event){
        SearchItemCommand.register(event.getDispatcher());
    }

    @SubscribeEvent
    public void onFarmlandTrample(BlockEvent.FarmlandTrampleEvent event) {
//        // Check if the event is related to farmland trample
//        if (event.getState().getBlock() instanceof FarmBlock) {
//            // Handle the trample event
//            System.out.println("Farmland trampled at: " + event.getPos());
//            // You can add your logic here, e.g., changing block state, notifying the player, etc.
//        }
        Entity entity = event.getEntity();
        if (entity instanceof Player player) {
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 10, 0, true, true));
            player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 10, 0, true, true));
            player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 10, 0, true, true));
            player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 10, 0, true, true));
            player.addEffect(new MobEffectInstance(MobEffects.HUNGER, 10, 0, true, true));
            player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 10, 0, true, true));
            player.addEffect(new MobEffectInstance(MobEffects.POISON, 10, 0, true, true));
            player.addEffect(new MobEffectInstance(MobEffects.WITHER, 10, 0, true, true));
            player.addEffect(new MobEffectInstance(MobEffects.UNLUCK, 10, 0, true, true));
            player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 10, 0, true, true));
//            player.addPotionEffect(new PotionEffect(PotionEffectType.MINING_FATIGUE, 10, 0));
//            player.addPotionEffect(new PotionEffect(PotionEffectType.NAUSEA, 10, 0));
//            player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 10, 0));
//            player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 10, 0));
//            player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 10, 0));
//            player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 10, 0));
//            player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 10, 0));
//            player.addPotionEffect(new PotionEffect(PotionEffectType.UNLUCK, 10, 0));
//            player.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, 10, 0));
        }
    }



}

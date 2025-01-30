package org.ajls.weirdgadgets.events;


import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.ajls.weirdgadgets.WeirdGadgets;
import org.ajls.weirdgadgets.commands.SearchItemCommand;

public class MyListener {


    @SubscribeEvent
    public void registerCommands(RegisterCommandsEvent event){
        SearchItemCommand.register(event.getDispatcher());
    }



}

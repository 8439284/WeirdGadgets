package org.ajls.weirdgadgets.events;


import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.ajls.weirdgadgets.commands.SearchItemCommand;

public class MyListener {


    @SubscribeEvent
    public void registerCommands(RegisterCommandsEvent event){
        SearchItemCommand.register(event.getDispatcher());
    }
}

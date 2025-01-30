package org.ajls.weirdgadgets.datacontainer;

import net.minecraft.world.item.ItemStack;

public class ItemStackCount {
    private ItemStack itemStack;
    private int count;
    public ItemStackCount(ItemStack itemStack, int count) {
        this.itemStack = itemStack;
        this.count = count;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }
    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
}

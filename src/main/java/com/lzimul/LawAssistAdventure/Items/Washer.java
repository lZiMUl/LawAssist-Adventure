package com.lzimul.LawAssistAdventure.Items;

import com.lzimul.LawAssistAdventure.BlockRegister;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

public class Washer extends BlockItem {
    public Washer() {
        super(BlockRegister.Washer.get(), new Item.Properties());
    }
}
package com.lzimul.LawAssistAdventure.screen.item;

import com.lzimul.LawAssistAdventure.menu.item.TeleporterMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

public class TeleporterScreen extends AbstractContainerScreen<TeleporterMenu> {
    public TeleporterScreen(TeleporterMenu teleporterMenu, Inventory inventory, Component component) {
        super(teleporterMenu, inventory, component);
    }

    @Override
    protected void renderBg(@NotNull GuiGraphics guiGraphics, float p_97788_, int p_97789_, int p_97790_) {

    }
}

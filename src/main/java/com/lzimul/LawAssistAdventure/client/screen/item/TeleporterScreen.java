package com.lzimul.LawAssistAdventure.client.screen.item;

import com.lzimul.LawAssistAdventure.client.menu.item.TeleporterMenu;
import com.lzimul.LawAssistAdventure.network.NetworkData;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;

import static com.lzimul.LawAssistAdventure.Config.GetResourceLocation;
import static com.lzimul.LawAssistAdventure.Config.ResourceType;

public class TeleporterScreen extends AbstractContainerScreen<TeleporterMenu> {

    public TeleporterScreen(TeleporterMenu teleporterMenu, Inventory inventory, Component component) {
        super(teleporterMenu, inventory, component);
    }

    @Override
    protected void init() {
        int width = super.width / 2;
        int height = super.height / 2;
        WidgetSprites widgetSprites = new WidgetSprites(GetResourceLocation(ResourceType.ITEM, "teleporter"), GetResourceLocation(ResourceType.ITEM, "teleporter"));

        super.addRenderableWidget(new ImageButton(width - width / 3, height - height / 2, 30, 30, widgetSprites, (button) -> this.send("Dust", 100), Component.literal("Dust")));
        super.addRenderableWidget(new ImageButton(width - width / 2, height - height / 2, 30, 30, widgetSprites, (button) -> this.send("FallIntoTheVoid", 101), Component.literal("FallIntoTheVoid")));
        super.addRenderableWidget(new ImageButton(width + width / 2, height - height / 2, 30, 30, widgetSprites, (button) -> this.send("FinalWing", 102), Component.literal("FinalWing")));
        super.addRenderableWidget(new ImageButton(width + width / 3, height - height / 2, 30, 30, widgetSprites, (button) -> this.send("RemnantDawn", 103), Component.literal("RemnantDawn")));
        super.addRenderableWidget(new ImageButton(width - width / 3, height + height / 2, 30, 30, widgetSprites, (button) -> this.send("Staring", 104), Component.literal("Staring")));
        super.addRenderableWidget(new ImageButton(width - width / 2, height + height / 2, 30, 30, widgetSprites, (button) -> this.send("Dimension", 105), Component.literal("Dimension")));
        super.addRenderableWidget(new ImageButton(width + width / 2, height + height / 2, 30, 30, widgetSprites, (button) -> this.send("TheNether", 106), Component.literal("TheNether")));
        super.addRenderableWidget(new ImageButton(width + width / 3, height + height / 2, 30, 30, widgetSprites, (button) -> this.send("TheEnd", 107), Component.literal("TheEnd")));
    }

    private void send(String occupation, int id) {
        PacketDistributor.sendToServer(new NetworkData("server:occupation/" + occupation, id));
    }

    @Override
    protected void renderBg(@NotNull GuiGraphics guiGraphics, float p_97788_, int p_97789_, int p_97790_) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        guiGraphics.blit(GetResourceLocation(ResourceType.ITEM, "teleporter"), 0, 0, 0, 0, imageWidth - 149, imageHeight - 170, imageWidth, imageHeight);
    }

    @Override
    public void render(@NotNull GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.renderBackground(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pGuiGraphics, pMouseX, pMouseY);
    }
}

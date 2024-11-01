package com.github.FlyBird.BetterMite.gui.grindstone;

import com.github.FlyBird.BetterMite.tileentity.TileEntityGrindStone;
import net.minecraft.*;
import org.lwjgl.opengl.GL11;


import java.util.List;


public class GuiGrindStone extends GuiContainer implements ICrafting {
    private static final ResourceLocation ENCHANT_RESERVER_TEXTURE = new ResourceLocation("textures/gui/container/grindstone.png");

    private final ContainerGrindStone containerGrindStone;

    private final TileEntityGrindStone tileEntityGrindStone = new TileEntityGrindStone();

    private final int x;

    private final int y;

    private final int z;

    private final InventoryPlayer inventory;

    private final EntityPlayer player;

    public GuiGrindStone(EntityPlayer player, int x, int y, int z, GrindStoneSlots slots) {
        super(new ContainerGrindStone(slots, player, x, y, z));
        this.containerGrindStone = (ContainerGrindStone) this.inventorySlots;
        this.x = x;
        this.y = y;
        this.z = z;
        this.inventory = player.inventory;
        this.player = player;
    }

    //父类中的方法
    public void drawScreen(int mouse_x, int mouse_y, float par3) {
        super.drawScreen(mouse_x, mouse_y, par3);
        /*Slot slot = getSlotThatMouseIsOver(mouse_x, mouse_y);
        if (slot == null)
            return;
        if (slot.getHasStack())
            return;
        if (slot.slotNumber > 1)
            return;
        String toDraw = "请放置水瓶, 金属粒等能够储存经验的物品";
        if (slot.slotNumber == 0)
            toDraw = "请放置钻石等具有经验的物品";
        drawCreativeTabHoveringText(toDraw + EnumChatFormatting.GOLD + EnumChatFormatting.GOLD, mouse_x, mouse_y);*/
        //创建一个悬浮面板，并显示一段文字
    }

    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        String var3 = this.tileEntityGrindStone.hasCustomName() ? this.tileEntityGrindStone.getCustomNameOrUnlocalized() : I18n.getString(this.tileEntityGrindStone.getCustomNameOrUnlocalized());
        this.fontRenderer.drawString(var3, this.xSize / 2 - this.fontRenderer.getStringWidth(var3) / 2, 6, 4210752);
        this.fontRenderer.drawString(I18n.getString("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }

    //背景底图
    protected void drawGuiContainerBackgroundLayer(float v, int i, int i1) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        drawDefaultBackground();
        this.mc.getTextureManager().bindTexture(ENCHANT_RESERVER_TEXTURE);
        int var4 = (this.width - this.xSize) / 2;
        int var5 = (this.height - this.ySize) / 2;
        int var6 = this.guiLeft;
        int var7 = this.guiTop;
        drawTexturedModalRect(var4, var5, 0, 0, this.xSize, this.ySize);
    }

    public void sendContainerAndContentsToPlayer(Container container, List list) {}

    public void sendSlotContents(Container container, int i, ItemStack itemStack) {}

    public void sendProgressBarUpdate(Container container, int i, int i1) {}

/*    public void setEnchantInfo(int exp) {
        this.tileEntityTest.setEXP(exp);
    }*/
}

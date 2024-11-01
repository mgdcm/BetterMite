package com.github.FlyBird.BetterMite;

import com.github.FlyBird.BetterMite.block.Blocks;
import com.github.FlyBird.BetterMite.entity.*;
import com.github.FlyBird.BetterMite.item.Items;
import com.github.FlyBird.BetterMite.network.ArmourStandInteractMessage;
import com.github.FlyBird.BetterMite.network.BoatMoveMessage;
import com.github.FlyBird.BetterMite.network.S2COpenWindow;
import com.github.FlyBird.BetterMite.render.entity.*;
import com.github.FlyBird.BetterMite.tileentity.BarrelTileEntity;
import com.github.FlyBird.BetterMite.tileentity.TileEntityGrindStone;
import com.google.common.eventbus.Subscribe;
import net.minecraft.ChatMessageComponent;
import net.minecraft.EntityPlayer;
import net.xiaoyu233.fml.reload.event.*;
import net.xiaoyu233.fml.reload.utils.IdUtil;

public class EventListen {

    @Subscribe
    public void onItemRegister(ItemRegistryEvent event) {
        //物品被注册事件
        Items.registerItems(event);
        Blocks.registerItemBlocks(event);
    }

    @Subscribe
    public void onRecipeRegister(RecipeRegistryEvent event) {
        //合成方式被注册事件
        Items.registerRecipes(event);
        Blocks.registerRecipes(event);
        Blocks.furnaceRecipe();
    }

    //玩家登录事件
    @Subscribe
    public void onPlayerLoggedIn(PlayerLoggedInEvent event) {

    }

    //指令事件
    @Subscribe
    public void handleChatCommand(HandleChatCommandEvent event) {
        String par2Str = event.getCommand();
        EntityPlayer player = event.getPlayer();
        if (par2Str.startsWith("Hello")) {    //当玩家输入 /Hello
            player.sendChatToPlayer(ChatMessageComponent.createFromText("你好，FML！"));
            /*EntityRabbit test=new EntityRabbit(event.getWorld());
            EntityEndermite test=new EntityEndermite(event.getWorld());
            EntityArmourStand test=new EntityArmourStand(event.getWorld());
            EntityNewBoat test=new EntityNewBoat(event.getWorld());
            test.setPosition(player.posX,player.posY,player.posZ);

            test.setRabbitType(4);
            event.getWorld().spawnEntityInWorld(test);*/
            event.setExecuteSuccess(true);
        }
    }

    @Subscribe
    public void onTileEntityRegister(TileEntityRegisterEvent event) {
        //注册方块实体的地方
        event.register(BarrelTileEntity.class, "Barrel");
        event.register(TileEntityGrindStone.class, "GrindStone");
    }

    @Subscribe
    public void onTileEntityRenderRegisterEvent(TileEntityRendererRegisterEvent event) {
        //注册方块实体渲染的地方
        //event.register();
    }

    @Subscribe
    public void onEntityRegister(EntityRegisterEvent event) {
        event.register(EntityRabbit.class, BetterMiteStart.MOD_ID, "EntityRabbit", getNextEntityID(),10051392,7555121);
        event.register(EntityEndermite.class, BetterMiteStart.MOD_ID, "EntityEndermite", getNextEntityID(),1447446,7237230);
        event.register(EntityArmourStand.class, BetterMiteStart.MOD_ID,"EntityArmourStand", getNextEntityID());

        event.register(EntityNewBoat.class, BetterMiteStart.MOD_ID, "EntityNewBoat", getNextEntityID());
        event.register(EntityNewBoatSeat.class, BetterMiteStart.MOD_ID, "EntityNewBoatSeat", getNextEntityID());
        event.register(EntityNewBoatWithChest.class, BetterMiteStart.MOD_ID, "EntityNewBoatWithChest", getNextEntityID());
    }

    @Subscribe
    public void onEntityRendererRegistry(EntityRendererRegistryEvent event)
    {
        event.register(EntityRabbit.class,new RabbitRenderer());
        event.register(EntityArmourStand.class,new ArmourStandRenderer());
        event.register(EntityEndermite.class,new EndermiteRenderer());

        event.register(EntityNewBoat.class,new NewBoatRenderer());
        //event.register(EntityNewBoatSeat.class,new NewBoatRenderer());
        event.register(EntityNewBoatWithChest.class,new ChestBoatRenderer());
    }

    @Subscribe
    public void onPacketRegister(PacketRegisterEvent event) {
        event.register(false, true, ArmourStandInteractMessage.class);
        event.register(false, true, BoatMoveMessage.class);
        event.register(true, false, S2COpenWindow.class);//138
    }


    public static int getNextEntityID() {
        return IdUtil.getNextEntityID();
    }
}

package com.github.FlyBird.BetterMite.item;

import com.github.FlyBird.BetterMite.block.Blocks;
import com.github.FlyBird.BetterMite.entity.EntityNewBoat;
import com.github.FlyBird.BetterMite.item.register.RecipesFurnaceExtend;
import net.minecraft.*;
import net.xiaoyu233.fml.reload.event.ItemRegistryEvent;
import net.xiaoyu233.fml.reload.event.RecipeRegistryEvent;
import net.xiaoyu233.fml.reload.utils.IdUtil;


public class Items extends Item {
    public static final ItemModDoor birchDoor = new ItemModDoor(getNextItemID(), Blocks.birchDoor,"birch");
    public static final ItemModDoor jungleDoor = new ItemModDoor(getNextItemID(), Blocks.jungleDoor,"jungle");
    public static final ItemModDoor spruceDoor = new ItemModDoor(getNextItemID(), Blocks.spruceDoor,"spruce");
    //ItemMeat
    public static final ItemMeat rabbitRaw= (ItemMeat)(new ItemMeat(getNextItemID(), 3, 3, false, false, "rabbit_raw")).setUnlocalizedName("rabbitRaw");
    public static final ItemMeat rabbitCooked= (ItemMeat)(new ItemMeat(getNextItemID(), 6, 6, false, false, "rabbit_cooked")).setUnlocalizedName("rabbitCooked");
    public static final Item rabbitHide = (new Items(getNextItemID(), Material.leather, "rabbit_hide")).setCraftingDifficultyAsComponent(100.0F).setUnlocalizedName("rabbitHide").setCreativeTab(CreativeTabs.tabMaterials);
    public static final Item rabbitFoot = (new Items(getNextItemID(), Material.blaze, "rabbit_foot")).setCraftingDifficultyAsComponent(25.0F).setUnlocalizedName("rabbitFoot").setPotionEffect(PotionHelper.blazePowderEffect).setCreativeTab(CreativeTabs.tabBrewing);
    public static final ItemArmorStand armorStand =new ItemArmorStand(getNextItemID(),Material.wood,"wooden_armorstand");
    public static final Item sweetBerry = new ItemSweetBerry(getNextItemID(), 1, 1, false, true, Blocks.sweetBerryBush.blockID, "sweet_berries").setUnlocalizedName("sweetberries");
    public static final Item bowlRabbitStew = (new ItemBowl(getNextItemID(), Material.beef_stew, "rabbit_stew")).setFoodValue(12, 14, true, false, true).setPlantProduct().setUnlocalizedName("rabbit_stew");
    public static final ItemNewBoat oakBoat=new ItemNewBoat(getNextItemID(), EntityNewBoat.Type.OAK, false);
    public static final ItemNewBoat birchBoat=new ItemNewBoat(getNextItemID(), EntityNewBoat.Type.BIRCH, false);
    public static final ItemNewBoat jungleBoat=new ItemNewBoat(getNextItemID(), EntityNewBoat.Type.JUNGLE, false);
    public static final ItemNewBoat spruceBoat=new ItemNewBoat(getNextItemID(), EntityNewBoat.Type.SPRUCE, false);

    public static final ItemNewBoat oakChestBoat=new ItemNewBoat(getNextItemID(), EntityNewBoat.Type.OAK, true);
    public static final ItemNewBoat birchChestBoat=new ItemNewBoat(getNextItemID(), EntityNewBoat.Type.BIRCH, true);
    public static final ItemNewBoat jungleChestBoat=new ItemNewBoat(getNextItemID(), EntityNewBoat.Type.JUNGLE, true);
    public static final ItemNewBoat spruceChestBoat=new ItemNewBoat(getNextItemID(), EntityNewBoat.Type.SPRUCE, true);

    //海晶碎片
    public static final Item prismarineShard = (new Items(getNextItemID(), Material.diamond, "prismarine_shard")).setCraftingDifficultyAsComponent(100.0F).setUnlocalizedName("prismarineShard").setCreativeTab(CreativeTabs.tabMaterials);
    //海晶砂粒
    public static final Item prismarineCrystals = (new Items(getNextItemID(), Material.diamond, "prismarine_crystals")).setCraftingDifficultyAsComponent(100.0F).setUnlocalizedName("prismarineCrystals").setCreativeTab(CreativeTabs.tabMaterials);

    protected Items(int id, Material material, String texture) {
        super(id,material,texture);
    }
    public static void registerItems(ItemRegistryEvent event) {
//        event.register("BetterMite","doors/birch", birchDoor);
//        birchDoor.setUnlocalizedName("birchdoor");
//
//        event.register("BetterMite","doors/jungle", jungleDoor);
//        jungleDoor.setUnlocalizedName("jungledoor");
//
//        event.register("BetterMite","doors/spruce", spruceDoor);
//        spruceDoor.setUnlocalizedName("sprucedoor");
    }

    public static void registerRecipes(RecipeRegistryEvent register) {
        //注册熔炉烧制的东西
        RecipesFurnaceExtend.registerFurnaceRecipes();

        register.registerShapelessRecipe(new ItemStack(bowlRabbitStew,1,0), true, new ItemStack(rabbitCooked),new ItemStack(carrot),new ItemStack(potato),new ItemStack(Block.mushroomRed),new ItemStack(bowlWater));
        register.registerShapelessRecipe(new ItemStack(bowlRabbitStew,1,0), true, new ItemStack(rabbitCooked),new ItemStack(carrot),new ItemStack(potato),new ItemStack(Block.mushroomBrown),new ItemStack(bowlWater));
        register.registerShapedRecipe(new ItemStack(leather,1),true,new Object[] {"AA","AA", 'A',new ItemStack(rabbitHide,1)});

        register.registerShapedRecipe(new ItemStack(armorStand, 1), true, new Object[] {"AAA"," A ","ABA",Character.valueOf('A'),new ItemStack(Item.stick,1),Character.valueOf('B'),new ItemStack(Block.stoneSingleSlab,1)});

        birchDoor.registerRecipeWithVanilla(register,1);
        jungleDoor.registerRecipeWithVanilla(register,2);
        spruceDoor.registerRecipeWithVanilla(register,3);

        for (ItemNewBoat itemNewBoat: new ItemNewBoat[]{oakBoat,birchBoat,jungleBoat,spruceBoat,oakChestBoat,birchChestBoat,jungleChestBoat,spruceChestBoat}){
            itemNewBoat.registerBoatRecipes(register);
        }
    }
    public static int getNextItemID() {
        return IdUtil.getNextItemID();
    }
}

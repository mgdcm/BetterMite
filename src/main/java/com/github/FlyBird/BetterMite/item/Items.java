package com.github.FlyBird.BetterMite.item;

import com.github.FlyBird.BetterMite.block.Blocks;
import com.github.FlyBird.BetterMite.entity.EntityNewBoat;
import com.github.FlyBird.BetterMite.item.register.RecipesFurnaceExtend;
import com.github.FlyBird.BetterMite.misc.EnumWoodType;
import net.minecraft.*;
import net.xiaoyu233.fml.reload.event.ItemRegistryEvent;
import net.xiaoyu233.fml.reload.event.RecipeRegistryEvent;
import net.xiaoyu233.fml.reload.utils.IdUtil;


public class Items extends Item {
    public static final Item birchDoor = new ItemNewDoor(getNextItemID(), EnumWoodType.BIRCH);
    public static final Item jungleDoor = new ItemNewDoor(getNextItemID(),EnumWoodType.JUNGLE);
    public static final Item spruceDoor = new ItemNewDoor(getNextItemID(),EnumWoodType.SPRUCE);
    //ItemMeat
    public static final ItemMeat rabbitRaw= (ItemMeat)(new ItemMeat(getNextItemID(), 3, 3, false, false, "rabbit_raw")).setUnlocalizedName("rabbitRaw");
    public static final ItemMeat rabbitCooked= (ItemMeat)(new ItemMeat(getNextItemID(), 6, 6, false, false, "rabbit_cooked")).setUnlocalizedName("rabbitCooked");
    public static final Item rabbitHide = (new Items(getNextItemID(), Material.leather, "rabbit_hide")).setCraftingDifficultyAsComponent(100.0F).setUnlocalizedName("rabbitHide").setCreativeTab(CreativeTabs.tabMaterials);
    public static final Item rabbitFoot = (new Items(getNextItemID(), Material.blaze, "rabbit_foot")).setCraftingDifficultyAsComponent(25.0F).setUnlocalizedName("rabbitFoot").setPotionEffect(PotionHelper.blazePowderEffect).setCreativeTab(CreativeTabs.tabBrewing);
    public static final ItemArmourStand armourStand=new ItemArmourStand(getNextItemID(),Material.wood,"wooden_armorstand");
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
        event.register("BetterMite","doors/birch_door", birchDoor);
        birchDoor.setUnlocalizedName("birchdoor");

        event.register("BetterMite","doors/jungle_door", jungleDoor);
        jungleDoor.setUnlocalizedName("jungledoor");

        event.register("BetterMite","doors/spruce_door", spruceDoor);
        spruceDoor.setUnlocalizedName("sprucedoor");
    }

    public static void registerRecipes(RecipeRegistryEvent register) {
        //注册熔炉烧制的东西
        RecipesFurnaceExtend.registerFurnaceRecipes();

        register.registerShapelessRecipe(new ItemStack(bowlRabbitStew,1,0), true, new ItemStack(rabbitCooked),new ItemStack(carrot),new ItemStack(potato),new ItemStack(Block.mushroomRed),new ItemStack(bowlWater));
        register.registerShapelessRecipe(new ItemStack(bowlRabbitStew,1,0), true, new ItemStack(rabbitCooked),new ItemStack(carrot),new ItemStack(potato),new ItemStack(Block.mushroomBrown),new ItemStack(bowlWater));
        register.registerShapedRecipe(new ItemStack(leather,1),true,new Object[] {"AA","AA", 'A',new ItemStack(rabbitHide,1)});

        register.registerShapedRecipe(new ItemStack(armourStand, 1), true, new Object[] {"AAA"," A ","ABA",Character.valueOf('A'),new ItemStack(Item.stick,1),Character.valueOf('B'),new ItemStack(Block.stoneSingleSlab,1)});

        register.registerShapedRecipe(new ItemStack(birchDoor, 1), true, new Object[]{"AA ", "AA ", "AA ", Character.valueOf('A'), new ItemStack((Block.planks), 1, 2)});
        register.registerShapedRecipe(new ItemStack(birchDoor, 1), true, new Object[] {" AA"," AA"," AA",Character.valueOf('A'),new ItemStack((Block.planks),1,2)});

        register.registerShapedRecipe(new ItemStack(jungleDoor, 1), true, new Object[] {"AA ","AA ","AA ",Character.valueOf('A'),new ItemStack((Block.planks),1,3)});
        register.registerShapedRecipe(new ItemStack(jungleDoor, 1), true, new Object[] {" AA"," AA"," AA",Character.valueOf('A'),new ItemStack((Block.planks),1,3)});

        register.registerShapedRecipe(new ItemStack(spruceDoor, 1), true, new Object[] {"AA ","AA ","AA ",Character.valueOf('A'),new ItemStack((Block.planks),1,1)});
        register.registerShapedRecipe(new ItemStack(spruceDoor, 1), true, new Object[] {" AA"," AA"," AA",Character.valueOf('A'),new ItemStack((Block.planks),1,1)});

        registerBoatRecipes(register,oakBoat);
        registerBoatRecipes(register,birchBoat);
        registerBoatRecipes(register,jungleBoat);
        registerBoatRecipes(register,spruceBoat);
        registerBoatRecipes(register,oakChestBoat);
        registerBoatRecipes(register,birchChestBoat);
        registerBoatRecipes(register,jungleChestBoat);
        registerBoatRecipes(register,spruceChestBoat);
    }

    public static void  registerBoatRecipes(RecipeRegistryEvent register,ItemNewBoat itemNewboat) {
        //ItemNewBoat.BoatInfo boatInfo = ItemNewBoat.BOAT_INFO.get(itemNewboat.type);
        if (!itemNewboat.isChest)
            register.registerShapedRecipe(new ItemStack(itemNewboat, 1), true, new Object[]{"B B", "A A", "AAA", Character.valueOf('A'), itemNewboat.boatinfo.getPlank(), Character.valueOf('B'), new ItemStack(Item.shovelWood, 1)});
        else {
            ItemNewBoat.BoatInfo boatInfo = ItemNewBoat.BOAT_INFO.get(itemNewboat.name);//获取没有箱子的对应Boatinfo  以便为了获取没有箱子的船
            register.registerShapedRecipe(new ItemStack(itemNewboat, 1), true, new Object[]{"A  ", "B  ", "   ", Character.valueOf('A'), new ItemStack(Block.chest), Character.valueOf('B'), boatInfo.getBoatItem()});
            register.registerShapedRecipe(new ItemStack(itemNewboat, 1), true, new Object[]{" A ", " B ", "   ", Character.valueOf('A'), new ItemStack(Block.chest), Character.valueOf('B'), boatInfo.getBoatItem()});
            register.registerShapedRecipe(new ItemStack(itemNewboat, 1), true, new Object[]{"  A", "  B", "   ", Character.valueOf('A'), new ItemStack(Block.chest), Character.valueOf('B'), boatInfo.getBoatItem()});
            register.registerShapedRecipe(new ItemStack(itemNewboat, 1), true, new Object[]{"   ", "A  ", "B  ", Character.valueOf('A'), new ItemStack(Block.chest), Character.valueOf('B'), boatInfo.getBoatItem()});
            register.registerShapedRecipe(new ItemStack(itemNewboat, 1), true, new Object[]{"   ", " A ", " B ", Character.valueOf('A'), new ItemStack(Block.chest), Character.valueOf('B'), boatInfo.getBoatItem()});
            register.registerShapedRecipe(new ItemStack(itemNewboat, 1), true, new Object[]{"   ", "  A", "  B", Character.valueOf('A'), new ItemStack(Block.chest), Character.valueOf('B'), boatInfo.getBoatItem()});

            register.registerShapedRecipe(new ItemStack(itemNewboat, 1), true, new Object[]{"B B", "ACA", "AAA", Character.valueOf('A'), itemNewboat.boatinfo.getPlank(), Character.valueOf('B'), new ItemStack(Item.shovelWood, 1), Character.valueOf('C'),new ItemStack(Block.chest)});
        }
    }
    public static int getNextItemID() {
        return IdUtil.getNextItemID();
    }

}

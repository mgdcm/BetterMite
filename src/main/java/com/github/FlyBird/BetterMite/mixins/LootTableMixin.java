package com.github.FlyBird.BetterMite.mixins;

import net.minecraft.Block;
import net.minecraft.ComponentVillageHouse2;
import net.minecraft.Item;
import net.minecraft.WeightedRandomChestContent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public class LootTableMixin {

}
@Mixin (ComponentVillageHouse2.class)
class ComponentVillageHouse2LootTableMixin{
    @Mutable @Shadow @Final private static WeightedRandomChestContent[] villageBlacksmithChestContents;

    @Inject(method = "<clinit>",at=@At("TAIL"))
    private static void clinit(CallbackInfo ci){
        villageBlacksmithChestContents = new WeightedRandomChestContent[]{
                new WeightedRandomChestContent(Item.shardEmerald.itemID, 0, 1, 3, 2),
                new WeightedRandomChestContent(Item.shardDiamond.itemID, 0, 1, 3, 1),
                new WeightedRandomChestContent(Item.copperNugget.itemID, 0, 1, 5, 5),
                new WeightedRandomChestContent(Item.silverNugget.itemID, 0, 1, 5, 5),
                new WeightedRandomChestContent(Item.goldNugget.itemID, 0, 1, 8, 5),
                new WeightedRandomChestContent(Item.ironNugget.itemID, 0, 1, 5, 5),
                new WeightedRandomChestContent(Item.ingotCopper.itemID, 0, 1, 3, 3),
                new WeightedRandomChestContent(Item.ingotIron.itemID, 0, 1, 3, 3),
                new WeightedRandomChestContent(Item.coinCopper.itemID, 0, 1, 20, 15),
                new WeightedRandomChestContent(Item.coinSilver.itemID, 0, 1, 10, 10),
                new WeightedRandomChestContent(Item.coinGold.itemID, 0, 1, 3, 5),
                new WeightedRandomChestContent(Item.bread.itemID, 0, 1, 3, 15),
                new WeightedRandomChestContent(Item.appleRed.itemID, 0, 1, 3, 15),
                new WeightedRandomChestContent(Item.plateLeather.itemID, 0, 1, 1, 5),
                new WeightedRandomChestContent(Item.helmetLeather.itemID, 0, 1, 1, 5),
                new WeightedRandomChestContent(Item.legsLeather.itemID, 0, 1, 1, 5),
                new WeightedRandomChestContent(Item.bootsLeather.itemID, 0, 1, 1, 5),
                new WeightedRandomChestContent(Item.saddle.itemID, 0, 1, 1, 3),
                new WeightedRandomChestContent(Item.helmetCopper.itemID, 0, 1, 1, 5),
                new WeightedRandomChestContent(Item.plateCopper.itemID, 0, 1, 1, 5),
                new WeightedRandomChestContent(Item.legsCopper.itemID, 0, 1, 1, 5),
                new WeightedRandomChestContent(Item.bootsCopper.itemID, 0, 1, 1, 5),
                new WeightedRandomChestContent(Item.helmetChainCopper.itemID, 0, 1, 1, 5),
                new WeightedRandomChestContent(Item.plateChainCopper.itemID, 0, 1, 1, 5),
                new WeightedRandomChestContent(Item.legsChainCopper.itemID, 0, 1, 1, 5),
                new WeightedRandomChestContent(Item.bootsChainCopper.itemID, 0, 1, 1, 5),
                new WeightedRandomChestContent(Item.helmetIron.itemID, 0, 1, 1, 5),
                new WeightedRandomChestContent(Item.plateIron.itemID, 0, 1, 1, 5),
                new WeightedRandomChestContent(Item.legsIron.itemID, 0, 1, 1, 5),
                new WeightedRandomChestContent(Item.bootsIron.itemID, 0, 1, 1, 5),
                new WeightedRandomChestContent(Item.helmetChainIron.itemID, 0, 1, 1, 5),
                new WeightedRandomChestContent(Item.plateChainIron.itemID, 0, 1, 1, 5),
                new WeightedRandomChestContent(Item.legsChainIron.itemID, 0, 1, 1, 5),
                new WeightedRandomChestContent(Item.bootsChainIron.itemID, 0, 1, 1, 5),
                new WeightedRandomChestContent(Item.shovelCopper.itemID, 0, 1, 1, 5),
                new WeightedRandomChestContent(Item.shovelIron.itemID, 0, 1, 1, 5),
                new WeightedRandomChestContent(Item.hoeCopper.itemID, 0, 1, 1, 5),
                new WeightedRandomChestContent(Item.hoeIron.itemID, 0, 1, 1, 5),
                new WeightedRandomChestContent(Item.mattockCopper.itemID, 0, 1, 1, 5),
                new WeightedRandomChestContent(Item.mattockIron.itemID, 0, 1, 1, 5),
                new WeightedRandomChestContent(Item.hatchetCopper.itemID, 0, 1, 1, 5),
                new WeightedRandomChestContent(Item.hatchetIron.itemID, 0, 1, 1, 5),
                new WeightedRandomChestContent(Item.axeCopper.itemID, 0, 1, 1, 5),
                new WeightedRandomChestContent(Item.axeIron.itemID, 0, 1, 1, 5),
                new WeightedRandomChestContent(Item.shearsCopper.itemID, 0, 1, 1, 5),
                new WeightedRandomChestContent(Item.shears.itemID, 0, 1, 1, 5),
                new WeightedRandomChestContent(Item.scytheCopper.itemID, 0, 1, 1, 5),
                new WeightedRandomChestContent(Item.pickaxeCopper.itemID, 0, 1, 1, 5),
                new WeightedRandomChestContent(Item.daggerCopper.itemID, 0, 1, 1, 5),
                new WeightedRandomChestContent(Item.daggerIron.itemID, 0, 1, 1, 5),
                new WeightedRandomChestContent(Item.swordCopper.itemID, 0, 1, 1, 5),
                new WeightedRandomChestContent(Item.swordIron.itemID, 0, 1, 1, 5),
                new WeightedRandomChestContent(Item.warHammerCopper.itemID, 0, 1, 1, 5),
                new WeightedRandomChestContent(Item.warHammerIron.itemID, 0, 1, 1, 5),
                new WeightedRandomChestContent(Item.battleAxeCopper.itemID, 0, 1, 1, 5),
                new WeightedRandomChestContent(Item.battleAxeIron.itemID, 0, 1, 1, 5),
                new WeightedRandomChestContent(Block.sponge.blockID, 1, 1, 2, 10)
        };
    }
}
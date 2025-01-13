from time import time as getTime
START_TIME=getTime()

zh_CN=open("zh_CN.lang","w",encoding="utf-8")
en_US=open("en_US.lang","w",encoding="utf-8")
def printf(NameSpaceID,Chinese,English):
    global zh_CN
    global en_US
    zh_CN.write(NameSpaceID+"="+Chinese+'\n')
    en_US.write(NameSpaceID+"="+English+'\n')

def ENTER(s=""):
    global zh_CN
    global en_US
    zh_CN.write(s+'\n')
    en_US.write(s+'\n')
    
BLOCK_FORMAT=lambda namespaceID:"tile."+namespaceID+".name"
ITEM_FORMAT=lambda namespaceID:"item."+namespaceID+".name"
ENTITY_FORMAT=lambda namespaceID:"entity."+namespaceID+".name"
CONTAINER_FORMAT=lambda namespaceID:"container."+namespaceID
DEATH_FORMAT=lambda namespaceID:"death."+namespaceID

class Woods:
    Types=["oak","birch","jungle","spruce","acacia","dark_oak"]
    ModTypes=Types[4:]
    VanillaTypes=Types[:4]
    def getNames(type):
        return [type+"_log","stripped_"+type+"_log",type+"_wood","stripped_"+type+"_wood"]
    woodChineseNames={
        "oak":["橡树原木","去皮橡树原木","橡木","去皮橡木"],
        "birch":["白桦原木","去皮白桦原木","桦木","去皮桦木"],
        "jungle":["丛林原木","去皮丛林原木","从林木","去皮从林木"],
        "spruce":["云杉原木","去皮云杉原木","云杉木","去皮云杉木"],
        "acacia":["金合欢原木","去皮金合欢原木","金合欢木","去皮金合欢木"],
        "dark_oak":["深色橡树原木","去皮深色橡树原木","深色橡木","去皮深色橡木"]
    }
    def getChineseNames(woodType):
        return Woods.woodChineseNames[woodType]
    def getChineseNameForType(woodType):
        return Woods.getChineseNames(woodType)[2]
    def getChineseName(woodName):
        woodNameSplitList = woodName.split('_')
        woodTypesList=[typeName for typeName in woodNameSplitList if not(typeName in ["stripped","log","wood"])]
        woodType="_".join(woodTypesList)
        index = 1
        if(woodNameSplitList[-1]=="wood"):
            index += 2
        if(woodNameSplitList[0]=="stripped"):
            index += 1
        index -= 1
        return Woods.woodChineseNames[woodType][Woods.getNames(woodType).index(woodName)]

ENTER("#Language Generator\n")
#Other Language Generator
printf(BLOCK_FORMAT("barrel"),"木桶","Barrel")
printf(CONTAINER_FORMAT("barrel"),"木桶","Barrel")
ENTER()
printf(BLOCK_FORMAT("sponge"),"干海绵","Sponge")
printf(BLOCK_FORMAT("sponge_wet"),"湿海绵","Sponge Wet")
ENTER()
printf(BLOCK_FORMAT("campfire"),"营火","Campfire")
printf(BLOCK_FORMAT("soul_campfire"),"灵魂营火","Soul Campfire")
ENTER()
printf(ITEM_FORMAT("sweetberries"),"甜浆果","Sweet Berries")
printf(BLOCK_FORMAT("sweetBerryBush"),"甜浆果丛","Sweet Berries Bush")
printf(DEATH_FORMAT("sweetBerryBush"),"%1$s 被甜浆果丛刺死了","%1$s was poked to death by a sweet berry bush.")
printf(DEATH_FORMAT("sweetBerryBush.player"),"%1$s 在试图逃离 %2$s 时被甜浆果丛刺死了","%1$s was poked to death by a sweet berry bush.")
printf(DEATH_FORMAT("CampFire"),"%1$s 被营火烧死了","%1$s was burned to death by a campfire.")
printf(DEATH_FORMAT("CampFire.player"),"%1$s 在试图逃离 %2$s 时营火烧死了","%1$s was burned to death by a campfire whilst trying to escape %2$s.")
ENTER()
printf(BLOCK_FORMAT("soul_torch"),"灵魂火把","Soul Torch")
ENTER()
printf(BLOCK_FORMAT("tall_grass"),"高草丛","Tall Grass")
printf(BLOCK_FORMAT("large_fern"),"大型蕨","Large Fern")
ENTER()
printf(BLOCK_FORMAT("slime"),"史莱姆块","Slime Block")
printf(BLOCK_FORMAT("dirtPath"),"土径","Dirt Path")
printf(BLOCK_FORMAT("grindstone"),"砂轮","Grind Stone")
printf(BLOCK_FORMAT("stonecutter"),"切石机","Stonecutter")
ENTER()
printf(ITEM_FORMAT("rabbitRaw"),"生兔肉","Rabbit Raw")
printf(ITEM_FORMAT("rabbitCooked"),"熟兔肉","Rabbit Cooked")
printf(ITEM_FORMAT("rabbitFoot"),"兔脚","Rabbit Foot")
printf(ITEM_FORMAT("rabbitHide"),"兔皮","Rabbit Hide")
printf(ITEM_FORMAT("rabbit_stew"),"兔肉煲","Rabbit Stew")
ENTER()
printf(ENTITY_FORMAT("EntityRabbit"),"兔子","Rabbit")
printf(ENTITY_FORMAT("EntityEndermite"),"末影螨","Endermite")
printf(ENTITY_FORMAT("EntityArmorStand"),"盔甲架","Armor Stand")
printf(ENTITY_FORMAT("EntityNewBoat"),"船","Boat")
printf(ENTITY_FORMAT("EntityNewBoatWithChest"),"运输船","Chest Boat")
printf(ITEM_FORMAT("wooden_armorstand"),"盔甲架","Armor Stand")
printf(ENTITY_FORMAT("EntityArmorStand"),"盔甲架","Armor Stand")
ENTER()
printf(BLOCK_FORMAT("stonebrickWall"),"石砖墙","Stone Brick Wall")
printf(BLOCK_FORMAT("brickWall"),"石砖墙","Brick Wall")
printf(BLOCK_FORMAT("end_stone_bricks"),"末地石砖","End Stone Brick")
printf(BLOCK_FORMAT("endStoneBrickWall"),"末地石砖墙","End Stone Brick Wall")
ENTER()
printf(BLOCK_FORMAT("prismarine_block.rough"),"海晶石","Prismarine")
printf(BLOCK_FORMAT("prismarine_block.bricks"),"海晶石砖","Prismarine Bricks")
printf(BLOCK_FORMAT("prismarine_block.dark"),"暗海晶石","Dark Prismarine")
printf(ITEM_FORMAT("prismarineShard"),"海晶碎片","Prismarine Shard")
printf(ITEM_FORMAT("prismarineCrystals"),"海晶沙粒","Prismarine Crystal")
ENTER()

#Mod Wood Planks, Stairs, Slab Language Generator
for logType in Woods.ModTypes:
    woodChineseName = Woods.getChineseNameForType(logType)
    planksChineseName = woodChineseName+"板"
    slabChineseName = woodChineseName+"台阶"
    stairsChineseName = woodChineseName+"楼梯"
    planksEnglishName = (logType+" "+"planks").title()
    slabEnglishName = (logType+" "+"slab").title()
    stairsEnglishName = (logType+" "+"stairs").title()
    printf(BLOCK_FORMAT("planks."+logType),planksChineseName,planksEnglishName)
    printf(BLOCK_FORMAT("slab."+logType),slabChineseName,slabEnglishName)
    printf(BLOCK_FORMAT("stairs."+logType),stairsChineseName,stairsEnglishName)

ENTER()


#Chain Language Generator
for chainType in ["iron","copper","silver","ancient","mithril","adamantium"]:
    chainName = chainType+"_"+"chain"
    chainEnglishName = (chainType+" "+"chain").title()
    chainChineseTypeNames={"iron":"铁","copper":"铜","silver":"银","ancient":"远古金属","mithril":"秘银","adamantium":"艾德曼"}
    chainChineseName=chainChineseTypeNames[chainType]+"锁链"
    printf(BLOCK_FORMAT(chainName),chainChineseName,chainEnglishName)

ENTER()


#Lantern Language Generator
for lanternType in ["iron","soul","copper","silver","ancient","mithril","adamantium"]:
    lanternName = lanternType+"_"+"lantern"
    lanternEnglishName = (lanternType+" "+"lantern").title()
    lanternChineseTypeNames={"iron":"铁","soul":"灵魂","copper":"铜","silver":"银","ancient":"远古金属","mithril":"秘银","adamantium":"艾德曼"}
    lanternChineseName=lanternChineseTypeNames[lanternType]+"灯笼"
    printf(BLOCK_FORMAT(lanternName),lanternChineseName,lanternEnglishName)

ENTER()


#Log Language Generator
for logType in Woods.Types:
    for i in range(len(Woods.getNames(logType))):
        logName=Woods.getNames(logType)[i&3]
        woodChineseName=Woods.getChineseName(logName)
        englishName=""
        englishNameList=[(word.title() if word != "with" else "with") for word in logName.split('_')]
        for word in englishNameList:
            englishName+=word+' '
        englishName.strip()
        printf(BLOCK_FORMAT("log."+logName),woodChineseName,englishName)

ENTER()

#Mod Boat and Chest Boat Language Generator
for logType in Woods.Types:
    woodChineseName = Woods.getChineseNameForType(logType)
    boatChineseName = woodChineseName+"船"
    chestBoatChineseName = woodChineseName+"运输船"
    boatEnglishName = (logType+" Boat").title()
    chestBoatEnglishName = (logType+" Chest Boat").title()
    printf(ITEM_FORMAT("boats."+logType),boatChineseName,boatEnglishName)
    printf(ITEM_FORMAT("chest_boats."+logType),chestBoatChineseName,chestBoatEnglishName)

ENTER()

#Mod Fence and Fence Gate Language Generator
for logType in Woods.Types:
    woodChineseName = Woods.getChineseNameForType(logType)
    fenceChineseName = woodChineseName+"栅栏"
    fenceGateChineseName = woodChineseName+"栅栏门"
    fenceEnglishName = (logType+" Fence").title()
    fenceGateEnglishName = (logType+" Fence Gate").title()
    printf(BLOCK_FORMAT("fences."+logType),fenceChineseName,fenceEnglishName)
    printf(BLOCK_FORMAT("fence_gates."+logType),fenceGateChineseName,fenceGateEnglishName)

ENTER()


#Work Bench Language Generator
for woodWorkBenchType in ["flint","obsidian"]:
    for logType in Woods.Types:
        for i in range(len(Woods.getNames(logType))):
            logName=Woods.getNames(logType)[i&3]
            woodChineseName=Woods.getChineseName(logName)
            woodWorkBenchTypeChineseName={"flint":"燧石","obsidian":"黑曜石"}[woodWorkBenchType]
            englishName=""
            englishNameList=[(word.title() if word != "with" else "with") for word in logName.split('_')+["with",woodWorkBenchType,"tool","bench"]]
            for word in englishNameList:
                englishName+=word+' '
            englishName.strip()
            printf(BLOCK_FORMAT("workbench."+woodWorkBenchType+"."+logName),woodChineseName+woodWorkBenchTypeChineseName+"工具台",englishName)

ENTER()


#Doors Language Generator
for logType in Woods.Types:
    woodChineseName = Woods.getChineseNameForType(logType)
    trapdoorChineseName = "门"
    chineseName = woodChineseName + trapdoorChineseName
    englishNameList=[word.title() for word in logType.split('_')]
    englishName = " ".join(englishNameList)
    englishName += " Door"
    printf(ITEM_FORMAT("doors."+logType),chineseName,englishName)
    printf(BLOCK_FORMAT("doors."+logType),chineseName,englishName)

ENTER()


#Trapdoors Language Generator
for logType in Woods.Types:
    woodChineseName = Woods.getChineseNameForType(logType)
    trapdoorChineseName = "活板门"
    chineseName = woodChineseName + trapdoorChineseName
    englishNameList=[word.title() for word in logType.split('_')]
    englishName = " ".join(englishNameList)
    englishName += " Trapdoor"
    printf(BLOCK_FORMAT("trapdoors."+logType),chineseName,englishName)

zh_CN.close()
en_US.close()

print("Build Successful! ")
print("Use","%.4f"%(getTime()-START_TIME),"Seconds.")
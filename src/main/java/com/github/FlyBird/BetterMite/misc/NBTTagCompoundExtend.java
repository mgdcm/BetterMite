package com.github.FlyBird.BetterMite.misc;


import net.minecraft.NBTBase;
import net.minecraft.NBTTagCompound;
import net.minecraft.NBTTagList;
import net.minecraft.ReportedException;

public class NBTTagCompoundExtend  {

    public static boolean hasKey(String str, int p_150297_2_,NBTTagCompound This)
    {
        byte b0 = func_150299_b(str,This);
        return b0 == p_150297_2_ ? true : (p_150297_2_ != 99 ? false : b0 == 1 || b0 == 2 || b0 == 3 || b0 == 4 || b0 == 5 || b0 == 6);
    }


    public static byte func_150299_b(String str,NBTTagCompound This)
    {
        NBTBase nbtbase = (NBTBase)This.tagMap.get(str);
        return nbtbase != null ? nbtbase.getId() : 0;
    }

    public static NBTTagList getTagList(String p_150295_1_, int p_150295_2_,NBTTagCompound This)
    {
        try
        {
            if (func_150299_b(p_150295_1_,This) != 9)
            {
                return new NBTTagList();
            }
            else
            {
                NBTTagList nbttaglist = (NBTTagList)This.tagMap.get(p_150295_1_);
                return nbttaglist.tagCount() > 0 && nbttaglist.tagType != p_150295_2_ ? new NBTTagList() : nbttaglist;
            }
        }
        catch (ClassCastException classcastexception)
        {
            throw new ReportedException(This.createCrashReport(p_150295_1_, 9, classcastexception));
        }
    }
}

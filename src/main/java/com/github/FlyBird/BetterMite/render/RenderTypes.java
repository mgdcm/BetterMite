package com.github.FlyBird.BetterMite.render;

import net.xiaoyu233.fml.reload.utils.IdUtil;

public class RenderTypes {
    //public static Render instance = new Render();
    public static int campfireRenderType =getNextRenderType();
    public static int normalcampfireRenderType =getNextRenderType();
    public static int chainRenderType =getNextRenderType();
    public static int lanternRenderType =getNextRenderType();
    public static int silmeRenderType=getNextRenderType();
    public static int stoneCutterRenderType=getNextRenderType();
    public static int grindStoneRenderType=getNextRenderType();
    public static int bambooRenderType=getNextRenderType();

    public static int getNextRenderType() {return IdUtil.getNextRenderType();}
}

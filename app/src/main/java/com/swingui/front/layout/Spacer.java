package com.swingui.front.layout;

import java.awt.Dimension;

import com.swingui.value.size.UILength;
import com.swingui.value.size.UILength.Height;
import com.swingui.value.size.UILength.Width;
import com.swingui.value.size.WxHSize;
import com.swingui.widget.PanelWT;

/**
 * スペーサー部品提供クラス
 * 
 * @author t.yoshida
 */
public class Spacer
{
    /**
     * 指定した幅・高さのスペース領域を確保する。
     * 
     * @param lengths 幅・高さのサイズ
     * @return {@code PanelWT}
     */
    public static PanelWT of(UILength... lengths)
    {
        // 幅・高さスペースの取得
        WxHSize size = WxHSize.of(WxHSize.zero(), lengths);

        // 幅・高さスペースの設定
        if(isInfinite(size.width) || isInfinite(size.height))
        {
            // 幅または高さが最大限の場合、柔軟なスペース領域を確保
            return flexible(new Dimension(size.width.length, size.height.length));
        }
        else
        {
            // 幅・高さに最大限の値を含まない場合、固定のスペース領域を確保
            return fixed(new Dimension(size.width.length, size.height.length));
        }
    }

    /**
     * 指定した幅が最大限かどうかを判定する。
     * 
     * @param width 幅
     * @return {@code true}: 最大限, {@code false}: 固定値
     */
    private static boolean isInfinite(Width width)
    {
        return width == Width.Infinite;
    }

    /**
     * 指定した高さが最大限かどうかを判定する。
     * 
     * @param height 高さ
     * @return {@code true}: 最大限, {@code false}: 固定値
     */
    private static boolean isInfinite(Height height)
    {
        return height == Height.Infinite;
    }

    /**
     * レイアウトマネージャの制限内で最大限のスペース領域を確保する。
     * 
     * @return {@code PanelWT}
     */
    public static PanelWT fill()
    {
        return flexible(new Dimension(Width.Infinite.length, Height.Infinite.length));
    }

    /**
     * 指定したサイズで柔軟なスペース領域を確保する。
     * 
     * @param dimension サイズ
     * @return {@code PanelWT}
     */
    private static PanelWT flexible(Dimension dimension)
    {
        //
        // レイアウトに応じて PanelWT を拡縮させる場合、
        // PanelWT#setPreferredSize() は設定しない。
        //
        PanelWT panel = new PanelWT();
        panel.setMinimumSize(new Dimension(0, 0));
        panel.setMaximumSize(dimension);

        return panel;
    }

    /**
     * 指定したサイズで固定のスペース領域を確保する。
     * 
     * @param dimension サイズ
     * @return {@code PanelWT}
     */
    private static PanelWT fixed(Dimension dimension)
    {
        //
        // サイズ固定の PanelWT を生成する場合、
        // PanelWT#setPreferredSize() を含めて設定する。
        //
        PanelWT panel = new PanelWT();
        panel.setPreferredSize(dimension);
        panel.setMinimumSize(dimension);
        panel.setMaximumSize(dimension);

        return panel;
    }
}

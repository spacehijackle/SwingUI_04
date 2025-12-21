package com.swingui.widget.util;

import javax.swing.JComponent;

import com.swingui.constant.UIDefaults;
import com.swingui.value.Gap.Bottom;
import com.swingui.value.Gap.Left;
import com.swingui.value.Gap.Right;
import com.swingui.value.Gap.Top;
import com.swingui.value.Symmetry;
import com.swingui.value.Symmetry.Horizontal;
import com.swingui.value.Symmetry.Vertical;
import com.swingui.widget.Widget;

/**
 * {@link Widget} のパディング設定用ユーティリティ・クラス
 * 
 * @author t.yoshida
 */
public class PaddingHelper
{
    /**
     * 指定ウィジェットの水平, 垂直方向のパディングの設定をする。
     * 
     * @param <T> JComponentの継承クラス
     * @param widget 対象ウィジェット
     * @param symmetries 水平, 垂直方向のパディング
     * @return 対象ウィジェット
     */
    public static <T extends JComponent> T padding(Widget<T> widget, Symmetry... symmetries)
    {
        Left left     = Left.of(UIDefaults.COMPONENT_GAP);
        Top  top      = Top.of(UIDefaults.COMPONENT_GAP);
        Right right   = Right.of(UIDefaults.COMPONENT_GAP);
        Bottom bottom = Bottom.of(UIDefaults.COMPONENT_GAP);
        for(Symmetry symmetry : symmetries)
        {
            if(symmetry instanceof Horizontal)
            {
                Horizontal horizontal = (Horizontal)symmetry;
                left  = Left.of(horizontal.gap);
                right = Right.of(horizontal.gap);
            }
            else if(symmetry instanceof Vertical)
            {
                Vertical vertical = (Vertical)symmetry;
                top    = Top.of(vertical.gap);
                bottom = Bottom.of(vertical.gap);
            }
        }

        return widget.padding(left, top, right, bottom);
    }
}

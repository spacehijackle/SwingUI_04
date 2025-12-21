package com.swingui.widget.util;

import java.awt.Dimension;
import java.awt.Window;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;

import com.swingui.constant.UIDefaults;
import com.swingui.value.Gap;
import com.swingui.value.Gap.Bottom;
import com.swingui.value.Gap.Left;
import com.swingui.value.Gap.Right;
import com.swingui.value.Gap.Top;
import com.swingui.value.UISize;
import com.swingui.value.UISize.Height;
import com.swingui.value.UISize.Width;
import com.swingui.widget.Framer;
import com.swingui.widget.Widget;

/**
 * {@link Widget} 関連のユーティリティ・クラス
 * 
 * @author t.yoshida
 */
public class WidgetHelper
{
    /**
     * ウィジェットが属するフレームまで遡り、下位コンポーネント全体の更新を行う。
     */
    public static <T extends JComponent> void invokeToRefresh(Widget<T> widget)
    {
        Window w = SwingUtilities.getWindowAncestor((JComponent)widget);
        if(w != null)
        {
            if(w instanceof Framer)
            {
                ((Framer)w).refreshWT();
            }
        }
    }

    /**
     * 指定コンポーネントのパディングの設定をする。
     * 
     * @param <T> JComponentの継承クラス
     * @param target 対象コンポーネント
     * @param gaps 四方（left, top, right, bottom）のパディング
     * @return 対象コンポーネント
     */
    public static <T extends JComponent> T padding(T target, Gap... gaps)
    {
        // 四方のパディング決定
        Left left     = Left.of(UIDefaults.COMPONENT_GAP);
        Top  top      = Top.of(UIDefaults.COMPONENT_GAP);
        Right right   = Right.of(UIDefaults.COMPONENT_GAP);
        Bottom bottom = Bottom.of(UIDefaults.COMPONENT_GAP);
        for(Gap gap : gaps)
        {
            if(gap instanceof Left)   left   = (Left)gap;
            if(gap instanceof Top)    top    = (Top)gap;
            if(gap instanceof Right)  right  = (Right)gap;
            if(gap instanceof Bottom) bottom = (Bottom)gap;
        }

        // パディング設定
        target.setBorder
        (
            BorderFactory.createCompoundBorder
            (
                target.getBorder(),
                BorderFactory.createEmptyBorder(top.gap, left.gap, bottom.gap, right.gap)
            )
        );
        return target;
    }

    /**
     * 指定コンポーネントのサイズの設定をする。
     * 
     * @param <T> JComponentの継承クラス
     * @param target 対象コンポーネント
     * @param sizes 幅・高さサイズ
     * @return 対象コンポーネント
     */
    public static <T extends JComponent> T frame(T target, UISize... sizes)
    {
        // 幅・高さ決定
        Width  width = Width.of(target.getPreferredSize().width);
        Height height = Height.of(target.getPreferredSize().height);
        for(UISize size : sizes)
        {
            if(size instanceof Width)  width = (Width)size;
            if(size instanceof Height) height = (Height)size;
        }

        // サイズ設定
        target.setMaximumSize(new Dimension(width.length, height.length));
        target.setMinimumSize(new Dimension(width.length, height.length));
        target.setPreferredSize(new Dimension(width.length, height.length));
        return target;
    }
}

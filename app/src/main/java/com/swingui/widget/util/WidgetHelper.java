package com.swingui.widget.util;

import java.awt.Dimension;
import java.awt.Window;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;

import com.swingui.value.gap.AllSidesGap;
import com.swingui.value.gap.Gap;
import com.swingui.value.size.UILength;
import com.swingui.value.size.UILength.Height;
import com.swingui.value.size.UILength.Width;
import com.swingui.value.size.WxHSize;
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
        // 四方のパディング取得
        AllSidesGap sides = AllSidesGap.of(gaps);

        // パディング設定
        target.setBorder
        (
            BorderFactory.createCompoundBorder
            (
                target.getBorder(),
                BorderFactory.createEmptyBorder
                (
                    sides.top.gap, sides.left.gap, sides.bottom.gap, sides.right.gap
                )
            )
        );
        return target;
    }

    /**
     * 指定コンポーネントのサイズの設定をする。
     * 
     * @param <T> JComponentの継承クラス
     * @param target 対象コンポーネント
     * @param lengths 幅・高さサイズ
     * @return 対象コンポーネント
     */
    public static <T extends JComponent> T frame(T target, UILength... lengths)
    {
        // 幅・高さ決定
        WxHSize defaults = WxHSize.from(target.getPreferredSize());
        WxHSize size = WxHSize.of(defaults, lengths);

        // サイズ設定
        target.setMaximumSize(new Dimension(size.width.length, size.height.length));
        target.setMinimumSize(new Dimension(size.width.length, size.height.length));
        target.setPreferredSize(new Dimension(size.width.length, size.height.length));
        return target;
    }
}

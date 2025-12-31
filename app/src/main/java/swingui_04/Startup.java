package swingui_04;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.swingui.constant.UIDefaults;
import com.swingui.front.Frame;
import com.swingui.front.button.Button;
import com.swingui.front.layout.HStack;
import com.swingui.front.layout.Spacer;
import com.swingui.front.layout.VStack;
import com.swingui.value.gap.Gap.Bottom;
import com.swingui.value.gap.Gap.Left;
import com.swingui.value.gap.Gap.Right;
import com.swingui.value.gap.Symmetry.Horizontal;
import com.swingui.value.size.UILength.Height;
import com.swingui.value.size.UILength.Width;
import com.swingui.value.Spacing;

public class Startup
{
    public static void main(String[] args)
    {
        new Startup();
    }

    public Startup()
    {
        SwingUtilities.invokeLater(() -> testPaddingPatterns());
        SwingUtilities.invokeLater(() -> testUISizePatterns());
    }

    /**
     * パディングのパターンをテストする。
     */
    private void testPaddingPatterns()
    {
        Frame.of
        (
            "SwingUI Padding Sample",

            (f) ->
            {
                f.setResizable(true);  // 画面リサイズ可能
                f.setSize(400, 300);  // 初期画面サイズ指定
            },

            VStack.of
            (
                Spacing.of(24),

                Spacer.fill(),

                Button.of("Padding (←, ↑, →, ↓) : (20, none, 40, 60)")
                    .padding(Left.of(20), Right.of(40), Bottom.of(60))
                    .onClicked(self -> showInfoDialog(self, "Padding on all sides")),

                Button.of("Padding (Horizontal, Vertical) : (30, none)")
                    .padding(Horizontal.of(30))
                    .onClicked(self -> showInfoDialog(self, "Padding on horizontal/vertical sides")),

                Spacer.fill()
            )
        );
    }

    /**
     * {@link Spacer} / {@link Widget#frame(UILength...)} のパターンをテストする。
     */
    private void testUISizePatterns()
    {
        Frame.of
        (
            "SwingUI Spacer Sample",

            (f) ->
            {
                f.setResizable(true);  // 画面リサイズ可能
                f.setSize(400, 300);  // 初期画面サイズ指定
            },

            VStack.of
            (
                Spacer.of(Height.Infinite),

                text("── Width / Height Only ──"),

                HStack.of
                (
                    Button.of("Width: 100")
                        .frame(Width.of(100)),

                    Spacer.of(Width.of(30)),

                    Button.of("Height: 40")
                        .frame(Height.of(40))
                ),

                Spacer.of(Height.Infinite),

                text("── Fixed & Infinite ──"),

                Button.of("Width: Infinite, Height: Fixed")
                    .frame(Width.Infinite, Height.of(60))
            )
            .padding(UIDefaults.COMPONENT_GAP)
        );
    }

    /**
     * ラベルを生成する。
     * 
     * @param text ラベル文字列
     * @return {@link JLabel}
     */
    private JLabel text(String text)
    {
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        return label;
    }

    /**
     * 情報ダイアログを表示する。
     * 
     * @param src イベント発生元コンポーネント
     * @param message 表示メッセージ
     */
    private void showInfoDialog(JComponent src, String message)
    {
        JOptionPane.showMessageDialog
        (
            src.getTopLevelAncestor(),
            message,
            "Information",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
}

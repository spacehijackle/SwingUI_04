package com.swingui.value;

/**
 * ギャップ（間隔）値提供クラス
 * 
 * @author t.yoshida
 */
public class Gap
{
    /** 間隔 */
    public final int gap;

    private Gap(int gap)
    {
        this.gap = gap;
    }

    /**
     * 左側の間隔値提供クラス
     */
    public static class Left extends Gap
    {
        private Left(int gap)
        {
            super(gap);
        }

        /**
         * 指定された間隔で {@code Left} を生成する。
         * 
         * @param gap 間隔
         * @return {@code Left}
         */
        public static Left of(int gap)
        {
            return new Left(gap);
        }
    }

    /**
     * 上部の間隔値提供クラス
     */
    public static class Top extends Gap
    {
        private Top(int gap)
        {
            super(gap);
        }

        /**
         * 指定された間隔で {@code Top} を生成する。
         *
         * @param gap 間隔
         * @return {@code Top}
         */
        public static Top of(int gap)
        {
            return new Top(gap);
        }
    }

    /**
     * 右側の間隔値提供クラス
     */
    public static class Right extends Gap
    {
        private Right(int value)
        {
            super(value);
        }

        /**
         * 指定された間隔で {@code Right} を生成する。
         *
         * @param value 間隔
         * @return {@code Right}
         */
        public static Right of(int value)
        {
            return new Right(value);
        }
    }

    /**
     * 下部の間隔値提供クラス
     */
    public static class Bottom extends Gap
    {
        private Bottom(int value)
        {
            super(value);
        }

        /**
         * 指定された間隔で {@code Bottom} を生成する。
         *
         * @param value 間隔
         * @return {@code Bottom}
         */
        public static Bottom of(int value)
        {
            return new Bottom(value);
        }
    }
}

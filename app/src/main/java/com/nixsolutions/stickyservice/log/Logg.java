package com.nixsolutions.stickyservice.log;

public class Logg {

    public static ILogger logger = new Debug();

    public static void e(String tag, String mes) {
        logger.e(tag, mes);
    }

    public static void d(String tag, String mes) {
        logger.d(tag, mes);
    }

    public static void setLogger(ILogger logger) {
        Logg.logger = logger;
    }

    public interface ILogger {

        void e(String tag, String message);

        void d(String tag, String message);

        void w(String tag, String message);

        void i(String tag, String message);
    }

    public static class NoLog implements Logg.ILogger {

        @Override
        public void e(String tag, String message) {}

        @Override
        public void d(String tag, String message) {}

        @Override
        public void w(String tag, String message) {}

        @Override
        public void i(String tag, String message) {}
    }

    public static class Debug implements Logg.ILogger {

        @Override
        public void e(String tag, String message) {
            android.util.Log.e(tag, message);
        }

        @Override
        public void d(String tag, String message) {
            android.util.Log.d(tag, message);
        }

        @Override
        public void w(String tag, String message) {
            android.util.Log.w(tag, message);
        }

        @Override
        public void i(String tag, String message) {
            android.util.Log.i(tag, message);
        }

    }
}

package model;

import com.itextpdf.text.Rectangle;
import com.itextpdf.text.RectangleReadOnly;

public enum Platform {
    
    API {
        @Override
        public Rectangle getScreenshotSize() {
            return null;//API doesn't have screenshot
        }
    },

    DESKTOP {
        @Override
        public Rectangle getScreenshotSize() {
            return HORIZONTAL;
        }
    },

    MAINFRAME {
        @Override
        public Rectangle getScreenshotSize() {
            return HORIZONTAL;
        }
    },

    MOBILE {
        @Override
        public Rectangle getScreenshotSize() {
            return VERTICAL;
        }
    },

    WEB{
        @Override
        public Rectangle getScreenshotSize() {
            return HORIZONTAL;
        }
    };

    Rectangle HORIZONTAL = new RectangleReadOnly(1280.0F, 720.0F);
    Rectangle VERTICAL = new RectangleReadOnly(1080.0F, 1920.0F);

    public abstract Rectangle getScreenshotSize();
}

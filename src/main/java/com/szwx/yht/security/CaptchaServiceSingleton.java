package com.szwx.yht.security;

import com.octo.captcha.service.captchastore.FastHashMapCaptchaStore;
import com.octo.captcha.service.image.DefaultManageableImageCaptchaService;
import com.octo.captcha.service.image.ImageCaptchaService;

/**
 * Created with IntelliJ IDEA.
 * User: hch
 * Date: 13-6-30
 * Time: 上午10:24
 * To change this template use File | Settings | File Templates.
 */
public class CaptchaServiceSingleton {
    private static ImageCaptchaService instance =
            new DefaultManageableImageCaptchaService(
                    new FastHashMapCaptchaStore(),
                    new CaptchaEngine(),
                    180,
                    100000,
                    75000);


    private CaptchaServiceSingleton() {
    }

    public static ImageCaptchaService getInstance() {
        return instance;
    }
}

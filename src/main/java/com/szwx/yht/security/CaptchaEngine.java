package com.szwx.yht.security;

import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;
import com.octo.captcha.component.image.backgroundgenerator.FunkyBackgroundGenerator;
import com.octo.captcha.component.image.backgroundgenerator.UniColorBackgroundGenerator;
import com.octo.captcha.component.image.color.RandomRangeColorGenerator;
import com.octo.captcha.component.image.fontgenerator.FontGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.fontgenerator.TwistedAndShearedRandomFontGenerator;
import com.octo.captcha.component.image.fontgenerator.TwistedRandomFontGenerator;
import com.octo.captcha.component.image.textpaster.RandomTextPaster;
import com.octo.captcha.component.image.textpaster.TextPaster;
import com.octo.captcha.component.image.wordtoimage.ComposedWordToImage;
import com.octo.captcha.component.image.wordtoimage.WordToImage;
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator;
import com.octo.captcha.component.word.wordgenerator.WordGenerator;
import com.octo.captcha.engine.image.ListImageCaptchaEngine;
import com.octo.captcha.image.gimpy.GimpyFactory;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: hch
 * Date: 13-6-30
 * Time: 上午10:22
 * To change this template use File | Settings | File Templates.
 */
public class CaptchaEngine extends ListImageCaptchaEngine {
    @Override
    protected void buildInitialFactories() {
        // 随机生成的字符
        WordGenerator wordGenerator = new RandomWordGenerator("01235689ab");
        // 文字显示的个数

        RandomRangeColorGenerator color = new RandomRangeColorGenerator(
                new int[]{0, 200},
                new int[]{0, 200},
                new int[]{0, 200}
        );

        TextPaster textPaster = new RandomTextPaster(4, 5, color);
        // 图片的大小
        BackgroundGenerator backgroundGenerator =
                new UniColorBackgroundGenerator(80, 50, Color.WHITE);

        // 字体格式
//        Font[] fontsList = new Font[]{
//                new Font("Arial", 0, 10),
//                new Font("Tahoma", 0, 10),
//                new Font("Verdana", 0, 10)
//        };
        // 文字的大小
        FontGenerator fontGenerator =
                new TwistedRandomFontGenerator(24,24);
//                new TwistedAndShearedRandomFontGenerator(24, 24);
//                new RandomFontGenerator(
//                        new Integer(16),
//                        new Integer(36),
//                        fontsList
//                );

        WordToImage wordToImage =
                new ComposedWordToImage(
                        fontGenerator,
                        backgroundGenerator,
                        textPaster
                );
        addFactory(new GimpyFactory(wordGenerator, wordToImage));
    }
}

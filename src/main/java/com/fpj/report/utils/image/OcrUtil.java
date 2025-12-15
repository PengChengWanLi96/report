
package com.fpj.report.utils.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;

/**
 * OCR工具类，用于处理PDF文件的文本提取和图像识别
 */
public class OcrUtil {

    /**
     * 主函数，用于测试OCR功能
     */
    public static void main(String[] args) {
        String userDir = System.getProperty("user.dir");
        System.out.println("userDir: " + userDir);

        String imagePath = userDir + File.separator + "pdf-images";
        String filePath = imagePath + File.separator + "poem.pdf";

        // pdf提取文字
        isPdfText(filePath);

        // 图片提取文字
        imageOCR(new File(imagePath, "poem.png"));
    }

    /**
     * 检测PDF文件是否包含可提取的文本内容
     *
     * @param filePath PDF文件路径
     * @return Boolean 是否包含文本内容
     */
    public static Boolean isPdfText(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("文件不存在");
            return Boolean.FALSE;
        }
        try (PDDocument doc = PDDocument.load(new File(filePath))) {
            String text = new PDFTextStripper().getText(doc);
            if (text.trim().isEmpty()) {
                System.out.println("这是一个图像型 PDF，需要 OCR");
                // 使用 pdfBoxImageIo 方法进行 OCR, 转换成图片
                pdfBoxImageIo(filePath);
                return Boolean.FALSE;
            } else {
                System.out.println("已有文本，无需 OCR");
                return Boolean.TRUE;
            }
        } catch (IOException e) {
            throw new RuntimeException("处理PDF文件时发生错误: " + e.getMessage(), e);
        }
    }

    /**
     * 将PDF文件的每一页转换为图片并保存
     *
     * @param filePath PDF文件路径
     * @return Boolean 转换是否成功
     */
    public static Boolean pdfBoxImageIo(String filePath) {
        try (PDDocument document = PDDocument.load(new File(filePath))) {
            PDFRenderer renderer = new PDFRenderer(document);

            String fileParent = new File(filePath).getParent();

            for (int i = 0; i < document.getNumberOfPages(); i++) {
                // 渲染PDF页面为图像，150 DPI 提高 OCR 效果
                BufferedImage image = renderer.renderImageWithDPI(i, 150);
                File imageFile = new File(fileParent, "page_" + i + ".png");
                System.out.println("image path: " + imageFile.getAbsolutePath());
                ImageIO.write(image, "png", imageFile);
                // 调用 imageOCR 方法进行 OCR, 识别图片中的文本
                imageOCR(imageFile);
            }
            return Boolean.TRUE;
        } catch (IOException e) {
            throw new RuntimeException("PDF转图片时发生错误: " + e.getMessage(), e);
        }
    }

    /**
     * 对图片进行OCR识别，提取文字内容
     *
     * @param file 待识别的图片文件
     */
    public static void imageOCR(File file) {
        if (!file.exists()) {
            System.out.println("文件不存在");
            return;
        }

        String fileParent = file.getParent();
        String fileName = file.getName();
        System.out.println(String.format("filePath: %s", fileParent));
        System.out.println(String.format("filename: %s", fileName));

        ITesseract tesseract = new Tesseract();
        // 设置语言数据文件目录
        tesseract.setDatapath(fileParent);
        // 设置识别语言为简体中文
        tesseract.setLanguage("chi_sim");

        try {
            String result = tesseract.doOCR(file);
            System.out.println("OCR识别结果:");
            System.out.println(result);
        } catch (TesseractException e) {
            System.err.println("OCR识别失败: " + e.getMessage());
            e.printStackTrace();
        }
    }
}